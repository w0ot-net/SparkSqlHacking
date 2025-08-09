package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class FMRegressionModel$ implements MLReadable, Serializable {
   public static final FMRegressionModel$ MODULE$ = new FMRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new FMRegressionModel.FMRegressionModelReader();
   }

   public FMRegressionModel load(final String path) {
      return (FMRegressionModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FMRegressionModel$.class);
   }

   private FMRegressionModel$() {
   }
}
