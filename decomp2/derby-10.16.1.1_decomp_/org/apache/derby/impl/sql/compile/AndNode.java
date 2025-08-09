package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class AndNode extends BinaryLogicalOperatorNode {
   AndNode(ValueNode var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var1, var2, "and", var3);
      this.shortCircuitValue = false;
   }

   AndNode(ValueNode var1, ValueNode var2, String var3, ContextManager var4) throws StandardException {
      super(var1, var2, var3, var4);
      this.shortCircuitValue = false;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      this.postBindFixup();
      return this;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      if (this.leftOperand instanceof OrNode) {
         ((OrNode)this.leftOperand).setFirstOr();
      }

      this.leftOperand = this.leftOperand.preprocess(var1, var2, var3, var4);
      if (this.leftOperand instanceof AndNode) {
         this.changeToCNF(false);
      }

      this.rightOperand = this.rightOperand.preprocess(var1, var2, var3, var4);
      return this;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      this.leftOperand = this.leftOperand.eliminateNots(var1);
      this.rightOperand = this.rightOperand.eliminateNots(var1);
      if (!var1) {
         return this;
      } else {
         OrNode var2 = new OrNode(this.leftOperand, this.rightOperand, this.getContextManager());
         ((ValueNode)var2).setType(this.getTypeServices());
         return var2;
      }
   }

   ValueNode putAndsOnTop() throws StandardException {
      this.rightOperand = this.rightOperand.putAndsOnTop();
      return this;
   }

   boolean verifyPutAndsOnTop() {
      boolean var1 = true;
      return var1;
   }

   ValueNode changeToCNF(boolean var1) throws StandardException {
      if (!(this.rightOperand instanceof AndNode) && !this.rightOperand.isBooleanTrue()) {
         BooleanConstantNode var3 = new BooleanConstantNode(true, this.getContextManager());
         AndNode var4 = new AndNode(this.getRightOperand(), var3, this.getContextManager());
         this.setRightOperand(var4);
         var4.postBindFixup();
      }

      while(this.leftOperand instanceof AndNode) {
         ValueNode var7 = ((AndNode)this.leftOperand).getLeftOperand();
         AndNode var8 = (AndNode)this.leftOperand;
         AndNode var5 = (AndNode)this.leftOperand;
         ValueNode var6 = this.rightOperand;
         this.leftOperand = var7;
         this.rightOperand = var5;
         var5.setLeftOperand(var8.getRightOperand());
         var5.setRightOperand(var6);
      }

      this.leftOperand = this.leftOperand.changeToCNF(var1);
      this.rightOperand = this.rightOperand.changeToCNF(var1);
      return this;
   }

   boolean verifyChangeToCNF() {
      boolean var1 = true;
      return var1;
   }

   void postBindFixup() throws StandardException {
      this.setType(this.resolveLogicalBinaryOperator(this.leftOperand.getTypeServices(), this.rightOperand.getTypeServices()));
   }
}
