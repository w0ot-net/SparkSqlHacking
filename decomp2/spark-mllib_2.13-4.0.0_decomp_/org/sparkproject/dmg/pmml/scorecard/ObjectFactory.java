package org.sparkproject.dmg.pmml.scorecard;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Scorecard createScorecard() {
      return new Scorecard();
   }

   public Characteristics createCharacteristics() {
      return new Characteristics();
   }

   public Characteristic createCharacteristic() {
      return new Characteristic();
   }

   public Attribute createAttribute() {
      return new Attribute();
   }

   public ComplexPartialScore createComplexPartialScore() {
      return new ComplexPartialScore();
   }
}
