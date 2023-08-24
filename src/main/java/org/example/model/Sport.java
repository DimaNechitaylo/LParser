package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Sport {
    private Long id;
    private String name;
    private List<Regions> regions;
}
