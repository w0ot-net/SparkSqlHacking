package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class CoalesceFunctionNode extends ValueNode {
   String functionName;
   ValueNodeList argumentsList;
   private int firstNonParameterNodeIdx = -1;

   CoalesceFunctionNode(String var1, ValueNodeList var2, ContextManager var3) {
      super(var3);
      this.functionName = var1;
      this.argumentsList = var2;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.argumentsList.bindExpression(var1, var2, var3);
      if (this.argumentsList.size() < 2) {
         throw StandardException.newException("42605", new Object[]{this.functionName});
      } else if (this.argumentsList.containsAllParameterNodes()) {
         throw StandardException.newException("42610", new Object[0]);
      } else {
         int var4 = this.argumentsList.size();

         for(int var5 = 0; var5 < var4; ++var5) {
            if (!((ValueNode)this.argumentsList.elementAt(var5)).requiresTypeFromContext()) {
               this.firstNonParameterNodeIdx = var5;
               break;
            }
         }

         for(ValueNode var6 : this.argumentsList) {
            if (!var6.requiresTypeFromContext()) {
               this.argumentsList.compatible(var6);
            }
         }

         this.setType(this.argumentsList.getDominantTypeServices());

         for(ValueNode var9 : this.argumentsList) {
            if (var9.requiresTypeFromContext()) {
               var9.setType(this.getTypeServices());
            }
         }

         return this;
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = this.argumentsList.size();
      String var4 = "org.apache.derby.iapi.types.DataValueDescriptor";
      String var5 = "org.apache.derby.iapi.types.DataValueDescriptor[]";
      LocalField var6 = var1.newFieldDeclaration(2, var5);
      MethodBuilder var7 = var1.getConstructor();
      var7.pushNewArray("org.apache.derby.iapi.types.DataValueDescriptor", var3);
      var7.setField(var6);
      int var8 = 0;
      MethodBuilder var9 = null;
      MethodBuilder var10 = var7;

      for(int var11 = 0; var11 < var3; ++var11) {
         MethodBuilder var12;
         if (this.argumentsList.elementAt(var11) instanceof ConstantNode) {
            ++var8;
            if (var10.statementNumHitLimit(1)) {
               MethodBuilder var13 = var1.newGeneratedFun("void", 2);
               var10.pushThis();
               var10.callMethod((short)182, (String)null, var13.getName(), "void", 0);
               if (var10 != var7) {
                  var10.methodReturn();
                  var10.complete();
               }

               var10 = var13;
            }

            var12 = var10;
         } else {
            if (var9 == null) {
               var9 = var1.newGeneratedFun("void", 4);
            }

            var12 = var9;
         }

         var12.getField(var6);
         ((ValueNode)this.argumentsList.elementAt(var11)).generateExpression(var1, var12);
         var12.upCast(var4);
         var12.setArrayElement(var11);
      }

      if (var10 != var7) {
         var10.methodReturn();
         var10.complete();
      }

      if (var9 != null) {
         var9.methodReturn();
         var9.complete();
         var2.pushThis();
         var2.callMethod((short)182, (String)null, var9.getName(), "void", 0);
      }

      ((ValueNode)this.argumentsList.elementAt(this.firstNonParameterNodeIdx)).generateExpression(var1, var2);
      var2.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
      var2.getField(var6);
      LocalField var14 = var1.newFieldDeclaration(2, var4);
      var1.generateNull(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType());
      var2.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
      var2.putField(var14);
      var2.callMethod((short)185, var4, "coalesce", var4, 2);
      if (this.getTypeId().variableLength()) {
         boolean var15 = this.getTypeId().isNumericTypeId();
         var2.dup();
         var2.push(var15 ? this.getTypeServices().getPrecision() : this.getTypeServices().getMaximumWidth());
         var2.push(this.getTypeServices().getScale());
         var2.push(true);
         var2.callMethod((short)185, "org.apache.derby.iapi.types.VariableSizeDataValue", "setWidth", "void", 3);
      }

   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         CoalesceFunctionNode var2 = (CoalesceFunctionNode)var1;
         return this.argumentsList.isEquivalent(var2.argumentsList);
      }
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      this.argumentsList = (ValueNodeList)this.argumentsList.accept(var1);
   }

   public boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return this.argumentsList.categorize(var1, var2);
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.argumentsList.preprocess(var1, var2, var3, var4);
      return this;
   }

   public ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.argumentsList = this.argumentsList.remapColumnReferencesToExpressions();
      return this;
   }
}
