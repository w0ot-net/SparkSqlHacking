package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class RegexTokenizer$ implements DefaultParamsReadable, Serializable {
   public static final RegexTokenizer$ MODULE$ = new RegexTokenizer$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public RegexTokenizer load(final String path) {
      return (RegexTokenizer)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RegexTokenizer$.class);
   }

   private RegexTokenizer$() {
   }
}
