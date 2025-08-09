package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class SubstituteExpressionVisitor implements Visitor {
   private ValueNode source;
   private ValueNode target;
   private Class skipOverClass;

   SubstituteExpressionVisitor(ValueNode var1, ValueNode var2, Class var3) {
      this.source = var1;
      this.target = var2;
      this.skipOverClass = var3;
   }

   ValueNode getSource() {
      return this.source;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (!(var1 instanceof ValueNode var2)) {
         return var1;
      } else {
         return (Visitable)(var2.isEquivalent(this.source) ? this.target : var1);
      }
   }

   public boolean stopTraversal() {
      return false;
   }

   public boolean skipChildren(Visitable var1) {
      return this.skipOverClass == null ? false : this.skipOverClass.isInstance(var1);
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }
}
