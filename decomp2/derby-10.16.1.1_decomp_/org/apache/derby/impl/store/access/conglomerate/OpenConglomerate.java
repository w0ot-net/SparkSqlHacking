package org.apache.derby.impl.store.access.conglomerate;

import java.util.Properties;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.SpaceInfo;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public abstract class OpenConglomerate {
   private Conglomerate init_conglomerate;
   private TransactionManager init_xact_manager;
   private Transaction init_rawtran;
   private int init_openmode;
   private int init_lock_level;
   private DynamicCompiledOpenConglomInfo init_dynamic_info;
   private boolean init_hold;
   private LockingPolicy init_locking_policy;
   private boolean useUpdateLocks;
   private boolean forUpdate;
   private boolean getBaseTableLocks;
   private OpenConglomerateScratchSpace runtime_mem;
   private ContainerHandle container;

   protected abstract RowLocation newRowLocationTemplate() throws StandardException;

   public abstract int[] getFormatIds();

   public boolean latchPageAndRepositionScan(RowPosition var1) throws StandardException {
      boolean var2 = false;
      var1.current_page = null;

      try {
         if (var1.current_rh != null) {
            var1.current_page = this.container.getPage(var1.current_rh.getPageNumber());
         }
      } catch (Throwable var5) {
      }

      if (var1.current_page != null) {
         try {
            var1.current_slot = var1.current_page.getSlotNumber(var1.current_rh);
         } catch (StandardException var6) {
            var2 = true;
            var1.current_slot = var1.current_page.getNextSlotNumber(var1.current_rh);
            if (var1.current_slot == -1) {
               var1.current_page.unlatch();
               var1.current_page = null;
            } else {
               --var1.current_slot;
            }
         }
      }

      if (var1.current_page == null) {
         long var3;
         if (var1.current_rh != null) {
            var3 = var1.current_rh.getPageNumber();
         } else {
            if (var1.current_pageno == -1L) {
               return false;
            }

            var3 = var1.current_pageno;
         }

         var1.current_page = this.container.getNextPage(var3);
         var1.current_slot = -1;
         var1.current_pageno = -1L;
         var2 = true;
      }

      if (var2) {
         var1.current_rh = null;
      }

      return var2;
   }

   public boolean latchPage(RowPosition var1) throws StandardException {
      var1.current_page = null;

      try {
         var1.current_page = this.container.getPage(var1.current_rh.getPageNumber());
      } catch (Throwable var3) {
      }

      if (var1.current_page != null) {
         try {
            var1.current_slot = var1.current_page.getSlotNumber(var1.current_rh);
            return true;
         } catch (Throwable var4) {
            var1.current_page.unlatch();
            var1.current_page = null;
         }
      }

      return false;
   }

   public boolean lockPositionForRead(RowPosition var1, RowPosition var2, boolean var3, boolean var4) throws StandardException {
      if (var1.current_rh == null) {
         var1.current_rh = var1.current_page.getRecordHandleAtSlot(var1.current_slot);
      }

      boolean var5 = this.container.getLockingPolicy().lockRecordForRead(this.init_rawtran, this.container, var1.current_rh, false, this.forUpdate);
      if (!var5) {
         var1.current_page.unlatch();
         var1.current_page = null;
         if (var2 != null) {
            var2.current_page.unlatch();
            var2.current_page = null;
         }

         if (!var4) {
            throw StandardException.newException("40XL1", new Object[0]);
         }

         this.container.getLockingPolicy().lockRecordForRead(this.init_rawtran, this.container, var1.current_rh, true, this.forUpdate);
         if (var3) {
            if (this.latchPageAndRepositionScan(var1) && var1.current_slot != -1) {
               var1.positionAtNextSlot();
               this.lockPositionForRead(var1, var2, true, true);
            }
         } else {
            this.latchPage(var1);
         }
      }

      return var5;
   }

   public boolean lockPositionForWrite(RowPosition var1, boolean var2) throws StandardException {
      if (var1.current_rh == null) {
         var1.current_rh = var1.current_page.fetchFromSlot((RecordHandle)null, var1.current_slot, RowUtil.EMPTY_ROW, RowUtil.EMPTY_ROW_FETCH_DESCRIPTOR, true);
      }

      boolean var3 = this.container.getLockingPolicy().lockRecordForWrite(this.init_rawtran, var1.current_rh, false, false);
      if (!var3) {
         var1.current_page.unlatch();
         var1.current_page = null;
         if (!var2) {
            throw StandardException.newException("40XL1", new Object[0]);
         }

         this.container.getLockingPolicy().lockRecordForWrite(this.init_rawtran, var1.current_rh, false, true);
         this.latchPage(var1);
      }

      return var3;
   }

   public void unlockPositionAfterRead(RowPosition var1) throws StandardException {
      if (!this.isClosed()) {
         this.container.getLockingPolicy().unlockRecordAfterRead(this.init_rawtran, this.container, var1.current_rh, this.forUpdate, var1.current_rh_qualified);
      }

   }

   public Properties getInternalTablePropertySet(Properties var1) throws StandardException {
      Properties var2 = ConglomerateUtil.createRawStorePropertySet(var1);
      this.getTableProperties(var2);
      return var2;
   }

   public void getTableProperties(Properties var1) throws StandardException {
      this.container.getContainerProperties(var1);
   }

   public final TransactionManager getXactMgr() {
      return this.init_xact_manager;
   }

   public final Transaction getRawTran() {
      return this.init_rawtran;
   }

   public final ContainerHandle getContainer() {
      return this.container;
   }

   public final int getOpenMode() {
      return this.init_openmode;
   }

   public final Conglomerate getConglomerate() {
      return this.init_conglomerate;
   }

   public final boolean getHold() {
      return this.init_hold;
   }

   public final boolean isForUpdate() {
      return this.forUpdate;
   }

   public final boolean isClosed() {
      return this.container == null;
   }

   public final boolean isUseUpdateLocks() {
      return this.useUpdateLocks;
   }

   public final OpenConglomerateScratchSpace getRuntimeMem() {
      return this.runtime_mem;
   }

   public void checkConsistency() throws StandardException {
   }

   public void debugConglomerate() throws StandardException {
   }

   public SpaceInfo getSpaceInfo() throws StandardException {
      return this.container.getSpaceInfo();
   }

   protected boolean isKeyed() {
      return false;
   }

   protected boolean isTableLocked() {
      return this.init_lock_level == 7;
   }

   public ContainerHandle init(ContainerHandle var1, Conglomerate var2, int[] var3, int[] var4, TransactionManager var5, Transaction var6, boolean var7, int var8, int var9, LockingPolicy var10, DynamicCompiledOpenConglomInfo var11) throws StandardException {
      this.init_conglomerate = var2;
      this.init_xact_manager = var5;
      this.init_rawtran = var6;
      this.init_openmode = var8;
      this.init_lock_level = var9;
      this.init_dynamic_info = var11;
      this.init_hold = var7;
      this.init_locking_policy = var10;
      this.runtime_mem = var11 != null ? (OpenConglomerateScratchSpace)var11 : (OpenConglomerateScratchSpace)var2.getDynamicCompiledConglomInfo();
      this.forUpdate = (var8 & 4) != 0;
      this.useUpdateLocks = (var8 & 4096) != 0;
      this.getBaseTableLocks = (var8 & 8192) == 0;
      if (var2.isTemporary()) {
         this.init_openmode |= 2048;
      }

      if (!this.getBaseTableLocks) {
         this.init_locking_policy = null;
      }

      this.container = var1 != null ? var1 : var6.openContainer(var2.getId(), this.init_locking_policy, this.init_openmode);
      return this.container;
   }

   public ContainerHandle reopen() throws StandardException {
      if (this.container == null) {
         this.container = this.init_rawtran.openContainer(this.init_conglomerate.getId(), this.init_locking_policy, this.init_openmode);
      }

      return this.container;
   }

   public void close() throws StandardException {
      if (this.container != null) {
         this.container.close();
         this.container = null;
      }

   }
}
