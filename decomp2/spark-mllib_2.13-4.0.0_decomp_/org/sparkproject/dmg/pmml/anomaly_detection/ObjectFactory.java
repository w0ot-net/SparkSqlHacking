package org.sparkproject.dmg.pmml.anomaly_detection;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public AnomalyDetectionModel createAnomalyDetectionModel() {
      return new AnomalyDetectionModel();
   }

   public MeanClusterDistances createMeanClusterDistances() {
      return new MeanClusterDistances();
   }
}
