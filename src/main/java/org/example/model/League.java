package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class League {
    private Long id;
    private String name;
    private String regionName;
    private String sportName;
    private boolean top;
    @SerializedName("events")
    private List<Match> matches;

    public Match getFirstMatch() {
        return matches != null && !matches.isEmpty()
                ? matches.get(0)
                : null;
    }
}
