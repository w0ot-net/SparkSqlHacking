package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RegressionEvaluator$ implements DefaultParamsReadable, Serializable {
   public static final RegressionEvaluator$ MODULE$ = new RegressionEvaluator$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public RegressionEvaluator load(final String path) {
      return (RegressionEvaluator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RegressionEvaluator$.class);
   }

   private RegressionEvaluator$() {
   }
}
