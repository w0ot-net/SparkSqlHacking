package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class CurrentOfResultSet extends NoPutResultSetImpl implements CursorResultSet {
   private boolean next;
   private RowLocation rowLocation;
   private CursorResultSet cursor;
   private CursorResultSet target;
   private ExecRow sparseRow;
   private final String cursorName;

   CurrentOfResultSet(String var1, Activation var2, int var3) {
      super(var2, var3, (double)0.0F, (double)0.0F);
      this.cursorName = var1;
   }

   public void openCore() throws StandardException {
      this.getCursor();
      this.next = false;
      this.isOpen = true;
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         if (this.isOpen) {
            if (!this.next) {
               this.next = true;
               ExecRow var1 = this.cursor.getCurrentRow();
               if (var1 == null) {
                  throw StandardException.newException("24000", new Object[0]);
               }

               this.rowLocation = this.cursor.getRowLocation();
               this.currentRow = this.target.getCurrentRow();
               if (this.rowLocation == null || var1 != null && this.currentRow == null) {
                  this.activation.addWarning(StandardException.newWarning("01001", new Object[0]));
                  return null;
               }

               if (this.target instanceof TableScanResultSet) {
                  TableScanResultSet var2 = (TableScanResultSet)this.target;
                  if (var2.indexCols != null && this.currentRow != null) {
                     this.currentRow = this.getSparseRow(this.currentRow, var2.indexCols);
                  }
               }
            } else {
               this.currentRow = null;
               this.rowLocation = null;
            }
         } else {
            this.currentRow = null;
            this.rowLocation = null;
         }

         this.setCurrentRow(this.currentRow);
         return this.currentRow;
      }
   }

   private ExecRow getSparseRow(ExecRow var1, int[] var2) throws StandardException {
      if (this.sparseRow == null) {
         int var4 = 1;

         for(int var5 = 0; var5 < var2.length; ++var5) {
            int var3 = var2[var5] > 0 ? var2[var5] : -var2[var5];
            if (var3 > var4) {
               var4 = var3;
            }
         }

         this.sparseRow = new ValueRow(var4);
      }

      for(int var7 = 1; var7 <= var2.length; ++var7) {
         int var6 = var2[var7 - 1] > 0 ? var2[var7 - 1] : -var2[var7 - 1];
         this.sparseRow.setColumn(var6, var1.getColumn(var7));
      }

      return this.sparseRow;
   }

   public void close() throws StandardException {
      if (this.isOpen) {
         this.clearCurrentRow();
         this.next = false;
         super.close();
      }

   }

   public void finish() throws StandardException {
      this.finishAndRTS();
   }

   public long getTimeSpent(int var1) {
      return 0L;
   }

   public RowLocation getRowLocation() {
      return this.rowLocation;
   }

   public ExecRow getCurrentRow() {
      return this.currentRow;
   }

   private void getCursor() throws StandardException {
      if (this.cursor != null && this.cursor.isClosed()) {
         this.cursor = null;
         this.target = null;
      }

      if (this.cursor == null) {
         LanguageConnectionContext var1 = this.getLanguageConnectionContext();
         org.apache.derby.iapi.sql.execute.CursorActivation var2 = var1.lookupCursorActivation(this.cursorName);
         if (var2 != null) {
            this.cursor = var2.getCursorResultSet();
            this.target = var2.getTargetResultSet();
            this.activation.setForUpdateIndexScan(var2.getForUpdateIndexScan());
            if (var2.getHeapConglomerateController() != null) {
               var2.getHeapConglomerateController().close();
            }

            var2.setHeapConglomerateController(this.activation.getHeapConglomerateController());
         }
      }

      if (this.cursor == null || this.cursor.isClosed()) {
         throw StandardException.newException("42X30", new Object[]{this.cursorName});
      }
   }

   public void updateRow(ExecRow var1, RowChanger var2) throws StandardException {
      ((NoPutResultSet)this.cursor).updateRow(var1, var2);
   }

   public void markRowAsDeleted() throws StandardException {
      ((NoPutResultSet)this.cursor).markRowAsDeleted();
   }
}
