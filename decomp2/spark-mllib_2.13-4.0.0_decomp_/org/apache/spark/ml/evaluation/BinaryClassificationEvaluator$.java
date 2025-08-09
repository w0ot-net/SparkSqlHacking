package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class BinaryClassificationEvaluator$ implements DefaultParamsReadable, Serializable {
   public static final BinaryClassificationEvaluator$ MODULE$ = new BinaryClassificationEvaluator$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public BinaryClassificationEvaluator load(final String path) {
      return (BinaryClassificationEvaluator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BinaryClassificationEvaluator$.class);
   }

   private BinaryClassificationEvaluator$() {
   }
}
