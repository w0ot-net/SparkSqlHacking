package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class RowResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public int rowsReturned;
   private boolean canCacheRow;
   private boolean next;
   private GeneratedMethod row;
   private ExecRow cachedRow;

   RowResultSet(Activation var1, GeneratedMethod var2, boolean var3, int var4, double var5, double var7) {
      super(var1, var4, var5, var7);
      this.row = var2;
      this.canCacheRow = var3;
      this.recordConstructorTime();
   }

   RowResultSet(Activation var1, ExecRow var2, boolean var3, int var4, double var5, double var7) {
      super(var1, var4, var5, var7);
      this.beginTime = this.getCurrentTimeMillis();
      this.cachedRow = var2;
      this.canCacheRow = var3;
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.next = false;
      this.beginTime = this.getCurrentTimeMillis();
      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         this.currentRow = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            if (!this.next) {
               this.next = true;
               if (this.cachedRow != null) {
                  this.currentRow = this.cachedRow;
               } else if (this.row != null) {
                  this.currentRow = (ExecRow)this.row.invoke(this.activation);
                  if (this.canCacheRow) {
                     this.cachedRow = this.currentRow;
                  }
               }

               ++this.rowsReturned;
            }

            this.setCurrentRow(this.currentRow);
            this.nextTime += this.getElapsedMillis(this.beginTime);
         }

         return this.currentRow;
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.next = false;
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var2;
   }

   public RowLocation getRowLocation() {
      return null;
   }

   public ExecRow getCurrentRow() {
      return null;
   }
}
