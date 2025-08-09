package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class ChiSqSelector$ implements DefaultParamsReadable, Serializable {
   public static final ChiSqSelector$ MODULE$ = new ChiSqSelector$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public ChiSqSelector load(final String path) {
      return (ChiSqSelector)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ChiSqSelector$.class);
   }

   private ChiSqSelector$() {
   }
}
