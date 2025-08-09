package org.sparkproject.dmg.pmml.regression;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field CATEGORICALPREDICTOR_EXTENSIONS = ReflectionUtil.getField(CategoricalPredictor.class, "extensions");
   Field NUMERICPREDICTOR_EXTENSIONS = ReflectionUtil.getField(NumericPredictor.class, "extensions");
   Field PREDICTORTERM_EXTENSIONS = ReflectionUtil.getField(PredictorTerm.class, "extensions");
   Field PREDICTORTERM_FIELDREFS = ReflectionUtil.getField(PredictorTerm.class, "fieldRefs");
   Field REGRESSION_EXTENSIONS = ReflectionUtil.getField(Regression.class, "extensions");
   Field REGRESSION_OUTPUT = ReflectionUtil.getField(Regression.class, "output");
   Field REGRESSION_MODELSTATS = ReflectionUtil.getField(Regression.class, "modelStats");
   Field REGRESSION_TARGETS = ReflectionUtil.getField(Regression.class, "targets");
   Field REGRESSION_LOCALTRANSFORMATIONS = ReflectionUtil.getField(Regression.class, "localTransformations");
   Field REGRESSION_RESULTFIELDS = ReflectionUtil.getField(Regression.class, "resultFields");
   Field REGRESSION_REGRESSIONTABLES = ReflectionUtil.getField(Regression.class, "regressionTables");
   Field REGRESSIONMODEL_EXTENSIONS = ReflectionUtil.getField(RegressionModel.class, "extensions");
   Field REGRESSIONMODEL_MININGSCHEMA = ReflectionUtil.getField(RegressionModel.class, "miningSchema");
   Field REGRESSIONMODEL_OUTPUT = ReflectionUtil.getField(RegressionModel.class, "output");
   Field REGRESSIONMODEL_MODELSTATS = ReflectionUtil.getField(RegressionModel.class, "modelStats");
   Field REGRESSIONMODEL_MODELEXPLANATION = ReflectionUtil.getField(RegressionModel.class, "modelExplanation");
   Field REGRESSIONMODEL_TARGETS = ReflectionUtil.getField(RegressionModel.class, "targets");
   Field REGRESSIONMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(RegressionModel.class, "localTransformations");
   Field REGRESSIONMODEL_REGRESSIONTABLES = ReflectionUtil.getField(RegressionModel.class, "regressionTables");
   Field REGRESSIONMODEL_MODELVERIFICATION = ReflectionUtil.getField(RegressionModel.class, "modelVerification");
   Field REGRESSIONTABLE_EXTENSIONS = ReflectionUtil.getField(RegressionTable.class, "extensions");
   Field REGRESSIONTABLE_NUMERICPREDICTORS = ReflectionUtil.getField(RegressionTable.class, "numericPredictors");
   Field REGRESSIONTABLE_CATEGORICALPREDICTORS = ReflectionUtil.getField(RegressionTable.class, "categoricalPredictors");
   Field REGRESSIONTABLE_PREDICTORTERMS = ReflectionUtil.getField(RegressionTable.class, "predictorTerms");
}
