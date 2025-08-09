package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class CreateRoleNode extends DDLStatementNode {
   private String name;

   CreateRoleNode(String var1, ContextManager var2) throws StandardException {
      super((TableName)null, var2);
      this.name = var1;
   }

   public String toString() {
      return "";
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      if (this.isPrivilegeCollectionRequired()) {
         var1.addRequiredRolePriv(this.name, 19);
      }

   }

   public String statementToString() {
      return "CREATE ROLE";
   }

   public ConstantAction makeConstantAction() {
      return this.getGenericConstantActionFactory().getCreateRoleConstantAction(this.name);
   }
}
