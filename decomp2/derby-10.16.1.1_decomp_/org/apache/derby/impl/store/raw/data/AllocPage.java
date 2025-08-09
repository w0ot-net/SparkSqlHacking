package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public class AllocPage extends StoredPage {
   public static final int FORMAT_NUMBER = 118;
   private long nextAllocPageNumber;
   private long nextAllocPageOffset;
   private long reserved1;
   private long reserved2;
   private long reserved3;
   private long reserved4;
   private AllocExtent extent;
   private int borrowedSpace;
   protected static final int ALLOC_PAGE_HEADER_OFFSET = 60;
   protected static final int ALLOC_PAGE_HEADER_SIZE = 48;
   protected static final int BORROWED_SPACE_OFFSET = 108;
   protected static final int BORROWED_SPACE_LEN = 1;
   protected static final int MAX_BORROWED_SPACE = 204;
   public static final String TEST_MULTIPLE_ALLOC_PAGE = null;

   public int getTypeFormatId() {
      return 118;
   }

   protected int getMaxFreeSpace() {
      return super.getMaxFreeSpace() - 48 - 1 - this.borrowedSpace;
   }

   protected void createPage(PageKey var1, PageCreationArgs var2) throws StandardException {
      this.borrowedSpace = var2.containerInfoSize;
      super.createPage(var1, var2);
      this.pageData[108] = (byte)this.borrowedSpace;
      if (this.borrowedSpace > 0) {
         this.clearSection(109, this.borrowedSpace);
      }

      this.nextAllocPageNumber = -1L;
      this.nextAllocPageOffset = 0L;
      this.reserved1 = this.reserved2 = this.reserved3 = this.reserved4 = 0L;
      this.extent = this.createExtent(var1.getPageNumber() + 1L, this.getPageSize(), 0, this.totalSpace);
   }

   private AllocExtent createExtent(long var1, int var3, int var4, int var5) {
      int var6 = AllocExtent.MAX_RANGE(var5);
      return new AllocExtent(var1 * (long)var3, var1, var4, var3, var6);
   }

   protected void initFromData(FileContainer var1, PageKey var2) throws StandardException {
      if (this.pageData.length < 109) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", new Object[]{var2}));
      } else {
         byte var3 = this.pageData[108];
         this.borrowedSpace = var3;
         if (this.pageData.length < 109 + var3) {
            throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", new Object[]{var2}));
         } else {
            if (this.borrowedSpace > 0) {
               this.clearSection(109, this.borrowedSpace);
            }

            super.initFromData(var1, var2);

            try {
               this.readAllocPageHeader();
               int var4 = 109 + this.borrowedSpace;
               this.extent = this.readExtent(var4);
            } catch (IOException var5) {
               throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var5, new Object[]{var2}));
            } catch (ClassNotFoundException var6) {
               throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var6, new Object[]{var2}));
            }
         }
      }
   }

   protected void writePage(PageKey var1) throws StandardException {
      try {
         this.updateAllocPageHeader();
         byte var2 = this.pageData[108];
         if (var2 > 0) {
            this.clearSection(109, var2);
         }

         int var3 = 109 + var2;
         this.writeExtent(var3);
      } catch (IOException var4) {
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDB0.D", var4, new Object[]{var1}));
      }

      super.writePage(var1);
   }

   private void readAllocPageHeader() throws IOException {
      ArrayInputStream var1 = this.rawDataIn;
      var1.setPosition(60);
      this.nextAllocPageNumber = var1.readLong();
      this.nextAllocPageOffset = var1.readLong();
      this.reserved1 = var1.readLong();
      this.reserved2 = var1.readLong();
      this.reserved3 = var1.readLong();
      this.reserved4 = var1.readLong();
   }

   private void updateAllocPageHeader() throws IOException {
      this.rawDataOut.setPosition(60);
      this.logicalDataOut.writeLong(this.nextAllocPageNumber);
      this.logicalDataOut.writeLong(this.nextAllocPageOffset);
      this.logicalDataOut.writeLong(0L);
      this.logicalDataOut.writeLong(0L);
      this.logicalDataOut.writeLong(0L);
      this.logicalDataOut.writeLong(0L);
   }

   private AllocExtent readExtent(int var1) throws IOException, ClassNotFoundException {
      ArrayInputStream var2 = this.rawDataIn;
      this.rawDataIn.setPosition(var1);
      AllocExtent var3 = new AllocExtent();
      var3.readExternal(var2);
      return var3;
   }

   private void writeExtent(int var1) throws IOException {
      this.rawDataOut.setPosition(var1);
      this.extent.writeExternal(this.logicalDataOut);
   }

   public static void WriteContainerInfo(byte[] var0, byte[] var1, boolean var2) throws StandardException {
      int var3 = var0 == null ? 0 : var0.length;
      if (var3 + 1 + 108 > var1.length) {
      }

      if (var2) {
         var1[108] = (byte)var3;
      } else {
         byte var4 = var1[108];
         if (var4 != var3) {
            throw StandardException.newException("XSDB3.D", new Object[]{Integer.valueOf(var4), var3});
         }
      }

      if (var3 != 0) {
         System.arraycopy(var0, 0, var1, 109, var3);
      }

   }

   public static void ReadContainerInfo(byte[] var0, byte[] var1) throws StandardException {
      byte var2 = var1[108];
      if (var2 != 0) {
         try {
            System.arraycopy(var1, 109, var0, 0, var2);
         } catch (ArrayIndexOutOfBoundsException var4) {
            throw StandardException.newException("XSDA4.S", StandardException.newException("XSDG4.D", var4, new Object[]{var1.length, var0.length, 204, 108, 1, 109, Integer.valueOf(var2), StringUtil.hexDump(var1)}), new Object[0]);
         }
      }

   }

   public long nextFreePageNumber(long var1) {
      return this.extent.getFreePageNumber(var1);
   }

   public void addPage(FileContainer var1, long var2, RawTransaction var4, BaseContainerHandle var5) throws StandardException {
      this.owner.getAllocationActionSet().actionAllocatePage(var4, this, var2, 0, 2);
   }

   public void deallocatePage(BaseContainerHandle var1, long var2) throws StandardException {
      this.owner.getAllocationActionSet().actionAllocatePage(var1.getTransaction(), this, var2, 1, 0);
   }

   protected void updateUnfilledPageInfo(AllocExtent var1) {
      this.extent.updateUnfilledPageInfo(var1);
   }

   public boolean canAddFreePage(long var1) {
      if (this.extent.isRetired()) {
         return false;
      } else {
         return var1 != -1L && this.extent.getLastPagenum() <= var1 && !this.isLast() ? false : this.extent.canAddFreePage(var1);
      }
   }

   public long getNextAllocPageOffset() {
      return this.nextAllocPageOffset;
   }

   public void chainNewAllocPage(BaseContainerHandle var1, long var2, long var4) throws StandardException {
      this.owner.getAllocationActionSet().actionChainAllocPage(var1.getTransaction(), this, var2, var4);
   }

   public long getNextAllocPageNumber() {
      return this.nextAllocPageNumber;
   }

   public boolean isLast() {
      return this.nextAllocPageNumber == -1L;
   }

   public long getLastPagenum() {
      return this.extent.getLastPagenum();
   }

   public long getMaxPagenum() {
      return this.extent.getExtentEnd();
   }

   protected long getLastPreallocPagenum() {
      return this.extent.getLastPreallocPagenum();
   }

   protected int getPageStatus(long var1) {
      return this.extent.getPageStatus(var1);
   }

   protected void setPageStatus(LogInstant var1, long var2, int var4) throws StandardException {
      this.logAction(var1);
      switch (var4) {
         case 0 -> this.extent.allocPage(var2);
         case 1 -> this.extent.deallocPage(var2);
         case 2 -> this.extent.deallocPage(var2);
      }

   }

   protected void chainNextAllocPage(LogInstant var1, long var2, long var4) throws StandardException {
      this.logAction(var1);
      this.nextAllocPageNumber = var2;
      this.nextAllocPageOffset = var4;
   }

   protected void compressSpace(LogInstant var1, int var2, int var3) throws StandardException {
      this.logAction(var1);
      this.extent.compressPages(var2, var3);
   }

   protected void undoCompressSpace(LogInstant var1, int var2, int var3) throws StandardException {
      this.logAction(var1);
      this.extent.undoCompressPages(var2, var3);
   }

   public String toString() {
      return null;
   }

   protected AllocExtent getAllocExtent() {
      return this.extent;
   }

   protected void preAllocatePage(FileContainer var1, int var2, int var3) {
      long var4 = this.extent.getLastPreallocPagenum();
      if (var4 >= (long)var2) {
         if (this.extent.getExtentEnd() < var4 + (long)var3) {
            var3 = (int)(this.extent.getExtentEnd() - var4);
         }

         if (var3 > 0) {
            int var6 = var1.preAllocate(var4, var3);
            if (var6 > 0) {
               this.extent.setLastPreallocPagenum(var4 + (long)var6);
            }

         }
      }
   }

   protected boolean compress(RawTransaction var1, FileContainer var2) throws StandardException {
      boolean var3 = false;
      int var4 = this.extent.compress(this.owner, var1, this);
      if (var4 >= 0) {
         var2.truncatePages(this.extent.getPagenum(var4));
         if (var4 == 0) {
            var3 = true;
         }
      }

      return var3;
   }
}
