package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class GrantRoleNode extends DDLStatementNode {
   private List roles;
   private List grantees;

   GrantRoleNode(List var1, List var2, ContextManager var3) throws StandardException {
      super((TableName)null, var3);
      this.roles = var1;
      this.grantees = var2;
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getGrantRoleConstantAction(this.roles, this.grantees);
   }

   public String toString() {
      return "";
   }

   public String statementToString() {
      return "GRANT role";
   }
}
