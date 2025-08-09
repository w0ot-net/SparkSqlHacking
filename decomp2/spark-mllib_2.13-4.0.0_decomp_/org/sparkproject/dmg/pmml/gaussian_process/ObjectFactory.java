package org.sparkproject.dmg.pmml.gaussian_process;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public GaussianProcessModel createGaussianProcessModel() {
      return new GaussianProcessModel();
   }

   public RadialBasisKernel createRadialBasisKernel() {
      return new RadialBasisKernel();
   }

   public ARDSquaredExponentialKernel createARDSquaredExponentialKernel() {
      return new ARDSquaredExponentialKernel();
   }

   public Lambda createLambda() {
      return new Lambda();
   }

   public AbsoluteExponentialKernel createAbsoluteExponentialKernel() {
      return new AbsoluteExponentialKernel();
   }

   public GeneralizedExponentialKernel createGeneralizedExponentialKernel() {
      return new GeneralizedExponentialKernel();
   }
}
