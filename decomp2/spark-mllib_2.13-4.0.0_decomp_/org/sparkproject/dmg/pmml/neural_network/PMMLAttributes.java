package org.sparkproject.dmg.pmml.neural_network;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field CONNECTION_FROM = ReflectionUtil.getField(Connection.class, "from");
   Field CONNECTION_WEIGHT = ReflectionUtil.getField(Connection.class, "weight");
   Field NEURALINPUT_ID = ReflectionUtil.getField(NeuralInput.class, "id");
   Field NEURALINPUTS_NUMBEROFINPUTS = ReflectionUtil.getField(NeuralInputs.class, "numberOfInputs");
   Field NEURALLAYER_NUMBEROFNEURONS = ReflectionUtil.getField(NeuralLayer.class, "numberOfNeurons");
   Field NEURALLAYER_ACTIVATIONFUNCTION = ReflectionUtil.getField(NeuralLayer.class, "activationFunction");
   Field NEURALLAYER_THRESHOLD = ReflectionUtil.getField(NeuralLayer.class, "threshold");
   Field NEURALLAYER_LEAKAGE = ReflectionUtil.getField(NeuralLayer.class, "leakage");
   Field NEURALLAYER_WIDTH = ReflectionUtil.getField(NeuralLayer.class, "width");
   Field NEURALLAYER_ALTITUDE = ReflectionUtil.getField(NeuralLayer.class, "altitude");
   Field NEURALLAYER_NORMALIZATIONMETHOD = ReflectionUtil.getField(NeuralLayer.class, "normalizationMethod");
   Field NEURALNETWORK_MODELNAME = ReflectionUtil.getField(NeuralNetwork.class, "modelName");
   Field NEURALNETWORK_MININGFUNCTION = ReflectionUtil.getField(NeuralNetwork.class, "miningFunction");
   Field NEURALNETWORK_ALGORITHMNAME = ReflectionUtil.getField(NeuralNetwork.class, "algorithmName");
   Field NEURALNETWORK_ACTIVATIONFUNCTION = ReflectionUtil.getField(NeuralNetwork.class, "activationFunction");
   Field NEURALNETWORK_NORMALIZATIONMETHOD = ReflectionUtil.getField(NeuralNetwork.class, "normalizationMethod");
   Field NEURALNETWORK_THRESHOLD = ReflectionUtil.getField(NeuralNetwork.class, "threshold");
   Field NEURALNETWORK_LEAKAGE = ReflectionUtil.getField(NeuralNetwork.class, "leakage");
   Field NEURALNETWORK_WIDTH = ReflectionUtil.getField(NeuralNetwork.class, "width");
   Field NEURALNETWORK_ALTITUDE = ReflectionUtil.getField(NeuralNetwork.class, "altitude");
   Field NEURALNETWORK_NUMBEROFLAYERS = ReflectionUtil.getField(NeuralNetwork.class, "numberOfLayers");
   Field NEURALNETWORK_SCORABLE = ReflectionUtil.getField(NeuralNetwork.class, "scorable");
   Field NEURALNETWORK_MATHCONTEXT = ReflectionUtil.getField(NeuralNetwork.class, "mathContext");
   Field NEURALOUTPUT_OUTPUTNEURON = ReflectionUtil.getField(NeuralOutput.class, "outputNeuron");
   Field NEURALOUTPUTS_NUMBEROFOUTPUTS = ReflectionUtil.getField(NeuralOutputs.class, "numberOfOutputs");
   Field NEURON_ID = ReflectionUtil.getField(Neuron.class, "id");
   Field NEURON_BIAS = ReflectionUtil.getField(Neuron.class, "bias");
   Field NEURON_WIDTH = ReflectionUtil.getField(Neuron.class, "width");
   Field NEURON_ALTITUDE = ReflectionUtil.getField(Neuron.class, "altitude");
}
