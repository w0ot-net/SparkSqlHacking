package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class JavaToSQLValueNode extends ValueNode {
   JavaValueNode javaNode;

   JavaToSQLValueNode(JavaValueNode var1, ContextManager var2) {
      super(var2);
      this.javaNode = var1;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.javaNode.preprocess(var1, var2, var3, var4);
      return this;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.javaNode.returnValueToSQLDomain();
      boolean var5 = this.javaNode.generateReceiver(var1, var2);
      if (var5) {
         String var6 = this.getTypeCompiler().interfaceName();
         LocalField var7 = var1.newFieldDeclaration(2, var6);
         var2.conditionalIfNull();
         var2.getField(var7);
         var1.generateNullWithExpress(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType());
         var2.startElseCode();
      }

      TypeId var3 = this.getTypeId();
      TypeCompiler var8 = this.getTypeCompiler();
      String var4 = var8.interfaceName();
      LocalField var9 = var1.newFieldDeclaration(2, var4);
      this.javaNode.generateExpression(var1, var2);
      var1.generateDataValue(var2, var8, this.getTypeServices().getCollationType(), var9);
      if (var5) {
         var2.completeConditional();
      }

   }

   void printSubNodes(int var1) {
   }

   JavaValueNode getJavaValueNode() {
      return this.javaNode;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.javaNode.checkReliability(this);
      this.javaNode = this.javaNode.bindExpression(var1, var2, var3);
      if (this.javaNode instanceof StaticMethodCallNode) {
         AggregateNode var4 = ((StaticMethodCallNode)this.javaNode).getResolvedAggregate();
         if (var4 != null) {
            return var4.bindExpression(var1, var2, var3);
         }
      }

      DataTypeDescriptor var6 = this.javaNode.getDataType();
      if (var6 == null) {
         throw StandardException.newException("X0X57.S", new Object[]{this.javaNode.getJavaTypeName()});
      } else {
         TypeDescriptor var5 = var6.getCatalogType();
         if (!var5.isRowMultiSet() && !var5.getTypeName().equals("java.sql.ResultSet")) {
            this.setType(var6);
            if (var6.getTypeId().isStringTypeId()) {
               this.setCollationInfo(this.javaNode.getCollationType(), 1);
            }

            return this;
         } else {
            throw StandardException.newException("42ZB6", new Object[0]);
         }
      }
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.javaNode = this.javaNode.remapColumnReferencesToExpressions();
      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return this.javaNode.categorize(var1, var2);
   }

   protected int getOrderableVariantType() throws StandardException {
      return this.javaNode.getOrderableVariantType();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.javaNode != null) {
         this.javaNode = (JavaValueNode)this.javaNode.accept(var1);
      }

   }

   boolean isEquivalent(ValueNode var1) {
      return false;
   }
}
