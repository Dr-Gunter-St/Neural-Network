package com.company.Network;

import com.company.Network.Neurons.Interfaces.NeuronInt;
import com.company.Network.Neurons.InputNeuron;
import com.company.Network.Neurons.Neuron;
import com.company.Network.Neurons.WeightedEdge;
import com.company.Training.TrainingInput;
import com.company.Training.TrainingSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network {

    private List<NeuronInt> inputs;
    private Neuron networkEnd;
    private TrainingSet trainingSet;

    public Network(TrainingSet trainingSet, int[] structure) {
        this.trainingSet = trainingSet;

        int inputsNum = trainingSet.getInputs().get(0).getInputs().length;
        inputs = new ArrayList<>(inputsNum);

        for (int i = 0; i < inputsNum; i++) {
            inputs.add(new InputNeuron());
        }

        List<NeuronInt> prevLayer;
        List<NeuronInt> thisLayer = new ArrayList<>();

        // FOR IRIS DATA
        // STRUCTURE: 5 4 3 3OUT
        // IMPORTANT
        // Last neuron made for running the network


        prevLayer = inputs;
        for (int i: structure) {
            for (int j = 0; j < i; j++) {
                NeuronInt neuron = new Neuron();
                for (NeuronInt n: prevLayer) {
                    WeightedEdge we = new WeightedEdge(n, neuron);
                    n.getForth().add(we);
                    neuron.getBack().add(we);
                }
                thisLayer.add(neuron);
                networkEnd = (Neuron) neuron;

            }

            prevLayer = thisLayer;
            thisLayer = new ArrayList<>();

        }


    }

    public List<Double> processInput(TrainingInput inputToProcess){
        List<Double> results = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).setInput(inputToProcess.getInputs()[i]);
        }

        networkEnd.processInputs();

        for (WeightedEdge we: networkEnd.getBack()) {
            results.add(we.getBackNeuron().getOutput());
        }
        return results;
    }

    public void train(boolean makeEncoder){
        List<Double> results;
        List<Double> wantedResults;

        for (TrainingInput ti: trainingSet.getInputs()) {
            results = this.processInput(ti);

            if (!makeEncoder) {
                wantedResults = getWantedResults(ti);
            } else {
                wantedResults = Arrays.asList(ti.getInputs());
            }

            this.backpropagate(results, wantedResults);

        }
    }

    public double backpropagate(List<Double> results, List<Double> wantedResults){
        double mistake = 0.0;

        List<NeuronInt> thisLayer = new ArrayList<>();
        List<NeuronInt> prevLayer;

        for (WeightedEdge we: networkEnd.getBack()) {
            thisLayer.add(we.getBackNeuron());
        }

        for (int i = 0; i < thisLayer.size(); i++) {
            thisLayer.get(i).setDelta(wantedResults.get(i) - results.get(i));
            thisLayer.get(i).adjustDeltaW();
        }

        while (thisLayer.get(0).getBack().size() != 0) {

            prevLayer = new ArrayList<>();

            for (WeightedEdge we : thisLayer.get(0).getBack()) {
                prevLayer.add(we.getBackNeuron());
            }

            thisLayer = prevLayer;

            for (int i = 0; i < thisLayer.size(); i++) {
                thisLayer.get(i).updateDelta();
                thisLayer.get(i).adjustDeltaW();
            }

        }


        ////////////////////////////////////////////////////////////
        for (int i = 0; i < results.size(); i++) {
            mistake = Math.abs(wantedResults.get(i) - results.get(i));
        }

        return mistake;
    }

    public Neuron getNetworkEnd() {
        return networkEnd;
    }

    public List<Double> getWantedResults(TrainingInput ti){

        String currClassName = ti.getClassName();
        List<Double> wantedResults = new ArrayList<>();

        for (int i = 0; i < trainingSet.getClasses().size(); i++) {
            wantedResults.add(0.0);
            if (currClassName.equals(trainingSet.getClasses().toArray()[i])){
                wantedResults.set(i, 1.0);
            }
        }

        return wantedResults;

    }

    public void testTraining(){

        List<Double> results;
        List<Double> wantedResults;
        int correctRes = trainingSet.getInputs().size();

        for (TrainingInput input: trainingSet.getInputs()) {
            results = this.processInput(input);
            wantedResults = this.getWantedResults(input);

            for (int i = 0; i < results.size(); i++) {
                if (Math.abs(wantedResults.get(i) - results.get(i)) > 0.05 ){
                    correctRes--;
                    break;
                }
            }
        }

        System.out.println((double)correctRes/(double)trainingSet.getInputs().size());
    }

    public double getError(){
        double error = 0.0;
        List<Double> results;
        List<Double> wantedResults;

        for (int i = 0; i < trainingSet.getInputs().size(); i++) {
            results = processInput(trainingSet.getInputs().get(i));
            wantedResults = getWantedResults(trainingSet.getInputs().get(i));

            for (int j = 0; j < results.size(); j++) {
                error += (wantedResults.get(j) - results.get(j))*(wantedResults.get(j) - results.get(j))/2.0;
            }
        }

        return error/(double) trainingSet.getInputs().size();
    }

    @Override
    public String toString() {
        String res = "Network{ \n";

        List<NeuronInt> thisLayer = inputs;
        List<NeuronInt> nextLayer;

        while (thisLayer.size() > 0) {

            nextLayer = new ArrayList<>();

            for (NeuronInt n : thisLayer) {
                res += n.toString() + "\n";
            }
            res += "////////////////////////\n";
            for (WeightedEdge we: thisLayer.get(0).getForth()) {
                nextLayer.add(we.getForthNeuron());
            }
            thisLayer = nextLayer;
        }
        res+="}\n";
        return res;
    }
}
