package org.apache.derby.impl.store.access.btree;

import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;

public class BranchRow {
   public static final long DUMMY_PAGE_NUMBER = -1L;
   private DataValueDescriptor[] branchrow = null;

   private BranchRow() {
   }

   private BranchRow(Transaction var1, BTree var2) throws StandardException {
      SQLLongint var3 = new SQLLongint(-1L);
      this.branchrow = var2.createBranchTemplate(var1, var3);
   }

   private SQLLongint getChildPage() {
      return (SQLLongint)this.branchrow[this.branchrow.length - 1];
   }

   public static BranchRow createEmptyTemplate(Transaction var0, BTree var1) throws StandardException {
      return new BranchRow(var0, var1);
   }

   public BranchRow createBranchRowFromOldBranchRow(long var1) {
      BranchRow var3 = new BranchRow();
      var3.branchrow = new DataValueDescriptor[this.branchrow.length];
      System.arraycopy(this.branchrow, 0, var3.branchrow, 0, var3.branchrow.length - 1);
      var3.branchrow[var3.branchrow.length - 1] = new SQLLongint(var1);
      return var3;
   }

   public static BranchRow createBranchRowFromOldLeafRow(DataValueDescriptor[] var0, long var1) {
      BranchRow var3 = new BranchRow();
      var3.branchrow = new DataValueDescriptor[var0.length + 1];
      System.arraycopy(var0, 0, var3.branchrow, 0, var0.length);
      var3.branchrow[var3.branchrow.length - 1] = new SQLLongint(var1);
      return var3;
   }

   protected DataValueDescriptor[] getRow() {
      return this.branchrow;
   }

   protected void setPageNumber(long var1) {
      this.getChildPage().setValue(var1);
   }

   public String toString() {
      return null;
   }
}
