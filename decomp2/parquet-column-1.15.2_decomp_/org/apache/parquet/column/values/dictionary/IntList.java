package org.apache.parquet.column.values.dictionary;

import java.util.ArrayList;
import java.util.List;

public class IntList {
   static final int MAX_SLAB_SIZE = 65536;
   static final int INITIAL_SLAB_SIZE = 4096;
   private int currentSlabSize = 4096;
   private List slabs = new ArrayList();
   private int[] currentSlab;
   private int currentSlabPos;

   int getCurrentSlabSize() {
      return this.currentSlabSize;
   }

   private void allocateSlab() {
      this.currentSlab = new int[this.currentSlabSize];
      this.currentSlabPos = 0;
   }

   private void updateCurrentSlabSize() {
      if (this.currentSlabSize < 65536) {
         this.currentSlabSize *= 2;
         if (this.currentSlabSize > 65536) {
            this.currentSlabSize = 65536;
         }
      }

   }

   public void add(int i) {
      if (this.currentSlab == null) {
         this.allocateSlab();
      } else if (this.currentSlabPos == this.currentSlab.length) {
         this.slabs.add(this.currentSlab);
         this.updateCurrentSlabSize();
         this.allocateSlab();
      }

      this.currentSlab[this.currentSlabPos] = i;
      ++this.currentSlabPos;
   }

   public IntIterator iterator() {
      if (this.currentSlab == null) {
         this.allocateSlab();
      }

      int[][] itSlabs = (int[][])this.slabs.toArray(new int[this.slabs.size() + 1][]);
      itSlabs[this.slabs.size()] = this.currentSlab;
      return new IntIterator(itSlabs, this.size());
   }

   public int size() {
      int size = this.currentSlabPos;

      for(int[] slab : this.slabs) {
         size += slab.length;
      }

      return size;
   }

   public static class IntIterator {
      private final int[][] slabs;
      private final int count;
      private int current;
      private int currentRow;
      private int currentCol;

      public IntIterator(int[][] slabs, int count) {
         this.slabs = slabs;
         this.count = count;
      }

      public boolean hasNext() {
         return this.current < this.count;
      }

      public int next() {
         int result = this.slabs[this.currentRow][this.currentCol];
         this.incrementPosition();
         return result;
      }

      private void incrementPosition() {
         ++this.current;
         ++this.currentCol;
         if (this.currentCol >= this.slabs[this.currentRow].length) {
            this.currentCol = 0;
            ++this.currentRow;
         }

      }
   }
}
