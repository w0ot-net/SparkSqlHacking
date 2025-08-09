package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class AFTSurvivalRegressionModel$ implements MLReadable, Serializable {
   public static final AFTSurvivalRegressionModel$ MODULE$ = new AFTSurvivalRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new AFTSurvivalRegressionModel.AFTSurvivalRegressionModelReader();
   }

   public AFTSurvivalRegressionModel load(final String path) {
      return (AFTSurvivalRegressionModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AFTSurvivalRegressionModel$.class);
   }

   private AFTSurvivalRegressionModel$() {
   }
}
