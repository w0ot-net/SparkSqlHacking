package org.apache.derby.impl.store.access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DatabaseInstant;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.FileResource;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortCostController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.XATransactionController;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.ConglomerateFactory;
import org.apache.derby.iapi.store.access.conglomerate.MethodFactory;
import org.apache.derby.iapi.store.access.conglomerate.ScanControllerRowSource;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.Sort;
import org.apache.derby.iapi.store.access.conglomerate.SortFactory;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Loggable;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.conglomerate.ConglomerateUtil;
import org.apache.derby.shared.common.error.StandardException;

public class RAMTransaction implements XATransactionController, TransactionManager {
   protected Transaction rawtran;
   protected RAMAccessManager accessmanager;
   protected RAMTransactionContext context;
   protected RAMTransaction parent_tran;
   private ArrayList scanControllers;
   private ArrayList conglomerateControllers;
   private ArrayList sorts;
   private ArrayList sortControllers;
   private ArrayList freeSortIds;
   protected HashMap tempCongloms;
   private long nextTempConglomId = -1L;
   private boolean alterTableCallMade = false;
   private int transaction_lock_level;

   private final void init(RAMAccessManager var1, Transaction var2, RAMTransaction var3) {
      this.rawtran = var2;
      this.parent_tran = var3;
      this.accessmanager = var1;
      this.scanControllers = new ArrayList();
      this.conglomerateControllers = new ArrayList();
      this.sorts = null;
      this.freeSortIds = null;
      this.sortControllers = null;
      if (var3 != null) {
         this.tempCongloms = var3.tempCongloms;
      } else {
         this.tempCongloms = null;
      }

   }

   protected RAMTransaction(RAMAccessManager var1, Transaction var2, RAMTransaction var3) throws StandardException {
      this.init(var1, var2, var3);
   }

   RAMTransaction(RAMAccessManager var1, RAMTransaction var2, int var3, byte[] var4, byte[] var5) throws StandardException {
      this.init(var1, var2.getRawStoreXact(), (RAMTransaction)null);
      this.context = var2.context;
      this.context.setTransaction(this);
      this.rawtran.createXATransactionFromLocalTransaction(var3, var4, var5);
      var2.rawtran = null;
   }

   RAMTransaction() {
   }

   protected void closeControllers(boolean var1) throws StandardException {
      if (!this.scanControllers.isEmpty()) {
         for(int var2 = this.scanControllers.size() - 1; var2 >= 0; --var2) {
            ScanManager var3 = (ScanManager)this.scanControllers.get(var2);
            if (var3.closeForEndTransaction(var1)) {
            }
         }

         if (var1) {
            this.scanControllers.clear();
         }
      }

      if (!this.conglomerateControllers.isEmpty()) {
         for(int var4 = this.conglomerateControllers.size() - 1; var4 >= 0; --var4) {
            ConglomerateController var7 = (ConglomerateController)this.conglomerateControllers.get(var4);
            if (var7.closeForEndTransaction(var1)) {
            }
         }

         if (var1) {
            this.conglomerateControllers.clear();
         }
      }

      if (this.sortControllers != null && !this.sortControllers.isEmpty() && var1) {
         for(int var5 = this.sortControllers.size() - 1; var5 >= 0; --var5) {
            SortController var8 = (SortController)this.sortControllers.get(var5);
            var8.completedInserts();
         }

         this.sortControllers.clear();
      }

      if (this.sorts != null && !this.sorts.isEmpty() && var1) {
         for(int var6 = this.sorts.size() - 1; var6 >= 0; --var6) {
            Sort var9 = (Sort)this.sorts.get(var6);
            if (var9 != null) {
               var9.drop(this);
            }
         }

         this.sorts.clear();
         this.freeSortIds.clear();
      }

   }

   private LockingPolicy determine_locking_policy(int var1, int var2) {
      LockingPolicy var3;
      if (this.accessmanager.getSystemLockLevel() != 7 && var1 != 7) {
         var3 = this.accessmanager.record_level_policy[var2];
      } else {
         var3 = this.accessmanager.table_level_policy[var2];
      }

      return var3;
   }

   private int determine_lock_level(int var1) {
      byte var2;
      if (this.accessmanager.getSystemLockLevel() != 7 && var1 != 7) {
         var2 = 6;
      } else {
         var2 = 7;
      }

      return var2;
   }

   private Conglomerate findExistingConglomerate(long var1) throws StandardException {
      Conglomerate var3 = this.findConglomerate(var1);
      if (var3 == null) {
         throw StandardException.newException("XSAI2.S", new Object[]{var1});
      } else {
         return var3;
      }
   }

   private Conglomerate findConglomerate(long var1) throws StandardException {
      Conglomerate var3 = null;
      if (var1 >= 0L) {
         var3 = this.accessmanager.conglomCacheFind(var1);
      } else if (this.tempCongloms != null) {
         var3 = (Conglomerate)this.tempCongloms.get(var1);
      }

      return var3;
   }

   void setContext(RAMTransactionContext var1) {
      this.context = var1;
   }

   private ConglomerateController openConglomerate(Conglomerate var1, boolean var2, int var3, int var4, int var5, StaticCompiledOpenConglomInfo var6, DynamicCompiledOpenConglomInfo var7) throws StandardException {
      ConglomerateController var8 = var1.open(this, this.rawtran, var2, var3, this.determine_lock_level(var4), this.determine_locking_policy(var4, var5), var6, var7);
      this.conglomerateControllers.add(var8);
      return var8;
   }

   private ScanController openScan(Conglomerate var1, boolean var2, int var3, int var4, int var5, FormatableBitSet var6, DataValueDescriptor[] var7, int var8, Qualifier[][] var9, DataValueDescriptor[] var10, int var11, StaticCompiledOpenConglomInfo var12, DynamicCompiledOpenConglomInfo var13) throws StandardException {
      ScanManager var14 = var1.openScan(this, this.rawtran, var2, var3, this.determine_lock_level(var4), this.determine_locking_policy(var4, var5), var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.scanControllers.add(var14);
      return var14;
   }

   protected void invalidateConglomerateCache() throws StandardException {
      if (this.alterTableCallMade) {
         this.accessmanager.conglomCacheInvalidate();
         this.alterTableCallMade = false;
      }

   }

   public void addColumnToConglomerate(long var1, int var3, Storable var4, int var5) throws StandardException {
      boolean var6 = var1 < 0L;
      Conglomerate var7 = this.findConglomerate(var1);
      if (var7 == null) {
         throw StandardException.newException("XSAM2.S", new Object[]{var1});
      } else {
         ConglomerateController var8 = var7.open(this, this.rawtran, false, 4, 7, this.accessmanager.table_level_policy[5], (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null);
         var7.addColumn(this, var3, var4, var5);
         if (!var6) {
            this.alterTableCallMade = true;
         }

         var8.close();
      }
   }

   public StaticCompiledOpenConglomInfo getStaticCompiledConglomInfo(long var1) throws StandardException {
      return this.findExistingConglomerate(var1).getStaticCompiledConglomInfo(this, var1);
   }

   public DynamicCompiledOpenConglomInfo getDynamicCompiledConglomInfo(long var1) throws StandardException {
      return this.findExistingConglomerate(var1).getDynamicCompiledConglomInfo();
   }

   private final int countCreatedSorts() {
      int var1 = 0;
      if (this.sorts != null) {
         for(int var2 = 0; var2 < this.sorts.size(); ++var2) {
            if (this.sorts.get(var2) != null) {
               ++var1;
            }
         }
      }

      return var1;
   }

   public int countOpens(int var1) throws StandardException {
      int var2 = -1;
      switch (var1) {
         case 1 -> var2 = this.conglomerateControllers.size();
         case 2 -> var2 = this.scanControllers.size();
         case 3 -> var2 = this.countCreatedSorts();
         case 4 -> var2 = this.sortControllers != null ? this.sortControllers.size() : 0;
         case 5 -> var2 = this.conglomerateControllers.size() + this.scanControllers.size() + (this.sortControllers != null ? this.sortControllers.size() : 0) + this.countCreatedSorts();
      }

      return var2;
   }

   public long createConglomerate(String var1, DataValueDescriptor[] var2, ColumnOrdering[] var3, int[] var4, Properties var5, int var6) throws StandardException {
      MethodFactory var7 = this.accessmanager.findMethodFactoryByImpl(var1);
      if (var7 != null && var7 instanceof ConglomerateFactory var8) {
         byte var9;
         long var10;
         if ((var6 & 1) == 1) {
            var9 = -1;
            var10 = 0L;
         } else {
            var9 = 0;
            var10 = this.accessmanager.getNextConglomId(var8.getConglomerateFactoryId());
         }

         Conglomerate var12 = var8.createConglomerate(this, var9, var10, var2, var3, var4, var5, var6);
         long var13;
         if ((var6 & 1) == 1) {
            var13 = (long)(this.nextTempConglomId--);
            if (this.tempCongloms == null) {
               this.tempCongloms = new HashMap();
            }

            this.tempCongloms.put(var13, var12);
         } else {
            var13 = var12.getContainerid();
            this.accessmanager.conglomCacheAddEntry(var13, var12);
         }

         return var13;
      } else {
         throw StandardException.newException("XSAM3.S", new Object[]{var1});
      }
   }

   public long createAndLoadConglomerate(String var1, DataValueDescriptor[] var2, ColumnOrdering[] var3, int[] var4, Properties var5, int var6, RowLocationRetRowSource var7, long[] var8) throws StandardException {
      return this.recreateAndLoadConglomerate(var1, true, var2, var3, var4, var5, var6, 0L, var7, var8);
   }

   public long recreateAndLoadConglomerate(String var1, boolean var2, DataValueDescriptor[] var3, ColumnOrdering[] var4, int[] var5, Properties var6, int var7, long var8, RowLocationRetRowSource var10, long[] var11) throws StandardException {
      long var12 = this.createConglomerate(var1, var3, var4, var5, var6, var7);
      long var14 = this.loadConglomerate(var12, true, var10);
      if (var11 != null) {
         var11[0] = var14;
      }

      if (!var2 && var14 == 0L) {
         this.dropConglomerate(var12);
         var12 = var8;
      }

      return var12;
   }

   public String debugOpened() throws StandardException {
      Object var1 = null;
      return (String)var1;
   }

   public boolean conglomerateExists(long var1) throws StandardException {
      Conglomerate var3 = this.findConglomerate(var1);
      return var3 != null;
   }

   public void dropConglomerate(long var1) throws StandardException {
      Conglomerate var3 = this.findExistingConglomerate(var1);
      var3.drop(this);
      if (var1 < 0L) {
         if (this.tempCongloms != null) {
            this.tempCongloms.remove(var1);
         }
      } else {
         this.accessmanager.conglomCacheRemoveEntry(var1);
      }

   }

   public boolean fetchMaxOnBtree(long var1, int var3, int var4, int var5, FormatableBitSet var6, DataValueDescriptor[] var7) throws StandardException {
      Conglomerate var8 = this.findExistingConglomerate(var1);
      return var8.fetchMaxOnBTree(this, this.rawtran, var1, var3, var4, this.determine_locking_policy(var4, var5), var5, var6, var7);
   }

   public Properties getUserCreateConglomPropList() {
      Properties var1 = ConglomerateUtil.createUserRawStorePropertySet((Properties)null);
      return var1;
   }

   public boolean isIdle() {
      return this.rawtran.isIdle();
   }

   public boolean isGlobal() {
      return this.rawtran.getGlobalId() != null;
   }

   public boolean isPristine() {
      return this.rawtran.isPristine();
   }

   public Object createXATransactionFromLocalTransaction(int var1, byte[] var2, byte[] var3) throws StandardException {
      this.getRawStoreXact().createXATransactionFromLocalTransaction(var1, var2, var3);
      return this;
   }

   public long loadConglomerate(long var1, boolean var3, RowLocationRetRowSource var4) throws StandardException {
      Conglomerate var5 = this.findExistingConglomerate(var1);
      return var5.load(this, var3, var4);
   }

   public void loadConglomerate(long var1, RowLocationRetRowSource var3) throws StandardException {
      this.loadConglomerate(var1, false, var3);
   }

   public void logAndDo(Loggable var1) throws StandardException {
      this.rawtran.logAndDo(var1);
   }

   public ConglomerateController openCompiledConglomerate(boolean var1, int var2, int var3, int var4, StaticCompiledOpenConglomInfo var5, DynamicCompiledOpenConglomInfo var6) throws StandardException {
      return this.openConglomerate((Conglomerate)var5.getConglom(), var1, var2, var3, var4, var5, var6);
   }

   public ConglomerateController openConglomerate(long var1, boolean var3, int var4, int var5, int var6) throws StandardException {
      return this.openConglomerate(this.findExistingConglomerate(var1), var3, var4, var5, var6, (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null);
   }

   public long findConglomid(long var1) throws StandardException {
      return var1;
   }

   public long findContainerid(long var1) throws StandardException {
      return var1;
   }

   public BackingStoreHashtable createBackingStoreHashtableFromScan(long var1, int var3, int var4, int var5, FormatableBitSet var6, DataValueDescriptor[] var7, int var8, Qualifier[][] var9, DataValueDescriptor[] var10, int var11, long var12, int[] var14, boolean var15, long var16, long var18, int var20, float var21, boolean var22, boolean var23, boolean var24, boolean var25) throws StandardException {
      return new BackingStoreHashTableFromScan(this, var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var14, var15, var16, var18, var20, var21, var22, var23, var24, var25);
   }

   public GroupFetchScanController openGroupFetchScan(long var1, boolean var3, int var4, int var5, int var6, FormatableBitSet var7, DataValueDescriptor[] var8, int var9, Qualifier[][] var10, DataValueDescriptor[] var11, int var12) throws StandardException {
      Conglomerate var13 = this.findExistingConglomerate(var1);
      ScanManager var14 = var13.openScan(this, this.rawtran, var3, var4, this.determine_lock_level(var5), this.determine_locking_policy(var5, var6), var6, var7, var8, var9, var10, var11, var12, (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null);
      this.scanControllers.add(var14);
      return var14;
   }

   public void purgeConglomerate(long var1) throws StandardException {
      this.findExistingConglomerate(var1).purgeConglomerate(this, this.rawtran);
   }

   public void compressConglomerate(long var1) throws StandardException {
      this.findExistingConglomerate(var1).compressConglomerate(this, this.rawtran);
   }

   public GroupFetchScanController defragmentConglomerate(long var1, boolean var3, boolean var4, int var5, int var6, int var7) throws StandardException {
      Conglomerate var8 = this.findExistingConglomerate(var1);
      ScanManager var9 = var8.defragmentConglomerate(this, this.rawtran, var4, var5, this.determine_lock_level(var6), this.determine_locking_policy(var6, var7), var7);
      this.scanControllers.add(var9);
      return var9;
   }

   public ScanController openScan(long var1, boolean var3, int var4, int var5, int var6, FormatableBitSet var7, DataValueDescriptor[] var8, int var9, Qualifier[][] var10, DataValueDescriptor[] var11, int var12) throws StandardException {
      return this.openScan(this.findExistingConglomerate(var1), var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null);
   }

   public ScanController openCompiledScan(boolean var1, int var2, int var3, int var4, FormatableBitSet var5, DataValueDescriptor[] var6, int var7, Qualifier[][] var8, DataValueDescriptor[] var9, int var10, StaticCompiledOpenConglomInfo var11, DynamicCompiledOpenConglomInfo var12) throws StandardException {
      return this.openScan((Conglomerate)var11.getConglom(), var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }

   public StoreCostController openStoreCost(long var1) throws StandardException {
      Conglomerate var3 = this.findExistingConglomerate(var1);
      StoreCostController var4 = var3.openStoreCost(this, this.rawtran);
      return var4;
   }

   public long createSort(Properties var1, DataValueDescriptor[] var2, ColumnOrdering[] var3, SortObserver var4, boolean var5, long var6, int var8) throws StandardException {
      String var9 = null;
      if (var1 != null) {
         var9 = var1.getProperty("implType");
      }

      if (var9 == null) {
         var9 = "sort external";
      }

      MethodFactory var10 = this.accessmanager.findMethodFactoryByImpl(var9);
      if (var10 != null && var10 instanceof SortFactory var11) {
         byte var12 = 0;
         Sort var13 = var11.createSort(this, var12, var1, var2, var3, var4, var5, var6, var8);
         if (this.sorts == null) {
            this.sorts = new ArrayList();
            this.freeSortIds = new ArrayList();
         }

         int var14;
         if (this.freeSortIds.isEmpty()) {
            var14 = this.sorts.size();
            this.sorts.add(var13);
         } else {
            var14 = (Integer)this.freeSortIds.remove(this.freeSortIds.size() - 1);
            this.sorts.set(var14, var13);
         }

         return (long)var14;
      } else {
         throw StandardException.newException("XSAM0.S", new Object[]{var9});
      }
   }

   public void dropSort(long var1) throws StandardException {
      Sort var3 = (Sort)this.sorts.get((int)var1);
      if (var3 != null) {
         var3.drop(this);
         this.sorts.set((int)var1, (Object)null);
         this.freeSortIds.add((int)var1);
      }

   }

   public Serializable getProperty(String var1) throws StandardException {
      return this.accessmanager.getTransactionalProperties().getProperty(this, var1);
   }

   public Serializable getPropertyDefault(String var1) throws StandardException {
      return this.accessmanager.getTransactionalProperties().getPropertyDefault(this, var1);
   }

   public void setProperty(String var1, Serializable var2, boolean var3) throws StandardException {
      this.accessmanager.getTransactionalProperties().setProperty(this, var1, var2, var3);
   }

   public void setPropertyDefault(String var1, Serializable var2) throws StandardException {
      this.accessmanager.getTransactionalProperties().setPropertyDefault(this, var1, var2);
   }

   public boolean propertyDefaultIsVisible(String var1) throws StandardException {
      return this.accessmanager.getTransactionalProperties().propertyDefaultIsVisible(this, var1);
   }

   public Properties getProperties() throws StandardException {
      return this.accessmanager.getTransactionalProperties().getProperties(this);
   }

   public SortController openSort(long var1) throws StandardException {
      Sort var3;
      if (this.sorts != null && var1 < (long)this.sorts.size() && (var3 = (Sort)this.sorts.get((int)var1)) != null) {
         SortController var4 = var3.open(this);
         if (this.sortControllers == null) {
            this.sortControllers = new ArrayList();
         }

         this.sortControllers.add(var4);
         return var4;
      } else {
         throw StandardException.newException("XSAM4.S", new Object[]{var1});
      }
   }

   public SortCostController openSortCostController() throws StandardException {
      String var1 = null;
      if (var1 == null) {
         var1 = "sort external";
      }

      MethodFactory var2 = this.accessmanager.findMethodFactoryByImpl(var1);
      if (var2 != null && var2 instanceof SortFactory var3) {
         return var3.openSortCostController();
      } else {
         throw StandardException.newException("XSAM0.S", new Object[]{var1});
      }
   }

   public ScanController openSortScan(long var1, boolean var3) throws StandardException {
      Sort var4;
      if (this.sorts != null && var1 < (long)this.sorts.size() && (var4 = (Sort)this.sorts.get((int)var1)) != null) {
         ScanManager var5 = var4.openSortScan(this, var3);
         this.scanControllers.add(var5);
         return var5;
      } else {
         throw StandardException.newException("XSAM4.S", new Object[]{var1});
      }
   }

   public RowLocationRetRowSource openSortRowSource(long var1) throws StandardException {
      Sort var3;
      if (this.sorts != null && var1 < (long)this.sorts.size() && (var3 = (Sort)this.sorts.get((int)var1)) != null) {
         ScanControllerRowSource var4 = var3.openSortRowSource(this);
         this.scanControllers.add((ScanManager)var4);
         return var4;
      } else {
         throw StandardException.newException("XSAM4.S", new Object[]{var1});
      }
   }

   public void commit() throws StandardException {
      this.closeControllers(false);
      this.rawtran.commit();
      this.alterTableCallMade = false;
   }

   public DatabaseInstant commitNoSync(int var1) throws StandardException {
      this.closeControllers(false);
      return this.rawtran.commitNoSync(var1);
   }

   public void abort() throws StandardException {
      this.invalidateConglomerateCache();
      this.closeControllers(true);
      this.rawtran.abort();
      if (this.parent_tran != null) {
         this.parent_tran.abort();
      }

   }

   public ContextManager getContextManager() {
      return this.context.getContextManager();
   }

   public int setSavePoint(String var1, Object var2) throws StandardException {
      return this.rawtran.setSavePoint(var1, var2);
   }

   public int releaseSavePoint(String var1, Object var2) throws StandardException {
      return this.rawtran.releaseSavePoint(var1, var2);
   }

   public int rollbackToSavePoint(String var1, boolean var2, Object var3) throws StandardException {
      if (var2) {
         this.closeControllers(true);
      }

      return this.rawtran.rollbackToSavePoint(var1, var3);
   }

   public void destroy() {
      try {
         this.closeControllers(true);
         if (this.rawtran != null) {
            this.rawtran.destroy();
            this.rawtran = null;
         }

         if (this.context != null) {
            this.context.popMe();
         }

         this.context = null;
         this.accessmanager = null;
         this.tempCongloms = null;
      } catch (StandardException var2) {
         this.rawtran = null;
         this.context = null;
         this.accessmanager = null;
         this.tempCongloms = null;
      }

   }

   public boolean anyoneBlocked() {
      return this.rawtran.anyoneBlocked();
   }

   public void xa_commit(boolean var1) throws StandardException {
      this.rawtran.xa_commit(var1);
   }

   public int xa_prepare() throws StandardException {
      return this.rawtran.xa_prepare();
   }

   public void xa_rollback() throws StandardException {
      this.rawtran.xa_rollback();
   }

   public Conglomerate findExistingConglomerateFromKey(ContainerKey var1) throws StandardException {
      return this.findExistingConglomerate(var1.getContainerId());
   }

   public void addPostCommitWork(Serviceable var1) {
      this.rawtran.addPostCommitWork(var1);
   }

   public boolean checkVersion(int var1, int var2, String var3) throws StandardException {
      return this.accessmanager.getRawStore().checkVersion(var1, var2, var3);
   }

   public void closeMe(ConglomerateController var1) {
      this.conglomerateControllers.remove(var1);
   }

   public void closeMe(SortController var1) {
      this.sortControllers.remove(var1);
   }

   public void closeMe(ScanManager var1) {
      this.scanControllers.remove(var1);
   }

   public AccessFactory getAccessManager() {
      return this.accessmanager;
   }

   public TransactionManager getInternalTransaction() throws StandardException {
      ContextManager var1 = this.getContextManager();
      Transaction var2 = this.accessmanager.getRawStore().startInternalTransaction(var1);
      RAMTransaction var3 = new RAMTransaction(this.accessmanager, var2, (RAMTransaction)null);
      new RAMTransactionContext(var1, "RAMInternalContext", var3, true);
      var2.setDefaultLockingPolicy(this.accessmanager.getDefaultLockingPolicy());
      return var3;
   }

   public TransactionController startNestedUserTransaction(boolean var1, boolean var2) throws StandardException {
      ContextManager var3 = this.getContextManager();
      Transaction var4 = var1 ? this.accessmanager.getRawStore().startNestedReadOnlyUserTransaction(this.rawtran, this.getLockSpace(), var3, "nestedReadOnlyUserTransaction") : this.accessmanager.getRawStore().startNestedUpdateUserTransaction(this.rawtran, var3, "nestedUpdateUserTransaction", var2);
      RAMTransaction var5 = new RAMTransaction(this.accessmanager, var4, this);
      new RAMTransactionContext(var3, "RAMChildContext", var5, true);
      var4.setDefaultLockingPolicy(this.accessmanager.getDefaultLockingPolicy());
      return var5;
   }

   public Transaction getRawStoreXact() {
      return this.rawtran;
   }

   public FileResource getFileHandler() {
      return this.rawtran.getFileHandler();
   }

   public CompatibilitySpace getLockSpace() {
      return this.rawtran.getCompatibilitySpace();
   }

   public void setNoLockWait(boolean var1) {
      this.rawtran.setNoLockWait(var1);
   }

   public String getTransactionIdString() {
      return this.rawtran.toString();
   }

   public String getActiveStateTxIdString() {
      return this.rawtran.getActiveStateTxIdString();
   }

   public String toString() {
      Object var1 = null;
      return (String)var1;
   }
}
