package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class BisectingKMeans$ implements DefaultParamsReadable, Serializable {
   public static final BisectingKMeans$ MODULE$ = new BisectingKMeans$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public BisectingKMeans load(final String path) {
      return (BisectingKMeans)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BisectingKMeans$.class);
   }

   private BisectingKMeans$() {
   }
}
