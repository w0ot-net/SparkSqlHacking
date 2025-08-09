package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class OrderByColumn extends OrderedColumn {
   private ResultColumn resultCol;
   private boolean ascending = true;
   private boolean nullsOrderedLow = false;
   private ValueNode expression;
   private OrderByList list;
   private int addedColumnOffset = -1;

   OrderByColumn(ValueNode var1, ContextManager var2) {
      super(var2);
      this.expression = var1;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   void setDescending() {
      this.ascending = false;
   }

   boolean isAscending() {
      return this.ascending;
   }

   void setNullsOrderedLow() {
      this.nullsOrderedLow = true;
   }

   boolean isNullsOrderedLow() {
      return this.nullsOrderedLow;
   }

   ResultColumn getResultColumn() {
      return this.resultCol;
   }

   ValueNode getNonRedundantExpression() {
      ResultColumn var1;
      for(var1 = this.resultCol; var1.isRedundant(); var1 = var3.getSource()) {
         ValueNode var2 = var1.getExpression();
         if (var2 instanceof ColumnReference var3) {
            ;
         }
      }

      return var1.getExpression();
   }

   void bindOrderByColumn(ResultSetNode var1, OrderByList var2) throws StandardException {
      this.list = var2;
      if (this.expression instanceof ColumnReference) {
         ColumnReference var3 = (ColumnReference)this.expression;
         this.resultCol = this.resolveColumnReference(var1, var3);
         this.columnPosition = this.resultCol.getColumnPosition();
         if (this.addedColumnOffset >= 0 && var1 instanceof SelectNode && ((SelectNode)var1).hasDistinct()) {
            throw StandardException.newException("42879", new Object[]{var3.getColumnName()});
         }
      } else if (isReferedColByNum(this.expression)) {
         ResultColumnList var8 = var1.getResultColumns();
         this.columnPosition = (Integer)this.expression.getConstantValueAsObject();
         this.resultCol = var8.getOrderByColumn(this.columnPosition);
         if (this.resultCol == null || this.resultCol.getColumnPosition() > var8.visibleSize()) {
            throw StandardException.newException("42X77", new Object[]{String.valueOf(this.columnPosition)});
         }
      } else {
         if (this.list.isTableValueCtorOrdering()) {
            throw StandardException.newException("4287B", new Object[0]);
         }

         if (this.addedColumnOffset >= 0 && var1 instanceof SelectNode && ((SelectNode)var1).hasDistinct() && !this.expressionMatch(var1)) {
            CollectNodesVisitor var9 = new CollectNodesVisitor(ColumnReference.class);
            this.expression.accept(var9);

            for(ColumnReference var5 : var9.getList()) {
               String var6 = var5.getColumnName();
               boolean var7 = this.columnMatchFound(var1, var5);
               if (!var7) {
                  throw StandardException.newException("42879", new Object[]{var6});
               }
            }
         }

         this.resolveAddedColumn(var1);
         if (this.resultCol == null) {
            throw StandardException.newException("42878", new Object[0]);
         }
      }

      this.resultCol.verifyOrderable();
   }

   private boolean expressionMatch(ResultSetNode var1) throws StandardException {
      ResultColumnList var2 = var1.getResultColumns();

      for(int var3 = 1; var3 <= var2.visibleSize(); ++var3) {
         if (var2.getResultColumn(var3).isEquivalent(this.resultCol)) {
            return true;
         }
      }

      return false;
   }

   private boolean columnMatchFound(ResultSetNode var1, ColumnReference var2) throws StandardException {
      ResultColumnList var3 = var1.getResultColumns();

      for(int var4 = 1; var4 <= var3.visibleSize(); ++var4) {
         ValueNode var5 = var3.getResultColumn(var4).getExpression();
         if (var5 instanceof ColumnReference var6) {
            if (var2.isEquivalent(var6)) {
               return true;
            }
         }
      }

      return false;
   }

   private void resolveAddedColumn(ResultSetNode var1) {
      ResultColumnList var2 = var1.getResultColumns();
      this.columnPosition = var2.visibleSize() + this.addedColumnOffset + 1;
      this.resultCol = var2.getResultColumn(this.columnPosition);
   }

   void pullUpOrderByColumn(ResultSetNode var1) throws StandardException {
      ResultColumnList var2 = var1.getResultColumns();
      if (this.expression instanceof ColumnReference) {
         ColumnReference var3 = (ColumnReference)this.expression;
         this.resultCol = var2.findResultColumnForOrderBy(var3.getColumnName(), var3.getQualifiedTableName());
         if (this.resultCol == null) {
            this.resultCol = new ResultColumn(var3.getColumnName(), var3, this.getContextManager());
            var2.addResultColumn(this.resultCol);
            this.addedColumnOffset = var2.getOrderBySelect();
            var2.incOrderBySelect();
         }
      } else if (!isReferedColByNum(this.expression)) {
         this.resultCol = new ResultColumn((String)null, this.expression, this.getContextManager());
         var2.addResultColumn(this.resultCol);
         this.addedColumnOffset = var2.getOrderBySelect();
         var2.incOrderBySelect();
      }

   }

   void resetToSourceRC() {
      this.resultCol = this.resultCol.getExpression().getSourceResultColumn();
   }

   boolean constantColumn(PredicateList var1) {
      ValueNode var2 = this.resultCol.getExpression();
      return var2.constantExpression(var1);
   }

   void remapColumnReferencesToExpressions() throws StandardException {
      this.resultCol.setExpression(this.resultCol.getExpression().remapColumnReferencesToExpressions());
   }

   private static boolean isReferedColByNum(ValueNode var0) throws StandardException {
      return var0 instanceof NumericConstantNode && var0.getConstantValueAsObject() instanceof Integer;
   }

   private ResultColumn resolveColumnReference(ResultSetNode var1, ColumnReference var2) throws StandardException {
      int var3 = -1;
      if (var1 instanceof SetOperatorNode && var2.getTableName() != null) {
         String var8 = var2.getSQLColumnName();
         throw StandardException.newException("42877", new Object[]{var8});
      } else {
         if (var2.getQualifiedTableName() != null) {
            TableName var4 = var2.getQualifiedTableName();
            FromTable var5 = var1.getFromTableByName(var4.getTableName(), var4.hasSchema() ? var4.getSchemaName() : null, true);
            if (var5 == null) {
               var5 = var1.getFromTableByName(var4.getTableName(), var4.hasSchema() ? var4.getSchemaName() : null, false);
               if (var5 == null) {
                  String var10 = var2.getQualifiedTableName().toString();
                  throw StandardException.newException("42X10", new Object[]{var10});
               }
            }

            if (var1 instanceof SetOperatorNode) {
               var3 = ((FromTable)var1).getTableNumber();
            } else {
               var3 = var5.getTableNumber();
            }
         }

         ResultColumnList var7 = var1.getResultColumns();
         ResultColumn var9 = var7.getOrderByColumnToBind(var2.getColumnName(), var2.getQualifiedTableName(), var3, this);
         if (var9 == null && this.addedColumnOffset >= 0) {
            this.resolveAddedColumn(var1);
         }

         if (var9 != null && !var9.isNameGenerated()) {
            return var9;
         } else {
            String var6 = var2.getColumnName();
            throw StandardException.newException("42X78", new Object[]{var6});
         }
      }
   }

   void clearAddedColumnOffset() {
      this.list.closeGap(this.addedColumnOffset);
      this.addedColumnOffset = -1;
   }

   void collapseAddedColumnGap(int var1) {
      if (this.addedColumnOffset > var1) {
         --this.addedColumnOffset;
      }

   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.expression != null) {
         this.expression = (ValueNode)this.expression.accept(var1);
      }

   }

   ValueNode getExpression() {
      return this.expression;
   }
}
