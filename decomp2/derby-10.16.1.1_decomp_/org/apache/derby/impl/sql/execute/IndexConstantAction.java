package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;

public abstract class IndexConstantAction extends DDLSingleTableConstantAction {
   String indexName;
   String tableName;
   String schemaName;
   protected transient UUID constraintID;

   protected IndexConstantAction(UUID var1, String var2, String var3, String var4) {
      super(var1);
      this.indexName = var2;
      this.tableName = var3;
      this.schemaName = var4;
   }

   public String getIndexName() {
      return this.indexName;
   }

   public void setIndexName(String var1) {
      this.indexName = var1;
   }

   public void setConstraintID(UUID var1) {
      this.constraintID = var1;
   }
}
