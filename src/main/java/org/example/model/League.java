package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class League {
    private Long id;
    private String name;
    private boolean top;
    @SerializedName("events")
    private List<Match> matches;
}
