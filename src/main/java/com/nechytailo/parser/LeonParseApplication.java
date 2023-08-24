package com.nechytailo.parser;

import com.google.gson.Gson;
import com.nechytailo.parser.api.ApiService;
import com.nechytailo.parser.api.ApiServiceImpl;
import com.nechytailo.parser.model.League;
import com.nechytailo.parser.parser.LeonParser;
import com.nechytailo.parser.util.ConsoleOutputFormatter;
import com.nechytailo.parser.parser.LeonParserImpl;

import java.util.List;


public class LeonParseApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        ApiService apiService = new ApiServiceImpl();
        LeonParser leonParser = new LeonParserImpl(gson, apiService);
        List<League> leagues = leonParser.parse();
        leagues.forEach(ConsoleOutputFormatter::printSportDetails);
    }
}