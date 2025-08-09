package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class AFTSurvivalRegression$ implements DefaultParamsReadable, Serializable {
   public static final AFTSurvivalRegression$ MODULE$ = new AFTSurvivalRegression$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public AFTSurvivalRegression load(final String path) {
      return (AFTSurvivalRegression)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AFTSurvivalRegression$.class);
   }

   private AFTSurvivalRegression$() {
   }
}
