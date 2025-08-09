package org.sparkproject.dmg.pmml.neural_network;

import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasNormalizationMethod {
   NeuralNetwork.NormalizationMethod getNormalizationMethod();

   PMMLObject setNormalizationMethod(NeuralNetwork.NormalizationMethod var1);
}
