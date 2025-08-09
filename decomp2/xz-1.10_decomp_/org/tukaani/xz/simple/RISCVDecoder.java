package org.tukaani.xz.simple;

import org.tukaani.xz.common.ByteArrayView;

public final class RISCVDecoder implements SimpleFilter {
   private int pos;

   public RISCVDecoder(int startPos) {
      this.pos = startPos;
   }

   public int code(byte[] buf, int off, int len) {
      int end = off + len - 8;

      int i;
      for(i = off; i <= end; i += 2) {
         int inst = buf[i] & 255;
         if (inst == 239) {
            int b1 = buf[i + 1] & 255;
            if ((b1 & 13) == 0) {
               int b2 = buf[i + 2] & 255;
               int b3 = buf[i + 3] & 255;
               int pc = this.pos + i - off;
               int addr = (b1 & 240) << 13 | b2 << 9 | b3 << 1;
               addr -= pc;
               buf[i + 1] = (byte)(b1 & 15 | addr >>> 8 & 240);
               buf[i + 2] = (byte)(addr >>> 16 & 15 | addr >>> 7 & 16 | addr << 4 & 224);
               buf[i + 3] = (byte)(addr >>> 4 & 127 | addr >>> 13 & 128);
               i += 2;
            }
         } else if ((inst & 127) == 23) {
            inst |= (buf[i + 1] & 255) << 8;
            inst |= (buf[i + 2] & 255) << 16;
            inst |= (buf[i + 3] & 255) << 24;
            int inst2;
            if ((inst & 3712) != 0) {
               inst2 = ByteArrayView.getIntLE(buf, i + 4);
               if (((inst << 8 ^ inst2) & 1015811) != 3) {
                  i += 4;
                  continue;
               }

               int addr = (inst & -4096) + (inst2 >>> 20);
               inst = 279 | inst2 << 12;
               inst2 = addr;
            } else {
               int inst2Rs1 = inst >>> 27;
               if ((inst - 12544 & 16256) >= (inst2Rs1 & 29)) {
                  i += 2;
                  continue;
               }

               int addr = ByteArrayView.getIntBE(buf, i + 4);
               addr -= this.pos + i - off;
               inst2 = inst >>> 12 | addr << 20;
               inst = 23 | inst2Rs1 << 7 | addr + 2048 & -4096;
            }

            ByteArrayView.setIntLE(buf, i, inst);
            ByteArrayView.setIntLE(buf, i + 4, inst2);
            i += 6;
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
