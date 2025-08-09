package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class MaterializeSubqueryNode extends ResultSetNode {
   private LocalField lf;

   MaterializeSubqueryNode(LocalField var1, ContextManager var2) {
      super(var2);
      this.lf = var1;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushThisAsActivation(var2);
      var2.getField(this.lf);
      var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "materializeResultSetIfPossible", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 1);
   }

   void decrementLevel(int var1) {
   }
}
