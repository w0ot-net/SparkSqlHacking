package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class CountVectorizer$ implements DefaultParamsReadable, Serializable {
   public static final CountVectorizer$ MODULE$ = new CountVectorizer$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public CountVectorizer load(final String path) {
      return (CountVectorizer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CountVectorizer$.class);
   }

   private CountVectorizer$() {
   }
}
