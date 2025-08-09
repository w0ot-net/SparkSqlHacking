package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class SimpleStringOperatorNode extends UnaryOperatorNode {
   SimpleStringOperatorNode(ValueNode var1, String var2, ContextManager var3) throws StandardException {
      super(var1, var2, var2, var3);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      TypeId var4 = this.operand.getTypeId();
      switch (var4.getJDBCTypeId()) {
         case 1111:
         case 2000:
            throw StandardException.newException("42X25", new Object[]{this.methodName, var4.getSQLTypeName()});
         default:
            DataTypeDescriptor var5 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, true, this.operand.getTypeCompiler().getCastToCharWidth(this.operand.getTypeServices()));
            this.operand = new CastNode(this.operand, var5, this.getContextManager());
            this.operand.setCollationUsingCompilationSchema();
            ((CastNode)this.operand).bindCastNodeOnly();
            var4 = this.operand.getTypeId();
         case -1:
         case 1:
         case 12:
         case 2005:
            this.setType(new DataTypeDescriptor(var4, this.operand.getTypeServices().isNullable(), this.operand.getTypeCompiler().getCastToCharWidth(this.operand.getTypeServices())));
            this.setCollationInfo(this.operand.getTypeServices());
            return this;
      }
   }

   void bindParameter() throws StandardException {
      this.operand.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(12));
      this.operand.setCollationUsingCompilationSchema();
   }

   String getReceiverInterfaceName() {
      return "org.apache.derby.iapi.types.StringDataValue";
   }
}
