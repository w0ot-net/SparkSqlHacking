package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropRoleNode extends DDLStatementNode {
   private String roleName;

   DropRoleNode(String var1, ContextManager var2) throws StandardException {
      super((TableName)null, var2);
      this.roleName = var1;
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      if (this.isPrivilegeCollectionRequired()) {
         var1.addRequiredRolePriv(this.roleName, 20);
      }

   }

   public String toString() {
      return "";
   }

   public String statementToString() {
      return "DROP ROLE";
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropRoleConstantAction(this.roleName);
   }
}
