package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class StandardScaler$ implements DefaultParamsReadable, Serializable {
   public static final StandardScaler$ MODULE$ = new StandardScaler$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public StandardScaler load(final String path) {
      return (StandardScaler)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StandardScaler$.class);
   }

   private StandardScaler$() {
   }
}
