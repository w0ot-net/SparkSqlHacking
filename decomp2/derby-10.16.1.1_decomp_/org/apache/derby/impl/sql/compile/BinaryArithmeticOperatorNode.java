package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class BinaryArithmeticOperatorNode extends BinaryOperatorNode {
   static final int K_DIVIDE = 0;
   static final int K_MINUS = 1;
   static final int K_PLUS = 2;
   static final int K_TIMES = 3;
   static final int K_MOD = 4;
   final int kind;

   BinaryArithmeticOperatorNode(int var1, ValueNode var2, ValueNode var3, ContextManager var4) {
      super(var2, var3, "org.apache.derby.iapi.types.NumberDataValue", "org.apache.derby.iapi.types.NumberDataValue", var4);
      this.kind = var1;
      String var5;
      String var6;
      switch (var1) {
         case 0:
            var5 = "/";
            var6 = "divide";
            break;
         case 1:
            var5 = "-";
            var6 = "minus";
            break;
         case 2:
            var5 = "+";
            var6 = "plus";
            break;
         case 3:
            var5 = "*";
            var6 = "times";
            break;
         case 4:
            var5 = "mod";
            var6 = "mod";
            break;
         default:
            var5 = null;
            var6 = null;
      }

      this.setOperator(var5);
      this.setMethodName(var6);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      TypeId var4 = this.leftOperand.getTypeId();
      TypeId var5 = this.rightOperand.getTypeId();
      DataTypeDescriptor var6 = this.leftOperand.getTypeServices();
      DataTypeDescriptor var7 = this.rightOperand.getTypeServices();
      if (var4.isStringTypeId() && var5.isNumericTypeId()) {
         boolean var13 = var6.isNullable() || var7.isNullable();
         int var14 = var7.getPrecision();
         int var15 = var7.getScale();
         int var16 = var7.getMaximumWidth();
         if (var5.isDecimalTypeId()) {
            int var17 = var6.getMaximumWidth();
            var14 += 2 * var17;
            var15 += var17;
            var16 = var14 + 3;
         }

         this.leftOperand = new CastNode(this.leftOperand, new DataTypeDescriptor(var5, var14, var15, var13, var16), this.getContextManager());
         ((CastNode)this.leftOperand).bindCastNodeOnly();
      } else if (var5.isStringTypeId() && var4.isNumericTypeId()) {
         boolean var8 = var6.isNullable() || var7.isNullable();
         int var9 = var6.getPrecision();
         int var10 = var6.getScale();
         int var11 = var6.getMaximumWidth();
         if (var4.isDecimalTypeId()) {
            int var12 = var7.getMaximumWidth();
            var9 += 2 * var12;
            var10 += var12;
            var11 = var9 + 3;
         }

         this.rightOperand = new CastNode(this.rightOperand, new DataTypeDescriptor(var4, var9, var10, var8, var11), this.getContextManager());
         ((CastNode)this.rightOperand).bindCastNodeOnly();
      }

      this.setType(this.leftOperand.getTypeCompiler().resolveArithmeticOperation(this.leftOperand.getTypeServices(), this.rightOperand.getTypeServices(), this.operator));
      return this;
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((BinaryArithmeticOperatorNode)var1).kind == this.kind;
   }
}
