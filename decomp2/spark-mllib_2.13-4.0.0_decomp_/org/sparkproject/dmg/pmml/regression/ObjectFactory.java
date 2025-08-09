package org.sparkproject.dmg.pmml.regression;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RegressionModel createRegressionModel() {
      return new RegressionModel();
   }

   public Regression createRegression() {
      return new Regression();
   }

   public RegressionTable createRegressionTable() {
      return new RegressionTable();
   }

   public NumericPredictor createNumericPredictor() {
      return new NumericPredictor();
   }

   public CategoricalPredictor createCategoricalPredictor() {
      return new CategoricalPredictor();
   }

   public PredictorTerm createPredictorTerm() {
      return new PredictorTerm();
   }
}
