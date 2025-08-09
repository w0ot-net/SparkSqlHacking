package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class ClusteringEvaluator$ implements DefaultParamsReadable, Serializable {
   public static final ClusteringEvaluator$ MODULE$ = new ClusteringEvaluator$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public ClusteringEvaluator load(final String path) {
      return (ClusteringEvaluator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ClusteringEvaluator$.class);
   }

   private ClusteringEvaluator$() {
   }
}
