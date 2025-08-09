package org.sparkproject.dmg.pmml.nearest_neighbor;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public NearestNeighborModel createNearestNeighborModel() {
      return new NearestNeighborModel();
   }

   public TrainingInstances createTrainingInstances() {
      return new TrainingInstances();
   }

   public InstanceFields createInstanceFields() {
      return new InstanceFields();
   }

   public InstanceField createInstanceField() {
      return new InstanceField();
   }

   public KNNInputs createKNNInputs() {
      return new KNNInputs();
   }

   public KNNInput createKNNInput() {
      return new KNNInput();
   }
}
