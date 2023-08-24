package org.example.util;

import org.example.model.League;
import org.example.model.Market;
import org.example.model.Match;
import org.example.model.Outcome;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class ConsoleOutputFormatter {
    private static final SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static void printSportDetails(League league) {
        printLeagueDetails(league);
        printMatchDetails(league.getFirstMatch());
    }

    private static void printLeagueDetails(League league) {
        System.out.println(league.getSportName() + ", " + league.getRegionName() + " - " + league.getName());
    }

    private static void printMatchDetails(Match match) {
        System.out.println("    " + match.getName() + ", " + dateFormat.format(match.getStartDate()) + ", " + match.getId());
        printMarketDetails(match.getMarkets());
    }

    private static void printMarketDetails(List<Market> markets) {
        for (Market market : markets) {
            System.out.println("       " + market.getName());
            printOutcomeDetails(market.getOutcomes());
        }
    }

    private static void printOutcomeDetails(List<Outcome> outcomes) {
        for (Outcome outcome : outcomes) {
            System.out.println("         " + outcome.getName() + ", " + outcome.getCoefficient() + ", " + outcome.getId());
        }
    }

}
