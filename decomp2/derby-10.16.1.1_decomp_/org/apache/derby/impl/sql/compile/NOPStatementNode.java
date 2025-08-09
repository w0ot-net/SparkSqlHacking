package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class NOPStatementNode extends StatementNode {
   NOPStatementNode(ContextManager var1) {
      super(var1);
   }

   String statementToString() {
      return "NO-OP";
   }

   public void bindStatement() throws StandardException {
      throw StandardException.newException("42Z54.U", new Object[0]);
   }

   int activationKind() {
      return 0;
   }
}
