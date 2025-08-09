package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.tree.TreeClassifierParams$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class DecisionTreeClassifier$ implements DefaultParamsReadable, Serializable {
   public static final DecisionTreeClassifier$ MODULE$ = new DecisionTreeClassifier$();
   private static final String[] supportedImpurities;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedImpurities = TreeClassifierParams$.MODULE$.supportedImpurities();
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public final String[] supportedImpurities() {
      return supportedImpurities;
   }

   public DecisionTreeClassifier load(final String path) {
      return (DecisionTreeClassifier)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeClassifier$.class);
   }

   private DecisionTreeClassifier$() {
   }
}
