package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class SetOpResultSet extends NoPutResultSetImpl implements CursorResultSet {
   private final NoPutResultSet leftSource;
   private final NoPutResultSet rightSource;
   private final Activation activation;
   private final int opType;
   private final boolean all;
   private final int resultSetNumber;
   private DataValueDescriptor[] prevCols;
   private ExecRow leftInputRow;
   private ExecRow rightInputRow;
   private final int[] intermediateOrderByColumns;
   private final int[] intermediateOrderByDirection;
   private final boolean[] intermediateOrderByNullsLow;
   private int rowsSeenLeft;
   private int rowsSeenRight;
   private int rowsReturned;

   SetOpResultSet(NoPutResultSet var1, NoPutResultSet var2, Activation var3, int var4, long var5, double var7, int var9, boolean var10, int var11, int var12, int var13) {
      super(var3, var4, (double)var5, var7);
      this.leftSource = var1;
      this.rightSource = var2;
      this.activation = var3;
      this.resultSetNumber = var4;
      this.opType = var9;
      this.all = var10;
      ExecPreparedStatement var14 = var3.getPreparedStatement();
      this.intermediateOrderByColumns = (int[])var14.getSavedObject(var11);
      this.intermediateOrderByDirection = (int[])var14.getSavedObject(var12);
      this.intermediateOrderByNullsLow = (boolean[])var14.getSavedObject(var13);
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.leftSource.openCore();

      try {
         this.rightSource.openCore();
         this.rightInputRow = this.rightSource.getNextRowCore();
      } catch (StandardException var4) {
         this.isOpen = true;

         try {
            this.close();
         } catch (StandardException var3) {
         }

         throw var4;
      }

      if (this.rightInputRow != null) {
         ++this.rowsSeenRight;
      }

      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            label62:
            while(true) {
               DataValueDescriptor[] var1;
               while(true) {
                  if ((this.leftInputRow = this.leftSource.getNextRowCore()) == null) {
                     break label62;
                  }

                  ++this.rowsSeenLeft;
                  var1 = this.leftInputRow.getRowArray();
                  if (this.all) {
                     break;
                  }

                  if (!this.isDuplicate(var1)) {
                     this.prevCols = this.leftInputRow.getRowArrayClone();
                     break;
                  }
               }

               int var2 = 0;

               while(this.rightInputRow != null && (var2 = this.compare(var1, this.rightInputRow.getRowArray())) > 0) {
                  this.rightInputRow = this.rightSource.getNextRowCore();
                  if (this.rightInputRow != null) {
                     ++this.rowsSeenRight;
                  }
               }

               if (this.rightInputRow != null && var2 >= 0) {
                  if (this.all) {
                     this.rightInputRow = this.rightSource.getNextRowCore();
                     if (this.rightInputRow != null) {
                        ++this.rowsSeenRight;
                     }
                  }

                  if (this.opType == 1) {
                     break;
                  }
               } else if (this.opType == 2) {
                  break;
               }
            }
         }

         this.setCurrentRow(this.leftInputRow);
         if (this.currentRow != null) {
            ++this.rowsReturned;
         }

         this.nextTime += this.getElapsedMillis(this.beginTime);
         return this.currentRow;
      }
   }

   private void advanceRightPastDuplicates(DataValueDescriptor[] var1) throws StandardException {
      while((this.rightInputRow = this.rightSource.getNextRowCore()) != null) {
         ++this.rowsSeenRight;
         if (this.compare(var1, this.rightInputRow.getRowArray()) == 0) {
         }
      }

   }

   private int compare(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      for(int var3 = 0; var3 < this.intermediateOrderByColumns.length; ++var3) {
         int var4 = this.intermediateOrderByColumns[var3];
         if (var1[var4].compare(1, var2[var4], true, this.intermediateOrderByNullsLow[var3], false)) {
            return -1 * this.intermediateOrderByDirection[var3];
         }

         if (!var1[var4].compare(2, var2[var4], true, this.intermediateOrderByNullsLow[var3], false)) {
            return this.intermediateOrderByDirection[var3];
         }
      }

      return 0;
   }

   private boolean isDuplicate(DataValueDescriptor[] var1) throws StandardException {
      if (this.prevCols == null) {
         return false;
      } else {
         for(int var2 = 0; var2 < this.intermediateOrderByColumns.length; ++var2) {
            int var3 = this.intermediateOrderByColumns[var2];
            if (!var1[var3].compare(2, this.prevCols[var3], true, false)) {
               return false;
            }
         }

         return true;
      }
   }

   public ExecRow getCurrentRow() {
      return this.currentRow;
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.prevCols = null;
         this.leftSource.close();
         this.rightSource.close();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      this.leftSource.finish();
      this.rightSource.finish();
      this.finishAndRTS();
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.leftSource.getTimeSpent(1) - this.rightSource.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.leftSource).getRowLocation();
   }

   public int getOpType() {
      return this.opType;
   }

   public int getResultSetNumber() {
      return this.resultSetNumber;
   }

   public NoPutResultSet getLeftSourceInput() {
      return this.leftSource;
   }

   public NoPutResultSet getRightSourceInput() {
      return this.rightSource;
   }

   public int getRowsSeenLeft() {
      return this.rowsSeenLeft;
   }

   public int getRowsSeenRight() {
      return this.rowsSeenRight;
   }

   public int getRowsReturned() {
      return this.rowsReturned;
   }
}
