package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.shared.common.error.StandardException;

public class ForeignKeyConstraintDescriptor extends KeyConstraintDescriptor {
   ReferencedKeyConstraintDescriptor referencedConstraintDescriptor;
   UUID referencedConstraintId;
   int raDeleteRule;
   int raUpdateRule;

   protected ForeignKeyConstraintDescriptor(DataDictionary var1, TableDescriptor var2, String var3, boolean var4, boolean var5, int[] var6, UUID var7, UUID var8, SchemaDescriptor var9, ReferencedKeyConstraintDescriptor var10, boolean var11, int var12, int var13) {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11);
      this.referencedConstraintDescriptor = var10;
      this.raDeleteRule = var12;
      this.raUpdateRule = var13;
   }

   ForeignKeyConstraintDescriptor(DataDictionary var1, TableDescriptor var2, String var3, boolean var4, boolean var5, int[] var6, UUID var7, UUID var8, SchemaDescriptor var9, UUID var10, boolean var11, int var12, int var13) {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9, var11);
      this.referencedConstraintId = var10;
      this.raDeleteRule = var12;
      this.raUpdateRule = var13;
   }

   public ReferencedKeyConstraintDescriptor getReferencedConstraint() throws StandardException {
      if (this.referencedConstraintDescriptor != null) {
         return this.referencedConstraintDescriptor;
      } else {
         if (this.referencedConstraintId == null) {
            this.getReferencedConstraintId();
         }

         TableDescriptor var1 = this.getDataDictionary().getConstraintTableDescriptor(this.referencedConstraintId);
         ConstraintDescriptorList var2 = this.getDataDictionary().getConstraintDescriptors(var1);
         this.referencedConstraintDescriptor = (ReferencedKeyConstraintDescriptor)var2.getConstraintDescriptorById(this.referencedConstraintId);
         return this.referencedConstraintDescriptor;
      }
   }

   public UUID getReferencedConstraintId() throws StandardException {
      if (this.referencedConstraintDescriptor != null) {
         return this.referencedConstraintDescriptor.getUUID();
      } else {
         SubKeyConstraintDescriptor var1 = this.getDataDictionary().getSubKeyConstraint(this.constraintId, 6);
         this.referencedConstraintId = var1.getKeyConstraintId();
         return this.referencedConstraintId;
      }
   }

   public int getConstraintType() {
      return 6;
   }

   public boolean needsToFire(int var1, int[] var2) {
      if (!this.enforced()) {
         return false;
      } else if (var1 == 4) {
         return false;
      } else {
         return var1 == 1 ? true : doColumnsIntersect(var2, this.getReferencedColumns());
      }
   }

   public boolean isSelfReferencingFK() throws StandardException {
      ReferencedKeyConstraintDescriptor var1 = this.getReferencedConstraint();
      return var1.getTableId().equals(this.getTableId());
   }

   public int getRaDeleteRule() {
      return this.raDeleteRule;
   }

   public int getRaUpdateRule() {
      return this.raUpdateRule;
   }
}
