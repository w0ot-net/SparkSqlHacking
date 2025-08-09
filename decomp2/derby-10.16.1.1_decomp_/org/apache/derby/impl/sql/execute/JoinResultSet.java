package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

abstract class JoinResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public int rowsSeenLeft;
   public int rowsSeenRight;
   public int rowsReturned;
   public long restrictionTime;
   protected boolean isRightOpen;
   protected ExecRow leftRow;
   protected ExecRow rightRow;
   protected ExecRow mergedRow;
   public NoPutResultSet leftResultSet;
   protected int leftNumCols;
   public NoPutResultSet rightResultSet;
   protected int rightNumCols;
   protected GeneratedMethod restriction;
   public boolean oneRowRightSide;
   public boolean notExistsRightSide;
   String userSuppliedOptimizerOverrides;

   JoinResultSet(NoPutResultSet var1, int var2, NoPutResultSet var3, int var4, Activation var5, GeneratedMethod var6, int var7, boolean var8, boolean var9, double var10, double var12, String var14) {
      super(var5, var7, var10, var12);
      this.leftResultSet = var1;
      this.leftNumCols = var2;
      this.rightResultSet = var3;
      this.rightNumCols = var4;
      this.restriction = var6;
      this.oneRowRightSide = var8;
      this.notExistsRightSide = var9;
      this.userSuppliedOptimizerOverrides = var14;
      this.recordConstructorTime();
   }

   void clearScanState() {
      this.leftRow = null;
      this.rightRow = null;
      this.mergedRow = null;
   }

   public void openCore() throws StandardException {
      this.clearScanState();
      this.beginTime = this.getCurrentTimeMillis();
      this.leftResultSet.openCore();

      try {
         this.leftRow = this.leftResultSet.getNextRowCore();
         if (this.leftRow != null) {
            this.openRight();
            ++this.rowsSeenLeft;
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

   public void reopenCore() throws StandardException {
      this.clearScanState();
      this.leftResultSet.reopenCore();
      this.leftRow = this.leftResultSet.getNextRowCore();
      if (this.leftRow != null) {
         this.openRight();
         ++this.rowsSeenLeft;
      } else if (this.isRightOpen) {
         this.closeRight();
      }

      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void close() throws StandardException {
      if (this.isOpen) {
         this.leftResultSet.close();
         if (this.isRightOpen) {
            this.closeRight();
         }

         super.close();
      }

      this.clearScanState();
   }

   public void finish() throws StandardException {
      this.leftResultSet.finish();
      this.rightResultSet.finish();
      super.finish();
   }

   public RowLocation getRowLocation() {
      return null;
   }

   public ExecRow getCurrentRow() {
      return null;
   }

   protected void openRight() throws StandardException {
      if (this.isRightOpen) {
         this.rightResultSet.reopenCore();
      } else {
         this.rightResultSet.openCore();
         this.isRightOpen = true;
      }

   }

   protected void closeRight() throws StandardException {
      this.rightResultSet.close();
      this.isRightOpen = false;
   }
}
