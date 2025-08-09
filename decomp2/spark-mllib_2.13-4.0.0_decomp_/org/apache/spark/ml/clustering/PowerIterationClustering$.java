package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class PowerIterationClustering$ implements DefaultParamsReadable, Serializable {
   public static final PowerIterationClustering$ MODULE$ = new PowerIterationClustering$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public PowerIterationClustering load(final String path) {
      return (PowerIterationClustering)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PowerIterationClustering$.class);
   }

   private PowerIterationClustering$() {
   }
}
