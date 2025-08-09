package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;

class ValueRow implements ExecRow {
   private DataValueDescriptor[] column;
   private int ncols;

   public ValueRow(int var1) {
      this.column = new DataValueDescriptor[var1];
      this.ncols = var1;
   }

   public int nColumns() {
      return this.ncols;
   }

   public void getNewObjectArray() {
      this.column = new DataValueDescriptor[this.ncols];
   }

   public DataValueDescriptor getColumn(int var1) {
      return var1 <= this.column.length ? this.column[var1 - 1] : (DataValueDescriptor)null;
   }

   public void setColumn(int var1, DataValueDescriptor var2) {
      if (var1 > this.column.length) {
         this.realloc(var1);
      }

      this.column[var1 - 1] = var2;
   }

   public ExecRow getClone() {
      return this.getClone((FormatableBitSet)null);
   }

   public ExecRow getClone(FormatableBitSet var1) {
      int var2 = this.column.length;
      ExecRow var3 = this.cloneMe();

      for(int var4 = 0; var4 < var2; ++var4) {
         if (var1 != null && !var1.get(var4 + 1)) {
            var3.setColumn(var4 + 1, this.column[var4]);
         } else if (this.column[var4] != null) {
            var3.setColumn(var4 + 1, this.column[var4].cloneValue(false));
         }
      }

      return var3;
   }

   public ExecRow getNewNullRow() {
      int var1 = this.column.length;
      ExecRow var2 = this.cloneMe();

      for(int var3 = 0; var3 < var1; ++var3) {
         if (this.column[var3] != null) {
            var2.setColumn(var3 + 1, this.column[var3].getNewNull());
         }
      }

      return var2;
   }

   ExecRow cloneMe() {
      return new ValueRow(this.ncols);
   }

   public void resetRowArray() {
      for(int var1 = 0; var1 < this.column.length; ++var1) {
         if (this.column[var1] != null) {
            this.column[var1] = this.column[var1].recycle();
         }
      }

   }

   public final DataValueDescriptor cloneColumn(int var1) {
      return this.column[var1 - 1].cloneValue(false);
   }

   public String toString() {
      String var1 = "{ ";

      for(int var2 = 0; var2 < this.column.length; ++var2) {
         if (this.column[var2] == null) {
            var1 = var1 + "null";
         } else {
            var1 = var1 + this.column[var2].toString();
         }

         if (var2 < this.column.length - 1) {
            var1 = var1 + ", ";
         }
      }

      var1 = var1 + " }";
      return var1;
   }

   public DataValueDescriptor[] getRowArray() {
      return this.column;
   }

   public DataValueDescriptor[] getRowArrayClone() {
      int var1 = this.column.length;
      DataValueDescriptor[] var2 = new DataValueDescriptor[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         if (this.column[var3] != null) {
            var2[var3] = this.column[var3].cloneValue(false);
         }
      }

      return var2;
   }

   public void setRowArray(DataValueDescriptor[] var1) {
      this.column = var1;
   }

   protected void realloc(int var1) {
      DataValueDescriptor[] var2 = new DataValueDescriptor[var1];
      System.arraycopy(this.column, 0, var2, 0, this.column.length);
      this.column = var2;
   }
}
