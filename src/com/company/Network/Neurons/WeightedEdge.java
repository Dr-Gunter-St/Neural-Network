package com.company.Network.Neurons;

import com.company.Network.Neurons.Interfaces.NeuronInt;

import java.util.Random;

public class WeightedEdge {

    public static final double n = -0.2;

    private NeuronInt forthNeuron;
    private NeuronInt backNeuron;
    private double weight;
    private double deltaW;

    public WeightedEdge(NeuronInt backNeuron, NeuronInt forthNeuron) {
        this.forthNeuron = forthNeuron;
        this.backNeuron = backNeuron;

        Random random = new Random();
        this.weight = random.nextDouble();

        //if (!random.nextBoolean()) this.weight = -this.weight;

        this.deltaW = 0.0;
    }

    public NeuronInt getForthNeuron() {
        return forthNeuron;
    }

    public void setForthNeuron(NeuronInt forthNeuron) {
        this.forthNeuron = forthNeuron;
    }

    public NeuronInt getBackNeuron() {
        return backNeuron;
    }

    public void setBackNeuron(NeuronInt backNeuron) {
        this.backNeuron = backNeuron;
    }

    double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDeltaW() {
        return deltaW;
    }

    public void setDeltaW(double deltaW) {
        this.deltaW = deltaW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeightedEdge that = (WeightedEdge) o;

        if (Double.compare(that.weight, weight) != 0) return false;
        if (Double.compare(that.deltaW, deltaW) != 0) return false;
        if (forthNeuron != null ? !forthNeuron.equals(that.forthNeuron) : that.forthNeuron != null) return false;
        return backNeuron != null ? backNeuron.equals(that.backNeuron) : that.backNeuron == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = forthNeuron != null ? forthNeuron.hashCode() : 0;
        result = 31 * result + (backNeuron != null ? backNeuron.hashCode() : 0);
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(deltaW);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "WeightedEdge{" +
                "forthNeuron=" + forthNeuron +
                ", backNeuron=" + backNeuron +
                ", weight=" + weight +
                ", deltaW=" + deltaW +
                '}';
    }
}


