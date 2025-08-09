package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class ReplaceWindowFuncCallsWithCRVisitor implements Visitor {
   private ResultColumnList rcl;
   private Class skipOverClass;
   private int tableNumber;

   ReplaceWindowFuncCallsWithCRVisitor(ResultColumnList var1, int var2, Class var3) {
      this.rcl = var1;
      this.tableNumber = var2;
      this.skipOverClass = var3;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (var1 instanceof WindowFunctionNode) {
         var1 = ((WindowFunctionNode)var1).replaceCallsWithColumnReferences(this.rcl, this.tableNumber);
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
