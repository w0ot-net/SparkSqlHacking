package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;

class HasNodeVisitor implements Visitor {
   protected boolean hasNode;
   private Class nodeClass;
   private Class skipOverClass;

   HasNodeVisitor(Class var1) {
      this.nodeClass = var1;
   }

   HasNodeVisitor(Class var1, Class var2) {
      this.nodeClass = var1;
      this.skipOverClass = var2;
   }

   public Visitable visit(Visitable var1) {
      if (this.nodeClass.isInstance(var1)) {
         this.hasNode = true;
      }

      return var1;
   }

   public boolean stopTraversal() {
      return this.hasNode;
   }

   public boolean skipChildren(Visitable var1) {
      return this.skipOverClass == null ? false : this.skipOverClass.isInstance(var1);
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   boolean hasNode() {
      return this.hasNode;
   }

   void reset() {
      this.hasNode = false;
   }
}
