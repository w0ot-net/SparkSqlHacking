package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class CreateSchemaNode extends DDLStatementNode {
   private String name;
   private String aid;

   CreateSchemaNode(String var1, String var2, ContextManager var3) throws StandardException {
      super((TableName)null, var3);
      this.name = var1;
      this.aid = var2;
   }

   public String toString() {
      return "";
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      if (this.isPrivilegeCollectionRequired()) {
         var1.addRequiredSchemaPriv(this.name, this.aid, 16);
      }

   }

   String statementToString() {
      return "CREATE SCHEMA";
   }

   public ConstantAction makeConstantAction() {
      return this.getGenericConstantActionFactory().getCreateSchemaConstantAction(this.name, this.aid);
   }
}
