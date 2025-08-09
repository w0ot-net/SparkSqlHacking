package org.sparkproject.dmg.pmml.sequence;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SetPredicate createSetPredicate() {
      return new SetPredicate();
   }

   public Delimiter createDelimiter() {
      return new Delimiter();
   }

   public SequenceModel createSequenceModel() {
      return new SequenceModel();
   }

   public Constraints createConstraints() {
      return new Constraints();
   }

   public Sequence createSequence() {
      return new Sequence();
   }

   public SetReference createSetReference() {
      return new SetReference();
   }

   public Time createTime() {
      return new Time();
   }

   public SequenceRule createSequenceRule() {
      return new SequenceRule();
   }

   public AntecedentSequence createAntecedentSequence() {
      return new AntecedentSequence();
   }

   public SequenceReference createSequenceReference() {
      return new SequenceReference();
   }

   public ConsequentSequence createConsequentSequence() {
      return new ConsequentSequence();
   }
}
