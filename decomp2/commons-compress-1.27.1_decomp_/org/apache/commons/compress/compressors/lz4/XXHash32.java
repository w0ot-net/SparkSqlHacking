package org.apache.commons.compress.compressors.lz4;

/** @deprecated */
@Deprecated
public class XXHash32 extends org.apache.commons.codec.digest.XXHash32 {
   public XXHash32() {
      super(0);
   }

   public XXHash32(int seed) {
      super(seed);
   }
}
