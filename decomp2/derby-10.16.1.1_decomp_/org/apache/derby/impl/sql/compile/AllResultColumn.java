package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class AllResultColumn extends ResultColumn {
   private TableName tableName;

   AllResultColumn(TableName var1, ContextManager var2) {
      super(var2);
      this.tableName = var1;
   }

   String getFullTableName() {
      return this.tableName == null ? null : this.tableName.getFullTableName();
   }

   ResultColumn cloneMe() throws StandardException {
      return new AllResultColumn(this.tableName, this.getContextManager());
   }

   public TableName getTableNameObject() {
      return this.tableName;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }
}
