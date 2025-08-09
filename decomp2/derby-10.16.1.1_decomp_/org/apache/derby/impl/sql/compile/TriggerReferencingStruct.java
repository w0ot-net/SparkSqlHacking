package org.apache.derby.impl.sql.compile;

class TriggerReferencingStruct {
   String identifier;
   boolean isRow;
   boolean isNew;

   TriggerReferencingStruct(boolean var1, boolean var2, String var3) {
      this.isRow = var1;
      this.isNew = var2;
      this.identifier = var3;
   }

   public String toString() {
      String var10000 = this.isRow ? "ROW " : "TABLE ";
      return var10000 + (this.isNew ? "new: " : "old: ") + this.identifier;
   }
}
