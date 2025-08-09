package org.apache.spark.ml;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class FitEnd$ implements Serializable {
   public static final FitEnd$ MODULE$ = new FitEnd$();

   public final String toString() {
      return "FitEnd";
   }

   public FitEnd apply() {
      return new FitEnd();
   }

   public boolean unapply(final FitEnd x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FitEnd$.class);
   }

   private FitEnd$() {
   }
}
