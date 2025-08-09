package org.xerial.snappy.buffer;

import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CachedBufferAllocator implements BufferAllocator {
   private static BufferAllocatorFactory factory = new BufferAllocatorFactory() {
      public BufferAllocator getBufferAllocator(int var1) {
         return CachedBufferAllocator.getAllocator(var1);
      }
   };
   private static final Map queueTable = new HashMap();
   private final int bufferSize;
   private final Deque bufferQueue;

   public static void setBufferAllocatorFactory(BufferAllocatorFactory var0) {
      assert var0 != null;

      factory = var0;
   }

   public static BufferAllocatorFactory getBufferAllocatorFactory() {
      return factory;
   }

   public CachedBufferAllocator(int var1) {
      this.bufferSize = var1;
      this.bufferQueue = new ArrayDeque();
   }

   public static synchronized CachedBufferAllocator getAllocator(int var0) {
      CachedBufferAllocator var1 = null;
      if (queueTable.containsKey(var0)) {
         var1 = (CachedBufferAllocator)((SoftReference)queueTable.get(var0)).get();
      }

      if (var1 == null) {
         var1 = new CachedBufferAllocator(var0);
         queueTable.put(var0, new SoftReference(var1));
      }

      return var1;
   }

   public byte[] allocate(int var1) {
      synchronized(this) {
         return this.bufferQueue.isEmpty() ? new byte[var1] : (byte[])this.bufferQueue.pollFirst();
      }
   }

   public void release(byte[] var1) {
      synchronized(this) {
         this.bufferQueue.addLast(var1);
      }
   }
}
