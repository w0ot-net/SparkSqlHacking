package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class CountVectorizerModel$ implements MLReadable, Serializable {
   public static final CountVectorizerModel$ MODULE$ = new CountVectorizerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new CountVectorizerModel.CountVectorizerModelReader();
   }

   public CountVectorizerModel load(final String path) {
      return (CountVectorizerModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CountVectorizerModel$.class);
   }

   private CountVectorizerModel$() {
   }
}
