package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class ReplaceAggregatesWithCRVisitor implements Visitor {
   private ResultColumnList rcl;
   private Class skipOverClass;
   private int tableNumber;

   ReplaceAggregatesWithCRVisitor(ResultColumnList var1, int var2) {
      this(var1, var2, (Class)null);
   }

   ReplaceAggregatesWithCRVisitor(ResultColumnList var1, int var2, Class var3) {
      this.rcl = var1;
      this.tableNumber = var2;
      this.skipOverClass = var3;
   }

   ReplaceAggregatesWithCRVisitor(ResultColumnList var1, Class var2) {
      this.rcl = var1;
      this.skipOverClass = var2;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof AggregateNode) {
         var1 = ((AggregateNode)var1).replaceAggregatesWithColumnReferences(this.rcl, this.tableNumber);
      }

      return (Visitable)var1;
   }

   public boolean skipChildren(Visitable var1) {
      return this.skipOverClass == null ? false : this.skipOverClass.isInstance(var1);
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   public boolean stopTraversal() {
      return false;
   }
}
