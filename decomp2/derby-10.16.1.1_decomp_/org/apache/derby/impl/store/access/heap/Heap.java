package org.apache.derby.impl.store.access.heap;

import java.io.DataInput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.iapi.services.cache.ClassSize;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.LogicalUndo;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.conglomerate.ConglomerateUtil;
import org.apache.derby.impl.store.access.conglomerate.GenericConglomerate;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerate;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerateScratchSpace;
import org.apache.derby.shared.common.error.StandardException;

public class Heap extends GenericConglomerate implements Conglomerate, StaticCompiledOpenConglomInfo {
   protected int conglom_format_id;
   private ContainerKey id;
   int[] format_ids;
   protected int[] collation_ids;
   private boolean hasCollatedTypes;
   private static final int BASE_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(Heap.class);
   private static final int CONTAINER_KEY_MEMORY_USAGE = ClassSize.estimateBaseFromCatalog(ContainerKey.class);

   public int estimateMemoryUsage() {
      int var1 = BASE_MEMORY_USAGE;
      if (null != this.id) {
         var1 += CONTAINER_KEY_MEMORY_USAGE;
      }

      if (null != this.format_ids) {
         var1 += this.format_ids.length * ClassSize.getIntSize();
      }

      return var1;
   }

   protected void create(Transaction var1, int var2, long var3, DataValueDescriptor[] var5, ColumnOrdering[] var6, int[] var7, Properties var8, int var9, int var10) throws StandardException {
      if (var8 != null) {
         String var11 = var8.getProperty("derby.storage.minimumRecordSize");
         int var12 = var11 == null ? 12 : Integer.parseInt(var11);
         if (var12 < 12) {
            var8.put("derby.storage.minimumRecordSize", Integer.toString(12));
         }
      }

      long var19 = var1.addContainer((long)var2, var3, 0, var8, var10);
      if (var19 < 0L) {
         throw StandardException.newException("XSCH0.S", new Object[0]);
      } else {
         this.id = new ContainerKey((long)var2, var19);
         if (var5 != null && var5.length != 0) {
            this.format_ids = ConglomerateUtil.createFormatIds(var5);
            this.conglom_format_id = var9;
            this.collation_ids = ConglomerateUtil.createCollationIds(this.format_ids.length, var7);
            this.hasCollatedTypes = hasCollatedColumns(this.collation_ids);
            ContainerHandle var13 = null;
            Page var14 = null;

            try {
               var13 = var1.openContainer(this.id, (LockingPolicy)null, 4 | (this.isTemporary() ? 2048 : 0));
               DataValueDescriptor[] var15 = new DataValueDescriptor[]{this};
               var14 = var13.getPage(1L);
               var14.insertAtSlot(0, var15, (FormatableBitSet)null, (LogicalUndo)null, (byte)8, 100);
               var14.unlatch();
               var14 = null;
               var13.setEstimatedRowCount(0L, 0);
            } finally {
               if (var13 != null) {
                  var13.close();
               }

               if (var14 != null) {
                  var14.unlatch();
               }

            }

         } else {
            throw StandardException.newException("XSCH4.S", new Object[0]);
         }
      }
   }

   public void boot_create(long var1, DataValueDescriptor[] var3) {
      this.id = new ContainerKey(0L, var1);
      this.format_ids = ConglomerateUtil.createFormatIds(var3);
   }

   public void addColumn(TransactionManager var1, int var2, Storable var3, int var4) throws StandardException {
      ContainerHandle var5 = null;
      Page var6 = null;
      Transaction var7 = var1.getRawStoreXact();

      try {
         var5 = var7.openContainer(this.id, var7.newLockingPolicy(2, 5, true), 4 | (this.isTemporary() ? 2048 : 0));
         if (var2 != this.format_ids.length) {
            throw StandardException.newException("XSCH5.S", new Object[]{var2, this.format_ids.length});
         }

         int[] var8 = this.format_ids;
         this.format_ids = new int[var8.length + 1];
         System.arraycopy(var8, 0, this.format_ids, 0, var8.length);
         this.format_ids[var8.length] = var3.getTypeFormatId();
         int[] var9 = this.collation_ids;
         this.collation_ids = new int[var9.length + 1];
         System.arraycopy(var9, 0, this.collation_ids, 0, var9.length);
         this.collation_ids[var9.length] = var4;
         DataValueDescriptor[] var10 = new DataValueDescriptor[]{this};
         var6 = var5.getPage(1L);
         var6.updateAtSlot(0, var10, (FormatableBitSet)null);
         var6.unlatch();
         var6 = null;
      } finally {
         if (var5 != null) {
            var5.close();
         }

         if (var6 != null) {
            var6.unlatch();
         }

      }

   }

   public void drop(TransactionManager var1) throws StandardException {
      var1.getRawStoreXact().dropContainer(this.id);
   }

   public boolean fetchMaxOnBTree(TransactionManager var1, Transaction var2, long var3, int var5, int var6, LockingPolicy var7, int var8, FormatableBitSet var9, DataValueDescriptor[] var10) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   public final ContainerKey getId() {
      return this.id;
   }

   public final long getContainerid() {
      return this.id.getContainerId();
   }

   public DynamicCompiledOpenConglomInfo getDynamicCompiledConglomInfo() throws StandardException {
      return new OpenConglomerateScratchSpace(this.format_ids, this.collation_ids, this.hasCollatedTypes);
   }

   public StaticCompiledOpenConglomInfo getStaticCompiledConglomInfo(TransactionController var1, long var2) throws StandardException {
      return this;
   }

   public boolean isTemporary() {
      return this.id.getSegmentId() == -1L;
   }

   public long load(TransactionManager var1, boolean var2, RowLocationRetRowSource var3) throws StandardException {
      long var4 = 0L;
      HeapController var6 = new HeapController();

      try {
         var4 = var6.load(var1, this, var2, var3);
      } finally {
         var6.close();
      }

      return var4;
   }

   public ConglomerateController open(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, StaticCompiledOpenConglomInfo var7, DynamicCompiledOpenConglomInfo var8) throws StandardException {
      OpenHeap var9 = new OpenHeap();
      if (((OpenConglomerate)var9).init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var1, var2, var3, var4, var5, var6, var8) == null) {
         throw StandardException.newException("XSCH1.S", new Object[]{Long.toString(this.id.getContainerId())});
      } else {
         HeapController var10 = new HeapController();
         var10.init(var9);
         return var10;
      }
   }

   static ConglomerateController openByContainerKey(ContainerKey var0, TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, StaticCompiledOpenConglomInfo var7, DynamicCompiledOpenConglomInfo var8) throws StandardException {
      if (var0.getSegmentId() == -1L) {
         var4 |= 2048;
      }

      ContainerHandle var9 = var2.openContainer(var0, var6, var4);
      Heap var10 = (Heap)var1.findExistingConglomerateFromKey(var0);
      OpenHeap var11 = new OpenHeap();
      if (((OpenConglomerate)var11).init(var9, var10, var10.format_ids, var10.collation_ids, var1, var2, var3, var4, var5, var6, var8) == null) {
         throw StandardException.newException("XSCH1.S", new Object[]{Long.toString(var0.getContainerId())});
      } else {
         HeapController var12 = new HeapController();
         var12.init(var11);
         return var12;
      }
   }

   public ScanManager openScan(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7, FormatableBitSet var8, DataValueDescriptor[] var9, int var10, Qualifier[][] var11, DataValueDescriptor[] var12, int var13, StaticCompiledOpenConglomInfo var14, DynamicCompiledOpenConglomInfo var15) throws StandardException {
      if (RowUtil.isRowEmpty(var9) && RowUtil.isRowEmpty(var12)) {
         OpenHeap var16 = new OpenHeap();
         if (((OpenConglomerate)var16).init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var1, var2, var3, var4, var5, var6, var15) == null) {
            throw StandardException.newException("XSCH1.S", new Object[]{this.id.getContainerId()});
         } else {
            HeapScan var17 = new HeapScan();
            var17.init(var16, var8, var9, var10, var11, var12, var13);
            return var17;
         }
      } else {
         throw StandardException.newException("XSCH8.S", new Object[0]);
      }
   }

   public void purgeConglomerate(TransactionManager var1, Transaction var2) throws StandardException {
      OpenHeap var3 = null;
      HeapController var4 = null;
      TransactionManager var5 = null;

      try {
         var3 = new OpenHeap();
         if (((OpenConglomerate)var3).init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var1, var2, false, 4, 6, (LockingPolicy)null, (DynamicCompiledOpenConglomInfo)null) == null) {
            throw StandardException.newException("XSCH1.S", new Object[]{this.id.getContainerId()});
         }

         var5 = (TransactionManager)var1.startNestedUserTransaction(false, true);
         OpenHeap var6 = new OpenHeap();
         if (((OpenConglomerate)var6).init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var5, var5.getRawStoreXact(), true, 4, 6, var5.getRawStoreXact().newLockingPolicy(1, 4, true), (DynamicCompiledOpenConglomInfo)null) == null) {
            throw StandardException.newException("XSCH1.S", new Object[]{Long.toString(this.id.getContainerId())});
         }

         var4 = new HeapController();
         var4.init(var6);
         Page var7 = ((OpenConglomerate)var6).getContainer().getFirstPage();

         long var9;
         for(boolean var8 = false; var7 != null; var7 = ((OpenConglomerate)var6).getContainer().getNextPage(var9)) {
            var9 = var7.getPageNumber();
            var8 = var4.purgeCommittedDeletes(var7);
            if (var8) {
               Object var14 = null;
               ((OpenConglomerate)var6).getXactMgr().commitNoSync(1);
               var4.closeForEndTransaction(false);
               ((OpenConglomerate)var6).reopen();
            } else {
               var7.unlatch();
               Object var15 = null;
            }
         }
      } finally {
         if (var3 != null) {
            ((OpenConglomerate)var3).close();
         }

         if (var4 != null) {
            var4.close();
         }

         if (var5 != null) {
            var5.commitNoSync(1);
            var5.destroy();
         }

      }

   }

   public void compressConglomerate(TransactionManager var1, Transaction var2) throws StandardException {
      OpenHeap var3 = null;
      HeapController var4 = null;

      try {
         var3 = new OpenHeap();
         if (((OpenConglomerate)var3).init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var1, var2, false, 4, 7, var2.newLockingPolicy(2, 4, true), (DynamicCompiledOpenConglomInfo)null) == null) {
            throw StandardException.newException("XSCH1.S", new Object[]{this.id.getContainerId()});
         }

         var4 = new HeapController();
         var4.init(var3);
         ((OpenConglomerate)var3).getContainer().compressContainer();
      } finally {
         if (var3 != null) {
            ((OpenConglomerate)var3).close();
         }

      }

   }

   public ScanManager defragmentConglomerate(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, int var7) throws StandardException {
      OpenHeap var8 = new OpenHeap();
      if (((OpenConglomerate)var8).init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var1, var2, var3, var4, var5, var2.newLockingPolicy(1, 4, true), (DynamicCompiledOpenConglomInfo)null) == null) {
         throw StandardException.newException("XSCH1.S", new Object[]{this.id.getContainerId()});
      } else {
         HeapCompressScan var9 = new HeapCompressScan();
         var9.init(var8, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
         return var9;
      }
   }

   public StoreCostController openStoreCost(TransactionManager var1, Transaction var2) throws StandardException {
      OpenHeap var3 = new OpenHeap();
      if (var3.init((ContainerHandle)null, this, this.format_ids, this.collation_ids, var1, var2, false, 8, 7, (LockingPolicy)null, (DynamicCompiledOpenConglomInfo)null) == null) {
         throw StandardException.newException("XSCH1.S", new Object[]{this.id.getContainerId()});
      } else {
         HeapCostController var4 = new HeapCostController();
         var4.init(var3);
         return var4;
      }
   }

   public String toString() {
      return this.id == null ? "null" : this.id.toString();
   }

   public DataValueDescriptor getConglom() {
      return this;
   }

   public int getTypeFormatId() {
      return 467;
   }

   public boolean isNull() {
      return this.id == null;
   }

   public void restoreToNull() {
      this.id = null;
   }

   protected void writeExternal_v10_2(ObjectOutput var1) throws IOException {
      FormatIdUtil.writeFormatIdInteger(var1, this.conglom_format_id);
      var1.writeInt((int)this.id.getSegmentId());
      var1.writeLong(this.id.getContainerId());
      var1.writeInt(this.format_ids.length);
      ConglomerateUtil.writeFormatIdArray(this.format_ids, var1);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      this.writeExternal_v10_2(var1);
      if (this.conglom_format_id == 467) {
         ConglomerateUtil.writeCollationIdArray(this.collation_ids, var1);
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.conglom_format_id = FormatIdUtil.readFormatIdInteger((DataInput)var1);
      int var2 = var1.readInt();
      long var3 = var1.readLong();
      this.id = new ContainerKey((long)var2, var3);
      int var5 = var1.readInt();
      this.format_ids = ConglomerateUtil.readFormatIdArray(var5, var1);
      this.collation_ids = new int[this.format_ids.length];

      for(int var6 = 0; var6 < this.format_ids.length; ++var6) {
         this.collation_ids[var6] = 0;
      }

      if (this.conglom_format_id == 467) {
         this.hasCollatedTypes = ConglomerateUtil.readCollationIdArray(this.collation_ids, var1);
      } else if (this.conglom_format_id != 91) {
      }

   }
}
