package org.antlr.v4.runtime.misc;

public final class MurmurHash {
   private static final int DEFAULT_SEED = 0;

   public static int initialize() {
      return initialize(0);
   }

   public static int initialize(int seed) {
      return seed;
   }

   public static int update(int hash, int value) {
      int c1 = -862048943;
      int c2 = 461845907;
      int r1 = 15;
      int r2 = 13;
      int m = 5;
      int n = -430675100;
      int k = value * -862048943;
      k = k << 15 | k >>> 17;
      k *= 461845907;
      hash ^= k;
      hash = hash << 13 | hash >>> 19;
      hash = hash * 5 + -430675100;
      return hash;
   }

   public static int update(int hash, Object value) {
      return update(hash, value != null ? value.hashCode() : 0);
   }

   public static int finish(int hash, int numberOfWords) {
      hash ^= numberOfWords * 4;
      hash ^= hash >>> 16;
      hash *= -2048144789;
      hash ^= hash >>> 13;
      hash *= -1028477387;
      hash ^= hash >>> 16;
      return hash;
   }

   public static int hashCode(Object[] data, int seed) {
      int hash = initialize(seed);

      for(Object value : data) {
         hash = update(hash, value);
      }

      hash = finish(hash, data.length);
      return hash;
   }

   private MurmurHash() {
   }
}
