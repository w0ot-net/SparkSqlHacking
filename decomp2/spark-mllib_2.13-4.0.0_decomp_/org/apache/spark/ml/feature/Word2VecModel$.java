package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class Word2VecModel$ implements MLReadable, Serializable {
   public static final Word2VecModel$ MODULE$ = new Word2VecModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new Word2VecModel.Word2VecModelReader();
   }

   public Word2VecModel load(final String path) {
      return (Word2VecModel)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Word2VecModel$.class);
   }

   private Word2VecModel$() {
   }
}
