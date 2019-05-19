package com.company.Network.Neurons;

import com.company.Network.Neurons.Interfaces.NeuronInt;

import java.util.ArrayList;
import java.util.List;

public class Neuron implements NeuronInt {

    private static int idCounter;

    private List<WeightedEdge> back;
    private List<WeightedEdge> forth;

    private double weightedSum;
    private double delta;
    private double output;
    private int id;

    public Neuron(List<WeightedEdge> back, List<WeightedEdge> forth) {
        this.back = back;
        this.forth = forth;

        weightedSum = 0.0;
        delta = 0.0;
        output = 0.0;
        id = idCounter;
        idCounter++;
    }

    public  Neuron(){
        forth = new ArrayList<>();
        back = new ArrayList<>();
        weightedSum = 0.0;
        delta = 0.0;
        output = 0.0;
        id = idCounter;
        idCounter++;
    }


    public double calculateWeightedSum(){
        back.forEach(weightedEdge -> {
            weightedSum += weightedEdge.getWeight() * weightedEdge.getBackNeuron().getOutput();
        });
        return weightedSum;
    }

    @Override
        public double applySigmoid() {
        output =  Sigmoid.sigmoid(calculateWeightedSum());
        return output;
    }

    @Override
    public void adjustDeltaW(){
        double deltaW = 0.0;
        for (WeightedEdge we: back) {
            deltaW = -WeightedEdge.n * delta * (1 - output) * output * we.getBackNeuron().getOutput();
            we.setDeltaW(deltaW);
            we.setWeight(we.getWeight() + deltaW);
        }
    }

    @Override
    public void updateDelta(){
        delta = 0.0;

        for (WeightedEdge we: forth) {
            delta += we.getForthNeuron().getDelta() * we.getWeight() * (1 - we.getForthNeuron().getOutput()) * we.getForthNeuron().getOutput();
        }

    }

    public List<WeightedEdge> getBack() {
        return back;
    }

    public void setBack(List<WeightedEdge> back) {
        this.back = back;
    }

    public List<WeightedEdge> getForth() {
        return forth;
    }

    public void setForth(List<WeightedEdge> forth) {
        this.forth = forth;
    }

    public double getWeightedSum() {
        return weightedSum;
    }

    public void setWeightedSum(double weightedSum) {
        this.weightedSum = weightedSum;
    }

    @Override
    public double getDelta() {
        return delta;
    }

    @Override
    public void setDelta(double delta) {
        this.delta = delta;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getOutput() {
        return applySigmoid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Neuron neuron = (Neuron) o;

        if (Double.compare(neuron.weightedSum, weightedSum) != 0) return false;
        if (Double.compare(neuron.delta, delta) != 0) return false;
        if (Double.compare(neuron.output, output) != 0) return false;
        if (back != null ? !back.equals(neuron.back) : neuron.back != null) return false;
        return forth != null ? forth.equals(neuron.forth) : neuron.forth == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = back != null ? back.hashCode() : 0;
        result = 31 * result + (forth != null ? forth.hashCode() : 0);
        temp = Double.doubleToLongBits(weightedSum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(delta);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(output);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }



    @Override
    public String toString() {
        return "Neuron{" +
                " id=" + id +
                ", weightedSum=" + weightedSum +
                ", delta=" + delta +
                ", output=" + output +
                '}';
    }
}
