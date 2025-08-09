package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.runtime.ModuleSerializationProxy;

public final class HashingTF$ implements DefaultParamsReadable, Serializable {
   public static final HashingTF$ MODULE$ = new HashingTF$();
   private static final int SPARK_2_MURMUR3_HASH;
   private static final int SPARK_3_MURMUR3_HASH;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      SPARK_2_MURMUR3_HASH = 1;
      SPARK_3_MURMUR3_HASH = 2;
   }

   public int SPARK_2_MURMUR3_HASH() {
      return SPARK_2_MURMUR3_HASH;
   }

   public int SPARK_3_MURMUR3_HASH() {
      return SPARK_3_MURMUR3_HASH;
   }

   public MLReader read() {
      return new HashingTF.HashingTFReader();
   }

   public HashingTF load(final String path) {
      return (HashingTF)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashingTF$.class);
   }

   private HashingTF$() {
   }
}
