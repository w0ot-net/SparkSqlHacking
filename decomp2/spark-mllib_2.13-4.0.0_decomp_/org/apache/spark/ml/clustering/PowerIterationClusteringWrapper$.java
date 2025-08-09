package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class PowerIterationClusteringWrapper$ implements DefaultParamsReadable, Serializable {
   public static final PowerIterationClusteringWrapper$ MODULE$ = new PowerIterationClusteringWrapper$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public PowerIterationClusteringWrapper load(final String path) {
      PowerIterationClustering pic = PowerIterationClustering$.MODULE$.load(path);
      return (new PowerIterationClusteringWrapper(pic.uid())).copy(pic.paramMap());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PowerIterationClusteringWrapper$.class);
   }

   private PowerIterationClusteringWrapper$() {
   }
}
