package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class ScalarAggregateResultSet extends GenericAggregateResultSet implements CursorResultSet {
   public int rowsInput;
   public boolean singleInputRow;
   protected boolean isInSortedOrder;
   protected ExecIndexRow sourceExecIndexRow;
   private boolean nextSatisfied;
   protected int countOfRows;

   ScalarAggregateResultSet(NoPutResultSet var1, boolean var2, int var3, Activation var4, int var5, int var6, boolean var7, double var8, double var10) throws StandardException {
      super(var1, var3, var4, var5, var6, var8, var10);
      this.isInSortedOrder = var2;
      this.singleInputRow = var7;
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.sourceExecIndexRow = (ExecIndexRow)this.getRowTemplate().getClone();
      this.source.openCore();
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else if (this.nextSatisfied) {
         this.clearCurrentRow();
         return null;
      } else {
         ExecIndexRow var1 = null;
         ExecIndexRow var2 = null;
         boolean var3 = this.singleInputRow && this.aggregates[0].getAggregatorInfo().aggregateName.equals("MIN");
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            while((var1 = this.getRowFromResultSet(false)) != null) {
               if (var2 == null) {
                  var2 = this.singleInputRow && var3 ? var1 : (ExecIndexRow)var1.getClone();
                  this.initializeScalarAggregation(var2);
               } else {
                  this.accumulateScalarAggregation(var1, var2, false);
               }

               if (this.singleInputRow && (var3 || !var2.getColumn(this.aggregates[0].aggregatorColumnId).isNull())) {
                  break;
               }
            }

            if (this.countOfRows == 0) {
               var2 = this.finishAggregation(var2);
               this.setCurrentRow(var2);
               ++this.countOfRows;
            }
         }

         this.nextSatisfied = true;
         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var2;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.countOfRows = 0;
         this.sourceExecIndexRow = null;
         this.source.close();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
      this.nextSatisfied = false;
      this.isOpen = false;
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.originalSource.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      return null;
   }

   public ExecRow getCurrentRow() throws StandardException {
      return this.currentRow;
   }

   public ExecIndexRow getRowFromResultSet(boolean var1) throws StandardException {
      ExecIndexRow var3 = null;
      ExecRow var2;
      if ((var2 = this.source.getNextRowCore()) != null) {
         ++this.rowsInput;
         this.sourceExecIndexRow.execRowToExecIndexRow(var1 ? var2.getClone() : var2);
         var3 = this.sourceExecIndexRow;
      }

      return var3;
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.reopenCore();
      ++this.numOpens;
      this.countOfRows = 0;
      this.nextSatisfied = false;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   protected void accumulateScalarAggregation(ExecRow var1, ExecRow var2, boolean var3) throws StandardException {
      int var4 = this.aggregates.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         GenericAggregator var6 = this.aggregates[var5];
         if (var3 && !var6.getAggregatorInfo().isDistinct()) {
            var6.merge(var1, var2);
         } else {
            var6.accumulate(var1, var2);
         }
      }

   }

   private void initializeScalarAggregation(ExecRow var1) throws StandardException {
      int var2 = this.aggregates.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         GenericAggregator var4 = this.aggregates[var3];
         var4.initialize(var1);
         var4.accumulate(var1, var1);
      }

   }
}
