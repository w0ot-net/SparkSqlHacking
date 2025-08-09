package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class IsotonicRegression$ implements DefaultParamsReadable, Serializable {
   public static final IsotonicRegression$ MODULE$ = new IsotonicRegression$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public IsotonicRegression load(final String path) {
      return (IsotonicRegression)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IsotonicRegression$.class);
   }

   private IsotonicRegression$() {
   }
}
