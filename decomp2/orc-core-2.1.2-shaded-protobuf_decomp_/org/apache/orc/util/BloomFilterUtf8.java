package org.apache.orc.util;

import java.nio.charset.StandardCharsets;

public class BloomFilterUtf8 extends BloomFilter {
   public BloomFilterUtf8(long expectedEntries, double fpp) {
      super(expectedEntries, fpp);
   }

   public BloomFilterUtf8(long[] bits, int numFuncs) {
      super(bits, numFuncs);
   }

   public void addString(String val) {
      if (val == null) {
         this.add((byte[])null);
      } else {
         this.add(val.getBytes(StandardCharsets.UTF_8));
      }

   }

   public boolean testString(String val) {
      return val == null ? this.test((byte[])null) : this.test(val.getBytes(StandardCharsets.UTF_8));
   }
}
