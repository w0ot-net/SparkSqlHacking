package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;

public abstract class OrderedColumn extends QueryTreeNode {
   protected static final int UNMATCHEDPOSITION = -1;
   protected int columnPosition = -1;

   public OrderedColumn(ContextManager var1) {
      super(var1);
   }

   boolean isAscending() {
      return true;
   }

   boolean isNullsOrderedLow() {
      return false;
   }

   public String toString() {
      return "";
   }

   int getColumnPosition() {
      return this.columnPosition;
   }

   void setColumnPosition(int var1) {
      this.columnPosition = var1;
   }
}
