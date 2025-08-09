package org.sparkproject.dmg.pmml.regression;

import java.util.List;
import org.sparkproject.dmg.pmml.PMMLObject;

public interface HasRegressionTables {
   RegressionModel.NormalizationMethod getNormalizationMethod();

   PMMLObject setNormalizationMethod(RegressionModel.NormalizationMethod var1);

   boolean hasRegressionTables();

   List requireRegressionTables();

   List getRegressionTables();

   PMMLObject addRegressionTables(RegressionTable... var1);
}
