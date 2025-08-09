package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class NestedLoopJoinResultSet extends JoinResultSet {
   private boolean returnedRowMatchingRightSide = false;
   private ExecRow rightTemplate;

   void clearScanState() {
      this.returnedRowMatchingRightSide = false;
      super.clearScanState();
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         boolean var2 = false;
         boolean var3 = false;
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else {
            if (!this.isRightOpen && this.leftRow != null) {
               this.leftRow = this.leftResultSet.getNextRowCore();
               if (this.leftRow == null) {
                  this.closeRight();
               } else {
                  ++this.rowsSeenLeft;
                  this.openRight();
               }
            }

            while(this.leftRow != null && !var2) {
               if (this.oneRowRightSide && this.returnedRowMatchingRightSide) {
                  this.rightRow = null;
                  this.returnedRowMatchingRightSide = false;
               } else {
                  this.rightRow = this.rightResultSet.getNextRowCore();
                  if (this.notExistsRightSide) {
                     if (this.rightRow == null) {
                        this.rightRow = this.rightTemplate;
                     } else {
                        this.rightRow = null;
                     }
                  }

                  this.returnedRowMatchingRightSide = this.rightRow != null;
               }

               if (this.rightRow == null) {
                  this.leftRow = this.leftResultSet.getNextRowCore();
                  if (this.leftRow == null) {
                     this.closeRight();
                  } else {
                     ++this.rowsSeenLeft;
                     this.openRight();
                  }
               } else {
                  ++this.rowsSeenRight;
                  if (this.restriction != null) {
                     DataValueDescriptor var6 = (DataValueDescriptor)this.restriction.invoke(this.activation);
                     var3 = !var6.isNull() && var6.getBoolean();
                     if (!var3) {
                        ++this.rowsFiltered;
                        continue;
                     }
                  }

                  if (this.mergedRow == null) {
                     this.mergedRow = this.getExecutionFactory().getValueRow(this.leftNumCols + this.rightNumCols);
                  }

                  int var4 = 1;

                  int var5;
                  for(var5 = 1; var4 <= this.leftNumCols; ++var5) {
                     DataValueDescriptor var7 = this.leftRow.getColumn(var4);
                     if (var7 != null && var7.hasStream()) {
                        var7 = var7.cloneValue(false);
                     }

                     this.mergedRow.setColumn(var5, var7);
                     ++var4;
                  }

                  if (!this.notExistsRightSide) {
                     for(int var9 = 1; var9 <= this.rightNumCols; ++var5) {
                        DataValueDescriptor var10 = this.rightRow.getColumn(var9);
                        if (var10 != null && var10.hasStream()) {
                           var10 = var10.cloneValue(false);
                        }

                        this.mergedRow.setColumn(var5, var10);
                        ++var9;
                     }
                  }

                  this.setCurrentRow(this.mergedRow);
                  var2 = true;
               }
            }

            if (var2) {
               var1 = this.mergedRow;
               ++this.rowsReturned;
            } else {
               this.clearCurrentRow();
            }

            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var1;
         }
      }
   }

   public void close() throws StandardException {
      if (this.isOpen) {
         this.beginTime = this.getCurrentTimeMillis();
         this.clearCurrentRow();
         super.close();
         this.returnedRowMatchingRightSide = false;
         this.closeTime += this.getElapsedMillis(this.beginTime);
      }

   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.leftResultSet.getTimeSpent(1) - this.rightResultSet.getTimeSpent(1) : var2;
   }

   NestedLoopJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, Activation var5, GeneratedMethod var6, int var7, boolean var8, boolean var9, double var10, double var12, String var14) {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var12, var14);
      if (var9) {
         this.rightTemplate = this.getExecutionFactory().getValueRow(var4);
      }

   }
}
