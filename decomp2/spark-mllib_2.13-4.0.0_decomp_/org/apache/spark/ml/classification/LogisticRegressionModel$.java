package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class LogisticRegressionModel$ implements MLReadable, Serializable {
   public static final LogisticRegressionModel$ MODULE$ = new LogisticRegressionModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new LogisticRegressionModel.LogisticRegressionModelReader();
   }

   public LogisticRegressionModel load(final String path) {
      return (LogisticRegressionModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LogisticRegressionModel$.class);
   }

   private LogisticRegressionModel$() {
   }
}
