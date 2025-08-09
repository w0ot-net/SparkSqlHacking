package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

public abstract class CachedPage extends BasePage implements Cacheable {
   protected boolean alreadyReadPage;
   protected byte[] pageData;
   protected boolean isDirty;
   protected boolean preDirty;
   protected int initialRowCount;
   private long containerRowCount;
   protected CacheManager pageCache;
   protected CacheManager containerCache;
   protected BaseDataFileFactory dataFactory;
   protected static final int PAGE_FORMAT_ID_SIZE = 4;
   public static final int WRITE_SYNC = 1;
   public static final int WRITE_NO_SYNC = 2;

   public final void setFactory(BaseDataFileFactory var1) {
      this.dataFactory = var1;
      this.pageCache = var1.getPageCache();
      this.containerCache = var1.getContainerCache();
   }

   protected void initialize() {
      super.initialize();
      this.isDirty = false;
      this.preDirty = false;
      this.initialRowCount = 0;
      this.containerRowCount = 0L;
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      this.initialize();
      PageKey var2 = (PageKey)var1;
      FileContainer var3 = (FileContainer)this.containerCache.find(var2.getContainerId());
      this.setContainerRowCount(var3.getEstimatedRowCount(0));

      label55: {
         Cacheable var6;
         try {
            if (!this.alreadyReadPage) {
               this.readPage(var3, var2);
            } else {
               this.alreadyReadPage = false;
            }

            int var4 = this.getTypeFormatId();
            int var5 = FormatIdUtil.readFormatIdInteger(this.pageData);
            if (var4 == var5) {
               this.initFromData(var3, var2);
               break label55;
            }

            var6 = this.changeInstanceTo(var5, var2).setIdentity(var1);
         } finally {
            this.containerCache.release(var3);
            var3 = null;
         }

         return var6;
      }

      this.fillInIdentity(var2);
      this.initialRowCount = 0;
      return this;
   }

   public Cacheable createIdentity(Object var1, Object var2) throws StandardException {
      this.initialize();
      PageKey var3 = (PageKey)var1;
      PageCreationArgs var4 = (PageCreationArgs)var2;
      int var5 = var4.formatId;
      if (var5 == -1) {
         throw StandardException.newException("XSDBB.D", new Object[]{var3, StringUtil.hexDump(this.pageData)});
      } else if (var5 != this.getTypeFormatId()) {
         return this.changeInstanceTo(var5, var3).createIdentity(var1, var2);
      } else {
         this.initializeHeaders(5);
         this.createPage(var3, var4);
         this.fillInIdentity(var3);
         this.initialRowCount = 0;
         int var6 = var4.syncFlag;
         if ((var6 & 1) != 0 || (var6 & 2) != 0) {
            this.writePage(var3, (var6 & 1) != 0);
         }

         return this;
      }
   }

   private CachedPage changeInstanceTo(int var1, PageKey var2) throws StandardException {
      CachedPage var3;
      try {
         var3 = (CachedPage)Monitor.newInstanceFromIdentifier(var1);
      } catch (StandardException var5) {
         if (var5.getSeverity() > 20000) {
            throw var5;
         }

         throw StandardException.newException("XSDBB.D", new Object[]{var2, StringUtil.hexDump(this.pageData)});
      }

      var3.setFactory(this.dataFactory);
      if (this.pageData != null) {
         var3.alreadyReadPage = true;
         var3.usePageBuffer(this.pageData);
      }

      return var3;
   }

   public boolean isDirty() {
      synchronized(this) {
         return this.isDirty || this.preDirty;
      }
   }

   public boolean isActuallyDirty() {
      synchronized(this) {
         return this.isDirty;
      }
   }

   public void preDirty() {
      synchronized(this) {
         if (!this.isDirty) {
            this.preDirty = true;
         }

      }
   }

   protected void setDirty() {
      synchronized(this) {
         this.isDirty = true;
         this.preDirty = false;
      }
   }

   protected void releaseExclusive() {
      if (this.isDirty && !this.isOverflowPage() && this.containerRowCount / 8L < (long)this.recordCount()) {
         int var1 = this.internalNonDeletedRecordCount();
         int var2 = var1 - this.initialRowCount;
         int var3 = var2 > 0 ? var2 : -var2;
         if (this.containerRowCount / 8L < (long)var3) {
            FileContainer var4 = null;

            try {
               var4 = (FileContainer)this.containerCache.find(this.identity.getContainerId());
               if (var4 != null) {
                  var4.updateEstimatedRowCount(var2);
                  this.setContainerRowCount(var4.getEstimatedRowCount(0));
                  this.initialRowCount = var1;
                  var4.trackUnfilledPage(this.identity.getPageNumber(), this.unfilled());
               }
            } catch (StandardException var9) {
            } finally {
               if (var4 != null) {
                  this.containerCache.release(var4);
               }

            }
         }
      }

      super.releaseExclusive();
   }

   public void clean(boolean var1) throws StandardException {
      synchronized(this) {
         if (!this.isDirty()) {
            return;
         }

         while(this.inClean) {
            try {
               this.wait();
            } catch (InterruptedException var20) {
               InterruptStatus.setInterrupted();
            }
         }

         if (!this.isDirty()) {
            return;
         }

         this.inClean = true;

         while(this.owner != null && !this.preLatch) {
            try {
               this.wait();
            } catch (InterruptedException var19) {
               InterruptStatus.setInterrupted();
            }
         }

         if (!this.isActuallyDirty()) {
            this.preDirty = false;
            this.inClean = false;
            this.notifyAll();
            return;
         }
      }

      try {
         this.writePage(this.getPageId(), false);
      } catch (StandardException var17) {
         throw this.dataFactory.markCorrupt(var17);
      } finally {
         synchronized(this) {
            this.inClean = false;
            this.notifyAll();
         }
      }

   }

   public void clearIdentity() {
      this.alreadyReadPage = false;
      super.clearIdentity();
   }

   private void readPage(FileContainer var1, PageKey var2) throws StandardException {
      int var3 = var1.getPageSize();
      this.setPageArray(var3);
      int var4 = 0;

      while(true) {
         try {
            var1.readPage(var2.getPageNumber(), this.pageData);
            return;
         } catch (IOException var7) {
            ++var4;
            if (var4 > 4) {
               StandardException var6 = StandardException.newException("XSDG0.D", var7, new Object[]{var2, var3});
               if (this.dataFactory.getLogFactory().inRFR()) {
                  throw var6;
               }

               throw var6;
            }
         }
      }
   }

   private void writePage(PageKey var1, boolean var2) throws StandardException {
      this.writeFormatId(var1);
      this.writePage(var1);
      LogInstant var3 = this.getLastLogInstant();
      this.dataFactory.flush(var3);
      if (var3 != null) {
         this.clearLastLogInstant();
      }

      FileContainer var4 = (FileContainer)this.containerCache.find(var1.getContainerId());
      if (var4 == null) {
         StandardException var15 = StandardException.newException("40XD2", new Object[]{var1.getContainerId()});
         throw this.dataFactory.markCorrupt(StandardException.newException("XSDG1.D", var15, new Object[]{var1}));
      } else {
         try {
            var4.writePage(var1.getPageNumber(), this.pageData, var2);
            if (!this.isOverflowPage() && this.isDirty()) {
               var4.trackUnfilledPage(var1.getPageNumber(), this.unfilled());
               int var5 = this.internalNonDeletedRecordCount();
               if (var5 != this.initialRowCount) {
                  var4.updateEstimatedRowCount(var5 - this.initialRowCount);
                  this.setContainerRowCount(var4.getEstimatedRowCount(0));
                  this.initialRowCount = var5;
               }
            }
         } catch (IOException var12) {
            throw StandardException.newException("XSDG1.D", var12, new Object[]{var1});
         } finally {
            this.containerCache.release(var4);
            Object var14 = null;
         }

         synchronized(this) {
            this.isDirty = false;
            this.preDirty = false;
         }
      }
   }

   public void setContainerRowCount(long var1) {
      this.containerRowCount = var1;
   }

   protected void setPageArray(int var1) {
      if (this.pageData == null || this.pageData.length != var1) {
         this.pageData = null;
         this.pageData = new byte[var1];
      }

      this.usePageBuffer(this.pageData);
   }

   protected byte[] getPageArray() throws StandardException {
      this.writeFormatId(this.identity);
      this.writePage(this.identity);
      return this.pageData;
   }

   protected abstract void usePageBuffer(byte[] var1);

   protected abstract void initFromData(FileContainer var1, PageKey var2) throws StandardException;

   protected abstract void createPage(PageKey var1, PageCreationArgs var2) throws StandardException;

   protected abstract void writePage(PageKey var1) throws StandardException;

   protected abstract void writeFormatId(PageKey var1) throws StandardException;
}
