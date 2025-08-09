package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.JSQLType;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class SQLToJavaValueNode extends JavaValueNode {
   ValueNode value;
   LocalField returnsNullOnNullState;

   SQLToJavaValueNode(ValueNode var1, ContextManager var2) {
      super(var2);
      this.value = var1;
   }

   void printSubNodes(int var1) {
   }

   String getJavaTypeName() throws StandardException {
      JSQLType var1 = this.getJSQLType();
      return var1 == null ? "" : mapToTypeID(var1).getCorrespondingJavaTypeName();
   }

   String getPrimitiveTypeName() throws StandardException {
      JSQLType var1 = this.getJSQLType();
      return var1 == null ? "" : this.getTypeCompiler(mapToTypeID(var1)).getCorrespondingPrimitiveTypeName();
   }

   JSQLType getJSQLType() throws StandardException {
      if (this.jsqlType == null) {
         if (this.value.requiresTypeFromContext()) {
            ParameterNode var1;
            if (this.value instanceof UnaryOperatorNode) {
               var1 = ((UnaryOperatorNode)this.value).getParameterOperand();
            } else {
               var1 = (ParameterNode)this.value;
            }

            this.jsqlType = var1.getJSQLType();
         } else {
            DataTypeDescriptor var2 = this.value.getTypeServices();
            if (var2 != null) {
               this.jsqlType = new JSQLType(var2);
            }
         }
      }

      return this.jsqlType;
   }

   JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.value = this.value.bindExpression(var1, var2, var3);
      return this;
   }

   DataTypeDescriptor getDataType() throws StandardException {
      return this.value.getTypeServices();
   }

   JavaValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.value = this.value.remapColumnReferencesToExpressions();
      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return this.value.categorize(var1, var2);
   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.value = this.value.preprocess(var1, var2, var3, var4);
   }

   int getOrderableVariantType() throws StandardException {
      return this.value.getOrderableVariantType();
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateSQLValue(var1, var2);
      this.generateJavaValue(var1, var2);
   }

   private void generateSQLValue(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.value.generateExpression(var1, var2);
   }

   private void generateJavaValue(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (!this.isPrimitiveType() && !this.mustCastToPrimitive()) {
         if (this.returnsNullOnNullState != null) {
            this.generateReturnsNullOnNullCheck(var2);
         }

         var2.callMethod((short)185, "org.apache.derby.iapi.types.DataValueDescriptor", "getObject", "java.lang.Object", 0);
         var2.cast(this.value.getTypeId().getCorrespondingJavaTypeName());
      } else {
         String var3 = this.value.getTypeCompiler().getCorrespondingPrimitiveTypeName();
         String[] var4 = new String[]{this.getSQLValueInterfaceName()};
         MethodBuilder var5 = var1.newGeneratedFun(var3, 2, var4);
         var5.getParameter(0);
         if (this.returnsNullOnNullState != null) {
            this.generateReturnsNullOnNullCheck(var5);
         } else {
            var5.dup();
            var5.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
            var5.push(var3);
            var5.callMethod((short)184, "org.apache.derby.impl.sql.execute.BaseActivation", "nullToPrimitiveTest", "void", 2);
         }

         var5.callMethod((short)185, "org.apache.derby.iapi.types.DataValueDescriptor", this.value.getTypeCompiler().getPrimitiveMethodName(), var3, 0);
         var5.methodReturn();
         var5.complete();
         var2.pushThis();
         var2.swap();
         var2.callMethod((short)182, (String)null, var5.getName(), var3, 1);
      }

   }

   private void generateReturnsNullOnNullCheck(MethodBuilder var1) {
      var1.dup();
      var1.callMethod((short)185, "org.apache.derby.iapi.services.io.Storable", "isNull", "boolean", 0);
      var1.conditionalIf();
      var1.push(true);
      var1.startElseCode();
      var1.getField(this.returnsNullOnNullState);
      var1.completeConditional();
      var1.setField(this.returnsNullOnNullState);
   }

   private String getSQLValueInterfaceName() throws StandardException {
      return this.value.getTypeCompiler().interfaceName();
   }

   ValueNode getSQLValueNode() {
      return this.value;
   }

   Object getConstantValueAsObject() throws StandardException {
      return this.value.getConstantValueAsObject();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.value != null) {
         this.value = (ValueNode)this.value.accept(var1);
      }

   }
}
