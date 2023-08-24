package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Regions {
    private Long id;
    private String name;
    private List<League> leagues;
}
