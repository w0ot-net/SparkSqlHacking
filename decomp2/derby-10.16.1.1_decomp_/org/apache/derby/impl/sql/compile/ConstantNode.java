package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

abstract class ConstantNode extends ValueNode {
   DataValueDescriptor value;

   ConstantNode(TypeId var1, boolean var2, int var3, ContextManager var4) throws StandardException {
      super(var4);
      this.setType(var1, var2, var3);
   }

   ConstantNode(ContextManager var1) {
      super(var1);
   }

   void setValue(DataValueDescriptor var1) {
      this.value = var1;
   }

   DataValueDescriptor getValue() {
      return this.value;
   }

   public String toString() {
      return "";
   }

   boolean isCloneable() {
      return true;
   }

   ValueNode getClone() {
      return this;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      return this;
   }

   boolean isConstantExpression() {
      return true;
   }

   boolean constantExpression(PredicateList var1) {
      return true;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.isNull()) {
         var1.generateNull(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType());
      } else {
         this.generateConstant(var1, var2);
         var1.generateDataValue(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType(), (LocalField)null);
      }

   }

   abstract void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException;

   boolean isNull() {
      return this.value == null || this.value.isNull();
   }

   protected int getOrderableVariantType() {
      return 3;
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         ConstantNode var2 = (ConstantNode)var1;
         return var2.getValue() == null && this.getValue() == null || var2.getValue() != null && var2.getValue().compare(this.getValue()) == 0;
      }
   }
}
