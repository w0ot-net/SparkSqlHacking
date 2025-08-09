package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class InListOperatorNode extends BinaryListOperatorNode {
   private boolean isOrdered;
   private boolean sortDescending;

   InListOperatorNode(ValueNode var1, ValueNodeList var2, ContextManager var3) throws StandardException {
      super(var1, var2, "IN", "in", var3);
   }

   public String toString() {
      return "";
   }

   protected InListOperatorNode shallowCopy() throws StandardException {
      InListOperatorNode var1 = new InListOperatorNode(this.leftOperand, this.rightOperandList, this.getContextManager());
      var1.copyFields(this);
      if (this.isOrdered) {
         var1.markAsOrdered();
      }

      if (this.sortDescending) {
         var1.markSortDescending();
      }

      return var1;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      super.preprocess(var1, var2, var3, var4);
      if (this.rightOperandList.size() == 1) {
         BinaryRelationalOperatorNode var14 = new BinaryRelationalOperatorNode(0, this.leftOperand, (ValueNode)this.rightOperandList.elementAt(0), false, this.getContextManager());
         ((BinaryComparisonOperatorNode)var14).bindComparisonOperator();
         return var14;
      } else {
         DataTypeDescriptor var5 = this.getDominantType();
         int var6 = var5.getTypeId().typePrecedence();
         if (this.leftOperand.getTypeServices().getTypeId().typePrecedence() != var6 && !this.rightOperandList.allSamePrecendence(var6)) {
            CastNode var7 = new CastNode(this.leftOperand, var5, this.getContextManager());
            var7.bindCastNodeOnly();
            this.leftOperand = var7;
         }

         if (this.leftOperand instanceof ColumnReference && this.rightOperandList.containsOnlyConstantAndParamNodes()) {
            boolean var15 = this.rightOperandList.containsAllConstantNodes();
            if (var15) {
               DataValueDescriptor var8 = var5.getNull();
               this.rightOperandList.sortInAscendingOrder(var8);
               this.isOrdered = true;
               ValueNode var9 = (ValueNode)this.rightOperandList.elementAt(0);
               ValueNode var10 = (ValueNode)this.rightOperandList.elementAt(this.rightOperandList.size() - 1);
               DataValueDescriptor var11 = ((ConstantNode)var9).getValue();
               DataValueDescriptor var12 = ((ConstantNode)var10).getValue();
               if (var8.equals(var11, var12).equals(true)) {
                  BinaryRelationalOperatorNode var13 = new BinaryRelationalOperatorNode(0, this.leftOperand, var9, false, this.getContextManager());
                  ((BinaryComparisonOperatorNode)var13).bindComparisonOperator();
                  return var13;
               }
            }

            ValueNode var16 = (ValueNode)this.rightOperandList.elementAt(0);
            ParameterNode var17 = new ParameterNode(0, (DataValueDescriptor)null, this.getContextManager());
            DataTypeDescriptor var18 = var16.getTypeServices();
            var17.setType(var18);
            var17.setValueToGenerate(var16);
            BinaryRelationalOperatorNode var19 = new BinaryRelationalOperatorNode(0, this.leftOperand, var17, this, false, this.getContextManager());
            ((BinaryComparisonOperatorNode)var19).bindComparisonOperator();
            return var19;
         } else {
            return this;
         }
      }
   }

   private DataTypeDescriptor getDominantType() {
      DataTypeDescriptor var1 = this.leftOperand.getTypeServices();
      TypeId var2 = var1.getTypeId();
      if (!this.rightOperandList.allSamePrecendence(var2.typePrecedence())) {
         ClassFactory var3 = this.getClassFactory();

         for(ValueNode var5 : this.rightOperandList) {
            var1 = var1.getDominantType(var5.getTypeServices(), var3);
         }
      }

      return var1;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      int var4 = this.rightOperandList.size();
      if (!var1) {
         return this;
      } else {
         ValueNode var6 = this.leftOperand instanceof ColumnReference ? this.leftOperand.getClone() : this.leftOperand;
         BinaryRelationalOperatorNode var2 = new BinaryRelationalOperatorNode(5, var6, (ValueNode)this.rightOperandList.elementAt(0), false, this.getContextManager());
         ((BinaryComparisonOperatorNode)var2).bindComparisonOperator();
         Object var5 = var2;

         for(int var7 = 1; var7 < var4; ++var7) {
            var6 = this.leftOperand instanceof ColumnReference ? this.leftOperand.getClone() : this.leftOperand;
            BinaryRelationalOperatorNode var3 = new BinaryRelationalOperatorNode(5, var6, (ValueNode)this.rightOperandList.elementAt(var7), false, this.getContextManager());
            ((BinaryComparisonOperatorNode)var3).bindComparisonOperator();
            AndNode var8 = new AndNode((ValueNode)var5, var3, this.getContextManager());
            var8.postBindFixup();
            var5 = var8;
         }

         return (ValueNode)var5;
      }
   }

   boolean selfReference(ColumnReference var1) throws StandardException {
      for(ValueNode var3 : this.rightOperandList) {
         if (var3.getTablesReferenced().get(var1.getTableNumber())) {
            return true;
         }
      }

      return false;
   }

   public double selectivity(Optimizable var1) {
      return 0.3;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.rightOperandList.size();
      String var5 = "org.apache.derby.iapi.types.DataValueDescriptor";
      String var6 = "org.apache.derby.iapi.types.DataValueDescriptor";
      String var7 = "org.apache.derby.iapi.types.DataValueDescriptor[]";
      String var4 = this.getTypeCompiler().interfaceName();
      LocalField var8 = this.generateListAsArray(var1, var2);
      this.leftOperand.generateExpression(var1, var2);
      var2.dup();
      var2.upCast(var6);
      var2.getField(var8);
      var2.push(this.isOrdered);
      var2.callMethod((short)185, var5, this.methodName, var4, 3);
   }

   protected LocalField generateListAsArray(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.rightOperandList.size();
      LocalField var4 = var1.newFieldDeclaration(2, "org.apache.derby.iapi.types.DataValueDescriptor[]");
      MethodBuilder var5 = var1.getConstructor();
      var5.pushNewArray("org.apache.derby.iapi.types.DataValueDescriptor", var3);
      var5.setField(var4);
      int var6 = 0;
      MethodBuilder var7 = null;
      MethodBuilder var8 = var5;

      for(int var9 = 0; var9 < var3; ++var9) {
         MethodBuilder var10;
         if (this.rightOperandList.elementAt(var9) instanceof ConstantNode) {
            ++var6;
            if (var8.statementNumHitLimit(1)) {
               MethodBuilder var11 = var1.newGeneratedFun("void", 2);
               var8.pushThis();
               var8.callMethod((short)182, (String)null, var11.getName(), "void", 0);
               if (var8 != var5) {
                  var8.methodReturn();
                  var8.complete();
               }

               var8 = var11;
            }

            var10 = var8;
         } else {
            if (var7 == null) {
               var7 = var1.newGeneratedFun("void", 4);
            }

            var10 = var7;
         }

         var10.getField(var4);
         ((ValueNode)this.rightOperandList.elementAt(var9)).generateExpression(var1, var10);
         var10.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
         var10.setArrayElement(var9);
      }

      if (var8 != var5) {
         var8.methodReturn();
         var8.complete();
      }

      if (var7 != null) {
         var7.methodReturn();
         var7.complete();
         var2.pushThis();
         var2.callMethod((short)182, (String)null, var7.getName(), "void", 0);
      }

      return var4;
   }

   void generateStartStopKey(boolean var1, boolean var2, ExpressionClassBuilder var3, MethodBuilder var4) throws StandardException {
      int var5 = this.leftOperand.getTypeId().getTypeFormatId();
      int var6 = this.leftOperand.getTypeServices().getPrecision();
      int var7 = this.leftOperand.getTypeServices().getScale();
      boolean var8 = this.leftOperand.getTypeServices().isNullable();
      int var9 = this.leftOperand.getTypeServices().getMaximumWidth();
      int var10 = this.leftOperand.getTypeServices().getCollationType();
      int var11 = this.leftOperand.getTypeServices().getCollationDerivation();
      int var12 = this.leftOperand.getTypeId().isUserDefinedTypeId() ? this.leftOperand.getTypeId().getJDBCTypeId() : -1;
      int var13 = this.rightOperandList.size();
      int var16 = 0;
      int var14;
      int var15;
      if (var13 < 5) {
         var14 = 1;
         var15 = (var13 - 1) % 4 + 1;
      } else {
         var14 = (var13 - 5) / 3 + 2;
         var15 = (var13 - 5) % 3 + 1;
      }

      for(int var17 = 0; var17 < var14; ++var17) {
         int var18 = var17 == var14 - 1 ? var15 : (var17 == 0 ? 4 : 3);

         for(int var19 = 0; var19 < var18; ++var19) {
            ValueNode var20 = (ValueNode)this.rightOperandList.elementAt(var16++);
            var20.generateExpression(var3, var4);
            var4.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
         }

         int var21 = var17 < var14 - 1 ? 0 : (var17 == 0 ? 4 - var15 : 3 - var15);

         for(int var22 = 0; var22 < var21; ++var22) {
            var4.pushNull("org.apache.derby.iapi.types.DataValueDescriptor");
         }

         var4.push(var5);
         var4.push(var12);
         var4.push(var6);
         var4.push(var7);
         var4.push(var8);
         var4.push(var9);
         var4.push(var10);
         var4.push(var11);
         String var23;
         if ((!var1 || !var2) && (var1 || var2)) {
            var23 = "maxValue";
         } else {
            var23 = "minValue";
         }

         var4.callMethod((short)184, "org.apache.derby.impl.sql.execute.BaseExpressionActivation", var23, "org.apache.derby.iapi.types.DataValueDescriptor", 12);
      }

   }

   protected void markAsOrdered() {
      this.isOrdered = true;
   }

   protected void markSortDescending() {
      this.sortDescending = true;
   }

   protected boolean isOrdered() {
      return this.isOrdered;
   }

   protected boolean sortDescending() {
      return this.sortDescending;
   }
}
