package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DistinctScalarAggregateResultSet extends ScalarAggregateResultSet {
   private ColumnOrdering[] order;
   private int maxRowSize;
   private boolean dropDistinctAggSort;
   private long sortId;
   private ScanController scanController;
   private ExecIndexRow sortResultRow;
   private boolean sorted;

   DistinctScalarAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, Activation var5, int var6, int var7, int var8, boolean var9, double var10, double var12) throws StandardException {
      super(var1, var2, var3, var5, var6, var8, var9, var10, var12);
      this.order = (ColumnOrdering[])((FormatableArrayHolder)var5.getPreparedStatement().getSavedObject(var4)).getArray(ColumnOrdering[].class);
      this.maxRowSize = var7;
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.sortResultRow = (ExecIndexRow)this.getRowTemplate().getClone();
      this.sourceExecIndexRow = (ExecIndexRow)this.getRowTemplate().getClone();
      this.source.openCore();

      try {
         this.scanController = this.loadSorter();
      } catch (StandardException var4) {
         this.isOpen = true;

         try {
            this.close();
         } catch (StandardException var3) {
         }

         throw var4;
      }

      this.sorted = true;
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecIndexRow var1 = null;
         ExecIndexRow var2 = null;
         boolean var3 = true;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            while((var1 = this.getRowFromResultSet(var3)) != null) {
               if (var2 == null) {
                  var3 = false;
                  var2 = (ExecIndexRow)var1.getClone();
               } else {
                  this.accumulateScalarAggregation(var1, var2, true);
               }
            }

            if (this.countOfRows == 0) {
               var2 = this.finishAggregation(var2);
               this.setCurrentRow(var2);
               ++this.countOfRows;
            }
         }

         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var2;
      }
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.scanController != null) {
         this.scanController.close();
         this.scanController = null;
      }

      this.source.reopenCore();
      this.scanController = this.loadSorter();
      this.sorted = true;
      ++this.numOpens;
      this.countOfRows = 0;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void close() throws StandardException {
      super.close();
      this.closeSource();
   }

   public ExecIndexRow getRowFromResultSet(boolean var1) throws StandardException {
      ExecIndexRow var2 = null;
      if (this.scanController.next()) {
         this.currentRow = (ExecRow)(var1 ? this.sortResultRow.getClone() : this.sortResultRow);
         var2 = this.getExecutionFactory().getIndexableRow(this.currentRow);
         this.scanController.fetch(var2.getRowArray());
      }

      return var2;
   }

   protected void closeSource() throws StandardException {
      if (this.scanController != null) {
         if (this.dropDistinctAggSort) {
            try {
               this.getTransactionController().dropSort(this.sortId);
            } catch (StandardException var2) {
            }

            this.dropDistinctAggSort = false;
         }

         this.scanController.close();
         this.scanController = null;
      }

      this.source.close();
   }

   private ScanController loadSorter() throws StandardException {
      ExecIndexRow var3 = this.getRowTemplate();
      int var4 = (int)this.optimizerEstimatedRowCount;
      TransactionController var5 = this.getTransactionController();
      GenericAggregator[] var6 = this.getSortAggregators(this.aggInfoList, true, this.activation.getLanguageConnectionContext(), this.source);
      AggregateSortObserver var7 = new AggregateSortObserver(true, var6, this.aggregates, var3);
      this.sortId = var5.createSort((Properties)null, var3.getRowArray(), this.order, var7, false, (long)var4, this.maxRowSize);
      SortController var1 = var5.openSort(this.sortId);

      ExecRow var2;
      for(this.dropDistinctAggSort = true; (var2 = this.source.getNextRowCore()) != null; ++this.rowsInput) {
         var1.insert(var2.getRowArray());
      }

      var1.completedInserts();
      this.scanController = var5.openSortScan(this.sortId, this.activation.getResultSetHoldability());
      var4 = this.rowsInput;
      return this.scanController;
   }
}
