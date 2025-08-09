package org.sparkproject.dmg.pmml.neural_network;

import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasActivationFunction {
   NeuralNetwork.ActivationFunction getActivationFunction();

   PMMLObject setActivationFunction(NeuralNetwork.ActivationFunction var1);

   Number getThreshold();

   PMMLObject setThreshold(Number var1);

   Number getLeakage();

   PMMLObject setLeakage(Number var1);

   Number getWidth();

   PMMLObject setWidth(Number var1);

   Number getAltitude();

   PMMLObject setAltitude(Number var1);
}
