package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class IndexValueRow implements ExecIndexRow {
   private ExecRow valueRow;

   IndexValueRow(ExecRow var1) {
      this.valueRow = var1;
   }

   public String toString() {
      return this.valueRow.toString();
   }

   public DataValueDescriptor[] getRowArray() {
      return this.valueRow.getRowArray();
   }

   public void setRowArray(DataValueDescriptor[] var1) {
      this.valueRow.setRowArray(var1);
   }

   public DataValueDescriptor[] getRowArrayClone() {
      return this.valueRow.getRowArrayClone();
   }

   public int nColumns() {
      return this.valueRow.nColumns();
   }

   public DataValueDescriptor getColumn(int var1) throws StandardException {
      return this.valueRow.getColumn(var1);
   }

   public void setColumn(int var1, DataValueDescriptor var2) {
      this.valueRow.setColumn(var1, var2);
   }

   public ExecRow getClone() {
      return new IndexValueRow(this.valueRow.getClone());
   }

   public ExecRow getClone(FormatableBitSet var1) {
      return new IndexValueRow(this.valueRow.getClone(var1));
   }

   public ExecRow getNewNullRow() {
      return new IndexValueRow(this.valueRow.getNewNullRow());
   }

   public void resetRowArray() {
      this.valueRow.resetRowArray();
   }

   public DataValueDescriptor cloneColumn(int var1) {
      return this.valueRow.cloneColumn(var1);
   }

   public void orderedNulls(int var1) {
   }

   public boolean areNullsOrdered(int var1) {
      return false;
   }

   public void execRowToExecIndexRow(ExecRow var1) {
      this.valueRow = var1;
   }

   public void getNewObjectArray() {
      this.valueRow.getNewObjectArray();
   }
}
