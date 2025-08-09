package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.DateTimeDataValue;
import org.apache.derby.shared.common.error.StandardException;

class UnaryDateTimestampOperatorNode extends UnaryOperatorNode {
   private static final String TIMESTAMP_METHOD_NAME = "getTimestamp";
   private static final String DATE_METHOD_NAME = "getDate";
   static final int K_DATE = 0;
   static final int K_TIMESTAMP = 1;
   final int kind;

   UnaryDateTimestampOperatorNode(ValueNode var1, int var2, ContextManager var3) throws StandardException {
      super(var1, var2 == 0 ? "date" : "timestamp", var2 == 0 ? "getDate" : "getTimestamp", var3);
      this.kind = var2;
      this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(var2 == 0 ? 91 : 93));
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      boolean var4 = false;
      boolean var5 = false;
      this.bindOperand(var1, var2, var3);
      DataTypeDescriptor var6 = this.operand.getTypeServices();
      switch (var6.getJDBCTypeId()) {
         case -6:
         case -5:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 8:
            if ("getTimestamp".equals(this.methodName)) {
               this.invalidOperandType();
            }

            var5 = true;
         case 0:
         case 1:
         case 12:
            break;
         case 91:
            if ("getTimestamp".equals(this.methodName)) {
               this.invalidOperandType();
            }

            var4 = true;
            break;
         case 93:
            if ("getTimestamp".equals(this.methodName)) {
               var4 = true;
            }
            break;
         default:
            this.invalidOperandType();
      }

      if (this.operand instanceof ConstantNode) {
         DataValueFactory var7 = this.getLanguageConnectionContext().getDataValueFactory();
         DataValueDescriptor var8 = ((ConstantNode)this.operand).getValue();
         DateTimeDataValue var9;
         if (var8.isNull()) {
            var9 = "getTimestamp".equals(this.methodName) ? var7.getNullTimestamp((DateTimeDataValue)null) : var7.getNullDate((DateTimeDataValue)null);
         } else {
            var9 = "getTimestamp".equals(this.methodName) ? var7.getTimestamp(var8) : var7.getDate(var8);
         }

         return new UserTypeConstantNode(var9, this.getContextManager());
      } else {
         return (ValueNode)(var4 ? this.operand : this);
      }
   }

   private void invalidOperandType() throws StandardException {
      throw StandardException.newException("42X25", new Object[]{this.getOperatorString(), this.getOperand().getTypeServices().getSQLstring()});
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushDataValueFactory(var2);
      this.operand.generateExpression(var1, var2);
      var2.cast("org.apache.derby.iapi.types.DataValueDescriptor");
      var2.callMethod((short)185, (String)null, this.methodName, this.getTypeCompiler().interfaceName(), 1);
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((UnaryDateTimestampOperatorNode)var1).kind == this.kind;
   }
}
