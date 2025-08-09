package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.util.Loader;
import scala.runtime.ModuleSerializationProxy;

public final class FPGrowthModel$ implements Loader, Serializable {
   public static final FPGrowthModel$ MODULE$ = new FPGrowthModel$();

   public FPGrowthModel load(final SparkContext sc, final String path) {
      return FPGrowthModel.SaveLoadV1_0$.MODULE$.load(sc, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FPGrowthModel$.class);
   }

   private FPGrowthModel$() {
   }
}
