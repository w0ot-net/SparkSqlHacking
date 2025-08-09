package org.sparkproject.dmg.pmml.baseline;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public TestDistributions createTestDistributions() {
      return new TestDistributions();
   }

   public BaselineModel createBaselineModel() {
      return new BaselineModel();
   }

   public Baseline createBaseline() {
      return new Baseline();
   }

   public CountTable createCountTable() {
      return new CountTable();
   }

   public Alternate createAlternate() {
      return new Alternate();
   }

   public FieldValue createFieldValue() {
      return new FieldValue();
   }

   public FieldValueCount createFieldValueCount() {
      return new FieldValueCount();
   }
}
