package org.tukaani.xz.rangecoder;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.ArrayCache;

public final class RangeEncoderToBuffer extends RangeEncoder {
   private final byte[] buf;
   private int bufPos;

   public RangeEncoderToBuffer(int bufSize, ArrayCache arrayCache) {
      this.buf = arrayCache.getByteArray(bufSize, false);
      this.reset();
   }

   public void putArraysToCache(ArrayCache arrayCache) {
      arrayCache.putArray(this.buf);
   }

   public void reset() {
      super.reset();
      this.bufPos = 0;
   }

   public int getPendingSize() {
      return this.bufPos + (int)this.cacheSize + 5 - 1;
   }

   public int finish() {
      try {
         super.finish();
      } catch (IOException var2) {
         throw new Error();
      }

      return this.bufPos;
   }

   public void write(OutputStream out) throws IOException {
      out.write(this.buf, 0, this.bufPos);
   }

   void writeByte(int b) {
      this.buf[this.bufPos++] = (byte)b;
   }
}
