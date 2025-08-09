package org.apache.derby.impl.sql.catalog;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.sql.execute.TupleFilter;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class TabInfoImpl {
   static final int ROWNOTDUPLICATE = -1;
   private IndexInfoImpl[] indexes;
   private long heapConglomerate = -1L;
   private int numIndexesSet;
   private boolean heapSet;
   private final CatalogRowFactory crf;
   private boolean computedStreamStorableHeapColIds = false;
   private int[] streamStorableHeapColIds;

   TabInfoImpl(CatalogRowFactory var1) {
      this.crf = var1;
      int var2 = var1.getNumIndexes();
      if (var2 > 0) {
         this.indexes = new IndexInfoImpl[var2];

         for(int var3 = 0; var3 < var2; ++var3) {
            this.indexes[var3] = new IndexInfoImpl(var3, var1);
         }
      }

   }

   long getHeapConglomerate() {
      return this.heapConglomerate;
   }

   void setHeapConglomerate(long var1) {
      this.heapConglomerate = var1;
      this.heapSet = true;
   }

   long getIndexConglomerate(int var1) {
      return this.indexes[var1].getConglomerateNumber();
   }

   void setIndexConglomerate(int var1, long var2) {
      this.indexes[var1].setConglomerateNumber(var2);
      ++this.numIndexesSet;
   }

   void setIndexConglomerate(ConglomerateDescriptor var1) {
      String var3 = var1.getConglomerateName();

      for(int var2 = 0; var2 < this.indexes.length; ++var2) {
         if (this.indexes[var2].getIndexName().equals(var3)) {
            this.indexes[var2].setConglomerateNumber(var1.getConglomerateNumber());
            break;
         }
      }

      ++this.numIndexesSet;
   }

   String getTableName() {
      return this.crf.getCatalogName();
   }

   String getIndexName(int var1) {
      return this.indexes[var1].getIndexName();
   }

   CatalogRowFactory getCatalogRowFactory() {
      return this.crf;
   }

   boolean isComplete() {
      if (!this.heapSet) {
         return false;
      } else {
         return this.indexes == null || this.indexes.length == this.numIndexesSet;
      }
   }

   int getIndexColumnCount(int var1) {
      return this.indexes[var1].getColumnCount();
   }

   IndexRowGenerator getIndexRowGenerator(int var1) {
      return this.indexes[var1].getIndexRowGenerator();
   }

   void setIndexRowGenerator(int var1, IndexRowGenerator var2) {
      this.indexes[var1].setIndexRowGenerator(var2);
   }

   int getNumberOfIndexes() {
      return this.indexes == null ? 0 : this.indexes.length;
   }

   int getBaseColumnPosition(int var1, int var2) {
      return this.indexes[var1].getBaseColumnPosition(var2);
   }

   boolean isIndexUnique(int var1) {
      return this.indexes[var1].isIndexUnique();
   }

   int insertRow(ExecRow var1, TransactionController var2) throws StandardException {
      RowLocation[] var3 = new RowLocation[1];
      return this.insertRowListImpl(new ExecRow[]{var1}, var2, var3);
   }

   int insertRowList(ExecRow[] var1, TransactionController var2) throws StandardException {
      RowLocation[] var3 = new RowLocation[1];
      return this.insertRowListImpl(var1, var2, var3);
   }

   private int insertRowListImpl(ExecRow[] var1, TransactionController var2, RowLocation[] var3) throws StandardException {
      int var8 = -1;
      int var9 = this.crf.getNumIndexes();
      ConglomerateController[] var10 = new ConglomerateController[var9];
      ConglomerateController var4 = var2.openConglomerate(this.getHeapConglomerate(), false, 4, 6, 4);

      for(int var11 = 0; var11 < var9; ++var11) {
         long var12 = this.getIndexConglomerate(var11);
         if (var12 > -1L) {
            var10[var11] = var2.openConglomerate(var12, false, 4, 6, 4);
         }
      }

      RowLocation var5 = var4.newRowLocationTemplate();
      var3[0] = var5;

      for(int var14 = 0; var14 < var1.length; ++var14) {
         ExecRow var16 = var1[var14];
         var4.insertAndFetchLocation(var16.getRowArray(), var5);

         for(int var13 = 0; var13 < var9; ++var13) {
            if (var10[var13] != null) {
               ExecIndexRow var6 = this.getIndexRowFromHeapRow(this.getIndexRowGenerator(var13), var5, var16);
               int var7 = var10[var13].insert(var6.getRowArray());
               if (var7 == 1) {
                  var8 = var14;
               }
            }
         }
      }

      for(int var15 = 0; var15 < var9; ++var15) {
         if (var10[var15] != null) {
            var10[var15].close();
         }
      }

      var4.close();
      return var8;
   }

   int deleteRow(TransactionController var1, ExecIndexRow var2, int var3) throws StandardException {
      return this.deleteRows(var1, var2, 1, (Qualifier[][])null, (TupleFilter)null, var2, -1, var3, true);
   }

   int deleteRow(TransactionController var1, ExecIndexRow var2, int var3, boolean var4) throws StandardException {
      return this.deleteRows(var1, var2, 1, (Qualifier[][])null, (TupleFilter)null, var2, -1, var3, var4);
   }

   int deleteRows(TransactionController var1, ExecIndexRow var2, int var3, Qualifier[][] var4, TupleFilter var5, ExecIndexRow var6, int var7, int var8) throws StandardException {
      return this.deleteRows(var1, var2, var3, var4, var5, var6, var7, var8, true);
   }

   private int deleteRows(TransactionController var1, ExecIndexRow var2, int var3, Qualifier[][] var4, TupleFilter var5, ExecIndexRow var6, int var7, int var8, boolean var9) throws StandardException {
      ExecRow var15 = this.crf.makeEmptyRow();
      int var16 = 0;
      boolean var17 = true;
      RowChanger var14 = this.getRowChanger(var1, (int[])null, var15);
      int var18 = var2 != null && var6 != null ? 6 : 7;
      int var19 = var2 != null && var6 != null && var2 == var6 ? 4 : 5;
      var14.open(var18, var9);
      DataValueDescriptor[] var20 = var2 == null ? null : var2.getRowArray();
      DataValueDescriptor[] var21 = var6 == null ? null : var6.getRowArray();
      ConglomerateController var10 = var1.openConglomerate(this.getHeapConglomerate(), false, 4 | (var9 ? 0 : 128), var18, 4);
      ScanController var11 = var1.openScan(this.getIndexConglomerate(var8), false, 4 | (var9 ? 0 : 128), var18, var19, (FormatableBitSet)null, var20, var3, var4, var21, var7);
      ExecIndexRow var12 = this.getIndexRowFromHeapRow(this.getIndexRowGenerator(var8), var10.newRowLocationTemplate(), this.crf.makeEmptyRow());

      while(var11.fetchNext(var12.getRowArray())) {
         RowLocation var13 = (RowLocation)var12.getColumn(var12.nColumns());
         var10.fetch(var13, var15.getRowArray(), (FormatableBitSet)null);
         if (var5 != null) {
            var17 = var5.execute(var15).equals(true);
         }

         if (var17) {
            var14.deleteRow(var15, var13);
            ++var16;
         }
      }

      var10.close();
      var11.close();
      var14.close();
      return var16;
   }

   ExecRow getRow(TransactionController var1, ExecIndexRow var2, int var3) throws StandardException {
      ConglomerateController var4 = var1.openConglomerate(this.getHeapConglomerate(), false, 0, 6, 4);

      ExecRow var5;
      try {
         var5 = this.getRow(var1, var4, var2, var3);
      } finally {
         var4.close();
      }

      return var5;
   }

   RowLocation getRowLocation(TransactionController var1, ExecIndexRow var2, int var3) throws StandardException {
      ConglomerateController var4 = var1.openConglomerate(this.getHeapConglomerate(), false, 0, 6, 4);

      RowLocation var7;
      try {
         RowLocation[] var5 = new RowLocation[1];
         this.getRowInternal(var1, var4, var2, var3, var5);
         var7 = var5[0];
      } finally {
         var4.close();
      }

      return var7;
   }

   ExecRow getRow(TransactionController var1, ConglomerateController var2, ExecIndexRow var3, int var4) throws StandardException {
      RowLocation[] var5 = new RowLocation[1];
      return this.getRowInternal(var1, var2, var3, var4, var5);
   }

   private ExecRow getRowInternal(TransactionController var1, ConglomerateController var2, ExecIndexRow var3, int var4, RowLocation[] var5) throws StandardException {
      ExecRow var9 = this.crf.makeEmptyRow();
      ScanController var6 = var1.openScan(this.getIndexConglomerate(var4), false, 0, 6, 4, (FormatableBitSet)null, var3.getRowArray(), 1, (Qualifier[][])null, var3.getRowArray(), -1);
      ExecIndexRow var7 = this.getIndexRowFromHeapRow(this.getIndexRowGenerator(var4), var2.newRowLocationTemplate(), this.crf.makeEmptyRow());

      ExecRow var11;
      try {
         if (!var6.fetchNext(var7.getRowArray())) {
            Object var10 = null;
            return (ExecRow)var10;
         }

         RowLocation var8;
         var5[0] = var8 = (RowLocation)var7.getColumn(var7.nColumns());
         var2.fetch(var8, var9.getRowArray(), (FormatableBitSet)null);
         var11 = var9;
      } finally {
         var6.close();
      }

      return var11;
   }

   void updateRow(ExecIndexRow var1, ExecRow var2, int var3, boolean[] var4, int[] var5, TransactionController var6) throws StandardException {
      ExecRow[] var7 = new ExecRow[]{var2};
      this.updateRow(var1, var7, var3, var4, var5, var6);
   }

   void updateRow(ExecIndexRow var1, ExecRow[] var2, int var3, boolean[] var4, int[] var5, TransactionController var6) throws StandardException {
      ExecRow var11 = this.crf.makeEmptyRow();
      RowChanger var12 = this.getRowChanger(var6, var5, var11);
      var12.openForUpdate(var4, 6, true);
      ConglomerateController var7 = var6.openConglomerate(this.getHeapConglomerate(), false, 4, 6, 4);
      ScanController var8 = var6.openScan(this.getIndexConglomerate(var3), false, 4, 6, 4, (FormatableBitSet)null, var1.getRowArray(), 1, (Qualifier[][])null, var1.getRowArray(), -1);
      ExecIndexRow var9 = this.getIndexRowFromHeapRow(this.getIndexRowGenerator(var3), var7.newRowLocationTemplate(), this.crf.makeEmptyRow());
      int var13 = 0;

      while(var8.fetchNext(var9.getRowArray())) {
         RowLocation var10 = (RowLocation)var9.getColumn(var9.nColumns());
         var7.fetch(var10, var11.getRowArray(), (FormatableBitSet)null);
         var12.updateRow(var11, var13 == var2.length - 1 ? var2[var13] : var2[var13++], var10);
      }

      var12.finish();
      var7.close();
      var8.close();
      var12.close();
   }

   Properties getCreateHeapProperties() {
      return this.crf.getCreateHeapProperties();
   }

   Properties getCreateIndexProperties(int var1) {
      return this.crf.getCreateIndexProperties(var1);
   }

   private RowChanger getRowChanger(TransactionController var1, int[] var2, ExecRow var3) throws StandardException {
      int var5 = this.crf.getNumIndexes();
      IndexRowGenerator[] var6 = new IndexRowGenerator[var5];
      long[] var7 = new long[var5];

      for(int var8 = 0; var8 < var5; ++var8) {
         var6[var8] = this.getIndexRowGenerator(var8);
         var7[var8] = this.getIndexConglomerate(var8);
      }

      RowChanger var4 = this.crf.getExecutionFactory().getRowChanger(this.getHeapConglomerate(), (StaticCompiledOpenConglomInfo)null, (DynamicCompiledOpenConglomInfo)null, var6, var7, (StaticCompiledOpenConglomInfo[])null, (DynamicCompiledOpenConglomInfo[])null, this.crf.getHeapColumnCount(), var1, var2, this.getStreamStorableHeapColIds(var3), (Activation)null);
      return var4;
   }

   private int[] getStreamStorableHeapColIds(ExecRow var1) throws StandardException {
      if (!this.computedStreamStorableHeapColIds) {
         int var2 = 0;
         DataValueDescriptor[] var3 = var1.getRowArray();

         for(int var4 = 0; var4 < var3.length; ++var4) {
            if (var3[var4] instanceof StreamStorable) {
               ++var2;
            }
         }

         if (var2 > 0) {
            this.streamStorableHeapColIds = new int[var2];
            int var6 = 0;

            for(int var5 = 0; var5 < var3.length; ++var5) {
               if (var3[var5] instanceof StreamStorable) {
                  this.streamStorableHeapColIds[var6++] = var5;
               }
            }
         }

         this.computedStreamStorableHeapColIds = true;
      }

      return this.streamStorableHeapColIds;
   }

   private ExecIndexRow getIndexRowFromHeapRow(IndexRowGenerator var1, RowLocation var2, ExecRow var3) throws StandardException {
      ExecIndexRow var4 = var1.getIndexRowTemplate();
      var1.getIndexRow(var3, var2, var4, (FormatableBitSet)null);
      return var4;
   }

   public String toString() {
      return "";
   }
}
