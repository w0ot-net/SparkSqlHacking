package org.apache.spark.mllib.feature;

import java.io.Serializable;
import org.apache.spark.SparkException;
import org.apache.spark.unsafe.hash.Murmur3_x86_32;
import org.apache.spark.unsafe.types.UTF8String;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class HashingTF$ implements Serializable {
   public static final HashingTF$ MODULE$ = new HashingTF$();
   private static final String Native = "native";
   private static final String Murmur3 = "murmur3";
   private static final int seed = 42;

   public String Native() {
      return Native;
   }

   public String Murmur3() {
      return Murmur3;
   }

   public int seed() {
      return seed;
   }

   public int nativeHash(final Object term) {
      return Statics.anyHash(term);
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
         return Murmur3_x86_32.hashUnsafeBytes(utf8.getBaseObject(), utf8.getBaseOffset(), utf8.numBytes(), this.seed());
      } else {
         throw new SparkException("HashingTF with murmur3 algorithm does not support type " + term.getClass().getCanonicalName() + " of input data.");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashingTF$.class);
   }

   private HashingTF$() {
   }
}
