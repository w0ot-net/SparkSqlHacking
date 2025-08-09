package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public abstract class BinaryListOperatorNode extends ValueNode {
   String methodName;
   String operator;
   ValueNode leftOperand;
   ValueNodeList rightOperandList;

   BinaryListOperatorNode(ValueNode var1, ValueNodeList var2, String var3, String var4, ContextManager var5) {
      super(var5);
      this.leftOperand = var1;
      this.rightOperandList = var2;
      this.operator = var3;
      this.methodName = var4;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.leftOperand = this.leftOperand.bindExpression(var1, var2, var3);
      this.rightOperandList.bindExpression(var1, var2, var3);
      if (this.leftOperand.requiresTypeFromContext()) {
         if (this.rightOperandList.containsAllParameterNodes()) {
            throw StandardException.newException("42X35", new Object[]{this.operator});
         }

         this.leftOperand.setType(this.rightOperandList.getTypeServices());
      }

      if (this.rightOperandList.containsParameterNode()) {
         this.rightOperandList.setParameterDescriptor(this.leftOperand.getTypeServices());
      }

      if (this.leftOperand.getTypeId().userType()) {
         this.leftOperand = this.leftOperand.genSQLJavaSQLTree();
      }

      this.rightOperandList.genSQLJavaSQLTrees();
      this.bindComparisonOperator();
      return this;
   }

   void bindComparisonOperator() throws StandardException {
      this.rightOperandList.comparable(this.leftOperand);
      boolean var1 = this.leftOperand.getTypeServices().isNullable() || this.rightOperandList.isNullable();
      this.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, var1));
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.leftOperand = this.leftOperand.preprocess(var1, var2, var3, var4);
      this.rightOperandList.preprocess(var1, var2, var3, var4);
      return this;
   }

   void setLeftOperand(ValueNode var1) {
      this.leftOperand = var1;
   }

   ValueNode getLeftOperand() {
      return this.leftOperand;
   }

   void setRightOperandList(ValueNodeList var1) {
      this.rightOperandList = var1;
   }

   ValueNodeList getRightOperandList() {
      return this.rightOperandList;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      boolean var3 = this.leftOperand.categorize(var1, var2);
      var3 = this.rightOperandList.categorize(var1, var2) && var3;
      return var3;
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.leftOperand = this.leftOperand.remapColumnReferencesToExpressions();
      this.rightOperandList.remapColumnReferencesToExpressions();
      return this;
   }

   boolean isConstantExpression() {
      return this.leftOperand.isConstantExpression() && this.rightOperandList.isConstantExpression();
   }

   boolean constantExpression(PredicateList var1) {
      return this.leftOperand.constantExpression(var1) && this.rightOperandList.constantExpression(var1);
   }

   protected int getOrderableVariantType() throws StandardException {
      int var1 = this.leftOperand.getOrderableVariantType();
      int var2 = this.rightOperandList.getOrderableVariantType();
      return Math.min(var1, var2);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.leftOperand != null) {
         this.leftOperand = (ValueNode)this.leftOperand.accept(var1);
      }

      if (this.rightOperandList != null) {
         this.rightOperandList = (ValueNodeList)this.rightOperandList.accept(var1);
      }

   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         BinaryListOperatorNode var2 = (BinaryListOperatorNode)var1;
         if (this.operator.equals(var2.operator) && this.leftOperand.isEquivalent(var2.getLeftOperand())) {
            return this.rightOperandList.isEquivalent(var2.rightOperandList);
         } else {
            return false;
         }
      }
   }
}
