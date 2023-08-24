package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Outcome {
    private String id;
    private String name;
    @SerializedName("price")
    private double coefficient;
}
