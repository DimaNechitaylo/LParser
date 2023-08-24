package com.nechytailo.parser.api;

import com.nechytailo.parser.config.JsoupConfig;
import org.jsoup.Connection;

public class ApiManager {
    private static final String SPORTS_URL = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    private static final String MATCHES_URL = "https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=";
    private static final String MARKETS_URL = "https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=";

    private static final String DATE_URL = "https://leonbets.com/api-1";

    private final JsoupConfig jsoupConfig;

    public ApiManager() {
        jsoupConfig = JsoupConfig.getInstance();
    }

    public Connection getSportsConnection() {
        String url = String.format(SPORTS_URL);
        return jsoupConfig.getConnect(url);
    }

    public Connection getMatchesConnection(Long leagueId) {
        String url = String.format("%s%s&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup", MATCHES_URL, leagueId);
        return jsoupConfig.getConnect(url);
    }

    public Connection getMarketsConnection(Long matchId) {
        String url = String.format("%s%s&flags=reg,urlv2,mm2,rrc,nodup,smg,outv2", MARKETS_URL, matchId);
        return jsoupConfig.getConnect(url);
    }

    public Connection getMatchDateConnection(Long matchId) {
        String url = String.format(DATE_URL);
        return jsoupConfig.postConnect(url).requestBody(String.format("[{\"id\":\"e4f084a1-7a0a-4815-ab34-8e9bf8237208\"," +
                "\"qKey\":\"ae089ed0-285\",\"operationName\":\"getSportradarLiveMatchTrackerConfiguration\"," +
                "\"variables\":{\"options\":{\"ts\":0}}},{\"id\":\"6e92e365-69f1-4fa0-ad3e-e9aeee899c42\"," +
                "\"qKey\":\"59e45314-207\",\"operationName\":\"getLSportsWidgetConfiguration\"," +
                "\"variables\":{\"options\":{\"ts\":0}}},{\"id\":\"8f477bfa-8d07-4b49-b5e3-59adf8d6f5ad\"," +
                "\"qKey\":\"5184a04e-224\",\"operationName\":\"getBetgeniusWidgetConfiguration\"," +
                "\"variables\":{\"options\":{\"ts\":0}}},{\"id\":\"9a1f2c83-5440-4f56-83fb-38a876e00e00\"," +
                "\"qKey\":\"5da4ff2d-888\",\"operationName\":\"getBetlineMatchStatistics\"," +
                "\"variables\":{\"options\":{\"eventId\":%1$s,\"ts\":0}}}," +
                "{\"id\":\"12d482ee-cc8d-48ba-824d-c1a33da24019\",\"qKey\":\"e3c5e12b-709\"," +
                "\"operationName\":\"getBetlineMatchEventStatistics\",\"variables\":" +
                "{\"options\":{\"eventId\":%1$s,\"ts\":0}}}," +
                "{\"id\":\"ca20a2bf-f965-46d8-b8d0-230ba920e352\",\"qKey\":\"98633253-613\"," +
                "\"operationName\":\"getBetlineLeagueStandings\",\"variables\":{\"options\":" +
                "{\"eventId\":%1$s,\"ts\":0}}}]", matchId));
    }
}
