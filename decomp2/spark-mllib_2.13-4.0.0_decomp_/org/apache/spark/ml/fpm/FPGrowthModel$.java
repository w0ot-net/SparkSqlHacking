package org.apache.spark.ml.fpm;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class FPGrowthModel$ implements MLReadable, Serializable {
   public static final FPGrowthModel$ MODULE$ = new FPGrowthModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new FPGrowthModel.FPGrowthModelReader();
   }

   public FPGrowthModel load(final String path) {
      return (FPGrowthModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FPGrowthModel$.class);
   }

   private FPGrowthModel$() {
   }
}
