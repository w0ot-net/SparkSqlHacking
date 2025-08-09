package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;

public class CollectNodesVisitor implements Visitor {
   private final List nodeList;
   private final Class nodeClass;
   private final Class skipOverClass;

   public CollectNodesVisitor(Class var1) {
      this(var1, (Class)null);
   }

   public CollectNodesVisitor(Class var1, Class var2) {
      this.nodeList = new ArrayList();
      this.nodeClass = var1;
      this.skipOverClass = var2;
   }

   public boolean visitChildrenFirst(Visitable var1) {
      return false;
   }

   public boolean stopTraversal() {
      return false;
   }

   public Visitable visit(Visitable var1) {
      if (this.nodeClass.isInstance(var1)) {
         this.nodeList.add((Visitable)this.nodeClass.cast(var1));
      }

      return var1;
   }

   public boolean skipChildren(Visitable var1) {
      return this.skipOverClass == null ? false : this.skipOverClass.isInstance(var1);
   }

   public List getList() {
      return this.nodeList;
   }
}
