package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class NestedLoopLeftOuterJoinResultSet extends NestedLoopJoinResultSet {
   protected GeneratedMethod emptyRowFun;
   private boolean wasRightOuterJoin;
   private boolean matchRight = false;
   private boolean returnedEmptyRight = false;
   private ExecRow rightEmptyRow = null;
   public int emptyRightRowsReturned = 0;

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
            if (this.returnedEmptyRight) {
               this.leftRow = this.leftResultSet.getNextRowCore();
               if (this.leftRow == null) {
                  this.closeRight();
               } else {
                  ++this.rowsSeenLeft;
                  this.openRight();
               }

               this.returnedEmptyRight = false;
            }

            while(this.leftRow != null && !var2) {
               this.rightRow = this.rightResultSet.getNextRowCore();
               if (this.rightRow == null) {
                  if (!this.matchRight) {
                     var2 = true;
                     this.returnedEmptyRight = true;
                     if (this.rightEmptyRow == null) {
                        this.rightEmptyRow = (ExecRow)this.emptyRowFun.invoke(this.activation);
                     }

                     this.getMergedRow(this.leftRow, this.rightEmptyRow);
                     ++this.emptyRightRowsReturned;
                  } else {
                     this.matchRight = false;
                     this.leftRow = this.leftResultSet.getNextRowCore();
                     if (this.leftRow == null) {
                        this.closeRight();
                     } else {
                        ++this.rowsSeenLeft;
                        this.openRight();
                     }
                  }
               } else {
                  ++this.rowsSeenRight;
                  if (this.restriction != null) {
                     DataValueDescriptor var4 = (DataValueDescriptor)this.restriction.invoke(this.activation);
                     var3 = !var4.isNull() && var4.getBoolean();
                     if (!var3) {
                        ++this.rowsFiltered;
                        continue;
                     }
                  }

                  this.matchRight = true;
                  this.getMergedRow(this.leftRow, this.rightRow);
                  var2 = true;
               }
            }

            if (var2) {
               var1 = this.mergedRow;
               this.setCurrentRow(this.mergedRow);
               ++this.rowsReturned;
            } else {
               this.clearCurrentRow();
            }

            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var1;
         }
      }
   }

   protected void getMergedRow(ExecRow var1, ExecRow var2) throws StandardException {
      int var5;
      int var6;
      if (this.wasRightOuterJoin) {
         ExecRow var7 = var1;
         var1 = var2;
         var2 = var7;
         var5 = this.rightNumCols;
         var6 = this.leftNumCols;
      } else {
         var5 = this.leftNumCols;
         var6 = this.rightNumCols;
      }

      if (this.mergedRow == null) {
         this.mergedRow = this.getExecutionFactory().getValueRow(var5 + var6);
      }

      int var3 = 1;

      int var4;
      for(var4 = 1; var3 <= var5; ++var4) {
         DataValueDescriptor var9 = var1.getColumn(var3);
         if (var9 != null && var9.hasStream()) {
            var9 = var9.cloneValue(false);
         }

         this.mergedRow.setColumn(var4, var9);
         ++var3;
      }

      for(int var8 = 1; var8 <= var6; ++var4) {
         DataValueDescriptor var10 = var2.getColumn(var8);
         if (var10 != null && var10.hasStream()) {
            var10 = var10.cloneValue(false);
         }

         this.mergedRow.setColumn(var4, var10);
         ++var8;
      }

   }

   void clearScanState() {
      this.matchRight = false;
      this.returnedEmptyRight = false;
      this.rightEmptyRow = null;
      this.emptyRightRowsReturned = 0;
      super.clearScanState();
   }

   NestedLoopLeftOuterJoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, Activation var5, GeneratedMethod var6, int var7, GeneratedMethod var8, boolean var9, boolean var10, boolean var11, double var12, double var14, String var16) {
      super(var1, var2, var3, var4, var5, var6, var7, var10, var11, var12, var14, var16);
      this.emptyRowFun = var8;
      this.wasRightOuterJoin = var9;
   }
}
