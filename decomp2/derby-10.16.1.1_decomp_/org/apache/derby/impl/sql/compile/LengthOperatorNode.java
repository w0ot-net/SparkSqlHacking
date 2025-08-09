package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class LengthOperatorNode extends UnaryOperatorNode {
   private int parameterType;
   private int parameterWidth;

   LengthOperatorNode(ValueNode var1, ContextManager var2) throws StandardException {
      super(var1, var2);
      String var3 = "char_length";
      String var4 = "charLength";
      this.parameterType = 12;
      this.parameterWidth = 32672;
      this.setOperator(var3);
      this.setMethodName(var4);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      TypeId var4 = this.operand.getTypeId();
      switch (var4.getJDBCTypeId()) {
         case -4:
         case -3:
         case -2:
         case -1:
         case 1:
         case 12:
         case 2004:
         case 2005:
            this.setType(new DataTypeDescriptor(TypeId.INTEGER_ID, this.operand.getTypeServices().isNullable()));
            return this;
         default:
            throw StandardException.newException("42X25", new Object[]{this.getOperatorString(), var4.getSQLTypeName()});
      }
   }

   void bindParameter() throws StandardException {
      this.operand.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(this.parameterType, true, this.parameterWidth));
   }

   String getReceiverInterfaceName() {
      return "org.apache.derby.iapi.types.ConcatableDataValue";
   }
}
