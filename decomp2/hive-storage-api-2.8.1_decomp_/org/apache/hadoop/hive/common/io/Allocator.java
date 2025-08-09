package org.apache.hadoop.hive.common.io;

import org.apache.hadoop.hive.common.io.encoded.MemoryBuffer;

public interface Allocator {
   /** @deprecated */
   @Deprecated
   void allocateMultiple(MemoryBuffer[] var1, int var2) throws AllocatorOutOfMemoryException;

   void allocateMultiple(MemoryBuffer[] var1, int var2, BufferObjectFactory var3) throws AllocatorOutOfMemoryException;

   /** @deprecated */
   @Deprecated
   MemoryBuffer createUnallocated();

   void deallocate(MemoryBuffer var1);

   boolean isDirectAlloc();

   int getMaxAllocation();

   public static class AllocatorOutOfMemoryException extends RuntimeException {
      private static final long serialVersionUID = 268124648177151761L;

      public AllocatorOutOfMemoryException(String msg) {
         super(msg);
      }
   }

   public interface BufferObjectFactory {
      MemoryBuffer create();
   }
}
