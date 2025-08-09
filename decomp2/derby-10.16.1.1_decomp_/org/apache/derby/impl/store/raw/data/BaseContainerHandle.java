package org.apache.derby.impl.store.raw.data;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.DerbyObservable;
import org.apache.derby.iapi.services.monitor.DerbyObserver;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.data.RawContainerHandle;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.iapi.util.ByteArray;
import org.apache.derby.shared.common.error.StandardException;

public class BaseContainerHandle extends DerbyObservable implements RawContainerHandle, DerbyObserver {
   private ContainerKey identity;
   private boolean active;
   protected BaseContainer container;
   private LockingPolicy locking;
   private RawTransaction xact;
   private boolean forUpdate;
   private int mode;
   private PageActions actionsSet;
   private AllocationActions allocActionsSet;

   public BaseContainerHandle(UUID var1, RawTransaction var2, ContainerKey var3, LockingPolicy var4, int var5) {
      this.identity = var3;
      this.xact = var2;
      this.locking = var4;
      this.mode = var5;
      this.forUpdate = (var5 & 4) == 4;
   }

   public BaseContainerHandle(UUID var1, RawTransaction var2, PageActions var3, AllocationActions var4, LockingPolicy var5, BaseContainer var6, int var7) {
      this(var1, var2, (ContainerKey)var6.getIdentity(), var5, var7);
      this.actionsSet = var3;
      this.allocActionsSet = var4;
      this.container = var6;
   }

   public Page addPage() throws StandardException {
      this.checkUpdateOpen();
      Page var1 = this.container.addPage(this, false);
      return var1;
   }

   public void compressContainer() throws StandardException {
      this.checkUpdateOpen();
      this.container.compressContainer(this);
   }

   public long getReusableRecordIdSequenceNumber() throws StandardException {
      this.checkOpen();
      return this.container.getReusableRecordIdSequenceNumber();
   }

   public Page addPage(int var1) throws StandardException {
      if ((var1 & 2) != 0 && this.active && this.forUpdate) {
         this.container.clearPreallocThreshold();
      }

      return this.addPage();
   }

   public void preAllocate(int var1) {
      if (var1 > 0 && this.active && this.forUpdate) {
         this.container.prepareForBulkLoad(this, var1);
      }

   }

   public void getContainerProperties(Properties var1) throws StandardException {
      this.checkOpen();
      this.container.getContainerProperties(var1);
   }

   public void removePage(Page var1) throws StandardException {
      if (!this.active) {
         if (var1 != null) {
            var1.unlatch();
         }

         throw StandardException.newException("40XD0", new Object[0]);
      } else if (!this.forUpdate) {
         if (var1 != null) {
            var1.unlatch();
         }

         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         this.container.removePage(this, (BasePage)var1);
      }
   }

   public Page getPage(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getPage(this, var1, true);
   }

   public Page getAllocPage(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getAllocPage(this, var1, true);
   }

   public Page getUserPageNoWait(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getHeadPage(this, var1, false);
   }

   public Page getUserPageWait(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getHeadPage(this, var1, true);
   }

   public Page getPageNoWait(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getPage(this, var1, false);
   }

   public Page getFirstPage() throws StandardException {
      this.checkOpen();
      return this.container.getFirstPage(this);
   }

   public Page getNextPage(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getNextPage(this, var1);
   }

   public Page getPageForInsert(int var1) throws StandardException {
      this.checkUpdateOpen();
      return this.container.getPageForInsert(this, var1);
   }

   public Page getPageForCompress(int var1, long var2) throws StandardException {
      this.checkUpdateOpen();
      return this.container.getPageForCompress(this, var1, var2);
   }

   public final boolean isReadOnly() {
      return !this.forUpdate;
   }

   public synchronized void close() {
      if (this.xact != null) {
         this.informObservers();
         this.active = false;
         this.getLockingPolicy().unlockContainer(this.xact, this);
         if (this.container != null) {
            this.container.letGo(this);
            this.container = null;
         }

         this.xact.deleteObserver(this);
         this.xact = null;
      }
   }

   public long getEstimatedRowCount(int var1) throws StandardException {
      this.checkOpen();
      return this.container.getEstimatedRowCount(var1);
   }

   public void setEstimatedRowCount(long var1, int var3) throws StandardException {
      this.checkOpen();
      this.container.setEstimatedRowCount(var1, var3);
   }

   public long getEstimatedPageCount(int var1) throws StandardException {
      this.checkOpen();
      return this.container.getEstimatedPageCount(this, var1);
   }

   public void flushContainer() throws StandardException {
      this.checkUpdateOpen();
      this.container.flushAll();
   }

   public void compactRecord(RecordHandle var1) throws StandardException {
      if (!this.forUpdate) {
         throw StandardException.newException("40XD1", new Object[0]);
      } else {
         PageKey var2 = (PageKey)var1.getPageId();
         BasePage var3 = (BasePage)this.getPage(var2.getPageNumber());
         if (var3 != null) {
            try {
               var3.compactRecord(var1);
            } finally {
               var3.unlatch();
            }
         }

      }
   }

   public int getContainerStatus() throws StandardException {
      this.checkOpen();
      return this.container.getContainerStatus();
   }

   public void removeContainer(LogInstant var1) throws StandardException {
      this.checkUpdateOpen();
      this.container.removeContainer(var1, true);
   }

   public ContainerKey getId() {
      return this.identity;
   }

   public Object getUniqueId() {
      return this;
   }

   public void dropContainer(LogInstant var1, boolean var2) throws StandardException {
      this.checkUpdateOpen();
      this.container.dropContainer(var1, var2);
   }

   public long getContainerVersion() throws StandardException {
      this.checkOpen();
      return this.container.getContainerVersion();
   }

   public Page getAnyPage(long var1) throws StandardException {
      this.checkOpen();
      return this.container.getAnyPage(this, var1, true);
   }

   public Page reCreatePageForRedoRecovery(int var1, long var2, long var4) throws StandardException {
      this.checkUpdateOpen();
      return this.container.reCreatePageForRedoRecovery(this, var1, var2, var4);
   }

   public ByteArray logCreateContainerInfo() throws StandardException {
      this.checkUpdateOpen();
      return this.container.logCreateContainerInfo();
   }

   public RecordHandle makeRecordHandle(long var1, int var3) throws StandardException {
      return new RecordId(this.identity, var1, var3);
   }

   public void update(DerbyObservable var1, Object var2) {
      if (this.xact != null) {
         if (!var2.equals(RawTransaction.COMMIT) && !var2.equals(RawTransaction.ABORT) && !var2.equals(this.identity)) {
            if (var2.equals(RawTransaction.SAVEPOINT_ROLLBACK)) {
               this.informObservers();
            } else {
               if (var2.equals(RawTransaction.LOCK_ESCALATE)) {
                  if (this.getLockingPolicy().getMode() != 1) {
                     return;
                  }

                  try {
                     this.getLockingPolicy().lockContainer(this.getTransaction(), this, false, this.forUpdate);
                  } catch (StandardException var4) {
                     this.xact.setObserverException(var4);
                  }
               }

            }
         } else {
            this.close();
         }
      }
   }

   public PageActions getActionSet() {
      return this.actionsSet;
   }

   public AllocationActions getAllocationActionSet() {
      return this.allocActionsSet;
   }

   public boolean useContainer(boolean var1, boolean var2) throws StandardException {
      boolean var3 = this.getLockingPolicy().lockContainer(this.getTransaction(), this, var2, this.forUpdate);
      if (!var3) {
         this.container = null;
         throw StandardException.newException("40XL1", new Object[0]);
      } else {
         if ((this.mode & 64) == 0) {
            if (!this.container.use(this, this.forUpdate, var1)) {
               this.getLockingPolicy().unlockContainer(this.xact, this);
               this.container = null;
               return false;
            }

            this.active = true;
         } else if (this.getLockingPolicy().getMode() != 1) {
            return true;
         }

         this.xact.addObserver(this);
         if ((this.mode & 1032) == 0) {
            if ((this.mode & 16) == 16) {
               this.xact.addObserver(new TruncateOnCommit(this.identity, true));
            } else if ((this.mode & 256) == 256) {
               this.xact.addObserver(new TruncateOnCommit(this.identity, false));
            }

            if ((this.mode & 32) == 32) {
               this.xact.addObserver(new DropOnCommit(this.identity));
            }

            if ((this.mode & 512) == 512) {
               this.xact.addObserver(new SyncOnCommit(this.identity));
            }
         }

         return true;
      }
   }

   public final RawTransaction getTransaction() {
      return this.xact;
   }

   public final LockingPolicy getLockingPolicy() {
      return this.locking;
   }

   public final void setLockingPolicy(LockingPolicy var1) {
      this.locking = var1;
   }

   public final boolean updateOK() {
      return this.forUpdate;
   }

   public int getMode() {
      return this.mode;
   }

   public void preDirty(boolean var1) throws StandardException {
      this.checkUpdateOpen();
      this.container.preDirty(var1);
   }

   public boolean isTemporaryContainer() throws StandardException {
      this.checkOpen();
      return this.identity != null && this.identity.getSegmentId() == -1L;
   }

   protected void checkOpen() throws StandardException {
      if (!this.active) {
         throw StandardException.newException("40XD0", new Object[0]);
      }
   }

   private void checkUpdateOpen() throws StandardException {
      if (!this.active) {
         throw StandardException.newException("40XD0", new Object[0]);
      } else if (!this.forUpdate) {
         throw StandardException.newException("40XD1", new Object[0]);
      }
   }

   protected void informObservers() {
      if (this.countObservers() != 0) {
         this.setChanged();
         this.notifyObservers();
      }

   }

   public SpaceInfo getSpaceInfo() throws StandardException {
      return this.container.getSpaceInfo(this);
   }

   public void backupContainer(String var1) throws StandardException {
      this.checkOpen();
      this.container.backupContainer(this, var1);
   }

   public void encryptOrDecryptContainer(String var1, boolean var2) throws StandardException {
      this.checkOpen();
      this.container.encryptOrDecryptContainer(this, var1, var2);
   }

   public String toString() {
      return super.toString();
   }
}
