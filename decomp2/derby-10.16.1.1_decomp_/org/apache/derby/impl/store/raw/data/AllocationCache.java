package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.shared.common.error.StandardException;

class AllocationCache {
   private int numExtents = 0;
   private long[] lowRange;
   private long[] hiRange;
   private boolean[] isDirty;
   private AllocExtent[] extents;
   private long[] extentPageNums;
   private boolean isValid = false;

   protected AllocationCache() {
   }

   protected void reset() {
      this.numExtents = 0;
      this.isValid = false;
      if (this.lowRange != null) {
         for(int var1 = 0; var1 < this.lowRange.length; ++var1) {
            this.lowRange[var1] = -1L;
            this.hiRange[var1] = -1L;
            this.extentPageNums[var1] = -1L;
            this.extents[var1] = null;
            this.isDirty[var1] = false;
         }
      }

   }

   protected long getAllocPageNumber(BaseContainerHandle var1, long var2, long var4) throws StandardException {
      for(int var6 = 0; var6 < this.numExtents; ++var6) {
         if (this.lowRange[var6] <= var2 && var2 <= this.hiRange[var6]) {
            return this.extentPageNums[var6];
         }
      }

      if (!this.isValid) {
         this.validate(var1, var4);

         for(int var7 = 0; var7 < this.numExtents; ++var7) {
            if (this.lowRange[var7] <= var2 && var2 <= this.hiRange[var7]) {
               return this.extentPageNums[var7];
            }
         }
      }

      return -1L;
   }

   protected long getLastPageNumber(BaseContainerHandle var1, long var2) throws StandardException {
      if (!this.isValid) {
         this.validate(var1, var2);
      }

      return this.hiRange[this.numExtents - 1];
   }

   protected void trackUnfilledPage(long var1, boolean var3) {
      if (this.isValid && this.numExtents > 0) {
         for(int var4 = 0; var4 < this.numExtents; ++var4) {
            if (this.lowRange[var4] <= var1 && var1 <= this.hiRange[var4]) {
               AllocExtent var5 = this.extents[var4];
               if (var5 != null && var5.trackUnfilledPage(var1, var3) && this.extents[var4] != null) {
                  this.isDirty[var4] = true;
               }
               break;
            }
         }

      }
   }

   protected long getUnfilledPageNumber(BaseContainerHandle var1, long var2, long var4) throws StandardException {
      if (!this.isValid) {
         this.validate(var1, var2);
      }

      if (var4 == -1L) {
         for(int var6 = 0; var6 < this.numExtents; ++var6) {
            if (this.extents[var6] != null) {
               return this.extents[var6].getUnfilledPageNumber(var4);
            }
         }
      } else {
         for(int var7 = 0; var7 < this.numExtents; ++var7) {
            if (var4 <= this.hiRange[var7] && this.extents[var7] != null) {
               return this.extents[var7].getUnfilledPageNumber(var4);
            }
         }
      }

      return -1L;
   }

   protected long getEstimatedPageCount(BaseContainerHandle var1, long var2) throws StandardException {
      if (!this.isValid) {
         this.validate(var1, var2);
      }

      long var4 = 0L;

      for(int var6 = 0; var6 < this.numExtents; ++var6) {
         if (this.extents[var6] != null) {
            var4 += (long)this.extents[var6].getAllocatedPageCount();
         }
      }

      return var4;
   }

   protected SpaceInformation getAllPageCounts(BaseContainerHandle var1, long var2) throws StandardException {
      long var4 = 0L;
      long var6 = 0L;
      long var8 = 0L;
      long var10 = 0L;
      if (!this.isValid) {
         this.validate(var1, var2);
      }

      for(int var12 = 0; var12 < this.numExtents; ++var12) {
         if (this.extents[var12] != null) {
            var4 = (long)this.extents[var12].getAllocatedPageCount();
            var6 += var4;
            var10 += (long)this.extents[var12].getUnfilledPageCount();
            var8 += (long)this.extents[var12].getTotalPageCount() - var4;
         }
      }

      return new SpaceInformation(var6, var8, var10);
   }

   protected void invalidate() {
      for(int var1 = 0; var1 < this.numExtents; ++var1) {
         this.isDirty[var1] = false;
         this.extents[var1] = null;
      }

      this.isValid = false;
   }

   protected void invalidate(AllocPage var1, long var2) throws StandardException {
      this.isValid = false;
      if (this.numExtents != 0) {
         for(int var4 = 0; var4 < this.numExtents; ++var4) {
            if (this.extentPageNums[var4] == var2) {
               if (var1 != null && this.extents[var4] != null && this.isDirty[var4]) {
                  var1.updateUnfilledPageInfo(this.extents[var4]);
                  this.isDirty[var4] = false;
               }

               this.extents[var4] = null;
               return;
            }
         }

         if (var2 <= this.hiRange[this.numExtents - 1]) {
            ;
         }
      }
   }

   protected void invalidateLastExtent() {
      this.isValid = false;
      if (this.numExtents > 0) {
         this.extents[this.numExtents - 1] = null;
      }

   }

   protected long getLastValidPage(BaseContainerHandle var1, long var2) throws StandardException {
      AllocExtent var4 = null;
      long var6 = -1L;
      if (!this.isValid) {
         this.validate(var1, var2);
      }

      if (this.numExtents == 0) {
         return -1L;
      } else {
         for(int var5 = this.numExtents - 1; var5 >= 0; --var5) {
            var4 = this.extents[var5];
            var6 = var4.getLastValidPageNumber();
            if (var6 != -1L) {
               break;
            }
         }

         return var6;
      }
   }

   protected long getNextValidPage(BaseContainerHandle var1, long var2, long var4) throws StandardException {
      if (!this.isValid) {
         this.validate(var1, var4);
      }

      if (this.numExtents == 0) {
         return -1L;
      } else {
         AllocExtent var7 = null;

         int var6;
         for(var6 = 0; var6 < this.numExtents; ++var6) {
            if (var2 < this.hiRange[var6]) {
               var7 = this.extents[var6];
               break;
            }
         }

         if (var7 == null) {
            return -1L;
         } else {
            long var8;
            for(var8 = -1L; var6 < this.numExtents; ++var6) {
               var7 = this.extents[var6];
               var8 = var7.getNextValidPageNumber(var2);
               if (var8 != -1L) {
                  break;
               }
            }

            return var8;
         }
      }
   }

   protected int getPageStatus(BaseContainerHandle var1, long var2, long var4) throws StandardException {
      AllocExtent var6 = null;

      for(int var7 = 0; var7 < this.numExtents; ++var7) {
         if (this.lowRange[var7] <= var2 && var2 <= this.hiRange[var7]) {
            var6 = this.extents[var7];
            break;
         }
      }

      if (var6 == null) {
         if (!this.isValid) {
            this.validate(var1, var4);
         }

         for(int var8 = 0; var8 < this.numExtents; ++var8) {
            if (this.lowRange[var8] <= var2 && var2 <= this.hiRange[var8]) {
               var6 = this.extents[var8];
               break;
            }
         }
      }

      return var6.getPageStatus(var2);
   }

   private void validate(BaseContainerHandle var1, long var2) throws StandardException {
      AllocPage var7;
      if (this.numExtents == 0) {
         for(long var4 = var2; !this.isValid; var7.unlatch()) {
            this.growArrays(++this.numExtents);
            Page var6 = var1.getAllocPage(var4);
            var7 = (AllocPage)var6;
            this.setArrays(this.numExtents - 1, var7);
            if (var7.isLast()) {
               this.isValid = true;
            } else {
               var4 = var7.getNextAllocPageNumber();
            }
         }
      } else {
         for(int var8 = 0; var8 < this.numExtents - 1; ++var8) {
            if (this.extents[var8] == null) {
               AllocPage var5 = (AllocPage)var1.getAllocPage(this.extentPageNums[var8]);
               this.setArrays(var8, var5);
               var5.unlatch();
            }
         }

         AllocPage var10;
         for(long var9 = this.extentPageNums[this.numExtents - 1]; !this.isValid; var10.unlatch()) {
            var10 = (AllocPage)var1.getAllocPage(var9);
            if (this.extents[this.numExtents - 1] == null) {
               this.setArrays(this.numExtents - 1, var10);
            }

            if (!var10.isLast()) {
               this.growArrays(++this.numExtents);
               var9 = var10.getNextAllocPageNumber();
            } else {
               this.isValid = true;
            }
         }
      }

   }

   private void setArrays(int var1, AllocPage var2) {
      AllocExtent var3 = var2.getAllocExtent();
      this.extents[var1] = var3;
      this.lowRange[var1] = var3.getFirstPagenum();
      this.hiRange[var1] = var3.getLastPagenum();
      this.extentPageNums[var1] = var2.getPageNumber();
   }

   private void growArrays(int var1) {
      int var2;
      if (this.lowRange != null && this.lowRange.length != 0) {
         var2 = this.lowRange.length;
      } else {
         var2 = 0;
      }

      if (var2 < var1) {
         long[] var3 = this.lowRange;
         long[] var4 = this.hiRange;
         AllocExtent[] var5 = this.extents;
         boolean[] var6 = this.isDirty;
         long[] var7 = this.extentPageNums;
         this.lowRange = new long[var1];
         this.hiRange = new long[var1];
         this.isDirty = new boolean[var1];
         this.extents = new AllocExtent[var1];
         this.extentPageNums = new long[var1];
         if (var2 > 0) {
            System.arraycopy(var3, 0, this.lowRange, 0, var3.length);
            System.arraycopy(var4, 0, this.hiRange, 0, var4.length);
            System.arraycopy(var6, 0, this.isDirty, 0, var6.length);
            System.arraycopy(var5, 0, this.extents, 0, var5.length);
            System.arraycopy(var7, 0, this.extentPageNums, 0, var7.length);
         }

         for(int var8 = var2; var8 < var1; ++var8) {
            this.lowRange[var8] = -1L;
            this.hiRange[var8] = -1L;
            this.isDirty[var8] = false;
            this.extentPageNums[var8] = -1L;
            this.extents[var8] = null;
         }

      }
   }

   protected void dumpAllocationCache() {
   }
}
