package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

public class TableName extends QueryTreeNode {
   String tableName;
   String schemaName;
   private boolean hasSchema;

   TableName(String var1, String var2, ContextManager var3) {
      super(var3);
      this.hasSchema = var1 != null;
      this.schemaName = var1;
      this.tableName = var2;
   }

   TableName(String var1, String var2, int var3, int var4, ContextManager var5) {
      this(var1, var2, var5);
      this.setBeginOffset(var3);
      this.setEndOffset(var4);
   }

   public String getTableName() {
      return this.tableName;
   }

   public boolean hasSchema() {
      return this.hasSchema;
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   void setSchemaName(String var1) {
      this.schemaName = var1;
   }

   String getFullTableName() {
      return this.schemaName != null ? this.schemaName + "." + this.tableName : this.tableName;
   }

   public String getFullSQLName() {
      return IdUtil.mkQualifiedName(this.schemaName, this.tableName);
   }

   public String toString() {
      return this.hasSchema ? this.getFullTableName() : this.tableName;
   }

   boolean equals(TableName var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = this.getFullTableName();
         if (var2 == null) {
            return true;
         } else {
            return this.schemaName != null && var1.getSchemaName() != null ? var2.equals(var1.getFullTableName()) : this.tableName.equals(var1.getTableName());
         }
      }
   }

   boolean equals(String var1, String var2) {
      String var3 = this.getFullTableName();
      if (var3 == null) {
         return true;
      } else {
         return this.schemaName != null && var1 != null ? var3.equals(var1 + "." + var2) : this.tableName.equals(var2);
      }
   }

   public TableName cloneMe() {
      return new TableName(this.schemaName, this.tableName, this.getContextManager());
   }

   void bind() throws StandardException {
      this.schemaName = this.getSchemaDescriptor(this.schemaName).getSchemaName();
   }

   public int hashCode() {
      return this.getFullTableName().hashCode();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof TableName var2)) {
         return false;
      } else {
         return this.getFullTableName().equals(var2.getFullTableName());
      }
   }
}
