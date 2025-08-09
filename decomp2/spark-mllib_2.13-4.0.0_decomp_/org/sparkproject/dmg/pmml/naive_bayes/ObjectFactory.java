package org.sparkproject.dmg.pmml.naive_bayes;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public NaiveBayesModel createNaiveBayesModel() {
      return new NaiveBayesModel();
   }

   public BayesInputs createBayesInputs() {
      return new BayesInputs();
   }

   public BayesInput createBayesInput() {
      return new BayesInput();
   }

   public TargetValueStats createTargetValueStats() {
      return new TargetValueStats();
   }

   public TargetValueStat createTargetValueStat() {
      return new TargetValueStat();
   }

   public PairCounts createPairCounts() {
      return new PairCounts();
   }

   public TargetValueCounts createTargetValueCounts() {
      return new TargetValueCounts();
   }

   public TargetValueCount createTargetValueCount() {
      return new TargetValueCount();
   }

   public BayesOutput createBayesOutput() {
      return new BayesOutput();
   }
}
