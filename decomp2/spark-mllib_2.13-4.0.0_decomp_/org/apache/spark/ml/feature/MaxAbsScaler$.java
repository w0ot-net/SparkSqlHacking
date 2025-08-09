package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MaxAbsScaler$ implements DefaultParamsReadable, Serializable {
   public static final MaxAbsScaler$ MODULE$ = new MaxAbsScaler$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public MaxAbsScaler load(final String path) {
      return (MaxAbsScaler)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MaxAbsScaler$.class);
   }

   private MaxAbsScaler$() {
   }
}
