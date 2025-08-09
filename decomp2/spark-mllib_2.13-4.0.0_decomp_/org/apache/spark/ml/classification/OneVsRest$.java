package org.apache.spark.ml.classification;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class OneVsRest$ implements MLReadable, Serializable {
   public static final OneVsRest$ MODULE$ = new OneVsRest$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new OneVsRest.OneVsRestReader();
   }

   public OneVsRest load(final String path) {
      return (OneVsRest)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneVsRest$.class);
   }

   private OneVsRest$() {
   }
}
