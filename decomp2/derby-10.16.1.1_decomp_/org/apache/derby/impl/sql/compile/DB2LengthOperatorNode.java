package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class DB2LengthOperatorNode extends UnaryOperatorNode {
   DB2LengthOperatorNode(ValueNode var1, ContextManager var2) throws StandardException {
      super(var1, "length", "getDB2Length", var2);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      TypeId var4 = this.operand.getTypeId();
      if (var4.isXMLTypeId()) {
         throw StandardException.newException("42X25", new Object[]{this.getOperatorString(), var4.getSQLTypeName()});
      } else {
         this.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(4), this.operand.getTypeServices().isNullable()));
         return this;
      }
   }

   String getReceiverInterfaceName() {
      return "org.apache.derby.iapi.types.ConcatableDataValue";
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.operand != null) {
         int var3 = this.getConstantLength();
         String var4 = this.getTypeCompiler().interfaceName();
         var2.pushThis();
         this.operand.generateExpression(var1, var2);
         var2.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
         var2.push(var3);
         LocalField var5 = var1.newFieldDeclaration(2, var4);
         var2.getField(var5);
         var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", this.methodName, var4, 3);
         var2.putField(var5);
      }
   }

   private int getConstantLength() throws StandardException {
      DataTypeDescriptor var1 = this.operand.getTypeServices();
      switch (var1.getJDBCTypeId()) {
         case -7:
         case 16:
            return 1;
         case -6:
            return 1;
         case -5:
            return 8;
         case -4:
         case -3:
         case -1:
         case 12:
         case 2004:
            return this.getConstantNodeLength();
         case -2:
         case 1:
            return var1.getMaximumWidth();
         case 2:
         case 3:
            return var1.getPrecision() / 2 + 1;
         case 4:
         case 6:
         case 7:
            return 4;
         case 5:
            return 2;
         case 8:
            return 8;
         case 91:
            return 4;
         case 92:
            return 3;
         case 93:
            return 10;
         default:
            return -1;
      }
   }

   private int getConstantNodeLength() throws StandardException {
      return this.operand instanceof ConstantNode ? ((ConstantNode)this.operand).getValue().getLength() : -1;
   }
}
