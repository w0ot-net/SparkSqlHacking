package org.sparkproject.dmg.pmml.support_vector_machine;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SupportVectorMachineModel createSupportVectorMachineModel() {
      return new SupportVectorMachineModel();
   }

   public LinearKernel createLinearKernel() {
      return new LinearKernel();
   }

   public PolynomialKernel createPolynomialKernel() {
      return new PolynomialKernel();
   }

   public RadialBasisKernel createRadialBasisKernel() {
      return new RadialBasisKernel();
   }

   public SigmoidKernel createSigmoidKernel() {
      return new SigmoidKernel();
   }

   public VectorDictionary createVectorDictionary() {
      return new VectorDictionary();
   }

   public VectorFields createVectorFields() {
      return new VectorFields();
   }

   public VectorInstance createVectorInstance() {
      return new VectorInstance();
   }

   public SupportVectorMachine createSupportVectorMachine() {
      return new SupportVectorMachine();
   }

   public SupportVectors createSupportVectors() {
      return new SupportVectors();
   }

   public SupportVector createSupportVector() {
      return new SupportVector();
   }

   public Coefficients createCoefficients() {
      return new Coefficients();
   }

   public Coefficient createCoefficient() {
      return new Coefficient();
   }
}
