package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class BucketedRandomProjectionLSH$ implements DefaultParamsReadable, Serializable {
   public static final BucketedRandomProjectionLSH$ MODULE$ = new BucketedRandomProjectionLSH$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public BucketedRandomProjectionLSH load(final String path) {
      return (BucketedRandomProjectionLSH)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BucketedRandomProjectionLSH$.class);
   }

   private BucketedRandomProjectionLSH$() {
   }
}
