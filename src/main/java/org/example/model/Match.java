package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Match {
    private Long id;
    private String name;
//    private String startDate;
//    private League league;
    private List<Market> markets;
}
