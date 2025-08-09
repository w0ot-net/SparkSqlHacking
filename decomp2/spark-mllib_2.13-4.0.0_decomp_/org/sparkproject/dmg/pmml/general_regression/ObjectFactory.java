package org.sparkproject.dmg.pmml.general_regression;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public PCovMatrix createPCovMatrix() {
      return new PCovMatrix();
   }

   public GeneralRegressionModel createGeneralRegressionModel() {
      return new GeneralRegressionModel();
   }

   public ParameterList createParameterList() {
      return new ParameterList();
   }

   public Parameter createParameter() {
      return new Parameter();
   }

   public FactorList createFactorList() {
      return new FactorList();
   }

   public Predictor createPredictor() {
      return new Predictor();
   }

   public Categories createCategories() {
      return new Categories();
   }

   public Category createCategory() {
      return new Category();
   }

   public CovariateList createCovariateList() {
      return new CovariateList();
   }

   public PPMatrix createPPMatrix() {
      return new PPMatrix();
   }

   public PPCell createPPCell() {
      return new PPCell();
   }

   public PCovCell createPCovCell() {
      return new PCovCell();
   }

   public ParamMatrix createParamMatrix() {
      return new ParamMatrix();
   }

   public PCell createPCell() {
      return new PCell();
   }

   public EventValues createEventValues() {
      return new EventValues();
   }

   public BaseCumHazardTables createBaseCumHazardTables() {
      return new BaseCumHazardTables();
   }

   public BaselineStratum createBaselineStratum() {
      return new BaselineStratum();
   }

   public BaselineCell createBaselineCell() {
      return new BaselineCell();
   }
}
