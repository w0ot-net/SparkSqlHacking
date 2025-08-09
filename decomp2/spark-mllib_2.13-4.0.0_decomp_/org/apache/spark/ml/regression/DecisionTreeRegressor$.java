package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.tree.HasVarianceImpurity$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class DecisionTreeRegressor$ implements DefaultParamsReadable, Serializable {
   public static final DecisionTreeRegressor$ MODULE$ = new DecisionTreeRegressor$();
   private static final String[] supportedImpurities;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedImpurities = HasVarianceImpurity$.MODULE$.supportedImpurities();
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public final String[] supportedImpurities() {
      return supportedImpurities;
   }

   public DecisionTreeRegressor load(final String path) {
      return (DecisionTreeRegressor)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeRegressor$.class);
   }

   private DecisionTreeRegressor$() {
   }
}
