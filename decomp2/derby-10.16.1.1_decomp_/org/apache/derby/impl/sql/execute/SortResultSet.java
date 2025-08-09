package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class SortResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public int rowsInput;
   public int rowsReturned;
   public boolean distinct;
   public NoPutResultSet source;
   private ColumnOrdering[] order;
   private ColumnOrdering[] savedOrder;
   private SortObserver observer;
   private ExecRow sortTemplateRow;
   public boolean isInSortedOrder;
   private NoPutResultSet originalSource;
   private int maxRowSize;
   private ScanController scanController;
   private ExecRow sortResultRow;
   private ExecRow currSortedRow;
   private boolean nextCalled;
   private int numColumns;
   private long genericSortId;
   private boolean dropGenericSort;
   private boolean sorted;
   public Properties sortProperties = new Properties();

   public SortResultSet(NoPutResultSet var1, boolean var2, boolean var3, int var4, Activation var5, int var6, int var7, int var8, double var9, double var11) throws StandardException {
      super(var5, var8, var9, var11);
      this.distinct = var2;
      this.isInSortedOrder = var3;
      this.source = var1;
      this.originalSource = var1;
      this.maxRowSize = var7;
      ExecPreparedStatement var13 = var5.getPreparedStatement();
      this.sortTemplateRow = ((ExecRowBuilder)var13.getSavedObject(var6)).build(var5.getExecutionFactory());
      this.order = (ColumnOrdering[])((FormatableArrayHolder)var13.getSavedObject(var4)).getArray(ColumnOrdering[].class);
      this.savedOrder = this.order;
      this.observer = new BasicSortObserver(true, var2, this.sortTemplateRow, true);
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.nextCalled = false;
      this.beginTime = this.getCurrentTimeMillis();
      this.order = this.savedOrder;
      this.sortResultRow = this.sortTemplateRow.getClone();
      this.source.openCore();

      try {
         if (this.isInSortedOrder && this.distinct) {
            this.currSortedRow = this.getNextRowFromRS();
            if (this.currSortedRow != null) {
               this.currSortedRow = this.currSortedRow.getClone();
            }
         } else {
            this.scanController = this.loadSorter();
            this.sorted = true;
         }
      } catch (StandardException var4) {
         this.isOpen = true;

         try {
            this.close();
         } catch (StandardException var3) {
         }

         throw var4;
      }

      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   private ScanController loadSorter() throws StandardException {
      boolean var6 = this.order.length == 0 || this.isInSortedOrder;
      int var7 = (int)this.optimizerEstimatedRowCount;
      TransactionController var8 = this.getTransactionController();
      long var2 = var8.createSort((Properties)null, this.sortTemplateRow.getRowArray(), this.order, this.observer, var6, (long)var7, this.maxRowSize);
      SortController var1 = var8.openSort(var2);
      this.genericSortId = var2;
      this.dropGenericSort = true;

      ExecRow var5;
      while((var5 = this.getNextRowFromRS()) != null) {
         var1.insert(var5.getRowArray());
      }

      this.source.close();
      this.sortProperties = var1.getSortInfo().getAllSortInfo(this.sortProperties);
      var1.completedInserts();
      return var8.openSortScan(var2, this.activation.getResultSetHoldability());
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else if (!this.isOpen) {
         return null;
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isInSortedOrder && this.distinct) {
            if (this.currSortedRow == null) {
               this.nextTime += this.getElapsedMillis(this.beginTime);
               return null;
            } else if (!this.nextCalled) {
               this.nextCalled = true;
               this.numColumns = this.currSortedRow.getRowArray().length;
               this.nextTime += this.getElapsedMillis(this.beginTime);
               ++this.rowsReturned;
               this.setCurrentRow(this.currSortedRow);
               return this.currSortedRow;
            } else {
               for(ExecRow var2 = this.getNextRowFromRS(); var2 != null; var2 = this.getNextRowFromRS()) {
                  if (!this.filterRow(this.currSortedRow, var2)) {
                     this.currSortedRow = var2.getClone();
                     this.setCurrentRow(this.currSortedRow);
                     this.nextTime += this.getElapsedMillis(this.beginTime);
                     ++this.rowsReturned;
                     return this.currSortedRow;
                  }
               }

               this.currSortedRow = null;
               this.nextTime += this.getElapsedMillis(this.beginTime);
               return null;
            }
         } else {
            ExecRow var1 = this.getNextRowFromRS();
            if (var1 != null) {
               this.setCurrentRow(var1);
               ++this.rowsReturned;
            }

            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var1;
         }
      }
   }

   private boolean filterRow(ExecRow var1, ExecRow var2) throws StandardException {
      for(int var3 = 1; var3 <= this.numColumns; ++var3) {
         DataValueDescriptor var4 = var1.getColumn(var3);
         DataValueDescriptor var5 = var2.getColumn(var3);
         if (!var4.compare(2, var5, true, true)) {
            return false;
         }
      }

      return true;
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.sortResultRow = null;
         this.closeSource();
         if (this.dropGenericSort) {
            this.getTransactionController().dropSort(this.genericSortId);
            this.dropGenericSort = false;
         }

         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
      this.isOpen = false;
   }

   public void finish() throws StandardException {
      this.source.finish();
      this.finishAndRTS();
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.originalSource.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      if (!this.isOpen) {
         return null;
      } else {
         RowLocation var1 = this.scanController.newRowLocationTemplate();
         this.scanController.fetchLocation(var1);
         return var1;
      }
   }

   public ExecRow getCurrentRow() throws StandardException {
      return this.currentRow;
   }

   private ExecRow getNextRowFromRS() throws StandardException {
      return this.scanController == null ? this.getRowFromResultSet() : this.getRowFromSorter();
   }

   private ExecRow getRowFromResultSet() throws StandardException {
      ExecRow var1;
      if ((var1 = this.source.getNextRowCore()) != null) {
         ++this.rowsInput;
      }

      return var1;
   }

   private ExecRow getRowFromSorter() throws StandardException {
      ExecRow var1 = null;
      if (this.scanController.next()) {
         this.currentRow = this.sortResultRow;
         var1 = this.sortResultRow;
         this.scanController.fetch(var1.getRowArray());
      }

      return var1;
   }

   private void closeSource() throws StandardException {
      if (this.scanController == null) {
         this.source.close();
      } else {
         this.scanController.close();
         this.scanController = null;
      }

   }
}
