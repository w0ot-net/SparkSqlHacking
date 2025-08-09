package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class SetTransactionIsolationNode extends TransactionStatementNode {
   private int isolationLevel;

   SetTransactionIsolationNode(int var1, ContextManager var2) {
      super(var2);
      this.isolationLevel = var1;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "SET TRANSACTION ISOLATION";
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getSetTransactionResultSet", "org.apache.derby.iapi.sql.ResultSet", 1);
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getSetTransactionIsolationConstantAction(this.isolationLevel);
   }
}
