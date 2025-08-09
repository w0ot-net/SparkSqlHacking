package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.shared.common.error.StandardException;

public class ReferencedKeyConstraintDescriptor extends KeyConstraintDescriptor {
   private final int constraintType;
   int referenceCount;
   private ConstraintDescriptorList fkEnabledConstraintList;
   private ConstraintDescriptorList fkConstraintList;
   private boolean checkedSelfReferencing;
   private boolean hasSelfReferencing;

   protected ReferencedKeyConstraintDescriptor(int var1, DataDictionary var2, TableDescriptor var3, String var4, boolean var5, boolean var6, int[] var7, UUID var8, UUID var9, SchemaDescriptor var10, boolean var11, int var12) {
      super(var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      this.referenceCount = var12;
      this.constraintType = var1;
   }

   public final int getConstraintType() {
      return this.constraintType;
   }

   public boolean hasSelfReferencingFK(ConstraintDescriptorList var1, int var2) throws StandardException {
      if (this.checkedSelfReferencing) {
         return this.hasSelfReferencing;
      } else {
         if (var1 == null) {
            var1 = this.getForeignKeyConstraints(var2);
         }

         for(ConstraintDescriptor var5 : var1) {
            if (var5 instanceof ForeignKeyConstraintDescriptor) {
               ForeignKeyConstraintDescriptor var3 = (ForeignKeyConstraintDescriptor)var5;
               if (var3.getReferencedConstraintId().equals(this.getUUID())) {
                  this.hasSelfReferencing = true;
                  break;
               }
            }
         }

         return this.hasSelfReferencing;
      }
   }

   public ConstraintDescriptorList getNonSelfReferencingFK(int var1) throws StandardException {
      ConstraintDescriptorList var2 = new ConstraintDescriptorList();

      for(ConstraintDescriptor var5 : this.getForeignKeyConstraints(var1)) {
         if (var5 instanceof ForeignKeyConstraintDescriptor var3) {
            if (!var3.getTableId().equals(this.getTableId())) {
               var2.add(var3);
            }
         }
      }

      return var2;
   }

   public ConstraintDescriptorList getForeignKeyConstraints(int var1) throws StandardException {
      if (var1 == 1) {
         if (!this.isReferenced()) {
            return new ConstraintDescriptorList();
         } else if (this.fkEnabledConstraintList != null) {
            return this.fkEnabledConstraintList;
         } else {
            if (this.fkConstraintList == null) {
               this.fkConstraintList = this.getDataDictionary().getForeignKeys(this.constraintId);
            }

            this.fkEnabledConstraintList = this.fkConstraintList.getConstraintDescriptorList(true);
            return this.fkEnabledConstraintList;
         }
      } else if (var1 == 2) {
         if (this.fkConstraintList == null) {
            this.fkConstraintList = this.getDataDictionary().getForeignKeys(this.constraintId);
         }

         return this.fkConstraintList.getConstraintDescriptorList(false);
      } else {
         if (this.fkConstraintList == null) {
            this.fkConstraintList = this.getDataDictionary().getForeignKeys(this.constraintId);
         }

         return this.fkConstraintList;
      }
   }

   public boolean isReferenced() {
      return this.referenceCount != 0;
   }

   public int getReferenceCount() {
      return this.referenceCount;
   }

   public int incrementReferenceCount() {
      return this.referenceCount++;
   }

   public int decrementReferenceCount() {
      return this.referenceCount--;
   }

   public boolean needsToFire(int var1, int[] var2) {
      if (!this.enforced()) {
         return false;
      } else if (this.isReferenced() && var1 != 1) {
         return var1 != 4 && var1 != 2 ? doColumnsIntersect(var2, this.getReferencedColumns()) : true;
      } else {
         return false;
      }
   }

   private void checkType(int var1) throws StandardException {
   }
}
