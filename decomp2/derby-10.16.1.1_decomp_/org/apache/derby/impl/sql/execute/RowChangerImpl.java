package org.apache.derby.impl.sql.execute;

import java.util.Arrays;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.sql.execute.TemporaryRowHolder;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class RowChangerImpl implements RowChanger {
   boolean isOpen = false;
   boolean[] fixOnUpdate = null;
   long heapConglom;
   DynamicCompiledOpenConglomInfo heapDCOCI;
   StaticCompiledOpenConglomInfo heapSCOCI;
   long[] indexCIDS = null;
   DynamicCompiledOpenConglomInfo[] indexDCOCIs;
   StaticCompiledOpenConglomInfo[] indexSCOCIs;
   IndexRowGenerator[] irgs = null;
   private final Activation activation;
   TransactionController tc;
   FormatableBitSet changedColumnBitSet;
   FormatableBitSet baseRowReadList;
   private int[] baseRowReadMap;
   int[] changedColumnIds;
   TemporaryRowHolderImpl rowHolder;
   String[] indexNames;
   private ConglomerateController baseCC;
   private RowLocation baseRowLocation;
   private IndexSetChanger isc;
   private DataValueDescriptor[] sparseRowArray;
   private int[] partialChangedColumnIds;

   public RowChangerImpl(long var1, StaticCompiledOpenConglomInfo var3, DynamicCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, DynamicCompiledOpenConglomInfo[] var8, int var9, int[] var10, TransactionController var11, FormatableBitSet var12, int[] var13, Activation var14) throws StandardException {
      this.heapConglom = var1;
      this.heapSCOCI = var3;
      this.heapDCOCI = var4;
      this.irgs = var5;
      this.indexCIDS = var6;
      this.indexSCOCIs = var7;
      this.indexDCOCIs = var8;
      this.tc = var11;
      this.baseRowReadList = var12;
      this.baseRowReadMap = var13;
      this.activation = var14;
      if (var10 != null) {
         this.changedColumnIds = RowUtil.inAscendingOrder(var10) ? var10 : this.sortArray(var10);
         this.sparseRowArray = new DataValueDescriptor[this.changedColumnIds[this.changedColumnIds.length - 1] + 1];
         this.changedColumnBitSet = new FormatableBitSet(var9);

         for(int var15 = 0; var15 < this.changedColumnIds.length; ++var15) {
            this.changedColumnBitSet.grow(this.changedColumnIds[var15]);
            this.changedColumnBitSet.set(this.changedColumnIds[var15] - 1);
         }

         if (var12 != null) {
            this.partialChangedColumnIds = new int[this.changedColumnIds.length];
            int var18 = 1;
            int var16 = 0;

            for(int var17 = 0; var17 < this.changedColumnIds.length; ++var17) {
               for(; var16 < this.changedColumnIds[var17]; ++var16) {
                  if (var12.get(var16)) {
                     ++var18;
                  }
               }

               this.partialChangedColumnIds[var17] = var18;
            }
         }
      }

   }

   public void setRowHolder(TemporaryRowHolder var1) {
      this.rowHolder = (TemporaryRowHolderImpl)var1;
   }

   public void setIndexNames(String[] var1) {
      this.indexNames = var1;
   }

   public void open(int var1) throws StandardException {
      this.open(var1, true);
   }

   public void open(int var1, boolean var2) throws StandardException {
      if (this.fixOnUpdate == null) {
         this.fixOnUpdate = new boolean[this.irgs.length];

         for(int var3 = 0; var3 < this.irgs.length; ++var3) {
            this.fixOnUpdate[var3] = true;
         }
      }

      this.openForUpdate(this.fixOnUpdate, var1, var2);
   }

   public void openForUpdate(boolean[] var1, int var2, boolean var3) throws StandardException {
      LanguageConnectionContext var4 = null;
      if (this.activation != null) {
         var4 = this.activation.getLanguageConnectionContext();
      }

      int var5;
      if (var4 == null) {
         var5 = 2;
      } else {
         var5 = var4.getCurrentIsolationLevel();
      }

      switch (var5) {
         case 1 -> var5 = 1;
         case 2 -> var5 = 2;
         case 3 -> var5 = 4;
         case 4 -> var5 = 5;
      }

      try {
         if (this.heapSCOCI != null) {
            this.baseCC = this.tc.openCompiledConglomerate(false, 4 | (var3 ? 0 : 128), var2, var5, this.heapSCOCI, this.heapDCOCI);
         } else {
            this.baseCC = this.tc.openConglomerate(this.heapConglom, false, 4 | (var3 ? 0 : 128), var2, var5);
         }
      } catch (StandardException var7) {
         if (this.activation != null) {
            this.activation.checkStatementValidity();
         }

         throw var7;
      }

      if (this.activation != null) {
         this.activation.checkStatementValidity();
         this.activation.setHeapConglomerateController(this.baseCC);
      }

      if (this.indexCIDS.length != 0) {
         if (this.isc == null) {
            this.isc = new IndexSetChanger(this.irgs, this.indexCIDS, this.indexSCOCIs, this.indexDCOCIs, this.indexNames, this.baseCC, this.tc, var2, this.baseRowReadList, var5, this.activation);
            this.isc.setRowHolder(this.rowHolder);
         } else {
            this.isc.setBaseCC(this.baseCC);
         }

         this.isc.open(var1);
         if (this.baseRowLocation == null) {
            this.baseRowLocation = this.baseCC.newRowLocationTemplate();
         }
      }

      this.isOpen = true;
   }

   public RowLocation insertRow(ExecRow var1, boolean var2) throws StandardException {
      if (!this.baseCC.isKeyed()) {
         if (this.isc == null && !var2) {
            this.baseCC.insert(var1.getRowArray());
         } else {
            if (this.baseRowLocation == null) {
               this.baseRowLocation = this.baseCC.newRowLocationTemplate();
            }

            this.baseCC.insertAndFetchLocation(var1.getRowArray(), this.baseRowLocation);
            if (this.isc != null) {
               this.isc.insert(var1, this.baseRowLocation);
            }
         }
      }

      return var2 ? this.baseRowLocation : null;
   }

   public void deleteRow(ExecRow var1, RowLocation var2) throws StandardException {
      if (this.isc != null) {
         this.isc.delete(var1, var2);
      }

      this.baseCC.delete(var2);
   }

   public void updateRow(ExecRow var1, ExecRow var2, RowLocation var3) throws StandardException {
      if (this.isc != null) {
         this.isc.update(var1, var2, var3);
      }

      if (this.changedColumnBitSet != null) {
         DataValueDescriptor[] var4 = var2.getRowArray();
         int[] var5 = this.partialChangedColumnIds == null ? this.changedColumnIds : this.partialChangedColumnIds;
         int var6 = -1;

         for(int var7 = 0; var7 < var5.length; ++var7) {
            int var8 = var5[var7] - 1;
            var6 = this.changedColumnBitSet.anySetBit(var6);
            this.sparseRowArray[var6] = var4[var8];
         }
      } else {
         this.sparseRowArray = var2.getRowArray();
      }

      this.baseCC.replace(var3, this.sparseRowArray, this.changedColumnBitSet);
   }

   public void finish() throws StandardException {
      if (this.isc != null) {
         this.isc.finish();
      }

   }

   public void close() throws StandardException {
      if (this.isc != null) {
         this.isc.close();
      }

      if (this.baseCC != null) {
         if (this.activation == null || this.activation.getForUpdateIndexScan() == null) {
            this.baseCC.close();
         }

         this.baseCC = null;
      }

      this.isOpen = false;
      if (this.activation != null) {
         this.activation.clearHeapConglomerateController();
      }

   }

   public ConglomerateController getHeapConglomerateController() {
      return this.baseCC;
   }

   private int[] sortArray(int[] var1) {
      int[] var2 = new int[var1.length];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      Arrays.sort(var2);
      return var2;
   }

   public int findSelectedCol(int var1) {
      if (var1 == -1) {
         return -1;
      } else {
         int[] var2 = this.partialChangedColumnIds == null ? this.changedColumnIds : this.partialChangedColumnIds;
         int var3 = -1;

         for(int var4 = 0; var4 < var2.length; ++var4) {
            var3 = this.changedColumnBitSet.anySetBit(var3);
            if (var1 == var3 + 1) {
               return var2[var4];
            }
         }

         return -1;
      }
   }

   public String toString() {
      return super.toString();
   }
}
