package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class TimestampOperatorNode extends BinaryOperatorNode {
   TimestampOperatorNode(ValueNode var1, ValueNode var2, ContextManager var3) {
      super(var3);
      this.leftOperand = var1;
      this.rightOperand = var2;
      this.operator = "timestamp";
      this.methodName = "getTimestamp";
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.leftOperand = this.leftOperand.bindExpression(var1, var2, var3);
      this.rightOperand = this.rightOperand.bindExpression(var1, var2, var3);
      if (this.leftOperand.requiresTypeFromContext()) {
         this.leftOperand.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(91));
      }

      if (this.rightOperand.requiresTypeFromContext()) {
         this.rightOperand.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(92));
      }

      TypeId var4 = this.leftOperand.getTypeId();
      TypeId var5 = this.rightOperand.getTypeId();
      if (!this.leftOperand.requiresTypeFromContext() && !var4.isStringTypeId() && var4.getJDBCTypeId() != 91) {
         throw StandardException.newException("42Y95", new Object[]{this.operator, var4.getSQLTypeName(), var5.getSQLTypeName()});
      } else if (!this.rightOperand.requiresTypeFromContext() && !var5.isStringTypeId() && var5.getJDBCTypeId() != 92) {
         throw StandardException.newException("42Y95", new Object[]{this.operator, var4.getSQLTypeName(), var5.getSQLTypeName()});
      } else {
         this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(93));
         return this.genSQLJavaSQLTree();
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushDataValueFactory(var2);
      this.leftOperand.generateExpression(var1, var2);
      var2.cast("org.apache.derby.iapi.types.DataValueDescriptor");
      this.rightOperand.generateExpression(var1, var2);
      var2.cast("org.apache.derby.iapi.types.DataValueDescriptor");
      var2.callMethod((short)185, (String)null, this.methodName, "org.apache.derby.iapi.types.DateTimeDataValue", 2);
   }
}
