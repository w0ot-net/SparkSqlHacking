package org.sparkproject.dmg.pmml.neural_network;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field CONNECTION_EXTENSIONS = ReflectionUtil.getField(Connection.class, "extensions");
   Field NEURALINPUT_EXTENSIONS = ReflectionUtil.getField(NeuralInput.class, "extensions");
   Field NEURALINPUT_DERIVEDFIELD = ReflectionUtil.getField(NeuralInput.class, "derivedField");
   Field NEURALINPUTS_EXTENSIONS = ReflectionUtil.getField(NeuralInputs.class, "extensions");
   Field NEURALINPUTS_NEURALINPUTS = ReflectionUtil.getField(NeuralInputs.class, "neuralInputs");
   Field NEURALLAYER_EXTENSIONS = ReflectionUtil.getField(NeuralLayer.class, "extensions");
   Field NEURALLAYER_NEURONS = ReflectionUtil.getField(NeuralLayer.class, "neurons");
   Field NEURALNETWORK_EXTENSIONS = ReflectionUtil.getField(NeuralNetwork.class, "extensions");
   Field NEURALNETWORK_MININGSCHEMA = ReflectionUtil.getField(NeuralNetwork.class, "miningSchema");
   Field NEURALNETWORK_OUTPUT = ReflectionUtil.getField(NeuralNetwork.class, "output");
   Field NEURALNETWORK_MODELSTATS = ReflectionUtil.getField(NeuralNetwork.class, "modelStats");
   Field NEURALNETWORK_MODELEXPLANATION = ReflectionUtil.getField(NeuralNetwork.class, "modelExplanation");
   Field NEURALNETWORK_TARGETS = ReflectionUtil.getField(NeuralNetwork.class, "targets");
   Field NEURALNETWORK_LOCALTRANSFORMATIONS = ReflectionUtil.getField(NeuralNetwork.class, "localTransformations");
   Field NEURALNETWORK_NEURALINPUTS = ReflectionUtil.getField(NeuralNetwork.class, "neuralInputs");
   Field NEURALNETWORK_NEURALLAYERS = ReflectionUtil.getField(NeuralNetwork.class, "neuralLayers");
   Field NEURALNETWORK_NEURALOUTPUTS = ReflectionUtil.getField(NeuralNetwork.class, "neuralOutputs");
   Field NEURALNETWORK_MODELVERIFICATION = ReflectionUtil.getField(NeuralNetwork.class, "modelVerification");
   Field NEURALOUTPUT_EXTENSIONS = ReflectionUtil.getField(NeuralOutput.class, "extensions");
   Field NEURALOUTPUT_DERIVEDFIELD = ReflectionUtil.getField(NeuralOutput.class, "derivedField");
   Field NEURALOUTPUTS_EXTENSIONS = ReflectionUtil.getField(NeuralOutputs.class, "extensions");
   Field NEURALOUTPUTS_NEURALOUTPUTS = ReflectionUtil.getField(NeuralOutputs.class, "neuralOutputs");
   Field NEURON_EXTENSIONS = ReflectionUtil.getField(Neuron.class, "extensions");
   Field NEURON_CONNECTIONS = ReflectionUtil.getField(Neuron.class, "connections");
}
