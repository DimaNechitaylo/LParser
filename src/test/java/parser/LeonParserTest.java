package parser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.nechytailo.parser.api.ApiService;
import com.nechytailo.parser.model.League;
import com.nechytailo.parser.model.Match;
import com.nechytailo.parser.model.Sport;
import com.nechytailo.parser.parser.LeonParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LeonParserTest {
    @Mock
    private ApiService apiService;

    private Gson gson;

    private LeonParserImpl leonParser;

    @BeforeEach
    void setup() {
        gson = new Gson();
        leonParser = new LeonParserImpl(gson, apiService);
    }

    @Test
    public void testParse_ValidData_ReturnsTopLeagues() throws JsonSyntaxException {
        String sportsJson = "[{\"id\": 1, \"name\": \"Football\", \"regions\": [{\"name\": \"Europe\", \"leagues\": [{\"id\": 11, \"name\": \"Premier League\", \"top\": true}]}]}]";
        String matchesJson = "{\"id\": 11, \"name\": \"Premier League\", \"top\": true, \"events\": [{\"id\": 101, \"name\": \"Match 1\", \"markets\": []}, {\"id\": 102, \"name\": \"Match 2\", \"markets\": []}]}";
        String marketsJson = "{\"id\": 101, \"name\": \"Match 1\", \"markets\": [{\"id\": 201, \"name\": \"Market 1\", \"runners\": []}, {\"id\": 202, \"name\": \"Market 2\", \"runners\": []}]}";


        when(apiService.receiveSportsJson()).thenReturn(sportsJson);
        when(apiService.receiveMatchesJson(anyLong())).thenReturn(matchesJson);
        when(apiService.receiveMatchWithMarketsJson(anyLong())).thenReturn(marketsJson);

        List<League> result = leonParser.parse();

        assertNotNull(result);
        assertEquals(1, result.size());
        League league = result.get(0);
        assertEquals(11L, league.getId());
        assertEquals("Premier League", league.getName());
        assertTrue(league.isTop());
        assertNotNull(league.getMatches());
        assertEquals(1, league.getMatches().size());
        Match match = league.getMatches().get(0);
        assertEquals(101L, match.getId());
        assertEquals("Match 1", match.getName());
    }

    @Test
    public void testParse_InvalidJson_ThrowsException() {
        when(apiService.receiveSportsJson()).thenReturn("invalid_json");

        assertThrows(JsonSyntaxException.class, () -> leonParser.parse());
    }

}
