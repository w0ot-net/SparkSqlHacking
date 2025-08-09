package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class FMClassifier$ implements DefaultParamsReadable, Serializable {
   public static final FMClassifier$ MODULE$ = new FMClassifier$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public FMClassifier load(final String path) {
      return (FMClassifier)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FMClassifier$.class);
   }

   private FMClassifier$() {
   }
}
