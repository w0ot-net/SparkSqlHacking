package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.types.DataValueDescriptor;

public class UTFQualifier implements Qualifier {
   private UTF value;
   private int columnId;

   public UTFQualifier(int var1, String var2) {
      this.columnId = var1;
      this.value = new UTF(var2);
   }

   public int getColumnId() {
      return this.columnId;
   }

   public DataValueDescriptor getOrderable() {
      return this.value;
   }

   public int getOperator() {
      return 2;
   }

   public boolean negateCompareResult() {
      return false;
   }

   public boolean getOrderedNulls() {
      return false;
   }

   public boolean getUnknownRV() {
      return false;
   }

   public void clearOrderableCache() {
   }

   public void reinitialize() {
   }
}
