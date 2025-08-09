package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.sql.execute.ResultSetStatisticsFactory;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.sql.execute.RunTimeStatistics;
import org.apache.derby.iapi.sql.execute.TargetResultSet;
import org.apache.derby.iapi.sql.execute.xplain.XPLAINVisitor;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.LocatedRow;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

abstract class NoPutResultSetImpl extends BasicNoPutResultSetImpl {
   public final int resultSetNumber;
   private boolean needsRowLocation;
   private boolean needsRowLocationForDeferredCheckConstraints;
   protected ExecRow clonedExecRow;
   protected TargetResultSet targetResultSet;
   protected int[] checkNullCols;
   protected int cncLen;

   NoPutResultSetImpl(Activation var1, int var2, double var3, double var5) {
      super((ResultDescription)null, var1, var3, var5);
      this.resultSetNumber = var2;
   }

   public ResultDescription getResultDescription() {
      return this.activation.getResultDescription();
   }

   public String getCursorName() {
      String var1 = this.activation.getCursorName();
      if (var1 == null && this.isForUpdate()) {
         this.activation.setCursorName(this.activation.getLanguageConnectionContext().getUniqueCursorName());
         var1 = this.activation.getCursorName();
      }

      return var1;
   }

   public int resultSetNumber() {
      return this.resultSetNumber;
   }

   public void close() throws StandardException {
      if (this.isOpen) {
         if (this.isTopResultSet) {
            LanguageConnectionContext var1 = this.getLanguageConnectionContext();
            if (var1.getRunTimeStatisticsMode() && !var1.getStatementContext().getStatementWasInvalidated()) {
               this.endExecutionTime = this.getCurrentTimeMillis();
               ExecutionFactory var2 = var1.getLanguageConnectionFactory().getExecutionFactory();
               ResultSetStatisticsFactory var3 = var2.getResultSetStatisticsFactory();
               RunTimeStatistics var4 = var3.getRunTimeStatistics(this.activation, this, this.subqueryTrackingArray);
               var1.setRunTimeStatisticsObject(var4);
               XPLAINVisitor var5 = var2.getXPLAINFactory().getXPLAINVisitor();
               var5.doXPLAIN(var4, this.activation);
            }

            int var6 = this.subqueryTrackingArray == null ? 0 : this.subqueryTrackingArray.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               if (this.subqueryTrackingArray[var7] != null && !this.subqueryTrackingArray[var7].isClosed()) {
                  this.subqueryTrackingArray[var7].close();
               }
            }
         }

         this.isOpen = false;
      }
   }

   public void setTargetResultSet(TargetResultSet var1) {
      this.targetResultSet = var1;
   }

   public void setNeedsRowLocation(boolean var1) {
      this.needsRowLocation = var1;
   }

   public void setHasDeferrableChecks() {
      this.needsRowLocationForDeferredCheckConstraints = true;
   }

   public FormatableBitSet getValidColumns() {
      return null;
   }

   public DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      ExecRow var1 = this.getNextRowCore();
      if (var1 != null) {
         this.clonedExecRow = this.targetResultSet.preprocessSourceRow(var1);
         return var1.getRowArray();
      } else {
         return null;
      }
   }

   public boolean needsToClone() {
      return true;
   }

   public void closeRowSource() {
   }

   public boolean needsRowLocation() {
      return this.needsRowLocation;
   }

   public boolean needsRowLocationForDeferredCheckConstraints() {
      return this.needsRowLocationForDeferredCheckConstraints;
   }

   public void rowLocation(RowLocation var1) throws StandardException {
      this.targetResultSet.changedRow(this.clonedExecRow, var1);
   }

   public void offendingRowLocation(RowLocation var1, long var2) throws StandardException {
      this.targetResultSet.offendingRowLocation(var1, var2);
   }

   protected void clearOrderableCache(Qualifier[][] var1) throws StandardException {
      if (var1 != null) {
         for(int var3 = 0; var3 < var1.length; ++var3) {
            for(int var4 = 0; var4 < var1[var3].length; ++var4) {
               Qualifier var2 = var1[var3][var4];
               var2.clearOrderableCache();
               if (((GenericQualifier)var2).variantType != 0) {
                  var2.getOrderable();
               }
            }
         }
      }

   }

   public final void setCurrentRow(ExecRow var1) {
      this.activation.setCurrentRow(var1, this.resultSetNumber);
      this.currentRow = var1;
   }

   public void clearCurrentRow() {
      this.currentRow = null;
      this.activation.clearCurrentRow(this.resultSetNumber);
   }

   public boolean isForUpdate() {
      return false;
   }

   protected boolean skipScan(ExecIndexRow var1, ExecIndexRow var2) throws StandardException {
      int var3 = var1 == null ? 0 : var1.nColumns();
      int var4 = var2 == null ? 0 : var2.nColumns();
      boolean var5 = false;
      int var6 = var4;
      if (var3 > var4) {
         var5 = true;
         var6 = var3;
      }

      if (var6 == 0) {
         return false;
      } else {
         if (this.checkNullCols == null || this.checkNullCols.length < var6) {
            this.checkNullCols = new int[var6];
         }

         this.cncLen = 0;
         boolean var7 = false;

         for(int var8 = 0; var8 < var3; ++var8) {
            if (!var1.areNullsOrdered(var8)) {
               if (var5) {
                  this.checkNullCols[this.cncLen++] = var8 + 1;
               }

               if (var1.getColumn(var8 + 1).isNull()) {
                  var7 = true;
                  if (!var5) {
                     break;
                  }
               }
            }
         }

         if (var5 && var7) {
            return true;
         } else {
            for(int var9 = 0; var9 < var4; ++var9) {
               if (!var2.areNullsOrdered(var9)) {
                  if (!var5) {
                     this.checkNullCols[this.cncLen++] = var9 + 1;
                  }

                  if (!var7 && var2.getColumn(var9 + 1).isNull()) {
                     var7 = true;
                     if (var5) {
                        break;
                     }
                  }
               }
            }

            return var7;
         }
      }
   }

   protected boolean skipRow(ExecRow var1) throws StandardException {
      for(int var2 = 0; var2 < this.cncLen; ++var2) {
         if (var1.getColumn(this.checkNullCols[var2]).isNull()) {
            return true;
         }
      }

      return false;
   }

   public static String printQualifiers(Qualifier[][] var0) {
      String var1 = "";
      String var2 = "";
      if (var0 == null) {
         return var1 + MessageService.getTextMessage("42Z37.U", new Object[0]);
      } else {
         for(int var3 = 0; var3 < var0.length; ++var3) {
            for(int var4 = 0; var4 < var0[var3].length; ++var4) {
               Qualifier var5 = var0[var3][var4];
               var2 = var1 + var2 + MessageService.getTextMessage("42Z48.U", new Object[]{String.valueOf(var3), String.valueOf(var4)}) + ": " + var5.getColumnId() + "\n";
               int var6 = var5.getOperator();
               Object var7 = null;
               String var9;
               switch (var6) {
                  case 1 -> var9 = "<";
                  case 2 -> var9 = "=";
                  case 3 -> var9 = "<=";
                  default -> var9 = "unknown value (" + var6 + ")";
               }

               var2 = var2 + var1 + MessageService.getTextMessage("42Z43.U", new Object[0]) + ": " + var9 + "\n" + var1 + MessageService.getTextMessage("42Z44.U", new Object[0]) + ": " + var5.getOrderedNulls() + "\n" + var1 + MessageService.getTextMessage("42Z45.U", new Object[0]) + ": " + var5.getUnknownRV() + "\n" + var1 + MessageService.getTextMessage("42Z46.U", new Object[0]) + ": " + var5.negateCompareResult() + "\n";
            }
         }

         return var2;
      }
   }

   public void updateRow(ExecRow var1, RowChanger var2) throws StandardException {
   }

   public void markRowAsDeleted() throws StandardException {
   }

   public void positionScanAtRowLocation(RowLocation var1) throws StandardException {
   }

   protected DataValueDescriptor[] unpackHashValue(Object var1) {
      if (var1 == null) {
         return null;
      } else {
         return var1 instanceof DataValueDescriptor[] ? (DataValueDescriptor[])var1 : ((LocatedRow)var1).flatten();
      }
   }
}
