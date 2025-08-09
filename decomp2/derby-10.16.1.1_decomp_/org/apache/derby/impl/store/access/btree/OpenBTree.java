package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerateScratchSpace;
import org.apache.derby.shared.common.error.StandardException;

public class OpenBTree {
   private BTree init_conglomerate;
   private TransactionManager init_xact_manager;
   private Transaction init_rawtran;
   private int init_openmode;
   protected int init_lock_level;
   private boolean init_hold;
   private BTreeLockingPolicy init_btree_locking_policy;
   protected ContainerHandle container;
   protected long err_containerid;
   protected TransactionManager init_open_user_scans = null;
   protected LogicalUndo btree_undo = null;
   protected OpenConglomerateScratchSpace runtime_mem;

   public final TransactionManager getXactMgr() {
      return this.init_xact_manager;
   }

   public final Transaction getRawTran() {
      return this.init_rawtran;
   }

   public final int getLockLevel() {
      return this.init_lock_level;
   }

   public final ContainerHandle getContainer() {
      return this.container;
   }

   public final int getOpenMode() {
      return this.init_openmode;
   }

   public final BTree getConglomerate() {
      return this.init_conglomerate;
   }

   public final boolean getHold() {
      return this.init_hold;
   }

   public final BTreeLockingPolicy getLockingPolicy() {
      return this.init_btree_locking_policy;
   }

   public final void setLockingPolicy(BTreeLockingPolicy var1) {
      this.init_btree_locking_policy = var1;
   }

   public final boolean isClosed() {
      return this.container == null;
   }

   public final OpenConglomerateScratchSpace getRuntimeMem() {
      return this.runtime_mem;
   }

   public long getEstimatedRowCount() throws StandardException {
      if (this.container == null) {
         this.reopen();
      }

      long var1 = this.container.getEstimatedRowCount(0);
      return var1 == 0L ? 1L : var1;
   }

   public void setEstimatedRowCount(long var1) throws StandardException {
      if (this.container == null) {
         this.reopen();
      }

      this.container.setEstimatedRowCount(var1, 0);
   }

   public void checkConsistency() throws StandardException {
      ControlRow var1 = null;

      try {
         if (this.container == null) {
            throw StandardException.newException("XSCB8.S", new Object[]{this.err_containerid});
         }

         var1 = ControlRow.get(this, 1L);
         var1.checkConsistency(this, (ControlRow)null, true);
      } finally {
         if (var1 != null) {
            var1.release();
         }

      }

   }

   public boolean isTableLocked() {
      return this.init_lock_level == 7;
   }

   public void init(TransactionManager var1, TransactionManager var2, ContainerHandle var3, Transaction var4, boolean var5, int var6, int var7, BTreeLockingPolicy var8, BTree var9, LogicalUndo var10, DynamicCompiledOpenConglomInfo var11) throws StandardException {
      if (this.container != null) {
         this.close();
      }

      this.err_containerid = var9.id.getContainerId();
      this.init_btree_locking_policy = var8;
      if (var9.isTemporary()) {
         var6 |= 2048;
      }

      if (var3 == null) {
         this.container = var4.openContainer(var9.id, (LockingPolicy)null, var6);
      } else {
         this.container = var3;
      }

      if (this.container == null) {
         throw StandardException.newException("XSCB1.S", new Object[]{this.err_containerid});
      } else {
         this.init_conglomerate = var9;
         this.init_xact_manager = var2;
         this.init_rawtran = var4;
         this.init_openmode = var6;
         this.init_lock_level = var7;
         this.init_hold = var5;
         this.init_open_user_scans = var1;
         this.btree_undo = var10;
         this.runtime_mem = var11 != null ? (OpenConglomerateScratchSpace)var11 : (OpenConglomerateScratchSpace)var9.getDynamicCompiledConglomInfo();
      }
   }

   public ContainerHandle reopen() throws StandardException {
      if (this.container == null) {
         this.container = this.init_xact_manager.getRawStoreXact().openContainer(this.init_conglomerate.id, (LockingPolicy)null, this.init_openmode);
      }

      return this.container;
   }

   public void close() throws StandardException {
      if (this.container != null) {
         this.container.close();
      }

      this.container = null;
   }

   void isIndexableRowConsistent(DataValueDescriptor[] var1) throws StandardException {
   }

   public ContainerHandle getContainerHandle() {
      return this.container;
   }

   public int getHeight() throws StandardException {
      ControlRow var1 = null;

      int var3;
      try {
         var1 = ControlRow.get(this, 1L);
         int var2 = var1.getLevel() + 1;
         var3 = var2;
      } finally {
         if (var1 != null) {
            var1.release();
         }

      }

      return var3;
   }

   public void debugConglomerate() throws StandardException {
      ControlRow var1 = null;

      try {
         var1 = ControlRow.get(this, 1L);
         var1.printTree(this);
      } finally {
         if (var1 != null) {
            var1.release();
         }

      }

   }

   public static boolean test_errors(OpenBTree var0, String var1, BTreeRowPosition var2, BTreeLockingPolicy var3, LeafControlRow var4, boolean var5) throws StandardException {
      return var5;
   }

   public SpaceInfo getSpaceInfo() throws StandardException {
      return this.container.getSpaceInfo();
   }

   public boolean[] getColumnSortOrderInfo() throws StandardException {
      return this.init_conglomerate.ascDescInfo;
   }
}
