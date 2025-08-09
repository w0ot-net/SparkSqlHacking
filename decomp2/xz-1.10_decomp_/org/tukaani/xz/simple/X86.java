package org.tukaani.xz.simple;

import org.tukaani.xz.common.ByteArrayView;

public final class X86 implements SimpleFilter {
   private static final boolean[] MASK_TO_ALLOWED_STATUS = new boolean[]{true, true, true, false, true, false, false, false};
   private static final int[] MASK_TO_BIT_NUMBER = new int[]{0, 1, 2, 2, 3, 3, 3, 3};
   private final boolean isEncoder;
   private int pos;
   private int prevMask = 0;

   private static boolean test86MSByte(byte b) {
      int i = b & 255;
      return i == 0 || i == 255;
   }

   public X86(boolean isEncoder, int startPos) {
      this.isEncoder = isEncoder;
      this.pos = startPos + 5;
   }

   public int code(byte[] buf, int off, int len) {
      int prevPos = off - 1;
      int end = off + len - 5;

      int i;
      for(i = off; i <= end; ++i) {
         if ((buf[i] & 254) == 232) {
            prevPos = i - prevPos;
            if ((prevPos & -4) != 0) {
               this.prevMask = 0;
            } else {
               this.prevMask = this.prevMask << prevPos - 1 & 7;
               if (this.prevMask != 0 && (!MASK_TO_ALLOWED_STATUS[this.prevMask] || test86MSByte(buf[i + 4 - MASK_TO_BIT_NUMBER[this.prevMask]]))) {
                  prevPos = i;
                  this.prevMask = this.prevMask << 1 | 1;
                  continue;
               }
            }

            prevPos = i;
            if (!test86MSByte(buf[i + 4])) {
               this.prevMask = this.prevMask << 1 | 1;
            } else {
               int src = ByteArrayView.getIntLE(buf, i + 1);

               int dest;
               while(true) {
                  if (this.isEncoder) {
                     dest = src + (this.pos + i - off);
                  } else {
                     dest = src - (this.pos + i - off);
                  }

                  if (this.prevMask == 0) {
                     break;
                  }

                  int index = MASK_TO_BIT_NUMBER[this.prevMask] * 8;
                  if (!test86MSByte((byte)(dest >>> 24 - index))) {
                     break;
                  }

                  src = dest ^ (1 << 32 - index) - 1;
               }

               dest <<= 7;
               dest >>= 7;
               ByteArrayView.setIntLE(buf, i + 1, dest);
               i += 4;
            }
         }
      }

      prevPos = i - prevPos;
      this.prevMask = (prevPos & -4) != 0 ? 0 : this.prevMask << prevPos - 1;
      i -= off;
      this.pos += i;
      return i;
   }
}
