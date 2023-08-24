package org.example.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Match {
    private Long id;
    private String name;
    private Date startDate = new Date(1212121212121L);
    private List<Market> markets;
}
