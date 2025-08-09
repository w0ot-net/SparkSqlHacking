package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class IDFModel$ implements MLReadable, Serializable {
   public static final IDFModel$ MODULE$ = new IDFModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new IDFModel.IDFModelReader();
   }

   public IDFModel load(final String path) {
      return (IDFModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IDFModel$.class);
   }

   private IDFModel$() {
   }
}
