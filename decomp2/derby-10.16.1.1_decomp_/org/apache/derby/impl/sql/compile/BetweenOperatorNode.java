package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class BetweenOperatorNode extends BinaryListOperatorNode {
   BetweenOperatorNode(ValueNode var1, ValueNodeList var2, ContextManager var3) throws StandardException {
      super(var1, vetValues(var2), "BETWEEN", (String)null, var3);
   }

   private static ValueNodeList vetValues(ValueNodeList var0) {
      return var0;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      if (!var1) {
         return this;
      } else {
         ContextManager var5 = this.getContextManager();
         BinaryRelationalOperatorNode var2 = new BinaryRelationalOperatorNode(4, this.leftOperand, (ValueNode)this.rightOperandList.elementAt(0), false, var5);
         ((BinaryComparisonOperatorNode)var2).bindComparisonOperator();
         ValueNode var6 = this.leftOperand instanceof ColumnReference ? this.leftOperand.getClone() : this.leftOperand;
         BinaryRelationalOperatorNode var3 = new BinaryRelationalOperatorNode(2, var6, (ValueNode)this.rightOperandList.elementAt(1), false, var5);
         ((BinaryComparisonOperatorNode)var3).bindComparisonOperator();
         OrNode var4 = new OrNode(var2, var3, var5);
         var4.postBindFixup();
         ((BinaryComparisonOperatorNode)var2).setBetweenSelectivity();
         ((BinaryComparisonOperatorNode)var3).setBetweenSelectivity();
         return var4;
      }
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      super.preprocess(var1, var2, var3, var4);
      if (!(this.leftOperand instanceof ColumnReference)) {
         return this;
      } else {
         ValueNode var5 = this.leftOperand.getClone();
         ContextManager var7 = this.getContextManager();
         BooleanConstantNode var8 = new BooleanConstantNode(true, var7);
         BinaryRelationalOperatorNode var9 = new BinaryRelationalOperatorNode(3, var5, (ValueNode)this.rightOperandList.elementAt(1), false, var7);
         ((BinaryComparisonOperatorNode)var9).bindComparisonOperator();
         AndNode var10 = new AndNode(var9, var8, var7);
         var10.postBindFixup();
         BinaryRelationalOperatorNode var11 = new BinaryRelationalOperatorNode(1, this.leftOperand, (ValueNode)this.rightOperandList.elementAt(0), false, var7);
         ((BinaryComparisonOperatorNode)var11).bindComparisonOperator();
         var10 = new AndNode(var11, var10, var7);
         var10.postBindFixup();
         ((BinaryComparisonOperatorNode)var9).setBetweenSelectivity();
         ((BinaryComparisonOperatorNode)var11).setBetweenSelectivity();
         return var10;
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      ContextManager var6 = this.getContextManager();
      BinaryRelationalOperatorNode var4 = new BinaryRelationalOperatorNode(1, this.leftOperand, (ValueNode)this.rightOperandList.elementAt(0), false, var6);
      ((BinaryComparisonOperatorNode)var4).bindComparisonOperator();
      BinaryRelationalOperatorNode var5 = new BinaryRelationalOperatorNode(3, this.leftOperand, (ValueNode)this.rightOperandList.elementAt(1), false, var6);
      ((BinaryComparisonOperatorNode)var5).bindComparisonOperator();
      AndNode var3 = new AndNode(var4, var5, var6);
      var3.postBindFixup();
      var3.generateExpression(var1, var2);
   }
}
