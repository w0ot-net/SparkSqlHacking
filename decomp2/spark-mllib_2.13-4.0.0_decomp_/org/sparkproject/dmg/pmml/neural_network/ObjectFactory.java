package org.sparkproject.dmg.pmml.neural_network;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public NeuralNetwork createNeuralNetwork() {
      return new NeuralNetwork();
   }

   public NeuralInputs createNeuralInputs() {
      return new NeuralInputs();
   }

   public NeuralInput createNeuralInput() {
      return new NeuralInput();
   }

   public NeuralLayer createNeuralLayer() {
      return new NeuralLayer();
   }

   public Neuron createNeuron() {
      return new Neuron();
   }

   public Connection createConnection() {
      return new Connection();
   }

   public NeuralOutputs createNeuralOutputs() {
      return new NeuralOutputs();
   }

   public NeuralOutput createNeuralOutput() {
      return new NeuralOutput();
   }
}
