package org.apache.spark.ml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class LoadInstanceStart$ implements Serializable {
   public static final LoadInstanceStart$ MODULE$ = new LoadInstanceStart$();

   public final String toString() {
      return "LoadInstanceStart";
   }

   public LoadInstanceStart apply(final String path) {
      return new LoadInstanceStart(path);
   }

   public Option unapply(final LoadInstanceStart x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.path()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LoadInstanceStart$.class);
   }

   private LoadInstanceStart$() {
   }
}
