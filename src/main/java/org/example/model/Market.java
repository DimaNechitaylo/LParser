package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Market {
    private String name;
    private List<Outcome> outcomes;
}
