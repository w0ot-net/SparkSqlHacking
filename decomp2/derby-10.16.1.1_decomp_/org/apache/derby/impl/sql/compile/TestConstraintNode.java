package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class TestConstraintNode extends UnaryLogicalOperatorNode {
   private final String sqlState;
   private final String tableName;
   private final UUID cid;
   private final boolean deferrable;
   private final String constraintName;

   TestConstraintNode(ValueNode var1, String var2, String var3, ConstraintDescriptor var4, ContextManager var5) throws StandardException {
      super(var1, var4.deferrable() ? "throwExceptionIfImmediateAndFalse" : "throwExceptionIfFalse", var5);
      this.sqlState = var2;
      this.tableName = var3;
      this.cid = var4.getUUID();
      this.deferrable = var4.deferrable();
      this.constraintName = var4.getConstraintName();
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      if (!this.operand.getTypeServices().getTypeId().isBooleanTypeId()) {
         this.operand = new CastNode(this.operand, new DataTypeDescriptor(TypeId.BOOLEAN_ID, true), this.getContextManager());
         ((CastNode)this.operand).bindCastNodeOnly();
      }

      this.setFullTypeInfo();
      return this;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.operand.generateExpression(var1, var2);
      var2.push(this.sqlState);
      var2.push(this.tableName);
      var2.push(this.constraintName);
      if (this.deferrable) {
         var1.pushThisAsActivation(var2);
         var2.push(var1.addItem(this.cid));
         var2.callMethod((short)185, "org.apache.derby.iapi.types.BooleanDataValue", "throwExceptionIfImmediateAndFalse", "org.apache.derby.iapi.types.BooleanDataValue", 5);
      } else {
         var2.callMethod((short)185, "org.apache.derby.iapi.types.BooleanDataValue", "throwExceptionIfFalse", "org.apache.derby.iapi.types.BooleanDataValue", 3);
      }

   }
}
