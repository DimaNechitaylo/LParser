package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Match {
    private String name;
    private String startDate;
    private String id;
    private League league;
    private List<Market> markets;
}
