package org.apache.arrow.memory.netty;

import org.apache.arrow.memory.AllocationManager;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;

public class DefaultAllocationManagerFactory implements AllocationManager.Factory {
   public static final AllocationManager.Factory FACTORY;

   public AllocationManager create(BufferAllocator accountingAllocator, long size) {
      return FACTORY.create(accountingAllocator, size);
   }

   public ArrowBuf empty() {
      return FACTORY.empty();
   }

   static {
      FACTORY = NettyAllocationManager.FACTORY;
   }
}
