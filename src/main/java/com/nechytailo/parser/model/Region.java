package com.nechytailo.parser.model;

import lombok.Data;

import java.util.List;

@Data
public class Region {
    private Long id;
    private String name;
    private List<League> leagues;
}
