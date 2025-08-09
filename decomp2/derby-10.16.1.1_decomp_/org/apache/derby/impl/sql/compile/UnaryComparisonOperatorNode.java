package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public abstract class UnaryComparisonOperatorNode extends UnaryOperatorNode {
   UnaryComparisonOperatorNode(ValueNode var1, ContextManager var2) throws StandardException {
      super(var1, var2);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      this.bindComparisonOperator();
      return this;
   }

   void bindComparisonOperator() throws StandardException {
      this.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, false));
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      return (ValueNode)(!var1 ? this : this.getNegation(this.operand));
   }

   abstract UnaryOperatorNode getNegation(ValueNode var1) throws StandardException;

   public ColumnReference getColumnOperand(Optimizable var1, int var2) {
      FromBaseTable var3 = (FromBaseTable)var1;
      if (this.operand instanceof ColumnReference) {
         ColumnReference var4 = (ColumnReference)this.operand;
         if (var4.getTableNumber() == var3.getTableNumber() && var4.getSource().getColumnPosition() == var2) {
            return var4;
         }
      }

      return null;
   }

   public ColumnReference getColumnOperand(Optimizable var1) {
      if (this.operand instanceof ColumnReference) {
         ColumnReference var2 = (ColumnReference)this.operand;
         if (var2.getTableNumber() == var1.getTableNumber()) {
            return var2;
         }
      }

      return null;
   }

   public ValueNode getOperand(ColumnReference var1, int var2, boolean var3) {
      if (var3) {
         return null;
      } else {
         if (this.operand instanceof ColumnReference) {
            JBitSet var5 = new JBitSet(var2);
            JBitSet var6 = new JBitSet(var2);
            BaseTableNumbersVisitor var7 = new BaseTableNumbersVisitor(var6);
            ColumnReference var4 = (ColumnReference)this.operand;

            try {
               var4.accept(var7);
               var7.setTableMap(var5);
               var1.accept(var7);
            } catch (StandardException var9) {
            }

            var6.and(var5);
            if (var6.getFirstSetBit() != -1 && var4.getSource().getColumnPosition() == var1.getColumnNumber()) {
               return this.operand;
            }
         }

         return null;
      }
   }

   public boolean selfComparison(ColumnReference var1) {
      return false;
   }

   public ValueNode getExpressionOperand(int var1, int var2, Optimizable var3) {
      return null;
   }

   public void generateExpressionOperand(Optimizable var1, int var2, ExpressionClassBuilderInterface var3, MethodBuilder var4) throws StandardException {
      ExpressionClassBuilder var5 = (ExpressionClassBuilder)var3;
      var5.generateNull(var4, this.operand.getTypeCompiler(), this.operand.getTypeServices().getCollationType());
   }

   public int getStartOperator(Optimizable var1) {
      return 1;
   }

   public int getStopOperator(Optimizable var1) {
      return -1;
   }

   public void generateOrderedNulls(MethodBuilder var1) {
      var1.push(true);
   }

   public void generateQualMethod(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException {
      ExpressionClassBuilder var4 = (ExpressionClassBuilder)var1;
      MethodBuilder var5 = var4.newUserExprFun();
      var4.generateNull(var5, this.operand.getTypeCompiler(), this.operand.getTypeServices().getCollationType());
      var5.methodReturn();
      var5.complete();
      var4.pushMethodReference(var2, var5);
   }

   public void generateAbsoluteColumnId(MethodBuilder var1, Optimizable var2) {
      int var3 = this.getAbsoluteColumnPosition(var2);
      var1.push(var3);
   }

   public void generateRelativeColumnId(MethodBuilder var1, Optimizable var2) {
      int var3 = this.getAbsoluteColumnPosition(var2);
      var3 = var2.convertAbsoluteToRelativeColumnPosition(var3);
      var1.push(var3);
   }

   private int getAbsoluteColumnPosition(Optimizable var1) {
      ColumnReference var2 = (ColumnReference)this.operand;
      int var3 = var2.getSource().getColumnPosition();
      ConglomerateDescriptor var4 = var1.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      if (var4.isIndex()) {
         var3 = var4.getIndexDescriptor().getKeyColumnPosition(var3);
      }

      return var3 - 1;
   }

   public boolean orderedNulls() {
      return true;
   }

   public boolean isQualifier(Optimizable var1, boolean var2) {
      if (!(this.operand instanceof ColumnReference)) {
         return false;
      } else {
         ColumnReference var3 = (ColumnReference)this.operand;
         FromTable var4 = (FromTable)var1;
         return var3.getTableNumber() == var4.getTableNumber();
      }
   }

   public int getOrderableVariantType(Optimizable var1) throws StandardException {
      return this.operand.getOrderableVariantType();
   }
}
