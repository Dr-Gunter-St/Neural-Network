package com.company.Training;

import java.util.Arrays;
import java.util.List;

public class TrainingInput {

    private Double[] inputs;
    private String className;

    TrainingInput(String line, TrainingSet trainingSet){

        String[] values = line.split(",");
        this.className = values[values.length - 1];

        inputs = new Double[values.length - 1];
        for (int i = 0; i < values.length - 1; i++) {
            inputs[i] = Double.parseDouble(values[i]);
        }
    }

    public Double[] getInputs() {
        return inputs;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "TrainingInput{" +
                "inputs=" + Arrays.toString(inputs) +
                ", className='" + className + '\'' +
                "}\n";
    }
}
