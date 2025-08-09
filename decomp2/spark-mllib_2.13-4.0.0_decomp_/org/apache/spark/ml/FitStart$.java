package org.apache.spark.ml;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class FitStart$ implements Serializable {
   public static final FitStart$ MODULE$ = new FitStart$();

   public final String toString() {
      return "FitStart";
   }

   public FitStart apply() {
      return new FitStart();
   }

   public boolean unapply(final FitStart x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FitStart$.class);
   }

   private FitStart$() {
   }
}
