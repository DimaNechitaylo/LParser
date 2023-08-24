package utils;

import com.nechytailo.parser.model.League;
import com.nechytailo.parser.model.Market;
import com.nechytailo.parser.model.Match;
import com.nechytailo.parser.model.Outcome;
import com.nechytailo.parser.util.ConsoleOutputFormatter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleOutputFormatterTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    void testPrintSportDetails() {
        System.setOut(new PrintStream(outputStream));

        League league = new League();
        league.setSportName("Football");
        league.setRegionName("Europe");
        league.setName("Premier League");

        List<Market> markets = new ArrayList<>();
        Market market = new Market();
        market.setName("Win");
        List<Outcome> outcomes = new ArrayList<>();
        Outcome outcome = new Outcome();
        outcome.setName("Team A");
        outcome.setCoefficient(1.5);
        outcomes.add(outcome);
        market.setOutcomes(outcomes);
        markets.add(market);

        Match match = new Match();
        match.setName("Match 1");
        match.setStartDate(new Date());
        match.setMarkets(markets);
        league.setMatches(List.of(match));

        ConsoleOutputFormatter.printSportDetails(league);

        System.setOut(System.out);

        String actualOutput = outputStream.toString().replaceAll("\r\n", "\n");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String expectedOutput = "Football, Europe Premier League\n" +
                "    Match 1, " + dateFormat.format(match.getStartDate()) + ", null\n" +
                "       Win\n" +
                "         Team A, 1.5, null\n";

        assertEquals(expectedOutput, actualOutput);
    }
}