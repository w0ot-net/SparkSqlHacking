package com.clearspring.experimental.stream.cardinality;

import com.clearspring.analytics.hash.MurmurHash;
import com.clearspring.analytics.stream.cardinality.CardinalityMergeException;
import com.clearspring.analytics.stream.cardinality.ICardinality;
import java.io.IOException;

public class HyperBitBit implements ICardinality {
   int lgN = 5;
   long sketch = 0L;
   long sketch2 = 0L;

   public boolean offer(Object o) {
      long x = MurmurHash.hash64(o);
      return this.offerHashed(x);
   }

   public boolean offerHashed(long hashedLong) {
      long k = hashedLong << 58 >> 58;
      int r = Long.numberOfLeadingZeros(hashedLong >> 6) - 6;
      boolean modified = false;
      if (r > this.lgN) {
         modified = true;
         this.sketch |= 1L << (int)k;
      }

      if (r > this.lgN + 1) {
         modified = true;
         this.sketch2 |= 1L << (int)k;
      }

      if (Long.bitCount(this.sketch) > 31) {
         modified = true;
         this.sketch = this.sketch2;
         this.sketch2 = 0L;
         ++this.lgN;
      }

      return modified;
   }

   public boolean offerHashed(int hashedInt) {
      throw new UnsupportedOperationException();
   }

   public long cardinality() {
      double exponent = (double)this.lgN + 5.4 + (double)Long.bitCount(this.sketch) / (double)32.0F;
      return (long)Math.pow((double)2.0F, exponent);
   }

   public int sizeof() {
      return 0;
   }

   public byte[] getBytes() throws IOException {
      return new byte[0];
   }

   public ICardinality merge(ICardinality... estimators) throws CardinalityMergeException {
      throw new HyperBitBitMergeException("Cannot merge estimators of HyperBitBit class");
   }

   static class HyperBitBitMergeException extends CardinalityMergeException {
      public HyperBitBitMergeException(String message) {
         super(message);
      }
   }
}
