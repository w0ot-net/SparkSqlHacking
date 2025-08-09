package org.tukaani.xz.delta;

public class DeltaEncoder extends DeltaCoder {
   public DeltaEncoder(int distance) {
      super(distance);
   }

   public void encode(byte[] in, int in_off, int len, byte[] out) {
      int i = 0;

      for(int j = Math.min(len, this.distance); i < j; ++i) {
         out[i] = (byte)(in[in_off + i] - this.history[i]);
      }

      if (len >= this.distance) {
         System.arraycopy(in, in_off + len - this.distance, this.history, 0, this.distance);
      } else {
         assert i == len;

         System.arraycopy(this.history, i, this.history, 0, this.distance - i);
         System.arraycopy(in, in_off, this.history, this.distance - i, len);
      }

      while(i < len) {
         out[i] = (byte)(in[in_off + i] - in[in_off + i - this.distance]);
         ++i;
      }

   }
}
