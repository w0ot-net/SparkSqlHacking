package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class GeneralizedLinearRegressionModel$ implements MLReadable, Serializable {
   public static final GeneralizedLinearRegressionModel$ MODULE$ = new GeneralizedLinearRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new GeneralizedLinearRegressionModel.GeneralizedLinearRegressionModelReader();
   }

   public GeneralizedLinearRegressionModel load(final String path) {
      return (GeneralizedLinearRegressionModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GeneralizedLinearRegressionModel$.class);
   }

   private GeneralizedLinearRegressionModel$() {
   }
}
