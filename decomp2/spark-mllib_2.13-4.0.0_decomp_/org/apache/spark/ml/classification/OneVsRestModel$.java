package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class OneVsRestModel$ implements MLReadable, Serializable {
   public static final OneVsRestModel$ MODULE$ = new OneVsRestModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new OneVsRestModel.OneVsRestModelReader();
   }

   public OneVsRestModel load(final String path) {
      return (OneVsRestModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneVsRestModel$.class);
   }

   private OneVsRestModel$() {
   }
}
