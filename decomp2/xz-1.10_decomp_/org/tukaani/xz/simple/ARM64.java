package org.tukaani.xz.simple;

import org.tukaani.xz.common.ByteArrayView;

public final class ARM64 implements SimpleFilter {
   private final boolean isEncoder;
   private int pos;

   public ARM64(boolean isEncoder, int startPos) {
      this.isEncoder = isEncoder;
      this.pos = startPos;
   }

   public int code(byte[] buf, int off, int len) {
      int end = off + len - 4;

      int i;
      for(i = off; i <= end; i += 4) {
         int instr = buf[i + 3];
         if ((instr & 252) == 148) {
            instr = ByteArrayView.getIntLE(buf, i);
            int pc = this.pos + i - off >>> 2;
            if (!this.isEncoder) {
               pc = -pc;
            }

            instr = -1811939328 | instr + pc & 67108863;
            ByteArrayView.setIntLE(buf, i, instr);
         } else if ((instr & 159) == 144) {
            instr = ByteArrayView.getIntLE(buf, i);
            int src = instr >>> 29 & 3 | instr >>> 3 & 2097148;
            if ((src + 131072 & 1835008) == 0) {
               int pc = this.pos + i - off >>> 12;
               if (!this.isEncoder) {
                  pc = -pc;
               }

               int dest = src + pc;
               instr &= -1879048161;
               instr |= (dest & 3) << 29;
               instr |= (dest & 262140) << 3;
               instr |= -(dest & 131072) & 14680064;
               ByteArrayView.setIntLE(buf, i, instr);
            }
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
