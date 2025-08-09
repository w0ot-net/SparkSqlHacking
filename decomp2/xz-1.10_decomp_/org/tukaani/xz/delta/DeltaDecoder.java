package org.tukaani.xz.delta;

public class DeltaDecoder extends DeltaCoder {
   public DeltaDecoder(int distance) {
      super(distance);
   }

   public void decode(byte[] buf, int off, int len) {
      int i = 0;

      for(int j = Math.min(len, this.distance); i < j; ++i) {
         buf[off + i] += this.history[i];
      }

      while(i < len) {
         buf[off + i] += buf[off + i - this.distance];
         ++i;
      }

      if (len >= this.distance) {
         System.arraycopy(buf, off + len - this.distance, this.history, 0, this.distance);
      } else {
         assert i == len;

         System.arraycopy(this.history, i, this.history, 0, this.distance - i);
         System.arraycopy(buf, off, this.history, this.distance - i, len);
      }

   }
}
