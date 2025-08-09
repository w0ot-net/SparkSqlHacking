package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;

public abstract class SubConstraintDescriptor extends UniqueTupleDescriptor {
   TableDescriptor td;
   UUID constraintId;

   SubConstraintDescriptor(UUID var1) {
      this.constraintId = var1;
   }

   public void setConstraintId(UUID var1) {
      this.constraintId = var1;
   }

   public UUID getUUID() {
      return this.constraintId;
   }

   public abstract boolean hasBackingIndex();

   public void setTableDescriptor(TableDescriptor var1) {
      this.td = var1;
   }

   public TableDescriptor getTableDescriptor() {
      return this.td;
   }

   public String toString() {
      return "";
   }
}
