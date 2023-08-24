package org.example.parser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.example.api.ApiService;
import org.example.api.ApiServiceImpl;
import org.example.exceptions.InvalidJsonException;
import org.example.model.League;
import org.example.model.Match;
import org.example.model.Sport;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeonParserImpl implements LeonParser {

    private static final int NUM_THREADS = 3;

    private static final String[] SPORTS = {"Football", "Tennis", "Ice Hockey", "Basketball"};

    private final ApiService apiService;
    private final Gson gson;

    public LeonParserImpl() {
        gson = new Gson();
        apiService = new ApiServiceImpl();
    }

    @Override
    public List<League> parse() {
        List<Sport> sports = findSports();
        List<League> topLeagues = findTopLeagues(sports);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        List<CompletableFuture<Void>> tasks = topLeagues.stream()
                .map(league -> CompletableFuture.runAsync(() -> processLeague(league), executor))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
        try {
            allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return topLeagues;
    }

    private void processLeague(League league){
        try {
            Match firstMatch = findFirstMatchInLeague(league.getId());
            Match fullFirstMatch = findMatchWithMarkets(firstMatch);

            league.setMatches(Collections.singletonList(fullFirstMatch));
        } catch (JsonSyntaxException e){
            System.err.println("Invalid JSON: " + e.getMessage());
        }
    }


    private List<Sport> findSports() throws JsonSyntaxException{
        String jsonResponse = apiService.receiveSportsJson();
        Type sportListType = new TypeToken<List<Sport>>() {}.getType();

        List<Sport> allSports = gson.fromJson(jsonResponse, sportListType);
        return allSports.stream()
                .filter(sport -> Arrays.asList(SPORTS).contains(sport.getName()))
                .collect(Collectors.toList());
    }

    private List<League> findTopLeagues(List<Sport> sports) {
        return sports.stream()
                .flatMap(this::findTopLeaguesForSport)
                .collect(Collectors.toList());
    }

    private Stream<League> findTopLeaguesForSport(Sport sport) {
        return sport.getRegions().stream()
                .flatMap(region -> region.getLeagues().stream()
                        .filter(League::isTop)
                        .peek(league -> {
                            league.setSportName(sport.getName());
                            league.setRegionName(region.getName());
                        }));
    }

    private Match findFirstMatchInLeague(Long leagueId) throws JsonSyntaxException{
        String jsonResponse = apiService.receiveMatchesJson(leagueId);
        League fullLeague = gson.fromJson(jsonResponse, League.class);

        if (fullLeague == null || fullLeague.getMatches() == null || fullLeague.getMatches().isEmpty()) {
            throw new InvalidJsonException("No valid matches found in the league JSON");
        }

        return fullLeague.getMatches().get(0);
    }

    private Match findMatchWithMarkets(Match match) throws JsonSyntaxException{
        String jsonResponse = apiService.receiveMarketsJson(match.getId());
        return gson.fromJson(jsonResponse, Match.class);
    }

}
