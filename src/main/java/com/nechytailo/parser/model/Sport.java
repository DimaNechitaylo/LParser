package com.nechytailo.parser.model;

import lombok.Data;

import java.util.List;

@Data
public class Sport {
    private Long id;
    private String name;
    private List<Region> regions;
}
