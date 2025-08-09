package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class FPGrowth$ implements Serializable {
   public static final FPGrowth$ MODULE$ = new FPGrowth$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(FPGrowth$.class);
   }

   private FPGrowth$() {
   }
}
