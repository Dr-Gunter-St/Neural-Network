package com.company.Network.Neurons.Interfaces;


import com.company.Network.Neurons.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

public interface NeuronInt {

    double getWeightedSum();

    double getOutput();

    List<WeightedEdge> getForth();

    default List<WeightedEdge> getBack(){
        return new ArrayList<>();
    }

    default void setInput(double input){}

    default double applySigmoid(){
        return 0;
    };

    default void setDelta(double d){}

    default double getDelta(){return 0.0;}

    default void adjustDeltaW(){}

    default void updateDelta(){}
}
