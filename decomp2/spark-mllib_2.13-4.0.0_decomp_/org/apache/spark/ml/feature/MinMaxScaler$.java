package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MinMaxScaler$ implements DefaultParamsReadable, Serializable {
   public static final MinMaxScaler$ MODULE$ = new MinMaxScaler$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public MinMaxScaler load(final String path) {
      return (MinMaxScaler)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MinMaxScaler$.class);
   }

   private MinMaxScaler$() {
   }
}
