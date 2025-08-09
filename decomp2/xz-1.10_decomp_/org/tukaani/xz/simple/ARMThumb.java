package org.tukaani.xz.simple;

public final class ARMThumb implements SimpleFilter {
   private final boolean isEncoder;
   private int pos;

   public ARMThumb(boolean isEncoder, int startPos) {
      this.isEncoder = isEncoder;
      this.pos = startPos + 4;
   }

   public int code(byte[] buf, int off, int len) {
      int end = off + len - 4;

      int i;
      for(i = off; i <= end; i += 2) {
         if ((buf[i + 1] & 248) == 240 && (buf[i + 3] & 248) == 248) {
            int src = (buf[i + 1] & 7) << 19 | (buf[i] & 255) << 11 | (buf[i + 3] & 7) << 8 | buf[i + 2] & 255;
            src <<= 1;
            int dest;
            if (this.isEncoder) {
               dest = src + (this.pos + i - off);
            } else {
               dest = src - (this.pos + i - off);
            }

            dest >>>= 1;
            buf[i + 1] = (byte)(240 | dest >>> 19 & 7);
            buf[i] = (byte)(dest >>> 11);
            buf[i + 3] = (byte)(248 | dest >>> 8 & 7);
            buf[i + 2] = (byte)dest;
            i += 2;
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
