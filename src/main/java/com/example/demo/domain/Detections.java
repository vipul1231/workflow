package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Detections {
    private int id;
    private String tactics;
    private String techniques;
    private String nextStep;
    private String type;
    private String detectionDescription;
    private String detectionQueryType;
    private String query;
}
