package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class Word2Vec$ implements DefaultParamsReadable, Serializable {
   public static final Word2Vec$ MODULE$ = new Word2Vec$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public Word2Vec load(final String path) {
      return (Word2Vec)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Word2Vec$.class);
   }

   private Word2Vec$() {
   }
}
