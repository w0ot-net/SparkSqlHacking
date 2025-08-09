package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class RemapCRsVisitor implements Visitor {
   private boolean remap;

   RemapCRsVisitor(boolean var1) {
      this.remap = var1;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof ColumnReference var2) {
         if (this.remap) {
            var2.remapColumnReferences();
         } else {
            var2.unRemapColumnReferences();
         }
      }

      return var1;
   }

   public boolean skipChildren(Visitable var1) {
      return var1 instanceof SubqueryNode;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   public boolean stopTraversal() {
      return false;
   }
}
