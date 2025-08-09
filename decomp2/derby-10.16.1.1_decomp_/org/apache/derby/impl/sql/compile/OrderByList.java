package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.store.access.SortCostController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class OrderByList extends OrderedColumnList implements RequiredRowOrdering {
   private boolean allAscending = true;
   private boolean alwaysSort;
   private ResultSetNode resultToSort;
   private SortCostController scc;
   private Object[] resultRow;
   private org.apache.derby.iapi.store.access.ColumnOrdering[] columnOrdering;
   private int estimatedRowSize;
   private boolean sortNeeded = true;
   private int resultSetNumber = -1;
   private boolean isTableValueCtorOrdering;

   OrderByList(ResultSetNode var1, ContextManager var2) {
      super(OrderByColumn.class, var2);
      this.isTableValueCtorOrdering = var1 instanceof UnionNode && ((UnionNode)var1).tableConstructor() || var1 instanceof RowResultSetNode;
   }

   void addOrderByColumn(OrderByColumn var1) {
      this.addElement(var1);
      if (!var1.isAscending()) {
         this.allAscending = false;
      }

   }

   boolean allAscending() {
      return this.allAscending;
   }

   OrderByColumn getOrderByColumn(int var1) {
      return (OrderByColumn)this.elementAt(var1);
   }

   void bindOrderByColumns(ResultSetNode var1) throws StandardException {
      this.resultToSort = var1;
      if (this.size() > 1012) {
         throw StandardException.newException("54004", new Object[0]);
      } else {
         for(OrderByColumn var3 : this) {
            var3.bindOrderByColumn(var1, this);
            if (!(var3.getResultColumn().getExpression() instanceof ColumnReference)) {
               this.alwaysSort = true;
            }
         }

      }
   }

   void closeGap(int var1) {
      for(OrderByColumn var3 : this) {
         var3.collapseAddedColumnGap(var1);
      }

   }

   void pullUpOrderByColumns(ResultSetNode var1) throws StandardException {
      this.resultToSort = var1;

      for(OrderByColumn var3 : this) {
         var3.pullUpOrderByColumn(var1);
      }

   }

   boolean isInOrderPrefix(ResultColumnList var1) {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         if (((OrderByColumn)this.elementAt(var3)).getResultColumn() != var1.elementAt(var3)) {
            return false;
         }
      }

      return true;
   }

   void resetToSourceRCs() {
      for(OrderByColumn var2 : this) {
         var2.resetToSourceRC();
      }

   }

   ResultColumnList reorderRCL(ResultColumnList var1) throws StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());

      for(OrderByColumn var4 : this) {
         var2.addElement(var4.getResultColumn());
         var1.removeElement(var4.getResultColumn());
      }

      var2.destructiveAppend(var1);
      var2.resetVirtualColumnIds();
      var2.copyOrderBySelect(var1);
      return var2;
   }

   void removeConstantColumns(PredicateList var1) {
      for(int var2 = this.size() - 1; var2 >= 0; --var2) {
         if (((OrderByColumn)this.elementAt(var2)).constantColumn(var1)) {
            this.removeElementAt(var2);
         }
      }

   }

   void removeDupColumns() {
      for(int var1 = this.size() - 1; var1 > 0; --var1) {
         OrderByColumn var2 = (OrderByColumn)this.elementAt(var1);
         int var3 = var2.getColumnPosition();

         for(int var4 = 0; var4 < var1; ++var4) {
            OrderByColumn var5 = (OrderByColumn)this.elementAt(var4);
            if (var3 == var5.getColumnPosition()) {
               this.removeElementAt(var1);
               break;
            }
         }
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2, ResultSetNode var3) throws StandardException {
      if (!this.sortNeeded) {
         var3.generate(var1, var2);
      } else {
         CompilerContext var4 = this.getCompilerContext();
         int var5 = var1.addItem(var1.getColumnOrdering(this));
         var1.pushGetResultSetFactoryExpression(var2);
         var3.generate(var1, var2);
         this.resultSetNumber = var4.getNextResultSetNumber();
         var2.push(false);
         var2.push(false);
         var2.push(var5);
         var2.push(var1.addItem(var3.getResultColumns().buildRowTemplate()));
         var2.push(var3.getResultColumns().getTotalColumnSize());
         var2.push(this.resultSetNumber);
         CostEstimate var6 = var3.getFinalCostEstimate();
         var2.push(var6.rowCount());
         var2.push(var6.getEstimatedCost());
         var2.callMethod((short)185, (String)null, "getSortResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 9);
      }
   }

   public int sortRequired(RowOrdering var1, OptimizableList var2, int[] var3) throws StandardException {
      return this.sortRequired(var1, (JBitSet)null, var2, var3);
   }

   public int sortRequired(RowOrdering var1, JBitSet var2, OptimizableList var3, int[] var4) throws StandardException {
      if (this.alwaysSort) {
         return 1;
      } else {
         int var5 = 0;
         int var6 = this.size();

         for(int var7 = 0; var7 < var6; ++var7) {
            OrderByColumn var8 = this.getOrderByColumn(var7);
            if (var8.isNullsOrderedLow()) {
               return 1;
            }

            ValueNode var9 = var8.getResultColumn().getExpression();
            if (!(var9 instanceof ColumnReference)) {
               return 1;
            }

            ColumnReference var10 = (ColumnReference)var9;
            if (var2 != null && !var2.get(var10.getTableNumber())) {
               for(int var16 = var7 + 1; var16 < this.size(); ++var16) {
                  OrderByColumn var17 = this.getOrderByColumn(var7);
                  ResultColumn var18 = var17.getResultColumn();
                  ValueNode var14 = var18.getExpression();
                  if (var14 instanceof ColumnReference) {
                     ColumnReference var15 = (ColumnReference)var14;
                     if (var2.get(var15.getTableNumber())) {
                        return 1;
                     }
                  }
               }

               return 3;
            }

            boolean var11 = var2 != null ? !var2.hasSingleBitSet() : false;
            if (var11 && !var1.alwaysOrdered(var10.getTableNumber()) && !var1.isColumnAlwaysOrdered(var10.getTableNumber(), var10.getColumnNumber())) {
               for(int var12 = 0; var12 < var4.length && var4[var12] != -1; ++var12) {
                  Optimizable var13 = var3.getOptimizable(var4[var12]);
                  if (var13.getTableNumber() == var10.getTableNumber()) {
                     break;
                  }

                  if (!var1.alwaysOrdered(var13.getTableNumber())) {
                     return 1;
                  }
               }
            }

            if (!var1.alwaysOrdered(var10.getTableNumber())) {
               if (!var1.orderedOnColumn(var8.isAscending() ? 1 : 2, var5, var10.getTableNumber(), var10.getColumnNumber())) {
                  return 1;
               }

               ++var5;
            }
         }

         return 3;
      }
   }

   public void estimateCost(double var1, RowOrdering var3, CostEstimate var4) throws StandardException {
      if (this.scc == null) {
         this.scc = this.getCompilerContext().getSortCostController();
         this.resultRow = this.resultToSort.getResultColumns().buildEmptyRow().getRowArray();
         this.columnOrdering = this.getColumnOrdering();
         this.estimatedRowSize = this.resultToSort.getResultColumns().getTotalColumnSize();
      }

      long var5 = (long)var1;
      double var9 = this.scc.getSortCost((DataValueDescriptor[])this.resultRow, this.columnOrdering, false, var5, var5, this.estimatedRowSize);
      var4.setCost(var9, var1, var1);
   }

   public void sortNeeded() {
      this.sortNeeded = true;
   }

   public void sortNotNeeded() {
      this.sortNeeded = false;
   }

   void remapColumnReferencesToExpressions() throws StandardException {
   }

   public boolean getSortNeeded() {
      return this.sortNeeded;
   }

   boolean requiresDescending(ColumnReference var1, int var2) throws StandardException {
      int var3 = this.size();
      JBitSet var4 = new JBitSet(var2);
      BaseTableNumbersVisitor var5 = new BaseTableNumbersVisitor(var4);
      var1.accept(var5);
      int var6 = var4.getFirstSetBit();
      int var7 = var5.getColumnNumber();

      for(int var8 = 0; var8 < var3; ++var8) {
         OrderByColumn var9 = this.getOrderByColumn(var8);
         ResultColumn var10 = var9.getResultColumn();
         var5.reset();
         var10.accept(var5);
         int var11 = var4.getFirstSetBit();
         int var12 = var5.getColumnNumber();
         if (var6 == var11 && var7 == var12) {
            return !var9.isAscending();
         }
      }

      return false;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.columnOrdering != null) {
         for(int var2 = 0; var2 < this.columnOrdering.length; ++var2) {
            var1.append("[" + var2 + "] " + this.columnOrdering[var2] + "\n");
         }
      }

      boolean var10000 = this.allAscending;
      return "allAscending: " + var10000 + "\nalwaysSort:" + this.allAscending + "\nsortNeeded: " + this.sortNeeded + "\ncolumnOrdering: \n" + var1.toString() + "\n" + super.toString();
   }

   public int getResultSetNumber() {
      return this.resultSetNumber;
   }

   public boolean isTableValueCtorOrdering() {
      return this.isTableValueCtorOrdering;
   }
}
