package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;

class HasTableFunctionVisitor extends HasNodeVisitor {
   public HasTableFunctionVisitor() {
      super(FromVTI.class);
   }

   public Visitable visit(Visitable var1) {
      if (var1 instanceof FromVTI var2) {
         if (var2.isDerbyStyleTableFunction()) {
            this.hasNode = true;
         }
      }

      return var1;
   }
}
