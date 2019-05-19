package com.company.Network.Neurons;


public final class Sigmoid {

    private static double beta = 1.0;

    public static final double sigmoid(double s){
        return 1 / (1 + Math.exp(-beta*s));
    }

    public static double getBeta() {
        return beta;
    }

    public static void setBeta(double beta) {
        Sigmoid.beta = beta;
    }
}
