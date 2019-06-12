package com.company.Network.Neurons;

import com.company.Network.Neurons.Interfaces.NeuronInt;

import java.util.ArrayList;
import java.util.List;

public class InputNeuron implements NeuronInt {

    private List<WeightedEdge> forth;
    private double input;

    public InputNeuron() {
        forth = new ArrayList<>();
    }

    public List<WeightedEdge> getForth() {
        return forth;
    }

    public double processInputs() {
        return input;
    }

    @Override
    public double getOutput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "InputNeuron{" +
                "input=" + input +
                '}';
    }
}

