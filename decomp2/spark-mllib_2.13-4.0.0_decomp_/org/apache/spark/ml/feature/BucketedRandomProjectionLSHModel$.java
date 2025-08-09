package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class BucketedRandomProjectionLSHModel$ implements MLReadable, Serializable {
   public static final BucketedRandomProjectionLSHModel$ MODULE$ = new BucketedRandomProjectionLSHModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new BucketedRandomProjectionLSHModel.BucketedRandomProjectionLSHModelReader();
   }

   public BucketedRandomProjectionLSHModel load(final String path) {
      return (BucketedRandomProjectionLSHModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BucketedRandomProjectionLSHModel$.class);
   }

   private BucketedRandomProjectionLSHModel$() {
   }
}
