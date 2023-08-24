package org.example.api;

public interface ApiService {
    String receiveSportsJson();
    String receiveMatchesJson(Long leagueId);
    String receiveMarketsJson(Long matchId);
}
