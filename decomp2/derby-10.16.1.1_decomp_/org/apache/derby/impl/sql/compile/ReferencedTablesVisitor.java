package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class ReferencedTablesVisitor implements Visitor {
   private JBitSet tableMap;

   ReferencedTablesVisitor(JBitSet var1) {
      this.tableMap = var1;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof ColumnReference) {
         ((ColumnReference)var1).getTablesReferenced(this.tableMap);
      } else if (var1 instanceof Predicate) {
         Predicate var2 = (Predicate)var1;
         this.tableMap.or(var2.getReferencedSet());
      } else if (var1 instanceof ResultSetNode) {
         ResultSetNode var3 = (ResultSetNode)var1;
         this.tableMap.or(var3.getReferencedTableMap());
      }

      return var1;
   }

   public boolean skipChildren(Visitable var1) {
      return var1 instanceof Predicate || var1 instanceof ResultSetNode;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   public boolean stopTraversal() {
      return false;
   }

   JBitSet getTableMap() {
      return this.tableMap;
   }
}
