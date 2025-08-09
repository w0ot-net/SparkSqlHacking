package org.tukaani.xz.simple;

import org.tukaani.xz.common.ByteArrayView;

public final class PowerPC implements SimpleFilter {
   private final boolean isEncoder;
   private int pos;

   public PowerPC(boolean isEncoder, int startPos) {
      this.isEncoder = isEncoder;
      this.pos = startPos;
   }

   public int code(byte[] buf, int off, int len) {
      int end = off + len - 4;

      int i;
      for(i = off; i <= end; i += 4) {
         if ((buf[i] & 252) == 72 && (buf[i + 3] & 3) == 1) {
            int instr = ByteArrayView.getIntBE(buf, i);
            int pc = this.pos + i - off;
            if (!this.isEncoder) {
               pc = -pc;
            }

            instr = 1207959553 | instr + pc & 67108860;
            ByteArrayView.setIntBE(buf, i, instr);
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
