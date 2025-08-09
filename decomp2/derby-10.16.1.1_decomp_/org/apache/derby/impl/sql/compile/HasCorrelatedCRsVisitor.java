package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;

class HasCorrelatedCRsVisitor implements Visitor {
   private boolean hasCorrelatedCRs;

   public Visitable visit(Visitable var1) {
      if (var1 instanceof ColumnReference) {
         if (((ColumnReference)var1).getCorrelated()) {
            this.hasCorrelatedCRs = true;
         }
      } else if (var1 instanceof VirtualColumnNode) {
         if (((VirtualColumnNode)var1).getCorrelated()) {
            this.hasCorrelatedCRs = true;
         }
      } else if (var1 instanceof MethodCallNode && (((MethodCallNode)var1).getMethodName().equals("getTriggerExecutionContext") || ((MethodCallNode)var1).getMethodName().equals("TriggerOldTransitionRows") || ((MethodCallNode)var1).getMethodName().equals("TriggerNewTransitionRows"))) {
         this.hasCorrelatedCRs = true;
      }

      return var1;
   }

   public boolean stopTraversal() {
      return this.hasCorrelatedCRs;
   }

   public boolean skipChildren(Visitable var1) {
      return false;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   boolean hasCorrelatedCRs() {
      return this.hasCorrelatedCRs;
   }

   void setHasCorrelatedCRs(boolean var1) {
      this.hasCorrelatedCRs = var1;
   }
}
