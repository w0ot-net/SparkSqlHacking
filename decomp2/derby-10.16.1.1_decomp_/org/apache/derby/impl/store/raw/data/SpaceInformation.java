package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.access.SpaceInfo;

public class SpaceInformation implements SpaceInfo {
   private long numAllocatedPages;
   private long numFreePages;
   private long numUnfilledPages;
   private int pageSize;

   public SpaceInformation(long var1, long var3, long var5) {
      this.numAllocatedPages = var1;
      this.numFreePages = var3;
      this.numUnfilledPages = var5;
   }

   public long getNumAllocatedPages() {
      return this.numAllocatedPages;
   }

   public long getNumFreePages() {
      return this.numFreePages;
   }

   public long getNumUnfilledPages() {
      return this.numUnfilledPages;
   }

   public int getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(int var1) {
      this.pageSize = var1;
   }
}
