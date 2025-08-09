package org.apache.datasketches.hash;

import org.apache.datasketches.memory.Memory;

public class XxHash {
   public static long hash(Memory mem, long offsetBytes, long lengthBytes, long seed) {
      return mem.xxHash64(offsetBytes, lengthBytes, seed);
   }

   public static long hash(long in, long seed) {
      return org.apache.datasketches.memory.XxHash.hashLong(in, seed);
   }
}
