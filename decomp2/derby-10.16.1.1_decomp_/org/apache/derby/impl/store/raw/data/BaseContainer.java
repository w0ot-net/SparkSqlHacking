package org.apache.derby.impl.store.raw.data;

import java.util.Hashtable;
import java.util.Properties;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

abstract class BaseContainer implements Lockable {
   protected ContainerKey identity;
   protected boolean isDropped;
   protected boolean isCommittedDrop;
   protected boolean isReusableRecordId = false;

   protected void fillInIdentity(ContainerKey var1) {
      this.identity = var1;
   }

   public void clearIdentity() {
      this.identity = null;
   }

   public Object getIdentity() {
      return this.identity;
   }

   public void lockEvent(Latch var1) {
   }

   public boolean requestCompatible(Object var1, Object var2) {
      return false;
   }

   public boolean lockerAlwaysCompatible() {
      return false;
   }

   public void unlockEvent(Latch var1) {
   }

   public void compressContainer(BaseContainerHandle var1) throws StandardException {
      RawTransaction var2 = var1.getTransaction().startNestedTopTransaction();
      int var3 = var1.getMode();
      if ((var3 & 2) == 0 && (var3 & 1) == 1) {
         var3 &= -2;
      }

      BaseContainerHandle var4 = (BaseContainerHandle)var2.openContainer(this.identity, (LockingPolicy)null, var3);
      if (var4 == null) {
         throw StandardException.newException("XSDAG.S", new Object[]{this.getSegmentId(), this.getContainerId()});
      } else {
         CompatibilitySpace var5 = var2.getCompatibilitySpace();
         var2.getLockFactory().lockObject(var5, var2, this, (Object)null, -1);

         try {
            this.incrementReusableRecordIdSequenceNumber();
            this.compressContainer(var2, var4);
         } finally {
            var2.commit();
            var2.close();
         }

      }
   }

   public abstract long getReusableRecordIdSequenceNumber();

   protected abstract void incrementReusableRecordIdSequenceNumber();

   public Page addPage(BaseContainerHandle var1, boolean var2) throws StandardException {
      RawTransaction var3 = var1.getTransaction().startNestedTopTransaction();
      int var4 = var1.getMode();
      if ((var4 & 2) == 0 && (var4 & 1) == 1) {
         var4 &= -2;
      }

      BaseContainerHandle var5 = (BaseContainerHandle)var3.openContainer(this.identity, (LockingPolicy)null, var4);
      if (var5 == null) {
         throw StandardException.newException("XSDAG.S", new Object[]{this.getSegmentId(), this.getContainerId()});
      } else {
         CompatibilitySpace var6 = var3.getCompatibilitySpace();
         var3.getLockFactory().lockObject(var6, var3, this, (Object)null, -1);
         BasePage var7 = null;

         try {
            var7 = this.newPage(var1, var3, var5, var2);
         } finally {
            if (var7 != null) {
               var3.commitNoSync(1);
            } else {
               var3.abort();
            }

            var3.close();
         }

         if (!this.identity.equals(var7.getPageId().getContainerId())) {
            throw StandardException.newException("XSDAC.S", new Object[]{this.identity, var7.getPageId().getContainerId()});
         } else {
            return var7;
         }
      }
   }

   public abstract void getContainerProperties(Properties var1) throws StandardException;

   protected void removePage(BaseContainerHandle var1, BasePage var2) throws StandardException {
      try {
         RecordHandle var3 = var2.makeRecordHandle(2);
         if (!this.getDeallocLock(var1, var3, false, false)) {
            throw StandardException.newException("XSDAI.S", new Object[]{var2.getIdentity()});
         }

         this.deallocatePage(var1, var2);
      } finally {
         if (var2 != null) {
            var2.unlatch();
         }

      }

   }

   protected boolean getDeallocLock(BaseContainerHandle var1, RecordHandle var2, boolean var3, boolean var4) throws StandardException {
      RawTransaction var5 = var1.getTransaction();
      LockingPolicy var6 = var5.newLockingPolicy(1, 4, true);
      PageKey var7 = new PageKey(this.identity, var2.getPageNumber());
      if (var6 != null) {
         return var4 ? var6.zeroDurationLockRecordForWrite(var5, var2, false, var3) : var6.lockRecordForWrite(var5, var2, false, var3);
      } else {
         throw StandardException.newException("XSDAI.S", new Object[]{var7});
      }
   }

   protected Page getAllocPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException {
      return this.latchPage(var1, this.getAllocPage(var2), var4);
   }

   protected Page getAnyPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException {
      return this.latchPage(var1, this.getAnyPage(var1, var2), var4);
   }

   protected Page getFirstPage(BaseContainerHandle var1) throws StandardException {
      return this.getFirstHeadPage(var1, true);
   }

   protected Page getNextPage(BaseContainerHandle var1, long var2) throws StandardException {
      return this.getNextHeadPage(var1, var2, true);
   }

   protected BasePage latchPage(BaseContainerHandle var1, BasePage var2, boolean var3) throws StandardException {
      if (var2 != null) {
         if (var3) {
            var2.setExclusive(var1);
         } else if (!var2.setExclusiveNoWait(var1)) {
            return null;
         }
      }

      return var2;
   }

   protected boolean use(BaseContainerHandle var1, boolean var2, boolean var3) throws StandardException {
      if (var2 && !this.canUpdate()) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         return var3 || !this.getDroppedState() && !this.getCommittedDropState();
      }
   }

   protected void letGo(BaseContainerHandle var1) {
      RawTransaction var2 = var1.getTransaction();
      var1.getLockingPolicy().unlockContainer(var2, var1);
   }

   protected boolean getDroppedState() {
      return this.isDropped;
   }

   protected boolean getCommittedDropState() {
      return this.isCommittedDrop;
   }

   protected boolean isReusableRecordId() {
      return this.isReusableRecordId;
   }

   public int getContainerStatus() {
      if (this.getCommittedDropState()) {
         return 4;
      } else {
         return this.getDroppedState() ? 2 : 1;
      }
   }

   public long getContainerId() {
      return this.identity.getContainerId();
   }

   public long getSegmentId() {
      return this.identity.getSegmentId();
   }

   protected abstract SpaceInfo getSpaceInfo(BaseContainerHandle var1) throws StandardException;

   protected abstract boolean canUpdate();

   protected abstract void preDirty(boolean var1);

   protected abstract BasePage getPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException;

   protected abstract BasePage getAllocPage(long var1) throws StandardException;

   protected abstract BasePage getAnyPage(BaseContainerHandle var1, long var2) throws StandardException;

   protected abstract BasePage reCreatePageForRedoRecovery(BaseContainerHandle var1, int var2, long var3, long var5) throws StandardException;

   protected abstract ByteArray logCreateContainerInfo() throws StandardException;

   protected abstract BasePage getHeadPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException;

   protected abstract BasePage getFirstHeadPage(BaseContainerHandle var1, boolean var2) throws StandardException;

   protected abstract BasePage getNextHeadPage(BaseContainerHandle var1, long var2, boolean var4) throws StandardException;

   protected abstract BasePage getPageForInsert(BaseContainerHandle var1, int var2) throws StandardException;

   protected abstract BasePage getPageForCompress(BaseContainerHandle var1, int var2, long var3) throws StandardException;

   protected abstract void truncatePages(long var1) throws StandardException;

   protected abstract BasePage newPage(BaseContainerHandle var1, RawTransaction var2, BaseContainerHandle var3, boolean var4) throws StandardException;

   protected abstract void compressContainer(RawTransaction var1, BaseContainerHandle var2) throws StandardException;

   protected abstract void deallocatePage(BaseContainerHandle var1, BasePage var2) throws StandardException;

   protected void truncate(BaseContainerHandle var1) throws StandardException {
   }

   protected abstract void dropContainer(LogInstant var1, boolean var2);

   protected abstract void removeContainer(LogInstant var1, boolean var2) throws StandardException;

   protected abstract long getContainerVersion() throws StandardException;

   protected abstract void flushAll() throws StandardException;

   protected abstract void prepareForBulkLoad(BaseContainerHandle var1, int var2);

   protected abstract void clearPreallocThreshold();

   public abstract long getEstimatedRowCount(int var1) throws StandardException;

   public abstract void setEstimatedRowCount(long var1, int var3) throws StandardException;

   public abstract long getEstimatedPageCount(BaseContainerHandle var1, int var2) throws StandardException;

   protected abstract void backupContainer(BaseContainerHandle var1, String var2) throws StandardException;

   protected abstract void encryptOrDecryptContainer(BaseContainerHandle var1, String var2, boolean var3) throws StandardException;

   protected void setDroppedState(boolean var1) {
      this.isDropped = var1;
   }

   protected void setCommittedDropState(boolean var1) {
      this.isCommittedDrop = var1;
   }

   protected void setReusableRecordIdState(boolean var1) {
      this.isReusableRecordId = var1;
   }

   public boolean lockAttributes(int var1, Hashtable var2) {
      return false;
   }
}
