package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.OutputStream;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.LimitObjectInput;
import org.apache.derby.iapi.services.io.TypedFormat;
import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.services.monitor.DerbyObserver;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.raw.AuxObject;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

abstract class BasePage implements Page, DerbyObserver, TypedFormat {
   private AuxObject auxObj;
   protected PageKey identity;
   private StoredRecordHeader[] headers;
   private int recordCount;
   protected BaseContainerHandle owner;
   private int nestedLatch;
   protected boolean inClean;
   protected boolean preLatch;
   private LogInstant lastLog;
   private long repositionNeededAfterVersion;
   private long pageVersion = 0L;
   private byte pageStatus;
   public static final byte VALID_PAGE = 1;
   public static final byte INVALID_PAGE = 2;
   public static final int INIT_PAGE_REUSE = 1;
   public static final int INIT_PAGE_OVERFLOW = 2;
   public static final int INIT_PAGE_REUSE_RECORDID = 4;
   public static final int LOG_RECORD_DEFAULT = 0;
   public static final int LOG_RECORD_FOR_UPDATE = 1;
   public static final int LOG_RECORD_FOR_PURGE = 2;
   private static final RecordHandle InvalidRecordHandle = new RecordId(new PageKey(new ContainerKey(0L, 0L), -1L), 0);

   protected BasePage() {
   }

   protected void initialize() {
      this.setAuxObject((AuxObject)null);
      this.identity = null;
      this.recordCount = 0;
      this.clearLastLogInstant();
      this.repositionNeededAfterVersion = 0L;
   }

   protected void initializeHeaders(int var1) {
      this.headers = new StoredRecordHeader[var1];
   }

   protected void fillInIdentity(PageKey var1) {
      this.identity = var1;
      this.repositionNeededAfterVersion = this.pageVersion;
   }

   public void clearIdentity() {
      this.identity = null;
      this.cleanPageForReuse();
   }

   protected void cleanPageForReuse() {
      this.setAuxObject((AuxObject)null);
      this.recordCount = 0;
      this.repositionNeededAfterVersion = 0L;
   }

   public Object getIdentity() {
      return this.identity;
   }

   public final RecordHandle getInvalidRecordHandle() {
      return InvalidRecordHandle;
   }

   public static final RecordHandle MakeRecordHandle(PageKey var0, int var1) throws StandardException {
      if (var1 >= 6) {
         throw StandardException.newException("XSDAE.S", new Object[]{var1});
      } else {
         return new RecordId(var0, var1);
      }
   }

   public final RecordHandle makeRecordHandle(int var1) throws StandardException {
      return MakeRecordHandle(this.getPageId(), var1);
   }

   public final long getPageNumber() {
      return this.identity.getPageNumber();
   }

   public final PageKey getPageKey() {
      return this.identity;
   }

   public final RecordHandle getRecordHandle(int var1) {
      int var2 = this.findRecordById(var1, 0);
      return var2 < 0 ? null : this.getRecordHandleAtSlot(var2);
   }

   public final RecordHandle getRecordHandleAtSlot(int var1) {
      return this.getHeaderAtSlot(var1).getHandle(this.getPageId(), var1);
   }

   public final boolean recordExists(RecordHandle var1, boolean var2) throws StandardException {
      if (var1.getId() < 6) {
         throw StandardException.newException("XSDAF.S", new Object[]{var1});
      } else if (var1.getPageNumber() != this.getPageNumber()) {
         return false;
      } else {
         int var3 = this.findRecordById(var1.getId(), var1.getSlotNumberHint());
         return var3 >= 0 && (var2 || !this.isDeletedAtSlot(var3));
      }
   }

   public RecordHandle fetchFromSlot(RecordHandle var1, int var2, Object[] var3, FetchDescriptor var4, boolean var5) throws StandardException {
      this.checkSlotOnPage(var2);
      StoredRecordHeader var6 = this.getHeaderAtSlot(var2);
      if (var1 == null) {
         var1 = var6.getHandle(this.getPageId(), var2);
      }

      if (!var5 && var6.isDeleted()) {
         return null;
      } else {
         return this.restoreRecordFromSlot(var2, var3, var4, var1, var6, true) ? var1 : null;
      }
   }

   public final RecordHandle fetchFieldFromSlot(int var1, int var2, Object var3) throws StandardException {
      Object[] var4 = new Object[var2 + 1];
      var4[var2] = var3;
      FetchDescriptor var5 = new FetchDescriptor(var2 + 1, var2);
      return this.fetchFromSlot((RecordHandle)null, var1, var4, var5, true);
   }

   public final int getSlotNumber(RecordHandle var1) throws StandardException {
      int var2 = this.findRecordById(var1.getId(), var1.getSlotNumberHint());
      if (var2 < 0) {
         throw StandardException.newException("XSRS9.S", new Object[]{var1});
      } else {
         return var2;
      }
   }

   public final int getNextSlotNumber(RecordHandle var1) throws StandardException {
      int var2 = this.findNextRecordById(var1.getId());
      return var2;
   }

   public RecordHandle insertAtSlot(int var1, Object[] var2, FormatableBitSet var3, LogicalUndo var4, byte var5, int var6) throws StandardException {
      return (var5 & 1) == 1 ? this.insertNoOverflow(var1, var2, var3, var4, var5, var6) : this.insertAllowOverflow(var1, var2, var3, 0, var5, var6, (RecordHandle)null);
   }

   protected RecordHandle insertNoOverflow(int var1, Object[] var2, FormatableBitSet var3, LogicalUndo var4, byte var5, int var6) throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else if (var1 >= 0 && var1 <= this.recordCount) {
         if (!this.allowInsert()) {
            return null;
         } else {
            RawTransaction var7 = this.owner.getTransaction();
            if (var4 != null) {
               var7.checkLogicalOperationOk();
            }

            int var8;
            RecordId var9;
            do {
               var8 = this.newRecordIdAndBump();
               var9 = new RecordId(this.getPageId(), var8, var1);
            } while(!this.owner.getLockingPolicy().lockRecordForWrite(var7, var9, true, false));

            this.owner.getActionSet().actionInsert(var7, this, var1, var8, var2, var3, var4, var5, 0, false, -1, (DynamicByteArrayOutputStream)null, -1, var6);
            return var9;
         }
      } else {
         throw StandardException.newException("XSDA1.S", new Object[0]);
      }
   }

   public final RecordHandle insert(Object[] var1, FormatableBitSet var2, byte var3, int var4) throws StandardException {
      return (var3 & 1) == 1 ? this.insertAtSlot(this.recordCount, var1, var2, (LogicalUndo)null, var3, var4) : this.insertAllowOverflow(this.recordCount, var1, var2, 0, var3, var4, (RecordHandle)null);
   }

   public RecordHandle insertAllowOverflow(int var1, Object[] var2, FormatableBitSet var3, int var4, byte var5, int var6, RecordHandle var7) throws StandardException {
      BasePage var8 = this;
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         RecordId var9 = null;
         RecordId var10 = null;

         BasePage var18;
         for(RawTransaction var11 = this.owner.getTransaction(); var8.allowInsert(); var8 = var18) {
            if (var8 != this) {
               var1 = var8.recordCount;
            }

            boolean var12 = false;
            int var13 = -1;
            int var14 = -1;
            DynamicByteArrayOutputStream var15 = null;
            int var16 = var8.newRecordIdAndBump();
            RecordId var17 = new RecordId(var8.getPageId(), var16, var1);
            if (var8 == this) {
               if (var10 == null) {
                  while(!this.owner.getLockingPolicy().lockRecordForWrite(var11, var17, true, false)) {
                     var16 = var8.newRecordIdAndBump();
                     var17 = new RecordId(var8.getPageId(), var16, var1);
                  }
               }

               var9 = var17;
            }

            do {
               try {
                  var4 = this.owner.getActionSet().actionInsert(var11, var8, var1, var16, var2, var3, (LogicalUndo)null, var5, var4, false, var13, var15, var14, var6);
                  var12 = false;
               } catch (LongColumnException var23) {
                  var15 = new DynamicByteArrayOutputStream(var23.getLogBuffer());
                  RecordHandle var19 = this.insertLongColumn(var8, var23, var5);
                  int var20 = 0;

                  try {
                     var20 += this.appendOverflowFieldHeader(var15, var19);
                  } catch (IOException var22) {
                     return null;
                  }

                  var13 = var23.getNextColumn() + 1;
                  var14 = var23.getRealSpaceOnPage() - var20;
                  var12 = true;
               }
            } while(var12);

            if (var10 != null) {
               this.updateOverflowDetails(var10, var17);
            }

            if (var4 == -1) {
               if (var8 != this) {
                  var8.unlatch();
               }

               if (var7 != null) {
                  this.updateOverflowDetails(var17, var7);
               }

               return var9;
            }

            var10 = var17;
            var18 = var8.getOverflowPageForInsert(var1, var2, var3, var4);
            if (var8 != this) {
               var8.unlatch();
            }
         }

         return null;
      }
   }

   protected RecordHandle insertLongColumn(BasePage var1, LongColumnException var2, byte var3) throws StandardException {
      Object[] var4 = new Object[]{var2.getColumn()};
      RecordId var5 = null;
      RecordId var6 = null;
      RecordId var7 = null;
      BasePage var8 = var1;
      BasePage var9 = null;
      boolean var10 = true;
      byte var11 = (byte)(var3 | 2);
      int var12 = 0;
      RawTransaction var13 = var1.owner.getTransaction();

      do {
         if (!var10) {
            var9 = var8;
            var7 = var6;
         }

         var8 = this.getNewOverflowPage();
         int var14 = var8.recordCount;
         int var15 = var8.newRecordId();
         var6 = new RecordId(var8.getPageId(), var15, var14);
         if (var10) {
            var5 = var6;
         }

         var12 = this.owner.getActionSet().actionInsert(var13, var8, var14, var15, var4, (FormatableBitSet)null, (LogicalUndo)null, var10 ? var3 : var11, var12, true, -1, (DynamicByteArrayOutputStream)null, -1, 100);
         if (!var10) {
            var9.updateFieldOverflowDetails(var7, var6);
            var9.unlatch();
            var9 = null;
         } else {
            var10 = false;
         }
      } while(var12 != -1);

      if (var8 != null) {
         var8.unlatch();
         Object var16 = null;
      }

      return var5;
   }

   public abstract void preDirty();

   public abstract void updateOverflowDetails(RecordHandle var1, RecordHandle var2) throws StandardException;

   public abstract void updateFieldOverflowDetails(RecordHandle var1, RecordHandle var2) throws StandardException;

   public abstract int appendOverflowFieldHeader(DynamicByteArrayOutputStream var1, RecordHandle var2) throws StandardException, IOException;

   public abstract BasePage getOverflowPageForInsert(int var1, Object[] var2, FormatableBitSet var3, int var4) throws StandardException;

   protected abstract BasePage getNewOverflowPage() throws StandardException;

   public final RecordHandle updateAtSlot(int var1, Object[] var2, FormatableBitSet var3) throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else if (this.isDeletedAtSlot(var1)) {
         throw StandardException.newException("XSDA2.S", new Object[0]);
      } else {
         RecordHandle var4 = this.getRecordHandleAtSlot(var1);
         RawTransaction var5 = this.owner.getTransaction();
         this.doUpdateAtSlot(var5, var1, var4.getId(), var2, var3);
         return var4;
      }
   }

   public abstract void doUpdateAtSlot(RawTransaction var1, int var2, int var3, Object[] var4, FormatableBitSet var5) throws StandardException;

   public RecordHandle updateFieldAtSlot(int var1, int var2, Object var3, LogicalUndo var4) throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else if (this.isDeletedAtSlot(var1)) {
         throw StandardException.newException("XSDA2.S", new Object[0]);
      } else {
         RawTransaction var5 = this.owner.getTransaction();
         RecordHandle var6 = this.getRecordHandleAtSlot(var1);
         this.owner.getActionSet().actionUpdateField(var5, this, var1, var6.getId(), var2, var3, var4);
         return var6;
      }
   }

   public final int fetchNumFields(RecordHandle var1) throws StandardException {
      return this.fetchNumFieldsAtSlot(this.getSlotNumber(var1));
   }

   public int fetchNumFieldsAtSlot(int var1) throws StandardException {
      return this.getHeaderAtSlot(var1).getNumberFields();
   }

   public RecordHandle deleteAtSlot(int var1, boolean var2, LogicalUndo var3) throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         if (var2) {
            if (this.isDeletedAtSlot(var1)) {
               throw StandardException.newException("XSDA2.S", new Object[0]);
            }
         } else if (!this.isDeletedAtSlot(var1)) {
            throw StandardException.newException("XSDA5.S", new Object[0]);
         }

         RawTransaction var4 = this.owner.getTransaction();
         if (var3 != null) {
            var4.checkLogicalOperationOk();
         }

         RecordHandle var5 = this.getRecordHandleAtSlot(var1);
         this.owner.getActionSet().actionDelete(var4, this, var1, var5.getId(), var2, var3);
         return var5;
      }
   }

   public void purgeAtSlot(int var1, int var2, boolean var3) throws StandardException {
      if (var2 > 0) {
         if (!this.owner.updateOK()) {
            throw StandardException.newException("40XD1", new Object[0]);
         } else if (var1 >= 0 && var1 + var2 <= this.recordCount) {
            RawTransaction var4 = this.owner.getTransaction();
            int[] var5 = new int[var2];
            PageKey var6 = this.getPageId();

            for(int var7 = 0; var7 < var2; ++var7) {
               var5[var7] = this.getHeaderAtSlot(var1 + var7).getId();
               RecordHandle var8 = this.getRecordHandleAtSlot(var1);
               this.owner.getLockingPolicy().lockRecordForWrite(var4, var8, false, true);
               if (!this.owner.isTemporaryContainer() && !this.entireRecordOnPage(var1 + var7)) {
                  RecordHandle var9 = this.getHeaderAtSlot(var1 + var7).getHandle(var6, var1 + var7);
                  this.purgeRowPieces(var4, var1 + var7, var9, var3);
               }
            }

            this.owner.getActionSet().actionPurge(var4, this, var1, var2, var5, var3);
         } else {
            throw StandardException.newException("XSDA1.S", new Object[0]);
         }
      }
   }

   protected abstract void purgeRowPieces(RawTransaction var1, int var2, RecordHandle var3, boolean var4) throws StandardException;

   public void copyAndPurge(Page var1, int var2, int var3, int var4) throws StandardException {
      if (var3 <= 0) {
         throw StandardException.newException("XSDAD.S", new Object[0]);
      } else if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else if (var2 >= 0 && var2 + var3 <= this.recordCount) {
         BasePage var5 = (BasePage)var1;
         PageKey var6 = this.getPageId();
         if (!var6.getContainerId().equals(var5.getPageId().getContainerId())) {
            throw StandardException.newException("XSDAC.S", new Object[]{var6.getContainerId(), var5.getPageId().getContainerId()});
         } else {
            int[] var7 = new int[var3];
            RawTransaction var8 = this.owner.getTransaction();

            for(int var9 = 0; var9 < var3; ++var9) {
               RecordHandle var10 = this.getRecordHandleAtSlot(var2 + var9);
               this.owner.getLockingPolicy().lockRecordForWrite(var8, var10, false, true);
               var7[var9] = this.getHeaderAtSlot(var2 + var9).getId();
            }

            var5.copyInto(this, var2, var3, var4);
            this.owner.getActionSet().actionPurge(var8, this, var2, var3, var7, true);
         }
      } else {
         throw StandardException.newException("XSDA1.S", new Object[0]);
      }
   }

   public void unlatch() {
      this.releaseExclusive();
   }

   public final synchronized boolean isLatched() {
      return this.owner != null;
   }

   public final int recordCount() {
      return this.recordCount;
   }

   protected abstract int internalDeletedRecordCount();

   protected int internalNonDeletedRecordCount() {
      if (this.pageStatus != 1) {
         return 0;
      } else {
         int var1 = this.internalDeletedRecordCount();
         if (var1 == -1) {
            int var2 = 0;
            int var3 = this.recordCount;

            for(int var4 = 0; var4 < var3; ++var4) {
               if (!this.isDeletedOnPage(var4)) {
                  ++var2;
               }
            }

            return var2;
         } else {
            return this.recordCount - var1;
         }
      }
   }

   public int nonDeletedRecordCount() {
      return this.internalNonDeletedRecordCount();
   }

   public boolean shouldReclaimSpace(int var1, int var2) throws StandardException {
      boolean var3 = false;
      if (!this.isOverflowPage()) {
         if (this.internalNonDeletedRecordCount() <= var1) {
            var3 = true;
         } else if (!this.entireRecordOnPage(var2)) {
            var3 = true;
         }
      }

      return var3;
   }

   protected final boolean isDeletedOnPage(int var1) {
      return this.getHeaderAtSlot(var1).isDeleted();
   }

   public boolean isDeletedAtSlot(int var1) throws StandardException {
      this.checkSlotOnPage(var1);
      return this.isDeletedOnPage(var1);
   }

   public void setAuxObject(AuxObject var1) {
      if (this.auxObj != null) {
         this.auxObj.auxObjectInvalidated();
      }

      this.auxObj = var1;
   }

   public AuxObject getAuxObject() {
      return this.auxObj;
   }

   public void setRepositionNeeded() {
      this.repositionNeededAfterVersion = this.getPageVersion();
   }

   public boolean isRepositionNeeded(long var1) {
      return this.repositionNeededAfterVersion > var1;
   }

   public void update(DerbyObservable var1, Object var2) {
      this.releaseExclusive();
   }

   public final PageKey getPageId() {
      return this.identity;
   }

   void setExclusive(BaseContainerHandle var1) throws StandardException {
      RawTransaction var2 = var1.getTransaction();
      synchronized(this) {
         if (this.owner != null && var2 == this.owner.getTransaction()) {
            if (var2.inAbort()) {
               ++this.nestedLatch;
            } else {
               throw StandardException.newException("XSDAO.S", new Object[]{this.identity});
            }
         } else {
            while(this.owner != null) {
               try {
                  this.wait();
               } catch (InterruptedException var7) {
                  InterruptStatus.setInterrupted();
               }
            }

            this.preLatch(var1);

            while(this.inClean) {
               try {
                  this.wait();
               } catch (InterruptedException var6) {
                  InterruptStatus.setInterrupted();
               }
            }

            this.preLatch = false;
         }
      }
   }

   boolean setExclusiveNoWait(BaseContainerHandle var1) throws StandardException {
      RawTransaction var2 = var1.getTransaction();
      synchronized(this) {
         if (this.owner != null && var2 == this.owner.getTransaction() && var2.inAbort()) {
            ++this.nestedLatch;
            return true;
         } else if (this.owner != null) {
            return false;
         } else {
            this.preLatch(var1);

            while(this.inClean) {
               try {
                  this.wait();
               } catch (InterruptedException var6) {
                  InterruptStatus.setInterrupted();
               }
            }

            this.preLatch = false;
            return true;
         }
      }
   }

   protected synchronized void releaseExclusive() {
      if (this.nestedLatch > 0) {
         --this.nestedLatch;
      } else {
         this.owner.deleteObserver(this);
         this.owner = null;
         this.notifyAll();
      }
   }

   private void preLatch(BaseContainerHandle var1) {
      this.owner = var1;
      var1.addObserver(this);
      this.preLatch = true;
   }

   protected final void setHeaderAtSlot(int var1, StoredRecordHeader var2) {
      if (var1 < this.headers.length) {
         if (var2 != null) {
            this.headers[var1] = var2;
         }
      } else {
         StoredRecordHeader[] var3 = new StoredRecordHeader[var1 + 1];
         System.arraycopy(this.headers, 0, var3, 0, this.headers.length);
         this.headers = var3;
         this.headers[var1] = var2;
      }

   }

   protected final void bumpRecordCount(int var1) {
      this.recordCount += var1;
   }

   public final StoredRecordHeader getHeaderAtSlot(int var1) {
      if (var1 < this.headers.length) {
         StoredRecordHeader var2 = this.headers[var1];
         return var2 != null ? var2 : this.recordHeaderOnDemand(var1);
      } else {
         return this.recordHeaderOnDemand(var1);
      }
   }

   public abstract boolean entireRecordOnPage(int var1) throws StandardException;

   public abstract StoredRecordHeader recordHeaderOnDemand(int var1);

   private final void checkSlotOnPage(int var1) throws StandardException {
      if (var1 < 0 || var1 >= this.recordCount) {
         throw StandardException.newException("XSDA1.S", new Object[0]);
      }
   }

   public int setDeleteStatus(int var1, boolean var2) throws StandardException, IOException {
      return this.getHeaderAtSlot(var1).setDeleted(var2);
   }

   public void deallocatePage() throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         RawTransaction var1 = this.owner.getTransaction();
         this.owner.getActionSet().actionInvalidatePage(var1, this);
      }
   }

   public void initPage(int var1, long var2) throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         RawTransaction var4 = this.owner.getTransaction();
         this.owner.getActionSet().actionInitPage(var4, this, var1, this.getTypeFormatId(), var2);
      }
   }

   public int findRecordById(int var1, int var2) {
      if (var2 == 0) {
         var2 = var1 - 6;
      }

      int var3 = this.recordCount();
      if (var2 > 0 && var2 < var3 && var1 == this.getHeaderAtSlot(var2).getId()) {
         return var2;
      } else {
         for(int var4 = 0; var4 < var3; ++var4) {
            if (var1 == this.getHeaderAtSlot(var4).getId()) {
               return var4;
            }
         }

         return -1;
      }
   }

   private int findNextRecordById(int var1) {
      int var2 = this.recordCount();

      for(int var3 = 0; var3 < var2; ++var3) {
         if (this.getHeaderAtSlot(var3).getId() > var1) {
            return var3;
         }
      }

      return -1;
   }

   private void copyInto(BasePage var1, int var2, int var3, int var4) throws StandardException {
      if (var4 >= 0 && var4 <= this.recordCount) {
         RawTransaction var5 = this.owner.getTransaction();
         int[] var6 = new int[var3];
         PageKey var7 = this.getPageId();

         for(int var8 = 0; var8 < var3; ++var8) {
            if (var8 == 0) {
               var6[var8] = this.newRecordId();
            } else {
               var6[var8] = this.newRecordId(var6[var8 - 1]);
            }

            RecordId var9 = new RecordId(var7, var6[var8], var8);
            this.owner.getLockingPolicy().lockRecordForWrite(var5, var9, false, true);
         }

         this.owner.getActionSet().actionCopyRows(var5, this, var1, var4, var3, var2, var6);
      } else {
         throw StandardException.newException("XSDA1.S", new Object[0]);
      }
   }

   protected void removeAndShiftDown(int var1) {
      System.arraycopy(this.headers, var1 + 1, this.headers, var1, this.headers.length - (var1 + 1));
      this.headers[this.headers.length - 1] = null;
      --this.recordCount;
   }

   protected StoredRecordHeader shiftUp(int var1) {
      if (var1 < this.headers.length) {
         System.arraycopy(this.headers, var1, this.headers, var1 + 1, this.headers.length - (var1 + 1));
         this.headers[var1] = null;
      }

      return null;
   }

   public void compactRecord(RecordHandle var1) throws StandardException {
      if (!this.owner.updateOK()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else if (var1.getId() < 6) {
         throw StandardException.newException("XSDAF.S", new Object[]{var1});
      } else if (var1.getPageNumber() != this.getPageNumber()) {
         throw StandardException.newException("XSDAK.S", new Object[]{var1});
      } else if (this.isOverflowPage()) {
         throw StandardException.newException("XSDAL.S", new Object[]{var1});
      } else {
         int var2 = this.findRecordById(var1.getId(), var1.getSlotNumberHint());
         if (var2 >= 0) {
            this.compactRecord(this.owner.getTransaction(), var2, var1.getId());
         }

      }
   }

   public final LogInstant getLastLogInstant() {
      return this.lastLog;
   }

   protected final void clearLastLogInstant() {
      this.lastLog = null;
   }

   protected final void updateLastLogInstant(LogInstant var1) {
      if (var1 != null) {
         this.lastLog = var1;
      }

   }

   public final long getPageVersion() {
      return this.pageVersion;
   }

   protected final long bumpPageVersion() {
      return ++this.pageVersion;
   }

   public final void setPageVersion(long var1) {
      this.pageVersion = var1;
   }

   protected void setPageStatus(byte var1) {
      this.pageStatus = var1;
   }

   public byte getPageStatus() {
      return this.pageStatus;
   }

   protected abstract boolean restoreRecordFromSlot(int var1, Object[] var2, FetchDescriptor var3, RecordHandle var4, StoredRecordHeader var5, boolean var6) throws StandardException;

   protected abstract void restorePortionLongColumn(OverflowInputStream var1) throws StandardException, IOException;

   public abstract int newRecordId() throws StandardException;

   public abstract int newRecordIdAndBump() throws StandardException;

   protected abstract int newRecordId(int var1) throws StandardException;

   public abstract boolean spaceForCopy(int var1, int[] var2) throws StandardException;

   public abstract int getTotalSpace(int var1) throws StandardException;

   public abstract int getReservedCount(int var1) throws IOException;

   public abstract int getRecordLength(int var1) throws IOException;

   public abstract void restoreRecordFromStream(LimitObjectInput var1, Object[] var2) throws StandardException, IOException;

   public abstract void logRecord(int var1, int var2, int var3, FormatableBitSet var4, OutputStream var5, RecordHandle var6) throws StandardException, IOException;

   public abstract int logRow(int var1, boolean var2, int var3, Object[] var4, FormatableBitSet var5, DynamicByteArrayOutputStream var6, int var7, byte var8, int var9, int var10, int var11) throws StandardException, IOException;

   public abstract void logField(int var1, int var2, OutputStream var3) throws StandardException, IOException;

   public abstract void logColumn(int var1, int var2, Object var3, DynamicByteArrayOutputStream var4, int var5) throws StandardException, IOException;

   public abstract int logLongColumn(int var1, int var2, Object var3, DynamicByteArrayOutputStream var4) throws StandardException, IOException;

   public abstract void storeRecord(LogInstant var1, int var2, boolean var3, ObjectInput var4) throws StandardException, IOException;

   public abstract void storeField(LogInstant var1, int var2, int var3, ObjectInput var4) throws StandardException, IOException;

   public abstract void reserveSpaceForSlot(LogInstant var1, int var2, int var3) throws StandardException, IOException;

   public abstract void skipField(ObjectInput var1) throws StandardException, IOException;

   public abstract void skipRecord(ObjectInput var1) throws StandardException, IOException;

   public abstract void setDeleteStatus(LogInstant var1, int var2, boolean var3) throws StandardException, IOException;

   public abstract void purgeRecord(LogInstant var1, int var2, int var3) throws StandardException, IOException;

   protected abstract void compactRecord(RawTransaction var1, int var2, int var3) throws StandardException;

   public abstract void setPageStatus(LogInstant var1, byte var2) throws StandardException;

   public abstract void initPage(LogInstant var1, byte var2, int var3, boolean var4, boolean var5) throws StandardException;

   public abstract void setReservedSpace(LogInstant var1, int var2, int var3) throws StandardException, IOException;

   public abstract boolean isOverflowPage();

   public abstract boolean allowInsert();

   public abstract boolean unfilled();

   public abstract void setContainerRowCount(long var1);

   protected abstract byte[] getPageArray() throws StandardException;

   protected String slotTableToString() {
      Object var1 = null;
      return (String)var1;
   }
}
