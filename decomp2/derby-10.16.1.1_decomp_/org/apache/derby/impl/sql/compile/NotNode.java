package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public final class NotNode extends UnaryLogicalOperatorNode {
   NotNode(ValueNode var1, ContextManager var2) throws StandardException {
      super(var1, "not", var2);
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      return this.operand.eliminateNots(!var1);
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      String var3 = this.getTypeCompiler().interfaceName();
      LocalField var4 = var1.newFieldDeclaration(2, var3);
      this.operand.generateExpression(var1, var2);
      var2.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
      var2.dup();
      var2.push(false);
      var1.generateDataValue(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType(), var4);
      var2.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
      var2.callMethod((short)185, (String)null, "equals", var3, 2);
   }
}
