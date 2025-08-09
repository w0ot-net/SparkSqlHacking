package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class FMRegressor$ implements DefaultParamsReadable, Serializable {
   public static final FMRegressor$ MODULE$ = new FMRegressor$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public FMRegressor load(final String path) {
      return (FMRegressor)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FMRegressor$.class);
   }

   private FMRegressor$() {
   }
}
