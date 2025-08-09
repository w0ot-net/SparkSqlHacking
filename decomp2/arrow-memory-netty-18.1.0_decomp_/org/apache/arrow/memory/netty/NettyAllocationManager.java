package org.apache.arrow.memory.netty;

import io.netty.buffer.PooledByteBufAllocatorL;
import io.netty.buffer.UnsafeDirectLittleEndian;
import io.netty.util.internal.PlatformDependent;
import org.apache.arrow.memory.AllocationManager;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.BufferManager;
import org.apache.arrow.memory.ReferenceManager;

public class NettyAllocationManager extends AllocationManager {
   public static final AllocationManager.Factory FACTORY = new AllocationManager.Factory() {
      public AllocationManager create(BufferAllocator accountingAllocator, long size) {
         return new NettyAllocationManager(accountingAllocator, size);
      }

      public ArrowBuf empty() {
         return NettyAllocationManager.EMPTY_BUFFER;
      }
   };
   public static final int DEFAULT_ALLOCATION_CUTOFF_VALUE = Integer.MAX_VALUE;
   private static final PooledByteBufAllocatorL INNER_ALLOCATOR = new PooledByteBufAllocatorL();
   static final UnsafeDirectLittleEndian EMPTY;
   static final ArrowBuf EMPTY_BUFFER;
   static final long CHUNK_SIZE;
   private final long allocatedSize;
   private final UnsafeDirectLittleEndian memoryChunk;
   private final long allocatedAddress;

   NettyAllocationManager(BufferAllocator accountingAllocator, long requestedSize, int allocationCutOffValue) {
      super(accountingAllocator);
      if (requestedSize > (long)allocationCutOffValue) {
         this.memoryChunk = null;
         this.allocatedAddress = PlatformDependent.allocateMemory(requestedSize);
         this.allocatedSize = requestedSize;
      } else {
         this.memoryChunk = INNER_ALLOCATOR.allocate(requestedSize);
         this.allocatedAddress = this.memoryChunk.memoryAddress();
         this.allocatedSize = (long)this.memoryChunk.capacity();
      }

   }

   NettyAllocationManager(BufferAllocator accountingAllocator, long requestedSize) {
      this(accountingAllocator, requestedSize, Integer.MAX_VALUE);
   }

   /** @deprecated */
   @Deprecated
   UnsafeDirectLittleEndian getMemoryChunk() {
      return this.memoryChunk;
   }

   protected long memoryAddress() {
      return this.allocatedAddress;
   }

   protected void release0() {
      if (this.memoryChunk == null) {
         PlatformDependent.freeMemory(this.allocatedAddress);
      } else {
         this.memoryChunk.release();
      }

   }

   public long getSize() {
      return this.allocatedSize;
   }

   static {
      EMPTY = INNER_ALLOCATOR.empty;
      EMPTY_BUFFER = new ArrowBuf(ReferenceManager.NO_OP, (BufferManager)null, 0L, EMPTY.memoryAddress());
      CHUNK_SIZE = (long)INNER_ALLOCATOR.getChunkSize();
   }
}
