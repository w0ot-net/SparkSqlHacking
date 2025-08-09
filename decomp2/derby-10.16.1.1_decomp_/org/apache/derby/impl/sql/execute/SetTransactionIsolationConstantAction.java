package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class SetTransactionIsolationConstantAction implements ConstantAction {
   private final int isolationLevel;

   SetTransactionIsolationConstantAction(int var1) {
      this.isolationLevel = var1;
   }

   public String toString() {
      return "SET TRANSACTION ISOLATION LEVEL = " + this.isolationLevel;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      var1.getLanguageConnectionContext().setIsolationLevel(this.isolationLevel);
   }
}
