package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import scala.runtime.ModuleSerializationProxy;

public final class PowerIterationClusteringModel$ implements Loader, Serializable {
   public static final PowerIterationClusteringModel$ MODULE$ = new PowerIterationClusteringModel$();

   public PowerIterationClusteringModel load(final SparkContext sc, final String path) {
      return PowerIterationClusteringModel.SaveLoadV1_0$.MODULE$.load(sc, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PowerIterationClusteringModel$.class);
   }

   private PowerIterationClusteringModel$() {
   }
}
