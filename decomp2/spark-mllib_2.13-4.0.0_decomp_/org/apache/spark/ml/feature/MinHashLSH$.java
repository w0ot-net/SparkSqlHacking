package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class MinHashLSH$ implements DefaultParamsReadable, Serializable {
   public static final MinHashLSH$ MODULE$ = new MinHashLSH$();
   private static final int HASH_PRIME;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      HASH_PRIME = 2038074743;
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public int HASH_PRIME() {
      return HASH_PRIME;
   }

   public MinHashLSH load(final String path) {
      return (MinHashLSH)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MinHashLSH$.class);
   }

   private MinHashLSH$() {
   }
}
