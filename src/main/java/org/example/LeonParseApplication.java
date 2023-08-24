package org.example;

import org.example.model.League;
import org.example.parser.LeonParserImpl;
import org.example.util.ConsoleOutputFormatter;

import java.util.List;


public class LeonParseApplication {
    public static void main(String[] args) {
        LeonParserImpl leonParser = new LeonParserImpl();
        List<League> leagues = leonParser.parse();
        leagues.forEach(league -> ConsoleOutputFormatter.printSportDetails(league));

    }
}