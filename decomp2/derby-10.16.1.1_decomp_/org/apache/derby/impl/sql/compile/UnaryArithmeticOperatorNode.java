package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class UnaryArithmeticOperatorNode extends UnaryOperatorNode {
   private static final String[] UNARY_OPERATORS = new String[]{"+", "-", "SQRT", "ABS/ABSVAL"};
   private static final String[] UNARY_METHODS = new String[]{"plus", "minus", "sqrt", "absolute"};
   static final int K_PLUS = 0;
   static final int K_MINUS = 1;
   static final int K_SQRT = 2;
   static final int K_ABS = 3;
   final int kind;

   UnaryArithmeticOperatorNode(ValueNode var1, int var2, ContextManager var3) throws StandardException {
      super(var1, UNARY_OPERATORS[var2], UNARY_METHODS[var2], var3);
      this.kind = var2;
   }

   public boolean requiresTypeFromContext() {
      return this.kind != 0 && this.kind != 1 ? false : this.operand.requiresTypeFromContext();
   }

   public boolean isParameterNode() {
      return this.kind != 0 && this.kind != 1 ? false : this.operand.isParameterNode();
   }

   void bindParameter() throws StandardException {
      if (this.kind != 2 && this.kind != 3) {
         if (this.kind != 1 && this.kind != 0) {
            super.bindParameter();
         }
      } else {
         this.operand.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(8), true));
      }
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (this.operand.requiresTypeFromContext() && (this.kind == 0 || this.kind == 1) && this.operand.getTypeServices() == null) {
         return this;
      } else {
         this.bindOperand(var1, var2, var3);
         if (this.kind != 2 && this.kind != 3) {
            if (this.kind == 0 || this.kind == 1) {
               this.checkOperandIsNumeric(this.operand.getTypeId());
            }
         } else {
            this.bindSQRTABS();
         }

         super.setType(this.operand.getTypeServices());
         return this;
      }
   }

   private void checkOperandIsNumeric(TypeId var1) throws StandardException {
      if (!var1.isNumericTypeId()) {
         throw StandardException.newException("42X37", new Object[]{this.kind == 0 ? "+" : "-", var1.getSQLTypeName()});
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.kind == 0) {
         this.operand.generateExpression(var1, var2);
      } else {
         super.generateExpression(var1, var2);
      }

   }

   private void bindSQRTABS() throws StandardException {
      TypeId var1 = this.operand.getTypeId();
      if (var1.userType()) {
         this.operand = this.operand.genSQLJavaSQLTree();
      }

      int var2 = var1.getJDBCTypeId();
      if (!var1.isNumericTypeId()) {
         throw StandardException.newException("42X25", new Object[]{this.getOperatorString(), var1.getSQLTypeName()});
      } else {
         if (this.kind == 2 && var2 != 8) {
            this.operand = new CastNode(this.operand, new DataTypeDescriptor(TypeId.getBuiltInTypeId(8), true), this.getContextManager());
            ((CastNode)this.operand).bindCastNodeOnly();
         }

      }
   }

   void setType(DataTypeDescriptor var1) throws StandardException {
      if (this.operand.requiresTypeFromContext() && this.operand.getTypeServices() == null) {
         this.checkOperandIsNumeric(var1.getTypeId());
         this.operand.setType(var1);
      }

      super.setType(var1);
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((UnaryArithmeticOperatorNode)var1).kind == this.kind;
   }
}
