package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class IsotonicRegressionModel$ implements MLReadable, Serializable {
   public static final IsotonicRegressionModel$ MODULE$ = new IsotonicRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new IsotonicRegressionModel.IsotonicRegressionModelReader();
   }

   public IsotonicRegressionModel load(final String path) {
      return (IsotonicRegressionModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IsotonicRegressionModel$.class);
   }

   private IsotonicRegressionModel$() {
   }
}
