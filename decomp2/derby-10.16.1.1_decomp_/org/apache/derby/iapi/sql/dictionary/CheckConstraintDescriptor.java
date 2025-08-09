package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.catalog.UUID;

public class CheckConstraintDescriptor extends ConstraintDescriptor {
   private ReferencedColumns referencedColumns;
   private String constraintText;

   CheckConstraintDescriptor(DataDictionary var1, TableDescriptor var2, String var3, boolean var4, boolean var5, UUID var6, String var7, ReferencedColumns var8, SchemaDescriptor var9, boolean var10) {
      super(var1, var2, var3, var4, var5, (int[])null, var6, var9, var10);
      this.constraintText = var7;
      this.referencedColumns = var8;
   }

   public boolean hasBackingIndex() {
      return false;
   }

   public int getConstraintType() {
      return 4;
   }

   public String getConstraintText() {
      return this.constraintText;
   }

   public UUID getConglomerateId() {
      return null;
   }

   public ReferencedColumns getReferencedColumnsDescriptor() {
      return this.referencedColumns;
   }

   public void setReferencedColumnsDescriptor(ReferencedColumns var1) {
      this.referencedColumns = var1;
   }

   public int[] getReferencedColumns() {
      return this.referencedColumns.getReferencedColumnPositions();
   }

   public boolean needsToFire(int var1, int[] var2) {
      if (!this.enforced()) {
         return false;
      } else if (var1 == 1) {
         return true;
      } else {
         return var1 == 4 ? false : doColumnsIntersect(var2, this.getReferencedColumns());
      }
   }

   public String toString() {
      return "";
   }
}
