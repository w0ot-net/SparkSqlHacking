package org.apache.derby.impl.store.raw.data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

public class AllocExtent implements Externalizable {
   private long extentOffset;
   private long extentStart;
   private long extentEnd;
   private int extentLength;
   int extentStatus;
   private int preAllocLength;
   private int reserved1;
   private long reserved2;
   private long reserved3;
   private static final int HAS_DEALLOCATED = 1;
   private static final int HAS_FREE = 2;
   private static final int ALL_FREE = 4;
   private static final int HAS_UNFILLED_PAGES = 16;
   private static final int KEEP_UNFILLED_PAGES = 268435456;
   private static final int NO_DEALLOC_PAGE_MAP = 536870912;
   private static final int RETIRED = 8;
   protected static final int ALLOCATED_PAGE = 0;
   protected static final int DEALLOCATED_PAGE = 1;
   protected static final int FREE_PAGE = 2;
   FormatableBitSet freePages;
   FormatableBitSet unFilledPages;

   protected static int MAX_RANGE(int var0) {
      byte var1 = 56;
      var0 -= var1;
      var0 /= 3;
      return var0 <= 0 ? 0 : FormatableBitSet.maxBitsForSpace(var0);
   }

   protected AllocExtent(long var1, long var3, int var5, int var6, int var7) {
      this.extentOffset = var1;
      this.extentStart = var3;
      this.extentEnd = var3 + (long)var7 - 1L;
      this.extentLength = var5;
      this.preAllocLength = this.extentLength;
      if (var5 > 0) {
         this.extentStatus = 6;
      } else {
         this.extentStatus = 0;
      }

      this.extentStatus |= 268435456;
      this.extentStatus |= 536870912;
      int var8 = (1 + var5 / 8) * 8;
      if (var8 > var7) {
         var8 = var7;
      }

      this.freePages = new FormatableBitSet(var8);
      this.unFilledPages = new FormatableBitSet(var8);

      for(int var9 = 0; var9 < var5; ++var9) {
         this.freePages.set(var9);
      }

   }

   protected AllocExtent(AllocExtent var1) {
      this.extentOffset = var1.extentOffset;
      this.extentStart = var1.extentStart;
      this.extentEnd = var1.extentEnd;
      this.extentLength = var1.extentLength;
      this.extentStatus = var1.extentStatus;
      this.preAllocLength = var1.preAllocLength;
      this.freePages = new FormatableBitSet(var1.freePages);
      this.unFilledPages = new FormatableBitSet(var1.unFilledPages);
   }

   public AllocExtent() {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeLong(this.extentOffset);
      var1.writeLong(this.extentStart);
      var1.writeLong(this.extentEnd);
      var1.writeInt(this.extentLength);
      var1.writeInt(this.extentStatus);
      var1.writeInt(this.preAllocLength);
      var1.writeInt(0);
      var1.writeLong(0L);
      var1.writeLong(0L);
      this.freePages.writeExternal(var1);
      this.unFilledPages.writeExternal(var1);
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.extentOffset = var1.readLong();
      this.extentStart = var1.readLong();
      this.extentEnd = var1.readLong();
      this.extentLength = var1.readInt();
      this.extentStatus = var1.readInt();
      this.preAllocLength = var1.readInt();
      this.reserved1 = var1.readInt();
      this.reserved2 = var1.readLong();
      this.reserved3 = var1.readLong();
      this.freePages = new FormatableBitSet();
      this.freePages.readExternal(var1);
      if ((this.extentStatus & 536870912) == 0) {
         FormatableBitSet var2 = new FormatableBitSet();
         var2.readExternal(var1);
         this.freePages.or(var2);
         this.extentStatus |= 536870912;
      }

      if ((this.extentStatus & 268435456) == 268435456) {
         this.unFilledPages = new FormatableBitSet();
         this.unFilledPages.readExternal(var1);
      } else {
         this.unFilledPages = new FormatableBitSet(this.freePages.getLength());
         this.extentStatus |= 268435456;
      }

   }

   protected void allocPage(long var1) throws StandardException {
      int var3 = (int)(var1 - this.extentStart);
      if (var3 >= this.freePages.getLength()) {
         int var4 = (1 + var3 / 8) * 8;
         if (var4 > (int)(this.extentEnd - this.extentStart + 1L)) {
            var4 = (int)(this.extentEnd - this.extentStart + 1L);
         }

         this.freePages.grow(var4);
         this.unFilledPages.grow(var4);
      }

      int var5 = (int)(var1 - this.extentStart + 1L);
      if (var5 > this.extentLength) {
         this.extentLength = var5;
      }

      this.freePages.clear(var3);
   }

   protected void deallocPage(long var1) throws StandardException {
      int var3 = (int)(var1 - this.extentStart);
      this.freePages.set(var3);
      this.unFilledPages.clear(var3);
      this.setExtentFreePageStatus(true);
   }

   protected int compress(BaseContainerHandle var1, RawTransaction var2, AllocPage var3) throws StandardException {
      int var4 = -1;
      int var5 = 0;

      for(int var6 = this.extentLength - 1; var6 >= 0 && this.freePages.isSet(var6); --var6) {
         var4 = var6;
         ++var5;
      }

      int var7 = var4 - 1;
      if (var5 > 0) {
         var1.getAllocationActionSet().actionCompressSpaceOperation(var2, var3, var7, var5);
         return var4;
      } else {
         return -1;
      }
   }

   protected void compressPages(int var1, int var2) {
      if (var1 + 1 >= 0) {
         this.freePages.shrink(var1 + 1);
         this.unFilledPages.shrink(var1 + 1);
         this.preAllocLength = this.extentLength = var1 + 1;
      }

   }

   protected void undoCompressPages(int var1, int var2) {
      if (var1 >= 0) {
         this.freePages.shrink(var1 + 1);
         this.unFilledPages.shrink(var1 + 1);
         this.preAllocLength = this.extentLength = var1 + 1;
      }

   }

   protected long getExtentEnd() {
      return this.extentEnd;
   }

   protected long getFreePageNumber(long var1) {
      if (this.mayHaveFreePage()) {
         int var3 = var1 < this.extentStart ? this.freePages.anySetBit() : this.freePages.anySetBit((int)(var1 - this.extentStart));
         if (var3 != -1) {
            return (long)var3 + this.extentStart;
         }

         if (var1 < this.extentStart) {
            this.setExtentFreePageStatus(false);
         }
      }

      return this.extentStart + (long)this.extentLength;
   }

   protected long getPageOffset(long var1, int var3, boolean var4) throws StandardException {
      return var1 * (long)var3;
   }

   protected boolean isRetired() {
      return (this.extentStatus & 8) != 0;
   }

   private boolean mayHaveFreePage() {
      return (this.extentStatus & 2) != 0;
   }

   private void setExtentFreePageStatus(boolean var1) {
      if (var1) {
         this.extentStatus |= 2;
      } else {
         this.extentStatus &= -3;
      }

   }

   protected boolean canAddFreePage(long var1) {
      if (this.extentStart + (long)this.extentLength <= this.extentEnd) {
         return true;
      } else if (!this.mayHaveFreePage()) {
         return false;
      } else if (var1 < this.extentStart) {
         return this.freePages.anySetBit() != -1;
      } else {
         return this.freePages.anySetBit((int)(var1 - this.extentStart)) != -1;
      }
   }

   protected int getPageStatus(long var1) {
      byte var3 = 0;
      int var4 = (int)(var1 - this.extentStart);
      if (this.freePages.isSet(var4)) {
         var3 = 2;
      } else {
         var3 = 0;
      }

      return var3;
   }

   protected long getFirstPagenum() {
      return this.extentStart;
   }

   protected long getLastPagenum() {
      return this.extentStart + (long)this.extentLength - 1L;
   }

   protected long getPagenum(int var1) {
      return this.extentStart + (long)var1;
   }

   protected long getLastPreallocPagenum() {
      if (this.extentLength > this.preAllocLength) {
         this.preAllocLength = this.extentLength;
      }

      return this.extentStart + (long)this.preAllocLength - 1L;
   }

   protected void setLastPreallocPagenum(long var1) {
      if (var1 > this.extentEnd) {
         var1 = this.extentEnd;
      }

      this.preAllocLength = (int)(var1 - this.extentStart + 1L);
   }

   protected long getNextValidPageNumber(long var1) {
      long var5 = this.getLastPagenum();
      long var3;
      if (var1 < this.extentStart) {
         var3 = this.extentStart;
      } else {
         var3 = var1 + 1L;
      }

      while(var3 <= var5) {
         int var7 = this.getPageStatus(var3);
         if (var7 == 0) {
            break;
         }

         ++var3;
      }

      if (var3 > var5) {
         var3 = -1L;
      }

      return var3;
   }

   protected long getLastValidPageNumber() {
      long var1;
      for(var1 = this.getLastPagenum(); var1 >= this.extentStart; --var1) {
         int var3 = this.getPageStatus(var1);
         if (var3 == 0) {
            break;
         }
      }

      if (var1 < this.extentStart) {
         var1 = -1L;
      }

      return var1;
   }

   private void checkInRange(long var1) {
   }

   protected void updateUnfilledPageInfo(AllocExtent var1) {
      this.unFilledPages = var1.unFilledPages;
      if (this.unFilledPages.anySetBit() >= 0) {
         this.extentStatus |= 16;
      } else {
         this.extentStatus &= -17;
      }

   }

   protected boolean trackUnfilledPage(long var1, boolean var3) {
      this.checkInRange(var1);
      int var4 = (int)(var1 - this.extentStart);
      boolean var5 = this.unFilledPages.isSet(var4);
      if (var3 != var5) {
         if (var3) {
            this.unFilledPages.set(var4);
            this.extentStatus |= 16;
         } else {
            this.unFilledPages.clear(var4);
         }

         return true;
      } else {
         return false;
      }
   }

   protected long getUnfilledPageNumber(long var1) {
      if ((this.extentStatus & 16) == 0) {
         return -1L;
      } else {
         int var3 = this.unFilledPages.anySetBit();
         if (var3 != -1) {
            if ((long)var3 + this.extentStart != var1) {
               return (long)var3 + this.extentStart;
            }

            var3 = this.unFilledPages.anySetBit(var3);
            if (var3 != -1) {
               return (long)var3 + this.extentStart;
            }
         }

         return -1L;
      }
   }

   protected int getAllocatedPageCount() {
      int var1 = this.extentLength;
      if (!this.mayHaveFreePage()) {
         return var1;
      } else {
         byte[] var2 = this.freePages.getByteArray();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            if (var2[var4] != 0) {
               for(int var5 = 0; var5 < 8; ++var5) {
                  if ((1 << var5 & var2[var4]) != 0) {
                     --var1;
                  }
               }
            }
         }

         return var1;
      }
   }

   protected int getUnfilledPageCount() {
      int var1 = 0;
      int var2 = this.freePages.size();

      for(int var3 = 0; var3 < this.unFilledPages.size(); ++var3) {
         if (this.unFilledPages.isSet(var3) && (var3 >= var2 || !this.freePages.isSet(var3))) {
            ++var1;
         }
      }

      return var1;
   }

   protected int getTotalPageCount() {
      return this.extentLength;
   }

   protected String toDebugString() {
      return null;
   }
}
