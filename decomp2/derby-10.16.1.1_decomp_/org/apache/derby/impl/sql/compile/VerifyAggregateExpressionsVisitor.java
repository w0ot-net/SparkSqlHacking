package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class VerifyAggregateExpressionsVisitor implements Visitor {
   private GroupByList groupByList;

   VerifyAggregateExpressionsVisitor(GroupByList var1) {
      this.groupByList = var1;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof ColumnReference var4) {
         if (this.groupByList == null) {
            throw StandardException.newException("42Y35", new Object[]{var4.getSQLColumnName()});
         }

         if (this.groupByList.findGroupingColumn(var4) == null) {
            throw StandardException.newException("42Y36", new Object[]{var4.getSQLColumnName()});
         }
      } else if (var1 instanceof SubqueryNode var2) {
         if (var2.getSubqueryType() != 17 || var2.hasCorrelatedCRs()) {
            throw StandardException.newException(this.groupByList == null ? "42Y29" : "42Y30", new Object[0]);
         }

         HasNodeVisitor var3 = new HasNodeVisitor(AggregateNode.class);
         var2.accept(var3);
         if (var3.hasNode()) {
            throw StandardException.newException(this.groupByList == null ? "42Y29" : "42Y30", new Object[0]);
         }
      }

      return var1;
   }

   public boolean skipChildren(Visitable var1) throws StandardException {
      return var1 instanceof AggregateNode || var1 instanceof SubqueryNode || var1 instanceof ValueNode && this.groupByList != null && this.groupByList.findGroupingColumn((ValueNode)var1) != null;
   }

   public boolean stopTraversal() {
      return false;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }
}
