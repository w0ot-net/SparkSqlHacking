package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class IsNode extends BinaryLogicalOperatorNode {
   private boolean notMe;

   IsNode(ValueNode var1, ValueNode var2, boolean var3, ContextManager var4) {
      super(var1, var2, "is", var4);
      this.notMe = var3;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      this.leftOperand.checkIsBoolean();
      this.rightOperand.checkIsBoolean();
      this.setType(this.leftOperand.getTypeServices());
      return this;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      if (var1) {
         this.notMe = !this.notMe;
      }

      this.leftOperand = this.leftOperand.eliminateNots(false);
      this.rightOperand = this.rightOperand.eliminateNots(false);
      return this;
   }

   public ValueNode putAndsOnTop() throws StandardException {
      this.leftOperand = this.leftOperand.putAndsOnTop();
      this.rightOperand = this.rightOperand.putAndsOnTop();
      return this;
   }

   public boolean verifyPutAndsOnTop() {
      return this.leftOperand.verifyPutAndsOnTop() && this.rightOperand.verifyPutAndsOnTop();
   }

   public ValueNode changeToCNF(boolean var1) throws StandardException {
      this.leftOperand = this.leftOperand.changeToCNF(false);
      this.rightOperand = this.rightOperand.changeToCNF(false);
      return this;
   }

   public boolean verifyChangeToCNF() {
      return this.leftOperand.verifyChangeToCNF() && this.rightOperand.verifyChangeToCNF();
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      String var3;
      if (this.notMe) {
         var3 = "isNot";
      } else {
         var3 = "is";
      }

      this.leftOperand.generateExpression(var1, var2);
      this.rightOperand.generateExpression(var1, var2);
      var2.callMethod((short)185, "org.apache.derby.iapi.types.BooleanDataValue", var3, "org.apache.derby.iapi.types.BooleanDataValue", 1);
   }
}
