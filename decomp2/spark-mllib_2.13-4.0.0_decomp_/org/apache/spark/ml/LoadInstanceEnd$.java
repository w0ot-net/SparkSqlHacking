package org.apache.spark.ml;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LoadInstanceEnd$ implements Serializable {
   public static final LoadInstanceEnd$ MODULE$ = new LoadInstanceEnd$();

   public final String toString() {
      return "LoadInstanceEnd";
   }

   public LoadInstanceEnd apply() {
      return new LoadInstanceEnd();
   }

   public boolean unapply(final LoadInstanceEnd x$0) {
      return x$0 != null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LoadInstanceEnd$.class);
   }

   private LoadInstanceEnd$() {
   }
}
