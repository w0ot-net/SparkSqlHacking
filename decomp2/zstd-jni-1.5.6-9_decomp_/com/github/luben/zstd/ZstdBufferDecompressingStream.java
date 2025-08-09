package com.github.luben.zstd;

import com.github.luben.zstd.util.Native;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ZstdBufferDecompressingStream implements Closeable {
   private final ZstdBufferDecompressingStreamNoFinalizer inner;
   private boolean finalize = true;

   protected ByteBuffer refill(ByteBuffer var1) {
      return var1;
   }

   public ZstdBufferDecompressingStream(ByteBuffer var1) {
      this.inner = new ZstdBufferDecompressingStreamNoFinalizer(var1) {
         protected ByteBuffer refill(ByteBuffer var1) {
            return ZstdBufferDecompressingStream.this.refill(var1);
         }
      };
   }

   public void setFinalize(boolean var1) {
      this.finalize = var1;
   }

   public synchronized boolean hasRemaining() {
      return this.inner.hasRemaining();
   }

   public static int recommendedTargetBufferSize() {
      return ZstdBufferDecompressingStreamNoFinalizer.recommendedTargetBufferSize();
   }

   public synchronized ZstdBufferDecompressingStream setDict(byte[] var1) throws IOException {
      this.inner.setDict(var1);
      return this;
   }

   public synchronized ZstdBufferDecompressingStream setDict(ZstdDictDecompress var1) throws IOException {
      this.inner.setDict(var1);
      return this;
   }

   public ZstdBufferDecompressingStream setLongMax(int var1) throws IOException {
      this.inner.setLongMax(var1);
      return this;
   }

   public synchronized int read(ByteBuffer var1) throws IOException {
      return this.inner.read(var1);
   }

   public synchronized void close() throws IOException {
      this.inner.close();
   }

   protected void finalize() throws Throwable {
      if (this.finalize) {
         this.close();
      }

   }

   static {
      Native.load();
   }
}
