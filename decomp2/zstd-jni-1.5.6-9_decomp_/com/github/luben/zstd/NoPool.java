package com.github.luben.zstd;

import java.nio.ByteBuffer;

public class NoPool implements BufferPool {
   public static final BufferPool INSTANCE = new NoPool();

   private NoPool() {
   }

   public ByteBuffer get(int var1) {
      return ByteBuffer.allocate(var1);
   }

   public void release(ByteBuffer var1) {
   }
}
