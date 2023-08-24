package com.nechytailo.parser.api;

import com.nechytailo.parser.config.JsoupConfig;
import org.jsoup.Connection;

public class ApiManager {
    private static final String SPORTS_URL = "https://leonbets.com/api-2/betline/sports?ctag=en-US&flags=urlv2";
    private static final String MATCHES_URL = "https://leonbets.com/api-2/betline/events/all?ctag=en-US&league_id=";
    private static final String MARKETS_URL = "https://leonbets.com/api-2/betline/event/all?ctag=en-US&eventId=";

    private final JsoupConfig jsoupConfig;

    public ApiManager() {
        jsoupConfig = JsoupConfig.getInstance();
    }

    public Connection getSportsConnection() {
        String url = String.format(SPORTS_URL);
        return jsoupConfig.connect(url);
    }

    public Connection getMatchesConnection(Long leagueId) {
        String url = String.format("%s%s&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup", MATCHES_URL, leagueId);
        return jsoupConfig.connect(url);
    }

    public Connection getMarketsConnection(Long matchId) {
        String url = String.format("%s%s&flags=reg,urlv2,mm2,rrc,nodup,smg,outv2", MARKETS_URL, matchId);
        return jsoupConfig.connect(url);
    }
}
