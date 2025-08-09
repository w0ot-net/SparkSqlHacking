package org.apache.datasketches.frequencies;

final class Util {
   static final int LG_MIN_MAP_SIZE = 3;
   static final int SAMPLE_SIZE = 1024;

   private Util() {
   }

   static long hash(long key) {
      key ^= key >>> 33;
      key *= -49064778989728563L;
      key ^= key >>> 33;
      key *= -4265267296055464877L;
      key ^= key >>> 33;
      return key;
   }
}
