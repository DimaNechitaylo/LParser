package org.example.api;

import org.example.config.JsoupConfig;
import org.jsoup.Connection;

import java.io.IOException;

public class ApiService {

    private ApiManager apiManager;

    public ApiService() {
        apiManager = new ApiManager();
    }
    public String getSportsJson(){
        Connection sportsConnection = apiManager.getSportsConnection();
        return executeRequestAndExtractJson(sportsConnection);
    }

    public String getMatchesJson(Long leagueId){
        Connection matchesConnection = apiManager.getMatchesConnection(leagueId);
        return executeRequestAndExtractJson(matchesConnection);
    }

    public String getMarketsJson(Long matchId){
        Connection marketsConnection = apiManager.getMarketsConnection(matchId);
        return executeRequestAndExtractJson(marketsConnection);
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
