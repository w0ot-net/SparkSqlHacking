package org.apache.spark.ml.regression;

import java.io.Serializable;
import org.apache.spark.ml.tree.HasVarianceImpurity$;
import org.apache.spark.ml.tree.TreeEnsembleParams$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RandomForestRegressor$ implements DefaultParamsReadable, Serializable {
   public static final RandomForestRegressor$ MODULE$ = new RandomForestRegressor$();
   private static final String[] supportedImpurities;
   private static final String[] supportedFeatureSubsetStrategies;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedImpurities = HasVarianceImpurity$.MODULE$.supportedImpurities();
      supportedFeatureSubsetStrategies = TreeEnsembleParams$.MODULE$.supportedFeatureSubsetStrategies();
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public final String[] supportedImpurities() {
      return supportedImpurities;
   }

   public final String[] supportedFeatureSubsetStrategies() {
      return supportedFeatureSubsetStrategies;
   }

   public RandomForestRegressor load(final String path) {
      return (RandomForestRegressor)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForestRegressor$.class);
   }

   private RandomForestRegressor$() {
   }
}
