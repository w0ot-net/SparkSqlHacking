package org.apache.spark.ml.fpm;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class FPGrowth$ implements DefaultParamsReadable, Serializable {
   public static final FPGrowth$ MODULE$ = new FPGrowth$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public FPGrowth load(final String path) {
      return (FPGrowth)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FPGrowth$.class);
   }

   private FPGrowth$() {
   }
}
