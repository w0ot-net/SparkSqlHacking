package org.sparkproject.dmg.pmml.clustering;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ClusteringField createClusteringField() {
      return new ClusteringField();
   }

   public ClusteringModel createClusteringModel() {
      return new ClusteringModel();
   }

   public Comparisons createComparisons() {
      return new Comparisons();
   }

   public CenterFields createCenterFields() {
      return new CenterFields();
   }

   public MissingValueWeights createMissingValueWeights() {
      return new MissingValueWeights();
   }

   public Cluster createCluster() {
      return new Cluster();
   }

   public KohonenMap createKohonenMap() {
      return new KohonenMap();
   }

   public Covariances createCovariances() {
      return new Covariances();
   }
}
