package com.nechytailo.parser.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Outcome {
    private String id;
    private String name;
    @SerializedName("price")
    private double coefficient;
}
