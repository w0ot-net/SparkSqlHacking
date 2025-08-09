package org.apache.spark.mllib.pmml.export;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.regression.LassoModel;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.RidgeRegressionModel;
import org.sparkproject.dmg.pmml.regression.RegressionModel;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class PMMLModelExportFactory$ {
   public static final PMMLModelExportFactory$ MODULE$ = new PMMLModelExportFactory$();

   public PMMLModelExport createPMMLModelExport(final Object model) {
      if (model instanceof KMeansModel var4) {
         return new KMeansPMMLModelExport(var4);
      } else if (model instanceof LinearRegressionModel var5) {
         return new GeneralizedLinearPMMLModelExport(var5, "linear regression");
      } else if (model instanceof RidgeRegressionModel var6) {
         return new GeneralizedLinearPMMLModelExport(var6, "ridge regression");
      } else if (model instanceof LassoModel var7) {
         return new GeneralizedLinearPMMLModelExport(var7, "lasso regression");
      } else if (model instanceof SVMModel var8) {
         return new BinaryClassificationPMMLModelExport(var8, "linear SVM", RegressionModel.NormalizationMethod.NONE, BoxesRunTime.unboxToDouble(var8.getThreshold().getOrElse((JFunction0.mcD.sp)() -> (double)0.0F)));
      } else if (model instanceof LogisticRegressionModel var9) {
         if (var9.numClasses() == 2) {
            return new BinaryClassificationPMMLModelExport(var9, "logistic regression", RegressionModel.NormalizationMethod.LOGIT, BoxesRunTime.unboxToDouble(var9.getThreshold().getOrElse((JFunction0.mcD.sp)() -> (double)0.5F)));
         } else {
            throw new IllegalArgumentException("PMML Export not supported for Multinomial Logistic Regression");
         }
      } else {
         throw new IllegalArgumentException("PMML Export not supported for model: " + model.getClass().getName());
      }
   }

   private PMMLModelExportFactory$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
