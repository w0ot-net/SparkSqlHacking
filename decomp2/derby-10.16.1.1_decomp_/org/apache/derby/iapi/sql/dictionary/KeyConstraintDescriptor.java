package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.shared.common.error.StandardException;

public abstract class KeyConstraintDescriptor extends ConstraintDescriptor {
   UUID indexId;
   private ConglomerateDescriptor indexConglom;

   KeyConstraintDescriptor(DataDictionary var1, TableDescriptor var2, String var3, boolean var4, boolean var5, int[] var6, UUID var7, UUID var8, SchemaDescriptor var9, boolean var10) {
      super(var1, var2, var3, var4, var5, var6, var7, var9, var10);
      this.indexId = var8;
   }

   public UUID getIndexId() {
      return this.indexId;
   }

   public ConglomerateDescriptor getIndexConglomerateDescriptor(DataDictionary var1) throws StandardException {
      if (this.indexConglom == null) {
         this.indexConglom = this.getTableDescriptor().getConglomerateDescriptor(this.indexId);
      }

      return this.indexConglom;
   }

   public String getIndexUUIDString() {
      return this.indexId.toString();
   }

   public boolean hasBackingIndex() {
      return true;
   }

   public UUID getConglomerateId() {
      return this.indexId;
   }

   public String toString() {
      return "";
   }
}
