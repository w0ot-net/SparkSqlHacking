package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.catalog.UUID;

public class SubCheckConstraintDescriptor extends SubConstraintDescriptor {
   private ReferencedColumns referencedColumns;
   private String constraintText;

   public SubCheckConstraintDescriptor(UUID var1, String var2, ReferencedColumns var3) {
      super(var1);
      this.constraintText = var2;
      this.referencedColumns = var3;
   }

   public String getConstraintText() {
      return this.constraintText;
   }

   public ReferencedColumns getReferencedColumnsDescriptor() {
      return this.referencedColumns;
   }

   public boolean hasBackingIndex() {
      return false;
   }

   public String toString() {
      return "";
   }
}
