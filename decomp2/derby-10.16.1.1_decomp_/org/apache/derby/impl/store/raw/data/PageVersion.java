package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.store.raw.PageTimeStamp;

public class PageVersion implements PageTimeStamp {
   private long pageNumber;
   private long pageVersion;

   public PageVersion(long var1, long var3) {
      this.pageNumber = var1;
      this.pageVersion = var3;
   }

   public long getPageVersion() {
      return this.pageVersion;
   }

   public long getPageNumber() {
      return this.pageNumber;
   }

   public void setPageVersion(long var1) {
      this.pageVersion = var1;
   }

   public void setPageNumber(long var1) {
      this.pageNumber = var1;
   }
}
