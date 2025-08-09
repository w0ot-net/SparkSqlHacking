package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.tree.GBTClassifierParams$;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class GBTClassifier$ implements DefaultParamsReadable, Serializable {
   public static final GBTClassifier$ MODULE$ = new GBTClassifier$();
   private static final String[] supportedLossTypes;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      supportedLossTypes = GBTClassifierParams$.MODULE$.supportedLossTypes();
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public final String[] supportedLossTypes() {
      return supportedLossTypes;
   }

   public GBTClassifier load(final String path) {
      return (GBTClassifier)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GBTClassifier$.class);
   }

   private GBTClassifier$() {
   }
}
