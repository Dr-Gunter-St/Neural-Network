package com.company;

import com.company.Network.Network;
import com.company.Training.TrainingSet;

import java.nio.file.Paths;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        TrainingSet trainingSet = new TrainingSet(Paths.get("resources\\inputs"));

        Network network = new Network(trainingSet);
        network.train();

        System.out.println(network.processInput(trainingSet.getInputs().get(0)));
        System.out.println(network.getWantedResults(trainingSet.getInputs().get(0)));

    }
}
