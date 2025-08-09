package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class LinearRegressionModel$ implements MLReadable, Serializable {
   public static final LinearRegressionModel$ MODULE$ = new LinearRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new LinearRegressionModel.LinearRegressionModelReader();
   }

   public LinearRegressionModel load(final String path) {
      return (LinearRegressionModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LinearRegressionModel$.class);
   }

   private LinearRegressionModel$() {
   }
}
