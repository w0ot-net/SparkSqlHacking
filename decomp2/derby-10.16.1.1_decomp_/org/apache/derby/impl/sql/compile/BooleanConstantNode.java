package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class BooleanConstantNode extends ConstantNode {
   boolean booleanValue;
   boolean unknownValue;

   BooleanConstantNode(ContextManager var1) throws StandardException {
      super(TypeId.BOOLEAN_ID, true, 1, var1);
      this.setValue((DataValueDescriptor)null);
   }

   BooleanConstantNode(boolean var1, ContextManager var2) throws StandardException {
      super(TypeId.BOOLEAN_ID, false, 1, var2);
      super.setValue(new SQLBoolean(var1));
      this.booleanValue = var1;
   }

   BooleanConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var1, true, 0, var2);
      this.unknownValue = true;
   }

   Object getConstantValueAsObject() {
      return this.booleanValue ? Boolean.TRUE : Boolean.FALSE;
   }

   String getValueAsString() {
      return this.booleanValue ? "true" : "false";
   }

   boolean isBooleanTrue() {
      return this.booleanValue && !this.unknownValue;
   }

   boolean isBooleanFalse() {
      return !this.booleanValue && !this.unknownValue;
   }

   public double selectivity(Optimizable var1) {
      return this.isBooleanTrue() ? (double)1.0F : (double)0.0F;
   }

   ValueNode eliminateNots(boolean var1) {
      if (!var1) {
         return this;
      } else {
         this.booleanValue = !this.booleanValue;
         super.setValue(new SQLBoolean(this.booleanValue));
         return this;
      }
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) {
      var2.push(this.booleanValue);
   }

   void setValue(DataValueDescriptor var1) {
      super.setValue(var1);
      this.unknownValue = true;

      try {
         if (var1 != null && var1.isNotNull().getBoolean()) {
            this.booleanValue = var1.getBoolean();
            this.unknownValue = false;
         }
      } catch (StandardException var3) {
      }

   }
}
