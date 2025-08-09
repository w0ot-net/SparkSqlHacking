package com.github.luben.zstd;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RecyclingBufferPool implements BufferPool {
   public static final BufferPool INSTANCE = new RecyclingBufferPool();
   private static final int buffSize = Math.max(Math.max((int)ZstdOutputStreamNoFinalizer.recommendedCOutSize(), (int)ZstdInputStreamNoFinalizer.recommendedDInSize()), (int)ZstdInputStreamNoFinalizer.recommendedDOutSize());
   private final ConcurrentLinkedQueue pool = new ConcurrentLinkedQueue();

   private RecyclingBufferPool() {
   }

   public ByteBuffer get(int var1) {
      if (var1 > buffSize) {
         throw new RuntimeException("Unsupported buffer size: " + var1 + ". Supported buffer sizes: " + buffSize + " or smaller.");
      } else {
         ByteBuffer var3;
         do {
            SoftReference var2 = (SoftReference)this.pool.poll();
            if (var2 == null) {
               return ByteBuffer.allocate(buffSize);
            }

            var3 = (ByteBuffer)var2.get();
         } while(var3 == null);

         return var3;
      }
   }

   public void release(ByteBuffer var1) {
      if (var1.capacity() >= buffSize) {
         var1.clear();
         this.pool.add(new SoftReference(var1));
      }

   }
}
