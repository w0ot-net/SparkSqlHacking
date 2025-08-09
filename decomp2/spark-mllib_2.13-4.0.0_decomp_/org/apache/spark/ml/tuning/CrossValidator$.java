package org.apache.spark.ml.tuning;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class CrossValidator$ implements MLReadable, Serializable {
   public static final CrossValidator$ MODULE$ = new CrossValidator$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new CrossValidator.CrossValidatorReader();
   }

   public CrossValidator load(final String path) {
      return (CrossValidator)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CrossValidator$.class);
   }

   private CrossValidator$() {
   }
}
