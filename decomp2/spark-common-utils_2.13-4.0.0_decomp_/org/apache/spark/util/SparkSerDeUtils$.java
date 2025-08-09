package org.apache.spark.util;

public final class SparkSerDeUtils$ implements SparkSerDeUtils {
   public static final SparkSerDeUtils$ MODULE$ = new SparkSerDeUtils$();

   static {
      SparkSerDeUtils.$init$(MODULE$);
   }

   public byte[] serialize(final Object o) {
      return SparkSerDeUtils.serialize$(this, o);
   }

   public Object deserialize(final byte[] bytes) {
      return SparkSerDeUtils.deserialize$(this, bytes);
   }

   public Object deserialize(final byte[] bytes, final ClassLoader loader) {
      return SparkSerDeUtils.deserialize$(this, bytes, loader);
   }

   private SparkSerDeUtils$() {
   }
}
