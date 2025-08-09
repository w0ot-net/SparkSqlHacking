package org.tukaani.xz.simple;

public final class IA64 implements SimpleFilter {
   private static final int[] BRANCH_TABLE = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 6, 6, 0, 0, 7, 7, 4, 4, 0, 0, 4, 4, 0, 0};
   private final boolean isEncoder;
   private int pos;

   public IA64(boolean isEncoder, int startPos) {
      this.isEncoder = isEncoder;
      this.pos = startPos;
   }

   public int code(byte[] buf, int off, int len) {
      int end = off + len - 16;

      int i;
      for(i = off; i <= end; i += 16) {
         int instrTemplate = buf[i] & 31;
         int mask = BRANCH_TABLE[instrTemplate];
         int slot = 0;

         for(int bitPos = 5; slot < 3; bitPos += 41) {
            if ((mask >>> slot & 1) != 0) {
               int bytePos = bitPos >>> 3;
               int bitRes = bitPos & 7;
               long instr = 0L;

               for(int j = 0; j < 6; ++j) {
                  instr |= ((long)buf[i + bytePos + j] & 255L) << 8 * j;
               }

               long instrNorm = instr >>> bitRes;
               if ((instrNorm >>> 37 & 15L) == 5L && (instrNorm >>> 9 & 7L) == 0L) {
                  int src = (int)(instrNorm >>> 13 & 1048575L);
                  src |= ((int)(instrNorm >>> 36) & 1) << 20;
                  src <<= 4;
                  int dest;
                  if (this.isEncoder) {
                     dest = src + (this.pos + i - off);
                  } else {
                     dest = src - (this.pos + i - off);
                  }

                  dest >>>= 4;
                  instrNorm &= -77309403137L;
                  instrNorm |= ((long)dest & 1048575L) << 13;
                  instrNorm |= ((long)dest & 1048576L) << 16;
                  instr &= (long)((1 << bitRes) - 1);
                  instr |= instrNorm << bitRes;

                  for(int j = 0; j < 6; ++j) {
                     buf[i + bytePos + j] = (byte)((int)(instr >>> 8 * j));
                  }
               }
            }

            ++slot;
         }
      }

      i -= off;
      this.pos += i;
      return i;
   }
}
