package com.nechytailo.parser.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Match {
    private Long id;
    private String name;
    private Date startDate;
    private List<Market> markets;
}
