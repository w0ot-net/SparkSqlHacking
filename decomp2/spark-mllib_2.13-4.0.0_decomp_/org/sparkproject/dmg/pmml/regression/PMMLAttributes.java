package org.sparkproject.dmg.pmml.regression;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field CATEGORICALPREDICTOR_FIELD = ReflectionUtil.getField(CategoricalPredictor.class, "field");
   Field CATEGORICALPREDICTOR_VALUE = ReflectionUtil.getField(CategoricalPredictor.class, "value");
   Field CATEGORICALPREDICTOR_COEFFICIENT = ReflectionUtil.getField(CategoricalPredictor.class, "coefficient");
   Field NUMERICPREDICTOR_FIELD = ReflectionUtil.getField(NumericPredictor.class, "field");
   Field NUMERICPREDICTOR_EXPONENT = ReflectionUtil.getField(NumericPredictor.class, "exponent");
   Field NUMERICPREDICTOR_COEFFICIENT = ReflectionUtil.getField(NumericPredictor.class, "coefficient");
   Field PREDICTORTERM_NAME = ReflectionUtil.getField(PredictorTerm.class, "name");
   Field PREDICTORTERM_COEFFICIENT = ReflectionUtil.getField(PredictorTerm.class, "coefficient");
   Field REGRESSION_MODELNAME = ReflectionUtil.getField(Regression.class, "modelName");
   Field REGRESSION_MININGFUNCTION = ReflectionUtil.getField(Regression.class, "miningFunction");
   Field REGRESSION_ALGORITHMNAME = ReflectionUtil.getField(Regression.class, "algorithmName");
   Field REGRESSION_NORMALIZATIONMETHOD = ReflectionUtil.getField(Regression.class, "normalizationMethod");
   Field REGRESSIONMODEL_MODELNAME = ReflectionUtil.getField(RegressionModel.class, "modelName");
   Field REGRESSIONMODEL_MININGFUNCTION = ReflectionUtil.getField(RegressionModel.class, "miningFunction");
   Field REGRESSIONMODEL_ALGORITHMNAME = ReflectionUtil.getField(RegressionModel.class, "algorithmName");
   Field REGRESSIONMODEL_MODELTYPE = ReflectionUtil.getField(RegressionModel.class, "modelType");
   Field REGRESSIONMODEL_TARGETFIELD = ReflectionUtil.getField(RegressionModel.class, "targetField");
   Field REGRESSIONMODEL_NORMALIZATIONMETHOD = ReflectionUtil.getField(RegressionModel.class, "normalizationMethod");
   Field REGRESSIONMODEL_SCORABLE = ReflectionUtil.getField(RegressionModel.class, "scorable");
   Field REGRESSIONMODEL_MATHCONTEXT = ReflectionUtil.getField(RegressionModel.class, "mathContext");
   Field REGRESSIONTABLE_INTERCEPT = ReflectionUtil.getField(RegressionTable.class, "intercept");
   Field REGRESSIONTABLE_TARGETCATEGORY = ReflectionUtil.getField(RegressionTable.class, "targetCategory");
}
