package org.sparkproject.dmg.pmml.mining;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Segmentation createSegmentation() {
      return new Segmentation();
   }

   public MiningModel createMiningModel() {
      return new MiningModel();
   }

   public Segment createSegment() {
      return new Segment();
   }

   public VariableWeight createVariableWeight() {
      return new VariableWeight();
   }
}
