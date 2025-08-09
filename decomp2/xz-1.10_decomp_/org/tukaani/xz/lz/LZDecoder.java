package org.tukaani.xz.lz;

import java.io.DataInputStream;
import java.io.IOException;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.CorruptedInputException;

public final class LZDecoder {
   private final byte[] buf;
   private final int bufSize;
   private int start = 0;
   private int pos = 0;
   private int full = 0;
   private int limit = 0;
   private int pendingLen = 0;
   private int pendingDist = 0;

   public LZDecoder(int dictSize, byte[] presetDict, ArrayCache arrayCache) {
      this.bufSize = dictSize;
      this.buf = arrayCache.getByteArray(this.bufSize, false);
      if (presetDict != null) {
         this.pos = Math.min(presetDict.length, dictSize);
         this.full = this.pos;
         this.start = this.pos;
         System.arraycopy(presetDict, presetDict.length - this.pos, this.buf, 0, this.pos);
      }

   }

   public void putArraysToCache(ArrayCache arrayCache) {
      arrayCache.putArray(this.buf);
   }

   public void reset() {
      this.start = 0;
      this.pos = 0;
      this.full = 0;
      this.limit = 0;
      this.buf[this.bufSize - 1] = 0;
   }

   public void setLimit(int outMax) {
      if (this.bufSize - this.pos <= outMax) {
         this.limit = this.bufSize;
      } else {
         this.limit = this.pos + outMax;
      }

   }

   public boolean hasSpace() {
      return this.pos < this.limit;
   }

   public boolean hasPending() {
      return this.pendingLen > 0;
   }

   public int getPos() {
      return this.pos;
   }

   public int getByte(int dist) {
      int offset = this.pos - dist - 1;
      if (dist >= this.pos) {
         offset += this.bufSize;
      }

      return this.buf[offset] & 255;
   }

   public void putByte(byte b) {
      this.buf[this.pos++] = b;
      if (this.full < this.pos) {
         this.full = this.pos;
      }

   }

   public void repeat(int dist, int len) throws IOException {
      if (dist >= 0 && dist < this.full) {
         int left = Math.min(this.limit - this.pos, len);
         this.pendingLen = len - left;
         this.pendingDist = dist;
         int back = this.pos - dist - 1;
         if (back < 0) {
            assert this.full == this.bufSize;

            back += this.bufSize;
            int copySize = Math.min(this.bufSize - back, left);

            assert copySize <= dist + 1;

            System.arraycopy(this.buf, back, this.buf, this.pos, copySize);
            this.pos += copySize;
            back = 0;
            left -= copySize;
            if (left == 0) {
               return;
            }
         }

         assert back < this.pos;

         assert left > 0;

         do {
            int copySize = Math.min(left, this.pos - back);
            System.arraycopy(this.buf, back, this.buf, this.pos, copySize);
            this.pos += copySize;
            left -= copySize;
         } while(left > 0);

         if (this.full < this.pos) {
            this.full = this.pos;
         }

      } else {
         throw new CorruptedInputException();
      }
   }

   public void repeatPending() throws IOException {
      if (this.pendingLen > 0) {
         this.repeat(this.pendingDist, this.pendingLen);
      }

   }

   public void copyUncompressed(DataInputStream inData, int len) throws IOException {
      int copySize = Math.min(this.bufSize - this.pos, len);
      inData.readFully(this.buf, this.pos, copySize);
      this.pos += copySize;
      if (this.full < this.pos) {
         this.full = this.pos;
      }

   }

   public int flush(byte[] out, int outOff) {
      int copySize = this.pos - this.start;
      if (this.pos == this.bufSize) {
         this.pos = 0;
      }

      System.arraycopy(this.buf, this.start, out, outOff, copySize);
      this.start = this.pos;
      return copySize;
   }
}
