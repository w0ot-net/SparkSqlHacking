package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;

public class SubKeyConstraintDescriptor extends SubConstraintDescriptor {
   UUID indexId;
   UUID keyConstraintId;
   int raDeleteRule;
   int raUpdateRule;

   public SubKeyConstraintDescriptor(UUID var1, UUID var2) {
      super(var1);
      this.indexId = var2;
   }

   public SubKeyConstraintDescriptor(UUID var1, UUID var2, UUID var3) {
      this(var1, var2);
      this.keyConstraintId = var3;
   }

   public SubKeyConstraintDescriptor(UUID var1, UUID var2, UUID var3, int var4, int var5) {
      this(var1, var2);
      this.keyConstraintId = var3;
      this.raDeleteRule = var4;
      this.raUpdateRule = var5;
   }

   public UUID getIndexId() {
      return this.indexId;
   }

   public UUID getKeyConstraintId() {
      return this.keyConstraintId;
   }

   public boolean hasBackingIndex() {
      return true;
   }

   public int getRaDeleteRule() {
      return this.raDeleteRule;
   }

   public int getRaUpdateRule() {
      return this.raUpdateRule;
   }

   public String toString() {
      return "";
   }
}
