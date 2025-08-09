package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class OrNode extends BinaryLogicalOperatorNode {
   private boolean firstOr;

   OrNode(ValueNode var1, ValueNode var2, ContextManager var3) {
      super(var1, var2, "or", var3);
      this.shortCircuitValue = true;
   }

   void setFirstOr() {
      this.firstOr = true;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      this.postBindFixup();
      return this;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      super.preprocess(var1, var2, var3, var4);
      if (this.firstOr) {
         boolean var5 = true;
         ColumnReference var6 = null;
         int var7 = -1;
         int var8 = -1;

         Object var9;
         for(var9 = this; var9 instanceof OrNode; var9 = ((OrNode)var9).getRightOperand()) {
            OrNode var10 = (OrNode)var9;
            ValueNode var11 = var10.getLeftOperand();
            if (!var11.isRelationalOperator()) {
               var5 = var11 instanceof BinaryRelationalOperatorNode;
               if (!var5) {
                  break;
               }
            }

            if (((RelationalOperator)var11).getOperator() != 1) {
               var5 = false;
               break;
            }

            BinaryRelationalOperatorNode var12 = (BinaryRelationalOperatorNode)var11;
            if (var12.getLeftOperand() instanceof ColumnReference) {
               var6 = (ColumnReference)var12.getLeftOperand();
               if (var8 == -1) {
                  var8 = var6.getTableNumber();
                  var7 = var6.getColumnNumber();
               } else if (var8 != var6.getTableNumber() || var7 != var6.getColumnNumber()) {
                  var5 = false;
                  break;
               }
            } else {
               if (!(var12.getRightOperand() instanceof ColumnReference)) {
                  var5 = false;
                  break;
               }

               var6 = (ColumnReference)var12.getRightOperand();
               if (var8 == -1) {
                  var8 = var6.getTableNumber();
                  var7 = var6.getColumnNumber();
               } else if (var8 != var6.getTableNumber() || var7 != var6.getColumnNumber()) {
                  var5 = false;
                  break;
               }
            }
         }

         var5 = var5 && ((ValueNode)var9).isBooleanFalse();
         if (var5) {
            ValueNodeList var15 = new ValueNodeList(this.getContextManager());

            for(Object var14 = this; var14 instanceof OrNode; var14 = ((OrNode)var14).getRightOperand()) {
               OrNode var16 = (OrNode)var14;
               BinaryRelationalOperatorNode var18 = (BinaryRelationalOperatorNode)var16.getLeftOperand();
               if (var18.isInListProbeNode()) {
                  var15.destructiveAppend(var18.getInListOp().getRightOperandList());
               } else if (var18.getLeftOperand() instanceof ColumnReference) {
                  var15.addValueNode(var18.getRightOperand());
               } else {
                  var15.addValueNode(var18.getLeftOperand());
               }
            }

            InListOperatorNode var17 = new InListOperatorNode(var6, var15, this.getContextManager());
            var17.setType(this.getTypeServices());
            return var17.preprocess(var1, var2, var3, var4);
         }
      }

      return this;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      this.leftOperand = this.leftOperand.eliminateNots(var1);
      this.rightOperand = this.rightOperand.eliminateNots(var1);
      if (!var1) {
         return this;
      } else {
         AndNode var2 = new AndNode(this.leftOperand, this.rightOperand, this.getContextManager());
         var2.setType(this.getTypeServices());
         return var2;
      }
   }

   ValueNode changeToCNF(boolean var1) throws StandardException {
      OrNode var2 = this;
      if (this.rightOperand instanceof AndNode) {
         BooleanConstantNode var3 = new BooleanConstantNode(false, this.getContextManager());
         this.rightOperand = new OrNode(this.rightOperand, var3, this.getContextManager());
         ((OrNode)this.rightOperand).postBindFixup();
      }

      while(var2.getRightOperand() instanceof OrNode) {
         var2 = (OrNode)var2.getRightOperand();
      }

      if (!var2.getRightOperand().isBooleanFalse()) {
         BooleanConstantNode var7 = new BooleanConstantNode(false, this.getContextManager());
         var2.setRightOperand(new OrNode(var2.getRightOperand(), var7, this.getContextManager()));
         ((OrNode)var2.getRightOperand()).postBindFixup();
      }

      while(this.leftOperand instanceof OrNode) {
         ValueNode var8 = ((OrNode)this.leftOperand).getLeftOperand();
         OrNode var4 = (OrNode)this.leftOperand;
         OrNode var5 = (OrNode)this.leftOperand;
         ValueNode var6 = this.rightOperand;
         this.leftOperand = var8;
         this.rightOperand = var5;
         var5.setLeftOperand(var4.getRightOperand());
         var5.setRightOperand(var6);
      }

      this.leftOperand = this.leftOperand.changeToCNF(false);
      this.rightOperand = this.rightOperand.changeToCNF(false);
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
