package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MinHashLSHModel$ implements MLReadable, Serializable {
   public static final MinHashLSHModel$ MODULE$ = new MinHashLSHModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new MinHashLSHModel.MinHashLSHModelReader();
   }

   public MinHashLSHModel load(final String path) {
      return (MinHashLSHModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MinHashLSHModel$.class);
   }

   private MinHashLSHModel$() {
   }
}
