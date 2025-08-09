package org.apache.derby.impl.sql.compile;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class OffsetOrderVisitor implements Visitor {
   private static final Comparator COMPARATOR = new Comparator() {
      public int compare(QueryTreeNode var1, QueryTreeNode var2) {
         return var1.getBeginOffset() - var2.getBeginOffset();
      }
   };
   private final Class nodeClass;
   private final TreeSet nodes;
   private final int lowOffset;
   private final int highOffset;

   OffsetOrderVisitor(Class var1, int var2, int var3) {
      this.nodes = new TreeSet(COMPARATOR);
      this.nodeClass = var1;
      this.lowOffset = var2;
      this.highOffset = var3;
   }

   public Visitable visit(Visitable var1) throws StandardException {
      if (this.nodeClass.isInstance(var1)) {
         QueryTreeNode var2 = (QueryTreeNode)this.nodeClass.cast(var1);
         if (var2.getBeginOffset() >= this.lowOffset && var2.getEndOffset() < this.highOffset) {
            this.nodes.add(var2);
         }
      }

      return var1;
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

   SortedSet getNodes() {
      return this.nodes;
   }
}
