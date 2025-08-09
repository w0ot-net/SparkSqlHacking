package org.apache.derby.iapi.sql.execute;

public interface ExecIndexRow extends ExecRow {
   void orderedNulls(int var1);

   boolean areNullsOrdered(int var1);

   void execRowToExecIndexRow(ExecRow var1);
}
