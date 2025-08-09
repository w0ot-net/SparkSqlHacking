package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class UnionResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public int rowsSeenLeft;
   public int rowsSeenRight;
   public int rowsReturned;
   private int whichSource = 1;
   private int source1FinalRowCount = -1;
   public NoPutResultSet source1;
   public NoPutResultSet source2;

   public UnionResultSet(NoPutResultSet var1, NoPutResultSet var2, Activation var3, int var4, double var5, double var7) {
      super(var3, var4, var5, var7);
      this.source1 = var1;
      this.source2 = var2;
      this.recordConstructorTime();
   }

   public ResultDescription getResultDescription() {
      return this.source1.getResultDescription();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source1.openCore();
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            switch (this.whichSource) {
               case 1:
                  var1 = this.source1.getNextRowCore();
                  if (var1 == (ExecRow)null) {
                     this.source1.close();
                     this.whichSource = 2;
                     this.source2.openCore();
                     var1 = this.source2.getNextRowCore();
                     if (var1 != null) {
                        ++this.rowsSeenRight;
                     }
                  } else {
                     ++this.rowsSeenLeft;
                  }
                  break;
               case 2:
                  var1 = this.source2.getNextRowCore();
                  if (var1 != null) {
                     ++this.rowsSeenRight;
                  }
            }
         }

         this.setCurrentRow(var1);
         if (var1 != null) {
            ++this.rowsReturned;
         }

         this.nextTime += this.getElapsedMillis(this.beginTime);
         return var1;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         switch (this.whichSource) {
            case 1:
               this.source1.close();
               break;
            case 2:
               this.source2.close();
               this.source1FinalRowCount = -1;
               this.whichSource = 1;
         }

         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public void finish() throws StandardException {
      this.source1.finish();
      this.source2.finish();
      this.finishAndRTS();
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source1.getTimeSpent(1) - this.source2.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      switch (this.whichSource) {
         case 1 -> {
            return ((CursorResultSet)this.source1).getRowLocation();
         }
         case 2 -> {
            return ((CursorResultSet)this.source2).getRowLocation();
         }
         default -> {
            return null;
         }
      }
   }

   public ExecRow getCurrentRow() throws StandardException {
      ExecRow var1 = null;
      switch (this.whichSource) {
         case 1 -> var1 = ((CursorResultSet)this.source1).getCurrentRow();
         case 2 -> var1 = ((CursorResultSet)this.source2).getCurrentRow();
      }

      this.setCurrentRow(var1);
      return var1;
   }
}
