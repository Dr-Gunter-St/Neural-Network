package com.company;

import com.company.Network.Network;
import com.company.Training.TrainingSet;

import java.nio.file.Paths;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        TrainingSet trainingSet = new TrainingSet(Paths.get("resources\\inputs"));

        int[] struct = new int[]{5,4,3,3,1};
        Network network = new Network(trainingSet, struct);
        network.train(false);


        System.out.println(network.processInput(trainingSet.getInputs().get(0)));
        System.out.println(network.getWantedResults(trainingSet.getInputs().get(0)));

    }
}
