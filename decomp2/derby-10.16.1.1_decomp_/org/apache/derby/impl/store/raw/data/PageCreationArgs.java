package org.apache.derby.impl.store.raw.data;

class PageCreationArgs {
   final int formatId;
   final int syncFlag;
   final int pageSize;
   final int spareSpace;
   final int minimumRecordSize;
   final int containerInfoSize;

   PageCreationArgs(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.formatId = var1;
      this.syncFlag = var2;
      this.pageSize = var3;
      this.spareSpace = var4;
      this.minimumRecordSize = var5;
      this.containerInfoSize = var6;
   }
}
