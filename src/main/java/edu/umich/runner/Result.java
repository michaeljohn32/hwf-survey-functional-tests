package edu.umich.runner;

import lombok.Data;

@Data
public class Result {
    private String className;
    private String name;
    private String failure;
}
