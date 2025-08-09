package org.apache.spark.ml.clustering;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class DistributedLDAModel$ implements MLReadable, Serializable {
   public static final DistributedLDAModel$ MODULE$ = new DistributedLDAModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new DistributedLDAModel.DistributedLDAModelReader();
   }

   public DistributedLDAModel load(final String path) {
      return (DistributedLDAModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DistributedLDAModel$.class);
   }

   private DistributedLDAModel$() {
   }
}
