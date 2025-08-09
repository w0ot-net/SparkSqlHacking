package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class BaseTableNumbersVisitor implements Visitor {
   private JBitSet tableMap;
   private int columnNumber;

   BaseTableNumbersVisitor(JBitSet var1) {
      this.tableMap = var1;
      this.columnNumber = -1;
   }

   protected void setTableMap(JBitSet var1) {
      this.tableMap = var1;
   }

   protected void reset() {
      this.tableMap.clearAll();
      this.columnNumber = -1;
   }

   protected int getColumnNumber() {
      return this.columnNumber;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      ResultColumn var2 = null;
      if (var1 instanceof ColumnReference) {
         var2 = ((ColumnReference)var1).getSource();
         if (var2 == null) {
            return var1;
         }
      } else if (var1 instanceof ResultColumn) {
         var2 = (ResultColumn)var1;
      } else if (var1 instanceof SelectNode) {
         ((SelectNode)var1).getFromList().accept(this);
      } else if (var1 instanceof FromBaseTable) {
         this.tableMap.set(((FromBaseTable)var1).getTableNumber());
      }

      if (var2 != null) {
         int var3 = var2.getTableNumber();
         if (var3 >= 0) {
            ValueNode var4;
            for(var4 = var2.getExpression(); var4 instanceof VirtualColumnNode; var4 = var2.getExpression()) {
               var2 = ((VirtualColumnNode)var4).getSourceColumn();
            }

            if (var4 instanceof ColumnReference) {
               var4.accept(this);
            } else {
               this.tableMap.set(var3);
               this.columnNumber = var2.getColumnPosition();
            }
         } else if (var1 instanceof ColumnReference) {
            ColumnReference var5 = (ColumnReference)var1;
            var5.getTablesReferenced(this.tableMap);
            this.columnNumber = var5.getColumnNumber();
         }
      }

      return var1;
   }

   public boolean skipChildren(Visitable var1) {
      return var1 instanceof FromBaseTable || var1 instanceof SelectNode || var1 instanceof PredicateList;
   }

   public boolean stopTraversal() {
      return false;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }
}
