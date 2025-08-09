package org.apache.derby.impl.store.access.btree.index;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.btree.BTree;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.LeafControlRow;
import org.apache.derby.impl.store.access.btree.OpenBTree;
import org.apache.derby.impl.store.access.conglomerate.ConglomerateUtil;
import org.apache.derby.shared.common.error.StandardException;

public class B2I extends BTree {
   private static final String PROPERTY_BASECONGLOMID = "baseConglomerateId";
   private static final String PROPERTY_ROWLOCCOLUMN = "rowLocationColumn";
   static final int FORMAT_NUMBER = 470;
   long baseConglomerateId;
   int rowLocationColumn;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(B2I.class);

   public int estimateMemoryUsage() {
      return BASE_MEMORY_USAGE;
   }

   protected BTreeLockingPolicy getBtreeLockingPolicy(Transaction var1, int var2, int var3, int var4, ConglomerateController var5, OpenBTree var6) throws StandardException {
      Object var7 = null;
      if (var2 == 7) {
         var7 = new B2ITableLocking3(var1, var2, var1.newLockingPolicy(2, var4, true), var5, var6);
      } else if (var2 == 6) {
         if (var4 == 5) {
            var7 = new B2IRowLocking3(var1, var2, var1.newLockingPolicy(1, var4, true), var5, var6);
         } else if (var4 == 4) {
            var7 = new B2IRowLockingRR(var1, var2, var1.newLockingPolicy(1, var4, true), var5, var6);
         } else if (var4 != 2 && var4 != 3) {
            if (var4 == 1) {
               var7 = new B2IRowLocking1(var1, var2, var1.newLockingPolicy(1, var4, true), var5, var6);
            }
         } else {
            var7 = new B2IRowLocking2(var1, var2, var1.newLockingPolicy(1, var4, true), var5, var6);
         }
      }

      return (BTreeLockingPolicy)var7;
   }

   public final ConglomerateController lockTable(TransactionManager var1, int var2, int var3, int var4) throws StandardException {
      var2 |= 64;
      ConglomerateController var5 = var1.openConglomerate(this.baseConglomerateId, false, var2, var3, var4);
      return var5;
   }

   private void traverseRight() {
   }

   public void create(TransactionManager var1, int var2, long var3, DataValueDescriptor[] var5, ColumnOrdering[] var6, int[] var7, Properties var8, int var9) throws StandardException {
      Object var10 = null;
      Transaction var11 = var1.getRawStoreXact();
      if (var8 == null) {
         throw StandardException.newException("XSCB2.S", new Object[]{"baseConglomerateId"});
      } else {
         String var15 = var8.getProperty("baseConglomerateId");
         if (var15 == null) {
            throw StandardException.newException("XSCB2.S", new Object[]{"baseConglomerateId"});
         } else {
            this.baseConglomerateId = Long.parseLong(var15);
            var15 = var8.getProperty("rowLocationColumn");
            if (var15 == null) {
               throw StandardException.newException("XSCB2.S", new Object[]{"baseConglomerateId"});
            } else {
               this.rowLocationColumn = Integer.parseInt(var15);
               this.ascDescInfo = new boolean[var5.length];

               for(int var12 = 0; var12 < this.ascDescInfo.length; ++var12) {
                  if (var6 != null && var12 < var6.length) {
                     this.ascDescInfo[var12] = var6[var12].getIsAscending();
                  } else {
                     this.ascDescInfo[var12] = true;
                  }
               }

               this.collation_ids = ConglomerateUtil.createCollationIds(var5.length, var7);
               this.hasCollatedTypes = hasCollatedColumns(this.collation_ids);
               super.create(var11, var2, var3, var5, var8, this.getTypeFormatId(), var9);
               ConglomerateController var17 = var1.openConglomerate(this.baseConglomerateId, false, 64, 7, 5);
               OpenBTree var13 = new OpenBTree();
               B2ITableLocking3 var14 = new B2ITableLocking3(var11, 7, var11.newLockingPolicy(2, 5, true), var17, var13);
               var13.init(var1, var1, (ContainerHandle)null, var11, false, 4, 7, var14, this, (LogicalUndo)null, (DynamicCompiledOpenConglomInfo)null);
               LeafControlRow.initEmptyBtree(var13);
               var13.close();
               var17.close();
            }
         }
      }
   }

   public boolean fetchMaxOnBTree(TransactionManager var1, Transaction var2, long var3, int var5, int var6, LockingPolicy var7, int var8, FormatableBitSet var9, DataValueDescriptor[] var10) throws StandardException {
      B2IMaxScan var12 = new B2IMaxScan();
      var12.init(var1, var2, var5, var6, var7, var8, true, var9, this, new B2IUndo());
      boolean var11 = var12.fetchMax(var10);
      var12.close();
      return var11;
   }

   public long load(TransactionManager var1, boolean var2, RowLocationRetRowSource var3) throws StandardException {
      long var4 = 0L;
      B2IController var6 = new B2IController();

      try {
         int var7 = 4;
         if (var2) {
            var7 |= 3;
         }

         var6.init(var1, var1.getRawStoreXact(), false, var7, 7, var1.getRawStoreXact().newLockingPolicy(2, 5, true), true, this, new B2IUndo(), (B2IStaticCompiledInfo)null, (DynamicCompiledOpenConglomInfo)null);
         var4 = var6.load(var1, var2, var3);
      } finally {
         var6.close();
      }

      return var4;
   }

   public ConglomerateController open(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, StaticCompiledOpenConglomInfo var7, DynamicCompiledOpenConglomInfo var8) throws StandardException {
      B2IController var9 = new B2IController();
      var9.init(var1, var2, var3, var4, var5, var6, true, this, new B2IUndo(), (B2IStaticCompiledInfo)var7, var8);
      return var9;
   }

   public ScanManager openScan(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7, FormatableBitSet var8, DataValueDescriptor[] var9, int var10, Qualifier[][] var11, DataValueDescriptor[] var12, int var13, StaticCompiledOpenConglomInfo var14, DynamicCompiledOpenConglomInfo var15) throws StandardException {
      B2IForwardScan var16 = new B2IForwardScan();
      var16.init(var1, var2, var3, var4, var5, var6, var7, true, var8, var9, var10, var11, var12, var13, this, new B2IUndo(), (B2IStaticCompiledInfo)var14, var15);
      return var16;
   }

   public ScanManager defragmentConglomerate(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public void purgeConglomerate(TransactionManager var1, Transaction var2) throws StandardException {
   }

   public void compressConglomerate(TransactionManager var1, Transaction var2) throws StandardException {
      B2IController var3 = new B2IController();

      try {
         byte var4 = 4;
         var3.init(var1, var1.getRawStoreXact(), false, var4, 7, var1.getRawStoreXact().newLockingPolicy(2, 5, true), true, this, new B2IUndo(), (B2IStaticCompiledInfo)null, (DynamicCompiledOpenConglomInfo)null);
         var3.getContainer().compressContainer();
      } finally {
         var3.close();
      }

   }

   public StoreCostController openStoreCost(TransactionManager var1, Transaction var2) throws StandardException {
      B2ICostController var3 = new B2ICostController();
      var3.init(var1, this, var2);
      return var3;
   }

   public void drop(TransactionManager var1) throws StandardException {
      ConglomerateController var2 = null;
      var2 = this.lockTable(var1, 4, 7, 4);
      var1.getRawStoreXact().dropContainer(this.id);
      if (var2 != null) {
         var2.close();
      }

   }

   public StaticCompiledOpenConglomInfo getStaticCompiledConglomInfo(TransactionController var1, long var2) throws StandardException {
      return new B2IStaticCompiledInfo(var1, this);
   }

   public int getTypeFormatId() {
      return 470;
   }

   public void writeExternal_v10_2(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      var1.writeLong(this.baseConglomerateId);
      var1.writeInt(this.rowLocationColumn);
      FormatableBitSet var2 = new FormatableBitSet(this.ascDescInfo.length);

      for(int var3 = 0; var3 < this.ascDescInfo.length; ++var3) {
         if (this.ascDescInfo[var3]) {
            var2.set(var3);
         }
      }

      var2.writeExternal(var1);
   }

   public void writeExternal_v10_3(ObjectOutput var1) throws IOException {
      this.writeExternal_v10_2(var1);
      if (this.conglom_format_id == 466 || this.conglom_format_id == 470) {
         ConglomerateUtil.writeCollationIdArray(this.collation_ids, var1);
      }

   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.writeExternal_v10_3(var1);
      if (this.conglom_format_id == 470) {
         var1.writeBoolean(this.isUniqueWithDuplicateNulls());
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.baseConglomerateId = var1.readLong();
      this.rowLocationColumn = var1.readInt();
      FormatableBitSet var2 = new FormatableBitSet();
      var2.readExternal(var1);
      this.ascDescInfo = new boolean[var2.getLength()];

      for(int var3 = 0; var3 < var2.getLength(); ++var3) {
         this.ascDescInfo[var3] = var2.isSet(var3);
      }

      this.collation_ids = new int[this.format_ids.length];

      for(int var4 = 0; var4 < this.format_ids.length; ++var4) {
         this.collation_ids[var4] = 0;
      }

      this.setUniqueWithDuplicateNulls(false);
      if (this.conglom_format_id != 466 && this.conglom_format_id != 470) {
         if (this.conglom_format_id != 388) {
         }
      } else {
         this.hasCollatedTypes = ConglomerateUtil.readCollationIdArray(this.collation_ids, var1);
      }

      if (this.conglom_format_id == 470) {
         this.setUniqueWithDuplicateNulls(var1.readBoolean());
      }

   }
}
