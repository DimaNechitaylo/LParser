package com.nechytailo.parser.api;

public interface ApiService {
    String receiveSportsJson();

    String receiveMatchesJson(Long leagueId);

    String receiveMatchWithMarketsJson(Long matchId);

    String receiveMatchDateJson(Long matchId);
}
