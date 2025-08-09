package org.tukaani.xz.check;

import org.tukaani.xz.common.ByteArrayView;

public class CRC64 extends Check {
   private static final long[][] TABLE = new long[4][256];
   private long crc = -1L;

   public CRC64() {
      this.size = 8;
      this.name = "CRC64";
   }

   public void update(byte[] buf, int off, int len) {
      int end = off + len;
      int i = off;

      for(int end4 = end - 3; i < end4; i += 4) {
         int tmp = (int)this.crc;
         this.crc = TABLE[3][tmp & 255 ^ buf[i] & 255] ^ TABLE[2][tmp >>> 8 & 255 ^ buf[i + 1] & 255] ^ this.crc >>> 32 ^ TABLE[1][tmp >>> 16 & 255 ^ buf[i + 2] & 255] ^ TABLE[0][tmp >>> 24 & 255 ^ buf[i + 3] & 255];
      }

      while(i < end) {
         this.crc = TABLE[0][buf[i++] & 255 ^ (int)this.crc & 255] ^ this.crc >>> 8;
      }

   }

   public byte[] finish() {
      byte[] buf = new byte[8];
      ByteArrayView.setLongLE(buf, 0, ~this.crc);
      this.crc = -1L;
      return buf;
   }

   static {
      long poly64 = -3932672073523589310L;

      for(int s = 0; s < 4; ++s) {
         for(int b = 0; b < 256; ++b) {
            long r = s == 0 ? (long)b : TABLE[s - 1][b];

            for(int i = 0; i < 8; ++i) {
               if ((r & 1L) == 1L) {
                  r = r >>> 1 ^ -3932672073523589310L;
               } else {
                  r >>>= 1;
               }
            }

            TABLE[s][b] = r;
         }
      }

   }
}
