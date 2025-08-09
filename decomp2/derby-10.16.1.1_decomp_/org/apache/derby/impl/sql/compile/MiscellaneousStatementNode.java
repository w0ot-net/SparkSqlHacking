package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

abstract class MiscellaneousStatementNode extends StatementNode {
   MiscellaneousStatementNode(ContextManager var1) {
      super(var1);
   }

   int activationKind() {
      return 0;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getMiscResultSet", "org.apache.derby.iapi.sql.ResultSet", 1);
   }

   public boolean needsSavepoint() {
      return false;
   }
}
