package org.tukaani.xz.simple;

import org.tukaani.xz.common.ByteArrayView;

public final class SPARC implements SimpleFilter {
   private final boolean isEncoder;
   private int pos;

   public SPARC(boolean isEncoder, int startPos) {
      this.isEncoder = isEncoder;
      this.pos = startPos;
   }

   public int code(byte[] buf, int off, int len) {
      int end = off + len - 4;

      int i;
      for(i = off; i <= end; i += 4) {
         if (buf[i] == 64 && (buf[i + 1] & 192) == 0 || buf[i] == 127 && (buf[i + 1] & 192) == 192) {
            int src = ByteArrayView.getIntBE(buf, i);
            int pc = this.pos + i - off >>> 2;
            if (!this.isEncoder) {
               pc = -pc;
            }

            int dest = src + pc;
            dest <<= 9;
            dest >>= 9;
            dest = 1073741824 | dest & 1073741823;
            ByteArrayView.setIntBE(buf, i, dest);
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
