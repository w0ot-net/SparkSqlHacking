package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;

abstract class TransactionStatementNode extends StatementNode {
   TransactionStatementNode(ContextManager var1) {
      super(var1);
   }

   int activationKind() {
      return 0;
   }

   public boolean isAtomic() {
      return false;
   }

   public boolean needsSavepoint() {
      return false;
   }
}
