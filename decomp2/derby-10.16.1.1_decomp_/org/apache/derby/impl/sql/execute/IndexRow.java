package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;

class IndexRow extends ValueRow implements ExecIndexRow {
   private boolean[] orderedNulls;

   IndexRow(int var1) {
      super(var1);
      this.orderedNulls = new boolean[var1];
   }

   public void orderedNulls(int var1) {
      this.orderedNulls[var1] = true;
   }

   public boolean areNullsOrdered(int var1) {
      return this.orderedNulls[var1];
   }

   public void execRowToExecIndexRow(ExecRow var1) {
   }

   ExecRow cloneMe() {
      return new IndexRow(this.nColumns());
   }
}
