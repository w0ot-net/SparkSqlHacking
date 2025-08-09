package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class ConstantExpressionVisitor implements Visitor {
   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof ValueNode) {
         var1 = ((ValueNode)var1).evaluateConstantExpressions();
      }

      return (Visitable)var1;
   }

   public boolean stopTraversal() {
      return false;
   }

   public boolean skipChildren(Visitable var1) {
      return false;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return true;
   }
}
