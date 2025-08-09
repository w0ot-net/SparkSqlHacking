package org.apache.derby.impl.sql.compile;

import java.util.HashMap;
import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class RevokeNode extends DDLStatementNode {
   private PrivilegeNode privileges;
   private List grantees;

   RevokeNode(PrivilegeNode var1, List var2, ContextManager var3) {
      super(var3);
      this.privileges = var1;
      this.grantees = var2;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "REVOKE";
   }

   public void bindStatement() throws StandardException {
      this.privileges = (PrivilegeNode)this.privileges.bind(new HashMap(), this.grantees, false);
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getRevokeConstantAction(this.privileges.makePrivilegeInfo(), this.grantees);
   }
}
