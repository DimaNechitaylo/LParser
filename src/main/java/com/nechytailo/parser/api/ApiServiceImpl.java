package com.nechytailo.parser.api;

import org.jsoup.Connection;

import java.io.IOException;

public class ApiServiceImpl implements ApiService {

    private final ApiManager apiManager;

    public ApiServiceImpl() {
        apiManager = new ApiManager();
    }

    @Override
    public String receiveSportsJson() {
        Connection sportsConnection = apiManager.getSportsConnection();
        return executeRequestAndExtractJson(sportsConnection);
    }

    @Override
    public String receiveMatchesJson(Long leagueId) {
        Connection matchesConnection = apiManager.getMatchesConnection(leagueId);
        return executeRequestAndExtractJson(matchesConnection);
    }

    @Override
    public String receiveMatchWithMarketsJson(Long matchId) {
        Connection marketsConnection = apiManager.getMarketsConnection(matchId);
        return executeRequestAndExtractJson(marketsConnection);
    }

    @Override
    public String receiveMatchDateJson(Long matchId) {
        Connection matchDateConnection = apiManager.getMatchDateConnection(matchId);
        return executeRequestAndExtractJson(matchDateConnection);
    }

    private String executeRequestAndExtractJson(Connection connection) {
        String json;
        try {
            Connection.Response response = connection.execute();
            json = response.parse().getElementsByTag("body").text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}
