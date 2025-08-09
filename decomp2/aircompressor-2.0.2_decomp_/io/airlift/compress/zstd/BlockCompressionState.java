package io.airlift.compress.zstd;

import java.util.Arrays;

class BlockCompressionState {
   public final int[] hashTable;
   public final int[] chainTable;
   private final long baseAddress;
   private int windowBaseOffset;

   public BlockCompressionState(CompressionParameters parameters, long baseAddress) {
      this.baseAddress = baseAddress;
      this.hashTable = new int[1 << parameters.getHashLog()];
      this.chainTable = new int[1 << parameters.getChainLog()];
   }

   public void slideWindow(int slideWindowSize) {
      for(int i = 0; i < this.hashTable.length; ++i) {
         int newValue = this.hashTable[i] - slideWindowSize;
         newValue &= ~(newValue >> 31);
         this.hashTable[i] = newValue;
      }

      for(int i = 0; i < this.chainTable.length; ++i) {
         int newValue = this.chainTable[i] - slideWindowSize;
         newValue &= ~(newValue >> 31);
         this.chainTable[i] = newValue;
      }

   }

   public void reset() {
      Arrays.fill(this.hashTable, 0);
      Arrays.fill(this.chainTable, 0);
   }

   public void enforceMaxDistance(long inputLimit, int maxDistance) {
      int distance = (int)(inputLimit - this.baseAddress);
      int newOffset = distance - maxDistance;
      if (this.windowBaseOffset < newOffset) {
         this.windowBaseOffset = newOffset;
      }

   }

   public long getBaseAddress() {
      return this.baseAddress;
   }

   public int getWindowBaseOffset() {
      return this.windowBaseOffset;
   }
}
