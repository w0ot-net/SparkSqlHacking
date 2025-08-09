package org.apache.derby.impl.sql.execute;

import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class GrantRevokeConstantAction implements ConstantAction {
   private boolean grant;
   private PrivilegeInfo privileges;
   private List grantees;

   GrantRevokeConstantAction(boolean var1, PrivilegeInfo var2, List var3) {
      this.grant = var1;
      this.privileges = var2;
      this.grantees = var3;
   }

   public String toString() {
      return this.grant ? "GRANT" : "REVOKE";
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      this.privileges.executeGrantRevoke(var1, this.grant, this.grantees);
   }
}
