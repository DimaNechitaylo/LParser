package org.example.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.api.ApiService;
import org.example.model.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeonParserImpl implements LeonParser{


    private static final String[] SPORTS = {"Footbal", "Tennis", "Ice Hockey", "Basketball"};

    private ApiService apiService;

    public LeonParserImpl(){
        apiService = new ApiService();
    }

    @Override
    public List<League> parse() {
        return null;
    }


    private List<Sport> findSportElement(Document document, String sportName) {
        return null;
    }

    private List<League> findTopLeagues(List<Sport> sports) {
        return null;
    }

    private Match parseFirstMatchInLeague(Long leagueId) {

        return null;
    }

    private Match findMarkets(Match match) {

        return null;
    }

}
