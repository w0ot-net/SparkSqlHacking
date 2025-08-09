package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class ConditionalNode extends ValueNode {
   private CachedValueNode caseOperand;
   private ValueNodeList testConditions;
   private ValueNodeList thenElseList;

   ConditionalNode(CachedValueNode var1, ValueNodeList var2, ValueNodeList var3, ContextManager var4) {
      super(var4);
      this.caseOperand = var1;
      this.testConditions = var2;
      this.thenElseList = var3;
   }

   void printSubNodes(int var1) {
   }

   private void recastNullNodes(DataTypeDescriptor var1, FromList var2, SubqueryList var3, List var4) throws StandardException {
      var1 = var1.getNullabilityType(true);

      for(int var5 = 0; var5 < this.thenElseList.size(); ++var5) {
         ValueNode var6 = (ValueNode)this.thenElseList.elementAt(var5);
         if (var6 instanceof UntypedNullConstantNode) {
            CastNode var7 = new CastNode(var6, var1, this.getContextManager());
            var7.bindExpression(var2, var3, var4);
            this.thenElseList.setElementAt(var7, var5);
         }
      }

   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      CompilerContext var4 = this.getCompilerContext();
      int var5 = this.orReliability(16384);
      ValueNodeList var6 = this.bindCaseOperand(var4, var1, var2, var3);
      this.testConditions.bindExpression(var1, var2, var3);
      if (var6 != null) {
         for(ValueNode var8 : var6) {
            var6.comparable(var8);
            this.testConditions.accept(new ReplaceNodeVisitor(var8, this.caseOperand));
         }

         this.caseOperand.setType(var6.getDominantTypeServices());
      }

      this.thenElseList.bindExpression(var1, var2, var3);
      DataTypeDescriptor var14 = this.thenElseList.getTypeServices();
      if (var14 == null) {
         throw StandardException.newException("42X87", new Object[0]);
      } else {
         this.recastNullNodes(var14, var1, var2, var3);
         this.setType(this.thenElseList.getDominantTypeServices());
         this.testConditions.setParameterDescriptor(new DataTypeDescriptor(TypeId.BOOLEAN_ID, true));

         for(ValueNode var9 : this.testConditions) {
            if (!var9.getTypeServices().getTypeId().equals(TypeId.BOOLEAN_ID)) {
               throw StandardException.newException("42X88", new Object[0]);
            }
         }

         this.thenElseList.setParameterDescriptor(this.getTypeServices());
         ClassInspector var16 = this.getClassFactory().getClassInspector();

         for(ValueNode var10 : this.thenElseList) {
            DataTypeDescriptor var11 = var10.getTypeServices();
            String var12 = var11.getTypeId().getCorrespondingJavaTypeName();
            String var13 = this.getTypeId().getCorrespondingJavaTypeName();
            if (!var11.comparable(this.getTypeServices(), false, this.getClassFactory()) && !var16.assignableTo(var12, var13) && !var16.assignableTo(var13, var12)) {
               throw StandardException.newException("42X89", new Object[]{var11.getTypeId().getSQLTypeName(), this.getTypeId().getSQLTypeName()});
            }
         }

         this.setNullability(this.thenElseList.isNullable());
         TypeId var18 = this.getTypeId();

         for(int var19 = 0; var19 < this.thenElseList.size(); ++var19) {
            ValueNode var20 = (ValueNode)this.thenElseList.elementAt(var19);
            if (var20.getTypeId().typePrecedence() != var18.typePrecedence()) {
               ValueNode var21 = new CastNode(var20, this.getTypeServices(), this.getContextManager());
               var21 = var21.bindExpression(var1, var2, var3);
               this.thenElseList.setElementAt(var21, var19);
            }
         }

         var4.setReliability(var5);
         return this;
      }
   }

   private ValueNodeList bindCaseOperand(CompilerContext var1, FromList var2, SubqueryList var3, List var4) throws StandardException {
      ValueNodeList var5 = null;
      if (this.caseOperand != null) {
         int var6 = this.orReliability(22528);
         if (this.caseOperand.requiresTypeFromContext()) {
            var5 = new ValueNodeList(this.getContextManager());
            this.testConditions.accept(new ReplaceCaseOperandVisitor(var5));
         }

         this.caseOperand = (CachedValueNode)this.caseOperand.bindExpression(var2, var3, var4);
         var1.setReliability(var6);
      }

      return var5;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.testConditions.preprocess(var1, var2, var3, var4);
      this.thenElseList.preprocess(var1, var2, var3, var4);
      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      if (var2) {
         return false;
      } else {
         boolean var3 = this.testConditions.categorize(var1, var2);
         var3 = this.thenElseList.categorize(var1, var2) && var3;
         return var3;
      }
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.testConditions = this.testConditions.remapColumnReferencesToExpressions();
      this.thenElseList = this.thenElseList.remapColumnReferencesToExpressions();
      return this;
   }

   boolean isConstantExpression() {
      return this.testConditions.isConstantExpression() && this.thenElseList.isConstantExpression();
   }

   boolean constantExpression(PredicateList var1) {
      return this.testConditions.constantExpression(var1) && this.thenElseList.constantExpression(var1);
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      this.thenElseList.eliminateNots(var1);
      this.testConditions.eliminateNots(false);
      return this;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      for(int var3 = 0; var3 < this.testConditions.size(); ++var3) {
         ((ValueNode)this.testConditions.elementAt(var3)).generateExpression(var1, var2);
         var2.cast("org.apache.derby.iapi.types.BooleanDataValue");
         var2.push(true);
         var2.callMethod((short)185, (String)null, "equals", "boolean", 1);
         var2.conditionalIf();
         ((ValueNode)this.thenElseList.elementAt(var3)).generateExpression(var1, var2);
         var2.startElseCode();
      }

      ((ValueNode)this.thenElseList.elementAt(this.thenElseList.size() - 1)).generateExpression(var1, var2);

      for(int var4 = 0; var4 < this.testConditions.size(); ++var4) {
         var2.completeConditional();
      }

      if (this.caseOperand != null) {
         this.caseOperand.generateClearField(var2);
      }

   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.testConditions != null) {
         this.testConditions = (ValueNodeList)this.testConditions.accept(var1);
      }

      if (this.thenElseList != null) {
         this.thenElseList = (ValueNodeList)this.thenElseList.accept(var1);
      }

   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         ConditionalNode var2 = (ConditionalNode)var1;
         return this.testConditions.isEquivalent(var2.testConditions) && this.thenElseList.isEquivalent(var2.thenElseList);
      }
   }

   private class ReplaceCaseOperandVisitor implements Visitor {
      private final ValueNodeList replacements;

      private ReplaceCaseOperandVisitor(ValueNodeList var2) {
         this.replacements = var2;
      }

      public Visitable visit(Visitable var1) throws StandardException {
         if (var1 == ConditionalNode.this.caseOperand) {
            ParameterNode var2 = new ParameterNode(0, (DataValueDescriptor)null, ConditionalNode.this.getContextManager());
            this.replacements.addElement(var2);
            return var2;
         } else {
            return var1;
         }
      }

      public boolean visitChildrenFirst(Visitable var1) {
         return false;
      }

      public boolean stopTraversal() {
         return false;
      }

      public boolean skipChildren(Visitable var1) throws StandardException {
         return false;
      }
   }
}
