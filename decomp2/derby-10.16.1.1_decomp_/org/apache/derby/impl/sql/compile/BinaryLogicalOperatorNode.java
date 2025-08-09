package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

abstract class BinaryLogicalOperatorNode extends BinaryOperatorNode {
   boolean shortCircuitValue;

   BinaryLogicalOperatorNode(ValueNode var1, ValueNode var2, String var3, ContextManager var4) {
      super(var1, var2, var3, var3, "org.apache.derby.iapi.types.BooleanDataValue", "org.apache.derby.iapi.types.BooleanDataValue", var4);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (!this.leftOperand.isParameterNode() && !this.rightOperand.isParameterNode()) {
         super.bindExpression(var1, var2, var3);
         return this;
      } else {
         throw StandardException.newException("42X19.S.2", new Object[0]);
      }
   }

   boolean verifyEliminateNots() {
      return true;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.leftOperand.generateExpression(var1, var2);
      var2.dup();
      var2.push(this.shortCircuitValue);
      var2.callMethod((short)185, (String)null, "equals", "boolean", 1);
      var2.conditionalIf();
      var2.callMethod((short)185, (String)null, "getImmutable", "org.apache.derby.iapi.types.BooleanDataValue", 0);
      var2.startElseCode();
      this.rightOperand.generateExpression(var1, var2);
      var2.upCast("org.apache.derby.iapi.types.BooleanDataValue");
      var2.callMethod((short)185, (String)null, this.methodName, "org.apache.derby.iapi.types.BooleanDataValue", 1);
      var2.completeConditional();
   }

   DataTypeDescriptor resolveLogicalBinaryOperator(DataTypeDescriptor var1, DataTypeDescriptor var2) throws StandardException {
      if (var1.getTypeId().isBooleanTypeId() && var2.getTypeId().isBooleanTypeId()) {
         return var1.getNullabilityType(var1.isNullable() || var2.isNullable());
      } else {
         throw StandardException.newException("42Y94", new Object[0]);
      }
   }
}
