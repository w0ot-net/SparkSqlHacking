package org.apache.orc.protobuf;

import java.nio.ByteBuffer;

@CheckReturnValue
abstract class BufferAllocator {
   private static final BufferAllocator UNPOOLED = new BufferAllocator() {
      public AllocatedBuffer allocateHeapBuffer(int capacity) {
         return AllocatedBuffer.wrap(new byte[capacity]);
      }

      public AllocatedBuffer allocateDirectBuffer(int capacity) {
         return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(capacity));
      }
   };

   public static BufferAllocator unpooled() {
      return UNPOOLED;
   }

   public abstract AllocatedBuffer allocateHeapBuffer(int capacity);

   public abstract AllocatedBuffer allocateDirectBuffer(int capacity);
}
