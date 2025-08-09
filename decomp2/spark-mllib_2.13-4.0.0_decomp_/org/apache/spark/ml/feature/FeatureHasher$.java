package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.spark.SparkException;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.unsafe.hash.Murmur3_x86_32;
import org.apache.spark.unsafe.types.UTF8String;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FeatureHasher$ implements DefaultParamsReadable, Serializable {
   public static final FeatureHasher$ MODULE$ = new FeatureHasher$();
   private static final int seed;

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
      seed = org.apache.spark.mllib.feature.HashingTF$.MODULE$.seed();
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   public FeatureHasher load(final String path) {
      return (FeatureHasher)MLReadable.load$(this, path);
   }

   private int seed() {
      return seed;
   }

   public int murmur3Hash(final Object term) {
      if (term == null) {
         return this.seed();
      } else if (term instanceof Boolean) {
         boolean var4 = BoxesRunTime.unboxToBoolean(term);
         return Murmur3_x86_32.hashInt(var4 ? 1 : 0, this.seed());
      } else if (term instanceof Byte) {
         byte var5 = BoxesRunTime.unboxToByte(term);
         return Murmur3_x86_32.hashInt(var5, this.seed());
      } else if (term instanceof Short) {
         short var6 = BoxesRunTime.unboxToShort(term);
         return Murmur3_x86_32.hashInt(var6, this.seed());
      } else if (term instanceof Integer) {
         int var7 = BoxesRunTime.unboxToInt(term);
         return Murmur3_x86_32.hashInt(var7, this.seed());
      } else if (term instanceof Long) {
         long var8 = BoxesRunTime.unboxToLong(term);
         return Murmur3_x86_32.hashLong(var8, this.seed());
      } else if (term instanceof Float) {
         float var10 = BoxesRunTime.unboxToFloat(term);
         return Murmur3_x86_32.hashInt(Float.floatToIntBits(var10), this.seed());
      } else if (term instanceof Double) {
         double var11 = BoxesRunTime.unboxToDouble(term);
         return Murmur3_x86_32.hashLong(Double.doubleToLongBits(var11), this.seed());
      } else if (term instanceof String) {
         String var13 = (String)term;
         UTF8String utf8 = UTF8String.fromString(var13);
         return Murmur3_x86_32.hashUnsafeBytes2(utf8.getBaseObject(), utf8.getBaseOffset(), utf8.numBytes(), this.seed());
      } else {
         throw new SparkException("FeatureHasher with murmur3 algorithm does not support type " + term.getClass().getCanonicalName() + " of input data.");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FeatureHasher$.class);
   }

   private FeatureHasher$() {
   }
}
