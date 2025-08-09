package org.tukaani.xz.simple;

import org.tukaani.xz.common.ByteArrayView;

public final class RISCVEncoder implements SimpleFilter {
   private int pos;

   public RISCVEncoder(int startPos) {
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
               int addr = (b1 & 240) << 8 | (b2 & 15) << 16 | (b2 & 16) << 7 | (b2 & 224) >>> 4 | (b3 & 127) << 4 | (b3 & 128) << 13;
               addr += pc;
               buf[i + 1] = (byte)(b1 & 15 | addr >>> 13 & 240);
               buf[i + 2] = (byte)(addr >>> 9);
               buf[i + 3] = (byte)(addr >>> 1);
               i += 2;
            }
         } else if ((inst & 127) == 23) {
            inst |= (buf[i + 1] & 255) << 8;
            inst |= (buf[i + 2] & 255) << 16;
            inst |= (buf[i + 3] & 255) << 24;
            if ((inst & 3712) != 0) {
               int inst2 = ByteArrayView.getIntLE(buf, i + 4);
               if (((inst << 8 ^ inst2) & 1015811) != 3) {
                  i += 4;
                  continue;
               }

               int addr = (inst & -4096) + (inst2 >> 20);
               addr += this.pos + i - off;
               inst = 279 | inst2 << 12;
               ByteArrayView.setIntLE(buf, i, inst);
               ByteArrayView.setIntBE(buf, i + 4, addr);
            } else {
               int fakeRs1 = inst >>> 27;
               if ((inst - 12544 & 16256) >= (fakeRs1 & 29)) {
                  i += 2;
                  continue;
               }

               int fakeAddr = ByteArrayView.getIntLE(buf, i + 4);
               int fakeInst2 = inst >>> 12 | fakeAddr << 20;
               inst = 23 | fakeRs1 << 7 | fakeAddr & -4096;
               ByteArrayView.setIntLE(buf, i, inst);
               ByteArrayView.setIntLE(buf, i + 4, fakeInst2);
            }

            i += 6;
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
