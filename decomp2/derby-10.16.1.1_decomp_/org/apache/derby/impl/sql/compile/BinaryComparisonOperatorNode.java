package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public abstract class BinaryComparisonOperatorNode extends BinaryOperatorNode {
   private boolean forQueryRewrite;
   private boolean betweenSelectivity;

   BinaryComparisonOperatorNode(ValueNode var1, ValueNode var2, String var3, String var4, boolean var5, ContextManager var6) throws StandardException {
      super(var1, var2, var3, var4, "org.apache.derby.iapi.types.DataValueDescriptor", "org.apache.derby.iapi.types.DataValueDescriptor", var6);
      this.forQueryRewrite = var5;
   }

   void setForQueryRewrite(boolean var1) {
      this.forQueryRewrite = var1;
   }

   boolean getForQueryRewrite() {
      return this.forQueryRewrite;
   }

   void setBetweenSelectivity() {
      this.betweenSelectivity = true;
   }

   boolean getBetweenSelectivity() {
      return this.betweenSelectivity;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      TypeId var4 = this.leftOperand.getTypeId();
      TypeId var5 = this.rightOperand.getTypeId();
      if (!var4.isStringTypeId() && var5.isStringTypeId()) {
         DataTypeDescriptor var7 = this.rightOperand.getTypeServices();
         this.rightOperand = new CastNode(this.rightOperand, new DataTypeDescriptor(var5, true, var7.getMaximumWidth()), this.getContextManager());
         ((CastNode)this.rightOperand).bindCastNodeOnly();
      } else if (!var5.isStringTypeId() && var4.isStringTypeId()) {
         DataTypeDescriptor var6 = this.leftOperand.getTypeServices();
         this.leftOperand = new CastNode(this.leftOperand, new DataTypeDescriptor(var4, true, var6.getMaximumWidth()), this.getContextManager());
         ((CastNode)this.leftOperand).bindCastNodeOnly();
      }

      this.bindComparisonOperator();
      return this;
   }

   void bindComparisonOperator() throws StandardException {
      boolean var2 = this.operator.equals("=") || this.operator.equals("<>");
      boolean var3 = this.leftOperand.getTypeServices().comparable(this.rightOperand.getTypeServices(), var2, this.getClassFactory());
      if (!var3 && !this.forQueryRewrite) {
         throw StandardException.newException("42818", new Object[]{this.leftOperand.getTypeServices().getSQLTypeNameWithCollation(), this.rightOperand.getTypeServices().getSQLTypeNameWithCollation()});
      } else {
         boolean var1 = this.leftOperand.getTypeServices().isNullable() || this.rightOperand.getTypeServices().isNullable();
         this.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, var1));
      }
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.leftOperand = this.leftOperand.preprocess(var1, var2, var3, var4);
      if (this.rightOperand instanceof SubqueryNode && !((SubqueryNode)this.rightOperand).getPreprocessed()) {
         ((SubqueryNode)this.rightOperand).setParentComparisonOperator(this);
         return this.rightOperand.preprocess(var1, var2, var3, var4);
      } else {
         this.rightOperand = this.rightOperand.preprocess(var1, var2, var3, var4);
         return this;
      }
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      return (ValueNode)(!var1 ? this : this.getNegation(this.leftOperand, this.rightOperand));
   }

   abstract BinaryOperatorNode getNegation(ValueNode var1, ValueNode var2) throws StandardException;

   abstract BinaryOperatorNode getSwappedEquivalent() throws StandardException;

   ValueNode changeToCNF(boolean var1) throws StandardException {
      if (var1 && this.rightOperand instanceof SubqueryNode) {
         this.rightOperand = this.rightOperand.changeToCNF(var1);
      }

      return this;
   }

   ValueNode genSQLJavaSQLTree() throws StandardException {
      TypeId var1 = this.leftOperand.getTypeId();
      if (var1.userType()) {
         if (this.leftOperand.getTypeServices().comparable(this.leftOperand.getTypeServices(), false, this.getClassFactory())) {
            return this;
         }

         this.leftOperand = this.leftOperand.genSQLJavaSQLTree();
      }

      TypeId var2 = this.rightOperand.getTypeId();
      if (var2.userType()) {
         if (this.rightOperand.getTypeServices().comparable(this.rightOperand.getTypeServices(), false, this.getClassFactory())) {
            return this;
         }

         this.rightOperand = this.rightOperand.genSQLJavaSQLTree();
      }

      return this;
   }
}
