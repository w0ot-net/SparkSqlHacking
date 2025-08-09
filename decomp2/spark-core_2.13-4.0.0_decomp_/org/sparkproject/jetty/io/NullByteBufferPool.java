package org.sparkproject.jetty.io;

import java.nio.ByteBuffer;
import org.sparkproject.jetty.util.BufferUtil;

public class NullByteBufferPool implements ByteBufferPool {
   private final RetainableByteBufferPool _retainableByteBufferPool = RetainableByteBufferPool.from(this);

   public ByteBuffer acquire(int size, boolean direct) {
      return direct ? BufferUtil.allocateDirect(size) : BufferUtil.allocate(size);
   }

   public void release(ByteBuffer buffer) {
      BufferUtil.clear(buffer);
   }

   public RetainableByteBufferPool asRetainableByteBufferPool() {
      return this._retainableByteBufferPool;
   }
}
