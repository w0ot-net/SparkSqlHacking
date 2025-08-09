package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.execute.ScanQualifier;
import org.apache.derby.iapi.types.DataValueDescriptor;

public class GenericScanQualifier implements ScanQualifier {
   private int columnId = -1;
   private DataValueDescriptor orderable = null;
   private int operator = -1;
   private boolean negateCR = false;
   private boolean orderedNulls = false;
   private boolean unknownRV = false;
   private boolean properInit = false;

   public int getColumnId() {
      return this.columnId;
   }

   public DataValueDescriptor getOrderable() {
      return this.orderable;
   }

   public int getOperator() {
      return this.operator;
   }

   public boolean negateCompareResult() {
      return this.negateCR;
   }

   public boolean getOrderedNulls() {
      return this.orderedNulls;
   }

   public boolean getUnknownRV() {
      return this.unknownRV;
   }

   public void clearOrderableCache() {
   }

   public void reinitialize() {
   }

   public void setQualifier(int var1, DataValueDescriptor var2, int var3, boolean var4, boolean var5, boolean var6) {
      this.columnId = var1;
      this.orderable = var2;
      this.operator = var3;
      this.negateCR = var4;
      this.orderedNulls = var5;
      this.unknownRV = var6;
      this.properInit = true;
   }
}
