package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class IsNullNode extends UnaryComparisonOperatorNode implements RelationalOperator {
   private DataValueDescriptor nullValue;
   private boolean notNull;

   IsNullNode(ValueNode var1, boolean var2, ContextManager var3) throws StandardException {
      super(var1, var3);
      this.notNull = var2;
      this.updateOperatorDetails();
   }

   private void updateOperatorDetails() {
      this.setOperator(this.notNull ? "is not null" : "is null");
      this.setMethodName(this.notNull ? "isNotNull" : "isNullOp");
   }

   UnaryOperatorNode getNegation(ValueNode var1) throws StandardException {
      this.notNull = !this.notNull;
      this.updateOperatorDetails();
      return this;
   }

   void bindParameter() throws StandardException {
      this.operand.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), true));
   }

   public boolean usefulStartKey(Optimizable var1) {
      return this.isNullNode();
   }

   public boolean usefulStopKey(Optimizable var1) {
      return this.isNullNode();
   }

   public int getStartOperator(Optimizable var1) {
      return 1;
   }

   public int getStopOperator(Optimizable var1) {
      return -1;
   }

   public void generateOperator(MethodBuilder var1, Optimizable var2) {
      var1.push((int)2);
   }

   public void generateNegate(MethodBuilder var1, Optimizable var2) {
      var1.push(this.notNull);
   }

   public int getOperator() {
      return this.notNull ? 8 : 7;
   }

   public boolean compareWithKnownConstant(Optimizable var1, boolean var2) {
      return true;
   }

   public DataValueDescriptor getCompareValue(Optimizable var1) throws StandardException {
      if (this.nullValue == null) {
         this.nullValue = this.operand.getTypeServices().getNull();
      }

      return this.nullValue;
   }

   public boolean equalsComparisonWithConstantExpression(Optimizable var1) {
      if (this.notNull) {
         return false;
      } else {
         boolean var2 = false;
         if (this.operand instanceof ColumnReference) {
            int var3 = ((ColumnReference)this.operand).getTableNumber();
            if (var1.hasTableNumber() && var1.getTableNumber() == var3) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   public RelationalOperator getTransitiveSearchClause(ColumnReference var1) throws StandardException {
      return new IsNullNode(var1, this.notNull, this.getContextManager());
   }

   String getReceiverInterfaceName() {
      return "org.apache.derby.iapi.types.DataValueDescriptor";
   }

   public double selectivity(Optimizable var1) {
      return this.notNull ? 0.9 : 0.1;
   }

   boolean isNullNode() {
      return !this.notNull;
   }

   boolean isRelationalOperator() {
      return true;
   }

   boolean optimizableEqualityNode(Optimizable var1, int var2, boolean var3) {
      if (this.isNullNode() && var3) {
         ColumnReference var4 = this.getColumnOperand(var1, var2);
         return var4 != null;
      } else {
         return false;
      }
   }
}
