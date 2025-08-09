package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class AndNoShortCircuitNode extends AndNode {
   AndNoShortCircuitNode(ValueNode var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var1, var2, "andnoshortcircuitnode", var3);
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.leftOperand.generateExpression(var1, var2);
      var2.upCast("org.apache.derby.iapi.types.BooleanDataValue");
      this.rightOperand.generateExpression(var1, var2);
      var2.upCast("org.apache.derby.iapi.types.BooleanDataValue");
      var2.callMethod((short)185, (String)null, "and", "org.apache.derby.iapi.types.BooleanDataValue", 1);
   }
}
