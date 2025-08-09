package org.sparkproject.dmg.pmml.bayesian_network;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public BayesianNetworkModel createBayesianNetworkModel() {
      return new BayesianNetworkModel();
   }

   public BayesianNetworkNodes createBayesianNetworkNodes() {
      return new BayesianNetworkNodes();
   }

   public DiscreteNode createDiscreteNode() {
      return new DiscreteNode();
   }

   public DiscreteConditionalProbability createDiscreteConditionalProbability() {
      return new DiscreteConditionalProbability();
   }

   public ParentValue createParentValue() {
      return new ParentValue();
   }

   public ValueProbability createValueProbability() {
      return new ValueProbability();
   }

   public ContinuousNode createContinuousNode() {
      return new ContinuousNode();
   }

   public ContinuousConditionalProbability createContinuousConditionalProbability() {
      return new ContinuousConditionalProbability();
   }

   public ContinuousDistribution createContinuousDistribution() {
      return new ContinuousDistribution();
   }

   public TriangularDistribution createTriangularDistribution() {
      return new TriangularDistribution();
   }

   public Mean createMean() {
      return new Mean();
   }

   public Lower createLower() {
      return new Lower();
   }

   public Upper createUpper() {
      return new Upper();
   }

   public NormalDistribution createNormalDistribution() {
      return new NormalDistribution();
   }

   public Variance createVariance() {
      return new Variance();
   }

   public LognormalDistribution createLognormalDistribution() {
      return new LognormalDistribution();
   }

   public UniformDistribution createUniformDistribution() {
      return new UniformDistribution();
   }
}
