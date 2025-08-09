package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class MergeJoinResultSet extends JoinResultSet {
   private static final int GREATER_THAN = 1;
   private static final int EQUAL = 0;
   private static final int LESS_THAN = -1;
   private GeneratedMethod leftGreaterThanRight;

   MergeJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, Activation var5, GeneratedMethod var6, GeneratedMethod var7, int var8, boolean var9, boolean var10, double var11, double var13) {
      super(var1, var2, var3, var4, var5, var7, var8, var9, var10, var11, var13, (String)null);
      this.leftGreaterThanRight = var6;
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else {
            if (!this.isRightOpen) {
               this.openRight();
            }

            while(this.leftRow != null) {
               int var1;
               while((var1 = (Integer)this.leftGreaterThanRight.invoke(this.activation)) == 1) {
                  this.rightRow = this.rightResultSet.getNextRowCore();
                  ++this.rowsSeenRight;
                  if (this.rightRow == null) {
                     this.clearCurrentRow();
                     return (ExecRow)null;
                  }
               }

               if (var1 == 0 && this.restrictionIsTrue()) {
                  ExecRow var2 = this.getReturnRow(this.leftRow, this.rightRow);
                  this.leftRow = this.leftResultSet.getNextRowCore();
                  return var2;
               }

               this.leftRow = this.leftResultSet.getNextRowCore();
               ++this.rowsSeenLeft;
            }

            this.clearCurrentRow();
            return (ExecRow)null;
         }
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.leftResultSet.getTimeSpent(1) - this.rightResultSet.getTimeSpent(1) : var2;
   }

   private ExecRow getReturnRow(ExecRow var1, ExecRow var2) throws StandardException {
      if (this.mergedRow == null) {
         this.mergedRow = this.getExecutionFactory().getValueRow(this.leftNumCols + this.rightNumCols);
      }

      int var3 = 1;

      int var4;
      for(var4 = 1; var3 <= this.leftNumCols; ++var4) {
         DataValueDescriptor var5 = var1.getColumn(var3);
         if (var5 != null && var5.hasStream()) {
            var5 = var5.cloneValue(false);
         }

         this.mergedRow.setColumn(var4, var5);
         ++var3;
      }

      for(int var6 = 1; var6 <= this.rightNumCols; ++var4) {
         DataValueDescriptor var7 = var2.getColumn(var6);
         if (var7 != null && var7.hasStream()) {
            var7 = var7.cloneValue(false);
         }

         this.mergedRow.setColumn(var4, var7);
         ++var6;
      }

      this.setCurrentRow(this.mergedRow);
      ++this.rowsReturned;
      this.nextTime += this.getElapsedMillis(this.beginTime);
      return this.mergedRow;
   }

   private boolean restrictionIsTrue() throws StandardException {
      if (this.restriction != null) {
         DataValueDescriptor var1 = (DataValueDescriptor)this.restriction.invoke(this.activation);
         if (var1.isNull() || !var1.getBoolean()) {
            ++this.rowsFiltered;
            return false;
         }
      }

      return true;
   }
}
