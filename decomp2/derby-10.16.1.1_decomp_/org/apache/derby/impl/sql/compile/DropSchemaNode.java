package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropSchemaNode extends DDLStatementNode {
   private int dropBehavior;
   private String schemaName;

   DropSchemaNode(String var1, int var2, ContextManager var3) {
      super((TableName)null, var3);
      this.schemaName = var1;
      this.dropBehavior = var2;
   }

   public void bindStatement() throws StandardException {
      if (this.getDataDictionary().isSystemSchemaName(this.schemaName)) {
         throw StandardException.newException("42Y67", new Object[]{this.schemaName});
      } else {
         if (this.isPrivilegeCollectionRequired()) {
            LanguageConnectionContext var1 = this.getLanguageConnectionContext();
            StatementContext var2 = var1.getStatementContext();
            String var3 = var2.getSQLSessionContext().getCurrentUser();
            this.getCompilerContext().addRequiredSchemaPriv(this.schemaName, var3, 18);
         }

      }
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "DROP SCHEMA";
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropSchemaConstantAction(this.schemaName);
   }
}
