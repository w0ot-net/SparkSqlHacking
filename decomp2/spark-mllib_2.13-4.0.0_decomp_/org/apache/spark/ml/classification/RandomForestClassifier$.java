package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.tree.TreeClassifierParams$;
import org.apache.spark.ml.tree.TreeEnsembleParams$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RandomForestClassifier$ implements DefaultParamsReadable, Serializable {
   public static final RandomForestClassifier$ MODULE$ = new RandomForestClassifier$();
   private static final String[] supportedImpurities;
   private static final String[] supportedFeatureSubsetStrategies;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedImpurities = TreeClassifierParams$.MODULE$.supportedImpurities();
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

   public RandomForestClassifier load(final String path) {
      return (RandomForestClassifier)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomForestClassifier$.class);
   }

   private RandomForestClassifier$() {
   }
}
