package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public abstract class ValueNode extends QueryTreeNode {
   private DataTypeDescriptor dataTypeServices;
   boolean transformed;

   ValueNode(ContextManager var1) {
      super(var1);
   }

   final void setType(TypeId var1, boolean var2, int var3) throws StandardException {
      this.setType(new DataTypeDescriptor(var1, var2, var3));
   }

   final void setType(TypeId var1, int var2, int var3, boolean var4, int var5) throws StandardException {
      this.setType(new DataTypeDescriptor(var1, var2, var3, var4, var5));
   }

   public String toString() {
      return "";
   }

   DataTypeDescriptor getTypeServices() {
      return this.dataTypeServices;
   }

   public void setNullability(boolean var1) throws StandardException {
      this.setType(this.getTypeServices().getNullabilityType(var1));
   }

   public void setCollationInfo(DataTypeDescriptor var1) throws StandardException {
      this.setCollationInfo(var1.getCollationType(), var1.getCollationDerivation());
   }

   public void setCollationInfo(int var1, int var2) throws StandardException {
      this.setType(this.getTypeServices().getCollatedType(var1, var2));
   }

   TypeId getTypeId() throws StandardException {
      DataTypeDescriptor var1 = this.getTypeServices();
      return var1 != null ? var1.getTypeId() : null;
   }

   protected final DataValueFactory getDataValueFactory() {
      return this.getLanguageConnectionContext().getDataValueFactory();
   }

   final TypeCompiler getTypeCompiler() throws StandardException {
      return this.getTypeCompiler(this.getTypeId());
   }

   void setType(DataTypeDescriptor var1) throws StandardException {
      if (var1 != null) {
         var1 = this.bindUserType(var1);
      }

      this.dataTypeServices = var1;
      if (var1 != null) {
         this.createTypeDependency(var1);
      }

   }

   protected final void setCollationUsingCompilationSchema() throws StandardException {
      this.setCollationUsingCompilationSchema(1);
   }

   protected final void setCollationUsingCompilationSchema(int var1) throws StandardException {
      this.setCollationInfo(this.getSchemaDescriptor((String)null, false).getCollationType(), var1);
   }

   ResultColumn getSourceResultColumn() {
      return null;
   }

   void setTransformed() {
      this.transformed = true;
   }

   boolean getTransformed() {
      return this.transformed;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      return this.bindExpression(var1, var2, var3, false);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3, boolean var4) throws StandardException {
      return this;
   }

   ValueNode genSQLJavaSQLTree() throws StandardException {
      ContextManager var1 = this.getContextManager();
      SQLToJavaValueNode var2 = new SQLToJavaValueNode(this, var1);
      JavaToSQLValueNode var3 = new JavaToSQLValueNode(var2, var1);
      DataTypeDescriptor var4;
      if (this.getTypeServices() != null && this.getTypeId().userType()) {
         var4 = this.getTypeServices();
      } else {
         var4 = DataTypeDescriptor.getSQLDataTypeDescriptor(((JavaValueNode)var2).getJavaTypeName());
      }

      ((ValueNode)var3).setType(var4);
      return var3;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      return this;
   }

   ValueNode evaluateConstantExpressions() throws StandardException {
      return this;
   }

   ValueNode eliminateNots(boolean var1) throws StandardException {
      return !var1 ? this : this.genEqualsFalseTree();
   }

   ValueNode genEqualsFalseTree() throws StandardException {
      BooleanConstantNode var2 = new BooleanConstantNode(false, this.getContextManager());
      BinaryRelationalOperatorNode var1 = new BinaryRelationalOperatorNode(0, this, var2, false, this.getContextManager());
      boolean var3 = this.getTypeServices().isNullable();
      var1.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, var3));
      return var1;
   }

   ValueNode genIsNullTree(boolean var1) throws StandardException {
      IsNullNode var2 = new IsNullNode(this, var1, this.getContextManager());
      var2.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, false));
      return var2;
   }

   boolean verifyEliminateNots() {
      return true;
   }

   ValueNode putAndsOnTop() throws StandardException {
      BooleanConstantNode var1 = new BooleanConstantNode(true, this.getContextManager());
      AndNode var2 = new AndNode(this, var1, this.getContextManager());
      var2.postBindFixup();
      return var2;
   }

   boolean verifyPutAndsOnTop() {
      return true;
   }

   ValueNode changeToCNF(boolean var1) throws StandardException {
      return this;
   }

   boolean verifyChangeToCNF() {
      return true;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return true;
   }

   String getSchemaName() throws StandardException {
      return null;
   }

   String getTableName() {
      return null;
   }

   public boolean updatableByCursor() {
      return false;
   }

   String getColumnName() {
      return null;
   }

   JBitSet getTablesReferenced() throws StandardException {
      ReferencedTablesVisitor var1 = new ReferencedTablesVisitor(new JBitSet(0));
      this.accept(var1);
      return var1.getTableMap();
   }

   boolean isCloneable() {
      return false;
   }

   ValueNode getClone() throws StandardException {
      return null;
   }

   void copyFields(ValueNode var1) throws StandardException {
      this.dataTypeServices = var1.getTypeServices();
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      return this;
   }

   boolean isConstantExpression() {
      return false;
   }

   boolean constantExpression(PredicateList var1) {
      return false;
   }

   protected int getOrderableVariantType() throws StandardException {
      return 0;
   }

   public ValueNode checkIsBoolean() throws StandardException {
      ValueNode var1 = this;
      TypeId var2 = this.getTypeId();
      if (var2.userType()) {
         var1 = this.genSQLJavaSQLTree();
         var2 = var1.getTypeId();
      }

      if (!var2.equals(TypeId.BOOLEAN_ID)) {
         throw StandardException.newException("42X19.S.1", new Object[]{var2.getSQLTypeName()});
      } else {
         return var1;
      }
   }

   Object getConstantValueAsObject() throws StandardException {
      return null;
   }

   final void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateExpression(var1, var2);
   }

   public double selectivity(Optimizable var1) throws StandardException {
      return this.transformed ? (double)1.0F : (double)0.5F;
   }

   void checkTopPredicatesForEqualsConditions(int var1, boolean[] var2, int[] var3, JBitSet[] var4, boolean var5) throws StandardException {
      for(ValueNode var6 = this; var6 instanceof AndNode; var6 = ((AndNode)var6).getRightOperand()) {
         AndNode var7 = (AndNode)var6;
         if (var7.getLeftOperand().isRelationalOperator() && ((RelationalOperator)var7.getLeftOperand()).getOperator() == 1) {
            BinaryRelationalOperatorNode var8 = (BinaryRelationalOperatorNode)var7.getLeftOperand();
            ValueNode var9 = var8.getLeftOperand();
            ValueNode var10 = var8.getRightOperand();
            int var11 = 0;
            if (var5) {
               while(var11 < var3.length && var3[var11] != var1) {
                  ++var11;
               }
            } else {
               var11 = -1;
            }

            if (var9 instanceof ColumnReference && ((ColumnReference)var9).getTableNumber() == var1) {
               this.updateMaps(var4, var2, var3, var1, var11, var10, var9);
            } else if (var10 instanceof ColumnReference && ((ColumnReference)var10).getTableNumber() == var1) {
               this.updateMaps(var4, var2, var3, var1, var11, var9, var10);
            }
         }
      }

   }

   boolean isBooleanTrue() {
      return false;
   }

   boolean isBooleanFalse() {
      return false;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
   }

   private void updateMaps(JBitSet[] var1, boolean[] var2, int[] var3, int var4, int var5, ValueNode var6, ValueNode var7) throws StandardException {
      if (!(var6 instanceof ConstantNode) && !var6.requiresTypeFromContext()) {
         if (var6 instanceof ColumnReference && ((ColumnReference)var6).getTableNumber() != var4) {
            int var11 = ((ColumnReference)var6).getTableNumber();
            int var12 = 0;

            int var13;
            for(var13 = ((ColumnReference)var7).getColumnNumber(); var12 < var3.length && var11 != var3[var12]; ++var12) {
            }

            if (var12 == var3.length) {
               this.setValueCols(var1, var2, var13, var5);
            } else if (var1 != null) {
               var1[var12].set(var13);
            }
         } else {
            JBitSet var8 = var6.getTablesReferenced();
            int var9 = 0;

            int var10;
            for(var10 = ((ColumnReference)var7).getColumnNumber(); var9 < var3.length && !var8.get(var3[var9]); ++var9) {
            }

            if (var9 == var3.length) {
               this.setValueCols(var1, var2, var10, var5);
            } else if (var1 != null && !var8.get(var4)) {
               var1[var9].set(var10);
            }
         }
      } else {
         this.setValueCols(var1, var2, ((ColumnReference)var7).getColumnNumber(), var5);
      }

   }

   private void setValueCols(JBitSet[] var1, boolean[] var2, int var3, int var4) {
      if (var2 != null) {
         var2[var3] = true;
      }

      if (var1 != null) {
         if (var4 == -1) {
            for(int var5 = 0; var5 < var1.length; ++var5) {
               var1[var5].set(var3);
            }
         } else {
            var1[var4].set(var3);
         }
      }

   }

   boolean isRelationalOperator() {
      return false;
   }

   boolean isBinaryEqualsOperatorNode() {
      return false;
   }

   boolean isInListProbeNode() {
      return false;
   }

   boolean optimizableEqualityNode(Optimizable var1, int var2, boolean var3) throws StandardException {
      return false;
   }

   boolean requiresTypeFromContext() {
      return false;
   }

   boolean isParameterNode() {
      return false;
   }

   abstract boolean isEquivalent(ValueNode var1) throws StandardException;

   boolean isSameNodeKind(ValueNode var1) {
      return var1 != null && var1.getClass().equals(this.getClass());
   }
}
