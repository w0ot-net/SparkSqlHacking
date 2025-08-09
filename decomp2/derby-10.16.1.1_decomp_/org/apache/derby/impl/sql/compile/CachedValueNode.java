package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class CachedValueNode extends ValueNode {
   private ValueNode value;
   private LocalField field;

   CachedValueNode(ValueNode var1) {
      super(var1.getContextManager());
      this.value = var1;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.field == null) {
         this.field = var1.newFieldDeclaration(2, "org.apache.derby.iapi.types.DataValueDescriptor");
         this.value.generateExpression(var1, var2);
         var2.putField(this.field);
      } else {
         var2.getField(this.field);
      }

   }

   void generateClearField(MethodBuilder var1) {
      if (this.field != null) {
         var1.pushNull("org.apache.derby.iapi.types.DataValueDescriptor");
         var1.setField(this.field);
      }

   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.value = this.value.bindExpression(var1, var2, var3);
      return this;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.value = this.value.preprocess(var1, var2, var3, var4);
      return this;
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (var1 instanceof CachedValueNode var2) {
         return this.value.isEquivalent(var2.value);
      } else {
         return false;
      }
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.value != null) {
         this.value = (ValueNode)this.value.accept(var1);
      }

   }

   DataTypeDescriptor getTypeServices() {
      return this.value.getTypeServices();
   }

   void setType(DataTypeDescriptor var1) throws StandardException {
      this.value.setType(var1);
   }

   boolean requiresTypeFromContext() {
      return this.value.requiresTypeFromContext();
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.value = this.value.remapColumnReferencesToExpressions();
      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return this.value.categorize(var1, var2);
   }
}
