package com.ning.compress.lzf.parallel;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

class BlockManager {
   private final BlockingDeque blockPool;

   public BlockManager(int blockPoolSize, int blockSize) {
      this.blockPool = new LinkedBlockingDeque(blockPoolSize);

      for(int i = 0; i < blockPoolSize; ++i) {
         this.blockPool.addFirst(new byte[blockSize]);
      }

   }

   public byte[] getBlockFromPool() {
      byte[] block = null;

      try {
         block = (byte[])this.blockPool.takeFirst();
         return block;
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }

   public void releaseBlockToPool(byte[] block) {
      assert !this.blockPool.contains(block);

      try {
         this.blockPool.putLast(block);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }
}
