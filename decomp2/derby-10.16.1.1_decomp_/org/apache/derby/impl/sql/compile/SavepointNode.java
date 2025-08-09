package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class SavepointNode extends DDLStatementNode {
   private String savepointName;
   private int savepointStatementType;

   SavepointNode(String var1, int var2, ContextManager var3) throws StandardException {
      super(var3);
      this.savepointName = var1;
      this.savepointStatementType = var2;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      if (this.savepointStatementType == 1) {
         return "SAVEPOINT";
      } else {
         return this.savepointStatementType == 2 ? "ROLLBACK WORK TO SAVEPOINT" : "RELEASE TO SAVEPOINT";
      }
   }

   public boolean needsSavepoint() {
      return false;
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getSavepointConstantAction(this.savepointName, this.savepointStatementType);
   }
}
