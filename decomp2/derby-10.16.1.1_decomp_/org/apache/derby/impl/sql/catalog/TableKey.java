package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;

final class TableKey {
   private final String tableName;
   private final UUID schemaId;

   TableKey(UUID var1, String var2) {
      this.tableName = var2;
      this.schemaId = var1;
   }

   String getTableName() {
      return this.tableName;
   }

   UUID getSchemaId() {
      return this.schemaId;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof TableKey var2) {
         if (this.tableName.equals(var2.tableName) && this.schemaId.equals(var2.schemaId)) {
            return true;
         }
      }

      return false;
   }

   public int hashCode() {
      return this.tableName.hashCode();
   }
}
