package org.example.util;

import org.example.model.League;
import org.example.model.Market;
import org.example.model.Match;
import org.example.model.Outcome;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ConsoleOutputFormatter {
    private static final SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static void printSportDetails(League league) {
        Match match = league.getFirstMatch();
        System.out.println(league.getSportName() + ", " + league.getName());
        System.out.println("    " + league.getFirstMatch().getName() + ", " + dateFormat.format(match.getStartDate()) + ", " + match.getId());

        for (Market market : match.getMarkets()) {
            System.out.println("       " + market.getName());
            for (Outcome outcome : market.getOutcomes()) {
                System.out.println("         " + outcome.getName() + ", " + outcome.getCoefficient() + ", " + outcome.getId());
            }
        }
    }
}
