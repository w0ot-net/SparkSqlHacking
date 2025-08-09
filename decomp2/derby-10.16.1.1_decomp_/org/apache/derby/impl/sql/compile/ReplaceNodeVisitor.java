package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class ReplaceNodeVisitor implements Visitor {
   private final Visitable nodeToReplace;
   private final Visitable replacement;

   ReplaceNodeVisitor(Visitable var1, Visitable var2) {
      this.nodeToReplace = var1;
      this.replacement = var2;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      return var1 == this.nodeToReplace ? this.replacement : var1;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   public boolean stopTraversal() {
      return false;
   }

   public boolean skipChildren(Visitable var1) throws StandardException {
      return false;
   }
}
