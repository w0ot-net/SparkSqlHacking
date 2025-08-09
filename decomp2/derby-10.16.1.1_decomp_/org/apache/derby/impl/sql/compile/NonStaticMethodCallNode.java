package org.apache.derby.impl.sql.compile;

import java.lang.reflect.Modifier;
import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class NonStaticMethodCallNode extends MethodCallNode {
   JavaValueNode receiver;
   private boolean isStatic;

   NonStaticMethodCallNode(String var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var1, var3);
      if (var2 instanceof JavaToSQLValueNode) {
         this.receiver = ((JavaToSQLValueNode)var2).getJavaValueNode();
      } else {
         this.receiver = new SQLToJavaValueNode(var2, this.getContextManager());
      }

   }

   JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (this.receiver instanceof SQLToJavaValueNode) {
         ValueNode var4 = ((SQLToJavaValueNode)this.receiver).getSQLValueNode();
         if (var4.requiresTypeFromContext() && var4.getTypeServices() == null) {
            throw StandardException.newException("42X54", new Object[]{this.methodName});
         }
      }

      this.bindParameters(var1, var2, var3);
      this.receiver = this.receiver.bindExpression(var1, var2, var3);
      String var5 = this.receiver.getJSQLType().getSQLType().getTypeId().getSQLTypeName();
      if (!var5.equals("BLOB") && !var5.equals("CLOB") && !var5.equals("NCLOB")) {
         this.javaClassName = this.receiver.getJavaTypeName();
         if (ClassInspector.primitiveType(this.javaClassName)) {
            throw StandardException.newException("42X52", new Object[]{this.methodName, this.javaClassName});
         } else {
            this.resolveMethodCall(this.javaClassName, false);
            this.isStatic = Modifier.isStatic(this.method.getModifiers());
            return this;
         }
      } else {
         throw StandardException.newException("XJ082.U", new Object[0]);
      }
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      if (var2) {
         return false;
      } else {
         boolean var3 = true;
         var3 = var3 && super.categorize(var1, var2);
         if (this.receiver != null) {
            var3 = var3 && this.receiver.categorize(var1, var2);
         }

         return var3;
      }
   }

   int getOrderableVariantType() throws StandardException {
      int var1 = this.receiver.getOrderableVariantType();
      if (var1 > 1 && this.receiver.getJavaTypeName().equals("org.apache.derby.iapi.db.TriggerExecutionContext")) {
         var1 = 1;
      }

      int var2 = super.getOrderableVariantType();
      return var1 < var2 ? var1 : var2;
   }

   JavaValueNode remapColumnReferencesToExpressions() throws StandardException {
      if (this.receiver != null) {
         this.receiver.remapColumnReferencesToExpressions();
      }

      return super.remapColumnReferencesToExpressions();
   }

   void printSubNodes(int var1) {
   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      super.preprocess(var1, var2, var3, var4);
      this.receiver.preprocess(var1, var2, var3, var4);
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      boolean var3 = false;
      if (!this.valueReturnedToSQLDomain() && !this.returnValueDiscarded() && this.generateReceiver(var1, var2, this.receiver)) {
         var3 = true;
         var2.conditionalIfNull();
         var2.pushNull(this.getJavaTypeName());
         var2.startElseCode();
      }

      Class var4 = this.method.getDeclaringClass();
      short var5;
      if (var4.isInterface()) {
         var5 = 185;
      } else if (this.isStatic) {
         var5 = 184;
      } else {
         var5 = 182;
      }

      this.getReceiverExpression(var1, var2, this.receiver);
      if (this.isStatic) {
         var2.endStatement();
      }

      int var6 = this.generateParameters(var1, var2);
      var2.callMethod(var5, var4.getName(), this.methodName, this.getJavaTypeName(), var6);
      if (var3) {
         var2.completeConditional();
      }

   }

   boolean generateReceiver(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      return this.isStatic ? false : this.generateReceiver(var1, var2, this.receiver);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.receiver != null) {
         this.receiver = (JavaValueNode)this.receiver.accept(var1);
      }

   }
}
