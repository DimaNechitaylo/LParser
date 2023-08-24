package com.nechytailo.parser.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Market {
    private Long id;
    private String name;
    @SerializedName("runners")
    private List<Outcome> outcomes;
}
