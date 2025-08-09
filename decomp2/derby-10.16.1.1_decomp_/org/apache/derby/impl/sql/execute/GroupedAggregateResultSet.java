package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class GroupedAggregateResultSet extends GenericAggregateResultSet implements CursorResultSet {
   public int rowsInput;
   public int rowsReturned;
   private ColumnOrdering[] order;
   public boolean hasDistinctAggregate;
   public boolean isInSortedOrder;
   private int numDistinctAggs = 0;
   private int maxRowSize;
   private ScanController scanController;
   private ExecIndexRow sourceExecIndexRow;
   private ExecIndexRow sortResultRow;
   private boolean resultsComplete;
   private List finishedResults;
   private ExecIndexRow[] resultRows;
   private List distinctValues;
   private boolean rollup;
   private boolean usingAggregateObserver = false;
   private long genericSortId;
   private TransactionController tc;
   public Properties sortProperties = new Properties();

   GroupedAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, int var4, Activation var5, int var6, int var7, int var8, double var9, double var11, boolean var13) throws StandardException {
      super(var1, var3, var5, var6, var8, var9, var11);
      this.isInSortedOrder = var2;
      this.rollup = var13;
      this.finishedResults = new ArrayList();
      this.order = (ColumnOrdering[])((FormatableArrayHolder)var5.getPreparedStatement().getSavedObject(var4)).getArray(ColumnOrdering[].class);
      this.hasDistinctAggregate = this.aggInfoList.hasDistinct();
      this.usingAggregateObserver = !var2 && !this.rollup && !this.hasDistinctAggregate;
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.sortResultRow = (ExecIndexRow)this.getRowTemplate().getClone();
      this.sourceExecIndexRow = (ExecIndexRow)this.getRowTemplate().getClone();
      this.source.openCore();

      try {
         if (!this.isInSortedOrder) {
            this.scanController = this.loadSorter();
         }

         ExecIndexRow var1 = this.getNextRowFromRS();
         this.resultsComplete = var1 == null;
         if (this.usingAggregateObserver) {
            if (var1 != null) {
               this.finishedResults.add(this.finishAggregation(var1).getClone());
            }
         } else if (!this.resultsComplete) {
            if (this.rollup) {
               this.resultRows = new ExecIndexRow[this.numGCols() + 1];
            } else {
               this.resultRows = new ExecIndexRow[1];
            }

            if (this.aggInfoList.hasDistinct()) {
               this.distinctValues = new ArrayList(this.resultRows.length);
            }

            for(int var2 = 0; var2 < this.resultRows.length; ++var2) {
               this.resultRows[var2] = (ExecIndexRow)var1.getClone();
               this.initializeVectorAggregation(this.resultRows[var2]);
               if (this.aggInfoList.hasDistinct()) {
                  this.distinctValues.add(new ArrayList(this.aggregates.length));
                  this.initializeDistinctMaps(var2, true);
               }
            }
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
      int var3 = (int)this.optimizerEstimatedRowCount;
      ExecIndexRow var4 = this.getRowTemplate();
      this.tc = this.getTransactionController();
      Object var5;
      if (this.usingAggregateObserver) {
         var5 = new AggregateSortObserver(true, this.aggregates, this.aggregates, var4);
      } else {
         var5 = new BasicSortObserver(true, false, var4, true);
      }

      this.genericSortId = this.tc.createSort((Properties)null, var4.getRowArray(), this.order, (SortObserver)var5, false, (long)var3, this.maxRowSize);
      SortController var1 = this.tc.openSort(this.genericSortId);

      ExecIndexRow var2;
      while((var2 = this.getNextRowFromRS()) != null) {
         var1.insert(var2.getRowArray());
      }

      this.source.close();
      var1.completedInserts();
      this.sortProperties = var1.getSortInfo().getAllSortInfo(this.sortProperties);
      if (this.aggInfoList.hasDistinct()) {
         this.numDistinctAggs = 1;
      }

      return this.tc.openSortScan(this.genericSortId, this.activation.getResultSetHoldability());
   }

   private int numGCols() {
      return this.order.length - this.numDistinctAggs;
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else if (!this.isOpen) {
         return null;
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         if (this.finishedResults.size() > 0) {
            return this.makeCurrent(this.finishedResults.remove(0));
         } else if (this.resultsComplete) {
            return null;
         } else {
            ExecIndexRow var1 = this.getNextRowFromRS();
            if (var1 == null) {
               return this.finalizeResults();
            } else if (this.usingAggregateObserver) {
               return this.finishAggregation(var1);
            } else {
               while(var1 != null) {
                  ExecIndexRow var2 = this.resultRows[this.resultRows.length - 1];
                  ExecRow var3 = var1.getClone();
                  this.initializeVectorAggregation(var1);
                  int var4 = this.sameGroupingValues(var2, var1);

                  for(int var5 = 0; var5 < this.resultRows.length; ++var5) {
                     boolean var6 = this.rollup ? var5 <= var4 : var4 == this.numGCols();
                     if (var6) {
                        this.mergeVectorAggregates(var1, this.resultRows[var5], var5);
                     } else {
                        this.setRollupColumnsToNull(this.resultRows[var5], var5);
                        this.finishedResults.add(this.finishAggregation(this.resultRows[var5]));
                        this.resultRows[var5] = (ExecIndexRow)var3.getClone();
                        this.initializeVectorAggregation(this.resultRows[var5]);
                        this.initializeDistinctMaps(var5, false);
                     }
                  }

                  if (this.finishedResults.size() > 0) {
                     this.nextTime += this.getElapsedMillis(this.beginTime);
                     ++this.rowsReturned;
                     return this.makeCurrent(this.finishedResults.remove(0));
                  }

                  var1 = this.getNextRowFromRS();
               }

               return this.finalizeResults();
            }
         }
      }
   }

   private ExecRow makeCurrent(Object var1) throws StandardException {
      ExecRow var2 = (ExecRow)var1;
      this.setCurrentRow(var2);
      return var2;
   }

   private ExecRow finalizeResults() throws StandardException {
      this.resultsComplete = true;
      if (!this.usingAggregateObserver) {
         for(int var1 = 0; var1 < this.resultRows.length; ++var1) {
            this.setRollupColumnsToNull(this.resultRows[var1], var1);
            this.finishedResults.add(this.finishAggregation(this.resultRows[var1]));
         }
      }

      this.nextTime += this.getElapsedMillis(this.beginTime);
      return this.finishedResults.size() > 0 ? this.makeCurrent(this.finishedResults.remove(0)) : null;
   }

   private int sameGroupingValues(ExecRow var1, ExecRow var2) throws StandardException {
      for(int var3 = 0; var3 < this.numGCols(); ++var3) {
         DataValueDescriptor var4 = var1.getColumn(this.order[var3].getColumnId() + 1);
         DataValueDescriptor var5 = var2.getColumn(this.order[var3].getColumnId() + 1);
         if (!var4.compare(2, var5, true, true)) {
            return var3;
         }
      }

      return this.numGCols();
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.sortResultRow = null;
         this.sourceExecIndexRow = null;
         this.closeSource();
         if (!this.isInSortedOrder) {
            this.tc.dropSort(this.genericSortId);
         }

         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
      this.isOpen = false;
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

   private ExecIndexRow getNextRowFromRS() throws StandardException {
      return this.scanController == null ? this.getRowFromResultSet() : this.getRowFromSorter();
   }

   private ExecIndexRow getRowFromResultSet() throws StandardException {
      ExecIndexRow var2 = null;
      ExecRow var1;
      if ((var1 = this.source.getNextRowCore()) != null) {
         ++this.rowsInput;
         this.sourceExecIndexRow.execRowToExecIndexRow(var1);
         var2 = this.sourceExecIndexRow;
      }

      return var2;
   }

   private void setRollupColumnsToNull(ExecRow var1, int var2) throws StandardException {
      int var3 = this.resultRows.length - var2 - 1;

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = this.numGCols() - 1 - var4;
         DataValueDescriptor var6 = var1.getColumn(this.order[var5].getColumnId() + 1);
         var6.setToNull();
      }

   }

   private ExecIndexRow getRowFromSorter() throws StandardException {
      ExecIndexRow var1 = null;
      if (this.scanController.next()) {
         this.currentRow = this.sortResultRow;
         var1 = this.getExecutionFactory().getIndexableRow(this.currentRow);
         this.scanController.fetch(var1.getRowArray());
      }

      return var1;
   }

   public void closeSource() throws StandardException {
      if (this.scanController == null) {
         this.source.close();
      } else {
         this.scanController.close();
         this.scanController = null;
      }

   }

   private void initializeVectorAggregation(ExecRow var1) throws StandardException {
      int var2 = this.aggregates.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         GenericAggregator var4 = this.aggregates[var3];
         var4.initialize(var1);
         var4.accumulate(var1, var1);
      }

   }

   private void mergeVectorAggregates(ExecRow var1, ExecRow var2, int var3) throws StandardException {
      for(int var4 = 0; var4 < this.aggregates.length; ++var4) {
         GenericAggregator var5 = this.aggregates[var4];
         AggregatorInfo var6 = (AggregatorInfo)this.aggInfoList.elementAt(var4);
         if (var6.isDistinct()) {
            DataValueDescriptor var7 = var5.getInputColumnValue(var1);
            if (!var7.isNull() && !((Set)((List)this.distinctValues.get(var3)).get(var4)).add(var7)) {
               continue;
            }
         }

         var5.merge(var1, var2);
      }

   }

   private void initializeDistinctMaps(int var1, boolean var2) throws StandardException {
      for(int var3 = 0; var3 < this.aggregates.length; ++var3) {
         AggregatorInfo var4 = (AggregatorInfo)this.aggInfoList.elementAt(var3);
         if (var2) {
            ((List)this.distinctValues.get(var1)).add(var4.isDistinct() ? new HashSet() : null);
         }

         if (var4.isDistinct()) {
            Set var5 = (Set)((List)this.distinctValues.get(var1)).get(var3);
            var5.clear();
            DataValueDescriptor var6 = this.aggregates[var3].getInputColumnValue(this.resultRows[var1]);
            var5.add(var6);
         }
      }

   }
}
