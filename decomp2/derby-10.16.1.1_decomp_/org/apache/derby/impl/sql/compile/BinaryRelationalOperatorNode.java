package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class BinaryRelationalOperatorNode extends BinaryComparisonOperatorNode implements RelationalOperator {
   static final int K_EQUALS = 0;
   static final int K_GREATER_EQUALS = 1;
   static final int K_GREATER_THAN = 2;
   static final int K_LESS_EQUALS = 3;
   static final int K_LESS_THAN = 4;
   static final int K_NOT_EQUALS = 5;
   final int kind;
   private int relOpType;
   private BaseTableNumbersVisitor btnVis;
   JBitSet optBaseTables;
   JBitSet valNodeBaseTables;
   private InListOperatorNode inListProbeSource = null;
   protected static final int LEFT = -1;
   protected static final int NEITHER = 0;
   protected static final int RIGHT = 1;

   BinaryRelationalOperatorNode(int var1, ValueNode var2, ValueNode var3, boolean var4, ContextManager var5) throws StandardException {
      super(var2, var3, getOperatorName(var1), getMethodName(var1), var4, var5);
      this.kind = var1;
      this.constructorMinion();
   }

   BinaryRelationalOperatorNode(int var1, ValueNode var2, ValueNode var3, InListOperatorNode var4, boolean var5, ContextManager var6) throws StandardException {
      super(var2, var3, getOperatorName(var1), getMethodName(var1), var5, var6);
      this.kind = var1;
      this.constructorMinion();
      this.inListProbeSource = var4;
   }

   private void constructorMinion() {
      this.relOpType = this.getRelOpType(this.kind);
      this.btnVis = null;
   }

   private static String getMethodName(int var0) {
      String var1 = "";
      switch (var0) {
         case 0 -> var1 = "equals";
         case 1 -> var1 = "greaterOrEquals";
         case 2 -> var1 = "greaterThan";
         case 3 -> var1 = "lessOrEquals";
         case 4 -> var1 = "lessThan";
         case 5 -> var1 = "notEquals";
      }

      return var1;
   }

   private static String getOperatorName(int var0) {
      String var1 = "";
      switch (var0) {
         case 0 -> var1 = "=";
         case 1 -> var1 = ">=";
         case 2 -> var1 = ">";
         case 3 -> var1 = "<=";
         case 4 -> var1 = "<";
         case 5 -> var1 = "<>";
      }

      return var1;
   }

   private int getRelOpType(int var1) {
      switch (var1) {
         case 0 -> {
            return 1;
         }
         case 1 -> {
            return 4;
         }
         case 2 -> {
            return 3;
         }
         case 3 -> {
            return 6;
         }
         case 4 -> {
            return 5;
         }
         case 5 -> {
            return 2;
         }
         default -> {
            return 0;
         }
      }
   }

   protected InListOperatorNode getInListOp() {
      if (this.inListProbeSource != null) {
         this.inListProbeSource.setLeftOperand(this.leftOperand);
      }

      return this.inListProbeSource;
   }

   public ColumnReference getColumnOperand(Optimizable var1, int var2) {
      FromTable var3 = (FromTable)var1;
      boolean var5 = true;
      if (this.leftOperand instanceof ColumnReference) {
         ColumnReference var4 = (ColumnReference)this.leftOperand;
         if (this.valNodeReferencesOptTable(var4, var3, false, var5) && var4.getSource().getColumnPosition() == var2) {
            return var4;
         }

         var5 = false;
      }

      if (this.rightOperand instanceof ColumnReference) {
         ColumnReference var6 = (ColumnReference)this.rightOperand;
         if (this.valNodeReferencesOptTable(var6, var3, false, var5) && var6.getSource().getColumnPosition() == var2) {
            return var6;
         }
      }

      return null;
   }

   public ColumnReference getColumnOperand(Optimizable var1) {
      boolean var3 = true;
      if (this.leftOperand instanceof ColumnReference) {
         ColumnReference var2 = (ColumnReference)this.leftOperand;
         if (this.valNodeReferencesOptTable(var2, (FromTable)var1, false, var3)) {
            return var2;
         }

         var3 = false;
      }

      if (this.rightOperand instanceof ColumnReference) {
         ColumnReference var4 = (ColumnReference)this.rightOperand;
         if (this.valNodeReferencesOptTable(var4, (FromTable)var1, false, var3)) {
            return var4;
         }
      }

      return null;
   }

   public ValueNode getExpressionOperand(int var1, int var2, Optimizable var3) {
      boolean var5 = true;
      if (this.leftOperand instanceof ColumnReference) {
         ColumnReference var4 = (ColumnReference)this.leftOperand;
         if (this.valNodeReferencesOptTable(var4, var3, false, var5) && var4.getSource().getColumnPosition() == var2) {
            return this.rightOperand;
         }

         var5 = false;
      }

      if (this.rightOperand instanceof ColumnReference) {
         ColumnReference var6 = (ColumnReference)this.rightOperand;
         if (this.valNodeReferencesOptTable(var6, var3, false, var5) && var6.getSource().getColumnPosition() == var2) {
            return this.leftOperand;
         }
      }

      return null;
   }

   public ValueNode getOperand(ColumnReference var1, int var2, boolean var3) {
      this.initBaseTableVisitor(var2, true);

      try {
         this.btnVis.setTableMap(this.optBaseTables);
         var1.accept(this.btnVis);
         this.btnVis.setTableMap(this.valNodeBaseTables);
         if (this.leftOperand instanceof ColumnReference) {
            ColumnReference var4 = (ColumnReference)this.leftOperand;
            var4.accept(this.btnVis);
            this.valNodeBaseTables.and(this.optBaseTables);
            if (this.valNodeBaseTables.getFirstSetBit() != -1 && var4.getSource().getColumnPosition() == var1.getColumnNumber()) {
               if (var3) {
                  return this.rightOperand;
               }

               return this.leftOperand;
            }
         }

         if (this.rightOperand instanceof ColumnReference) {
            this.valNodeBaseTables.clearAll();
            ColumnReference var6 = (ColumnReference)this.rightOperand;
            var6.accept(this.btnVis);
            this.valNodeBaseTables.and(this.optBaseTables);
            if (this.valNodeBaseTables.getFirstSetBit() != -1 && var6.getSource().getColumnPosition() == var1.getColumnNumber()) {
               if (var3) {
                  return this.leftOperand;
               }

               return this.rightOperand;
            }
         }
      } catch (StandardException var5) {
      }

      return null;
   }

   public void generateExpressionOperand(Optimizable var1, int var2, ExpressionClassBuilderInterface var3, MethodBuilder var4) throws StandardException {
      ExpressionClassBuilder var5 = (ExpressionClassBuilder)var3;
      FromBaseTable var6 = (FromBaseTable)var1;
      ValueNode var7 = this.getExpressionOperand(var6.getTableNumber(), var2, var6);
      var7.generateExpression(var5, var4);
   }

   public boolean selfComparison(ColumnReference var1) throws StandardException {
      ValueNode var2;
      if (this.leftOperand == var1) {
         var2 = this.rightOperand;
      } else if (this.rightOperand == var1) {
         var2 = this.leftOperand;
      } else {
         var2 = null;
      }

      JBitSet var3 = var2.getTablesReferenced();
      return var3.get(var1.getTableNumber());
   }

   public boolean usefulStartKey(Optimizable var1) {
      int var2 = this.columnOnOneSide(var1);
      return var2 == 0 ? false : this.usefulStartKey(var2 == -1);
   }

   protected boolean keyColumnOnLeft(Optimizable var1) {
      boolean var3 = false;
      if (this.leftOperand instanceof ColumnReference) {
         ColumnReference var2 = (ColumnReference)this.leftOperand;
         if (this.valNodeReferencesOptTable(var2, (FromTable)var1, false, true)) {
            var3 = true;
         }
      }

      return var3;
   }

   protected int columnOnOneSide(Optimizable var1) {
      boolean var3 = false;
      boolean var4 = true;
      if (this.leftOperand instanceof ColumnReference) {
         ColumnReference var2 = (ColumnReference)this.leftOperand;
         if (this.valNodeReferencesOptTable(var2, (FromTable)var1, false, var4)) {
            return -1;
         }

         var4 = false;
      }

      if (this.rightOperand instanceof ColumnReference) {
         ColumnReference var5 = (ColumnReference)this.rightOperand;
         if (this.valNodeReferencesOptTable(var5, (FromTable)var1, false, var4)) {
            return 1;
         }
      }

      return 0;
   }

   public boolean usefulStopKey(Optimizable var1) {
      int var2 = this.columnOnOneSide(var1);
      return var2 == 0 ? false : this.usefulStopKey(var2 == -1);
   }

   public void generateAbsoluteColumnId(MethodBuilder var1, Optimizable var2) {
      int var3 = this.getAbsoluteColumnPosition(var2);
      var1.push(var3);
   }

   public void generateRelativeColumnId(MethodBuilder var1, Optimizable var2) {
      int var3 = this.getAbsoluteColumnPosition(var2);
      var3 = var2.convertAbsoluteToRelativeColumnPosition(var3);
      var1.push(var3);
   }

   private int getAbsoluteColumnPosition(Optimizable var1) {
      ColumnReference var2;
      if (this.keyColumnOnLeft(var1)) {
         var2 = (ColumnReference)this.leftOperand;
      } else {
         var2 = (ColumnReference)this.rightOperand;
      }

      ConglomerateDescriptor var3 = var1.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      int var4 = var2.getSource().getColumnPosition();
      if (var3 != null && var3.isIndex()) {
         var4 = var3.getIndexDescriptor().getKeyColumnPosition(var4);
      }

      return var4 - 1;
   }

   public void generateQualMethod(ExpressionClassBuilderInterface var1, MethodBuilder var2, Optimizable var3) throws StandardException {
      ExpressionClassBuilder var4 = (ExpressionClassBuilder)var1;
      MethodBuilder var5 = var4.newUserExprFun();
      if (this.keyColumnOnLeft(var3)) {
         this.rightOperand.generateExpression(var4, var5);
      } else {
         this.leftOperand.generateExpression(var4, var5);
      }

      var5.methodReturn();
      var5.complete();
      var4.pushMethodReference(var2, var5);
   }

   public void generateOrderedNulls(MethodBuilder var1) {
      var1.push(false);
   }

   public boolean orderedNulls() {
      return false;
   }

   public boolean isQualifier(Optimizable var1, boolean var2) throws StandardException {
      if (this.isInListProbeNode()) {
         return false;
      } else {
         ValueNode var4 = null;
         boolean var7 = false;
         boolean var8 = true;
         FromTable var3 = (FromTable)var1;
         if (this.leftOperand instanceof ColumnReference) {
            ColumnReference var6 = (ColumnReference)this.leftOperand;
            if (this.valNodeReferencesOptTable(var6, var3, var2, var8)) {
               var4 = this.rightOperand;
               var7 = true;
            }

            var8 = false;
         }

         if (!var7 && this.rightOperand instanceof ColumnReference) {
            ColumnReference var9 = (ColumnReference)this.rightOperand;
            if (this.valNodeReferencesOptTable(var9, var3, var2, var8)) {
               var4 = this.leftOperand;
               var7 = true;
            }
         }

         if (!var7) {
            return false;
         } else {
            return !this.valNodeReferencesOptTable(var4, var3, var2, true);
         }
      }
   }

   public int getOrderableVariantType(Optimizable var1) throws StandardException {
      return this.keyColumnOnLeft(var1) ? this.rightOperand.getOrderableVariantType() : this.leftOperand.getOrderableVariantType();
   }

   public boolean compareWithKnownConstant(Optimizable var1, boolean var2) {
      ValueNode var3 = this.keyColumnOnLeft(var1) ? this.rightOperand : this.leftOperand;
      if (!var2) {
         return var3 instanceof ConstantNode;
      } else {
         return var3 instanceof ConstantNode || var3.requiresTypeFromContext() && ((ParameterNode)var3).getDefaultValue() != null;
      }
   }

   public DataValueDescriptor getCompareValue(Optimizable var1) throws StandardException {
      ValueNode var2 = this.keyColumnOnLeft(var1) ? this.rightOperand : this.leftOperand;
      if (var2 instanceof ConstantNode) {
         return ((ConstantNode)var2).getValue();
      } else if (var2.requiresTypeFromContext()) {
         ParameterNode var3;
         if (var2 instanceof UnaryOperatorNode) {
            var3 = ((UnaryOperatorNode)var2).getParameterOperand();
         } else {
            var3 = (ParameterNode)var2;
         }

         return var3.getDefaultValue();
      } else {
         return null;
      }
   }

   protected double booleanSelectivity(Optimizable var1) throws StandardException {
      TypeId var2 = null;
      double var3 = (double)-1.0F;
      int var5 = this.columnOnOneSide(var1);
      if (var5 == -1) {
         var2 = this.leftOperand.getTypeId();
      } else if (var5 == 1) {
         var2 = this.rightOperand.getTypeId();
      }

      if (var2 != null && (var2.getJDBCTypeId() == -7 || var2.getJDBCTypeId() == 16)) {
         var3 = (double)0.5F;
      }

      return var3;
   }

   String getReceiverInterfaceName() {
      return "org.apache.derby.iapi.types.DataValueDescriptor";
   }

   ValueNode evaluateConstantExpressions() throws StandardException {
      if (this.leftOperand instanceof ConstantNode && this.rightOperand instanceof ConstantNode) {
         ConstantNode var1 = (ConstantNode)this.leftOperand;
         ConstantNode var2 = (ConstantNode)this.rightOperand;
         DataValueDescriptor var3 = var1.getValue();
         DataValueDescriptor var4 = var2.getValue();
         if (!var3.isNull() && !var4.isNull()) {
            int var5 = var3.compare(var4);
            switch (this.relOpType) {
               case 1 -> {
                  return this.newBool(var5 == 0);
               }
               case 2 -> {
                  return this.newBool(var5 != 0);
               }
               case 3 -> {
                  return this.newBool(var5 > 0);
               }
               case 4 -> {
                  return this.newBool(var5 >= 0);
               }
               case 5 -> {
                  return this.newBool(var5 < 0);
               }
               case 6 -> {
                  return this.newBool(var5 <= 0);
               }
            }
         }
      }

      return this;
   }

   private ValueNode newBool(boolean var1) throws StandardException {
      return new BooleanConstantNode(var1, this.getContextManager());
   }

   BinaryOperatorNode getNegation(ValueNode var1, ValueNode var2) throws StandardException {
      BinaryRelationalOperatorNode var3 = new BinaryRelationalOperatorNode(this.getNegationNode(), var1, var2, false, this.getContextManager());
      ((BinaryOperatorNode)var3).setType(this.getTypeServices());
      return var3;
   }

   private int getNegationNode() {
      switch (this.kind) {
         case 0 -> {
            return 5;
         }
         case 1 -> {
            return 4;
         }
         case 2 -> {
            return 3;
         }
         case 3 -> {
            return 2;
         }
         case 4 -> {
            return 1;
         }
         case 5 -> {
            return 0;
         }
         default -> {
            return -1;
         }
      }
   }

   BinaryOperatorNode getSwappedEquivalent() throws StandardException {
      BinaryRelationalOperatorNode var1 = new BinaryRelationalOperatorNode(this.getKindForSwap(), this.rightOperand, this.leftOperand, false, this.getContextManager());
      ((BinaryOperatorNode)var1).setType(this.getTypeServices());
      return var1;
   }

   private int getKindForSwap() {
      switch (this.kind) {
         case 0 -> {
            return 0;
         }
         case 1 -> {
            return 3;
         }
         case 2 -> {
            return 4;
         }
         case 3 -> {
            return 1;
         }
         case 4 -> {
            return 2;
         }
         case 5 -> {
            return 5;
         }
         default -> {
            return -1;
         }
      }
   }

   protected boolean usefulStartKey(boolean var1) {
      switch (this.relOpType) {
         case 1:
            return true;
         case 2:
            return false;
         case 3:
         case 4:
            return var1;
         case 5:
         case 6:
            return !var1;
         default:
            return false;
      }
   }

   protected boolean usefulStopKey(boolean var1) {
      switch (this.relOpType) {
         case 1:
            return true;
         case 2:
            return false;
         case 3:
         case 4:
            return !var1;
         case 5:
         case 6:
            return var1;
         default:
            return false;
      }
   }

   public int getStartOperator(Optimizable var1) {
      switch (this.relOpType) {
         case 1:
         case 4:
         case 6:
            return 1;
         case 2:
            return 0;
         case 3:
         case 5:
            return -1;
         default:
            return 0;
      }
   }

   public int getStopOperator(Optimizable var1) {
      switch (this.relOpType) {
         case 1:
         case 4:
         case 6:
            return -1;
         case 2:
            return 0;
         case 3:
         case 5:
            return 1;
         default:
            return 0;
      }
   }

   public void generateOperator(MethodBuilder var1, Optimizable var2) {
      switch (this.relOpType) {
         case 1:
            var1.push((int)2);
            break;
         case 2:
            var1.push((int)2);
            break;
         case 3:
         case 6:
            var1.push(this.keyColumnOnLeft(var2) ? 3 : 1);
            break;
         case 4:
         case 5:
            var1.push(this.keyColumnOnLeft(var2) ? 1 : 3);
      }

   }

   public void generateNegate(MethodBuilder var1, Optimizable var2) {
      switch (this.relOpType) {
         case 1:
            var1.push(false);
            break;
         case 2:
            var1.push(true);
            break;
         case 3:
         case 4:
            var1.push(this.keyColumnOnLeft(var2));
            break;
         case 5:
         case 6:
            var1.push(!this.keyColumnOnLeft(var2));
      }

   }

   public int getOperator() {
      return this.relOpType;
   }

   public double selectivity(Optimizable var1) throws StandardException {
      double var2 = this.booleanSelectivity(var1);
      if (var2 >= (double)0.0F) {
         return var2;
      } else {
         switch (this.relOpType) {
            case 1:
               return 0.1;
            case 2:
            case 4:
            case 5:
            case 6:
               if (this.getBetweenSelectivity()) {
                  return (double)0.5F;
               }
            case 3:
               return 0.33;
            default:
               return (double)0.0F;
         }
      }
   }

   public RelationalOperator getTransitiveSearchClause(ColumnReference var1) throws StandardException {
      return new BinaryRelationalOperatorNode(this.kind, var1, this.rightOperand, false, this.getContextManager());
   }

   public boolean equalsComparisonWithConstantExpression(Optimizable var1) {
      if (this.relOpType != 1) {
         return false;
      } else {
         boolean var2 = false;
         Object var3 = null;
         int var4 = this.columnOnOneSide(var1);
         if (var4 == -1) {
            var2 = this.rightOperand.isConstantExpression();
         } else if (var4 == 1) {
            var2 = this.leftOperand.isConstantExpression();
         }

         return var2;
      }
   }

   boolean isRelationalOperator() {
      return !this.isInListProbeNode();
   }

   boolean isBinaryEqualsOperatorNode() {
      return !this.isInListProbeNode() && this.relOpType == 1;
   }

   boolean isInListProbeNode() {
      return this.inListProbeSource != null;
   }

   boolean optimizableEqualityNode(Optimizable var1, int var2, boolean var3) throws StandardException {
      if (this.relOpType != 1) {
         return false;
      } else if (this.isInListProbeNode()) {
         return false;
      } else {
         ColumnReference var4 = this.getColumnOperand(var1, var2);
         if (var4 == null) {
            return false;
         } else if (this.selfComparison(var4)) {
            return false;
         } else {
            return !this.implicitVarcharComparison();
         }
      }
   }

   private boolean implicitVarcharComparison() throws StandardException {
      TypeId var1 = this.leftOperand.getTypeId();
      TypeId var2 = this.rightOperand.getTypeId();
      if (var1.isStringTypeId() && !var2.isStringTypeId()) {
         return true;
      } else {
         return var2.isStringTypeId() && !var1.isStringTypeId();
      }
   }

   ValueNode genSQLJavaSQLTree() throws StandardException {
      return (ValueNode)(this.relOpType == 1 ? this : super.genSQLJavaSQLTree());
   }

   ValueNode getScopedOperand(int var1, JBitSet var2, ResultSetNode var3, int[] var4) throws StandardException {
      ColumnReference var6 = var1 == -1 ? (ColumnReference)this.leftOperand : (ColumnReference)this.rightOperand;
      JBitSet var7 = new JBitSet(var2.size());
      BaseTableNumbersVisitor var8 = new BaseTableNumbersVisitor(var7);
      var6.accept(var8);
      if (!var2.contains(var7)) {
         return (ColumnReference)var6.getClone();
      } else {
         ResultColumn var5;
         if (var4[0] == -1) {
            int[] var9 = new int[]{-1};
            ResultSetNode var10 = var6.getSourceResultSet(var9);
            var5 = var3.getResultColumns().getResultColumn(var9[0], var10, var4);
         } else {
            var5 = var3.getResultColumns().getResultColumn(var4[0]);
         }

         if (var5.getExpression() instanceof ColumnReference) {
            ColumnReference var11 = (ColumnReference)((ColumnReference)var5.getExpression()).getClone();
            var11.markAsScoped();
            return var11;
         } else {
            return var5.getExpression();
         }
      }
   }

   private boolean valNodeReferencesOptTable(ValueNode var1, Optimizable var2, boolean var3, boolean var4) {
      this.initBaseTableVisitor(var2.getReferencedTableMap().size(), var4);
      boolean var5 = false;

      try {
         if (var4) {
            this.buildTableNumList(var2, var3);
         }

         this.btnVis.setTableMap(this.valNodeBaseTables);
         var1.accept(this.btnVis);
         this.valNodeBaseTables.and(this.optBaseTables);
         var5 = this.valNodeBaseTables.getFirstSetBit() != -1;
      } catch (StandardException var7) {
      }

      return var5;
   }

   private void initBaseTableVisitor(int var1, boolean var2) {
      if (this.valNodeBaseTables == null) {
         this.valNodeBaseTables = new JBitSet(var1);
      } else {
         this.valNodeBaseTables.clearAll();
      }

      if (var2) {
         if (this.optBaseTables == null) {
            this.optBaseTables = new JBitSet(var1);
         } else {
            this.optBaseTables.clearAll();
         }
      }

      if (this.btnVis == null) {
         this.btnVis = new BaseTableNumbersVisitor(this.valNodeBaseTables);
      }

   }

   private void buildTableNumList(Optimizable var1, boolean var2) throws StandardException {
      if (var1.getTableNumber() >= 0) {
         this.optBaseTables.set(var1.getTableNumber());
      }

      if (!var2) {
         this.optBaseTables.or(var1.getReferencedTableMap());
         this.btnVis.setTableMap(this.optBaseTables);
         var1.accept(this.btnVis);
      }
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((BinaryRelationalOperatorNode)var1).kind == this.kind;
   }
}
