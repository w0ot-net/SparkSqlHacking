package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.shared.common.error.StandardException;

class QueryTreeNodeVector extends QueryTreeNode implements Iterable {
   private final ArrayList v = new ArrayList();
   final Class eltClass;

   QueryTreeNodeVector(Class var1, ContextManager var2) {
      super(var2);
      this.eltClass = var1;
   }

   public final int size() {
      return this.v.size();
   }

   final QueryTreeNode elementAt(int var1) {
      return (QueryTreeNode)this.v.get(var1);
   }

   void addElement(QueryTreeNode var1) {
      this.v.add(var1);
   }

   final QueryTreeNode removeElementAt(int var1) {
      return (QueryTreeNode)this.v.remove(var1);
   }

   final void removeElement(QueryTreeNode var1) {
      this.v.remove(var1);
   }

   final int indexOf(QueryTreeNode var1) {
      return this.v.indexOf(var1);
   }

   final void setElementAt(QueryTreeNode var1, int var2) {
      this.v.set(var2, var1);
   }

   final void destructiveAppend(QueryTreeNodeVector var1) {
      this.nondestructiveAppend(var1);
      var1.removeAllElements();
   }

   final void nondestructiveAppend(QueryTreeNodeVector var1) {
      this.v.addAll(var1.v);
   }

   final void removeAllElements() {
      this.v.clear();
   }

   final void insertElementAt(QueryTreeNode var1, int var2) {
      this.v.add(var2, var1);
   }

   void printSubNodes(int var1) {
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         Visitable var4 = this.elementAt(var3).accept(var1);
         this.setElementAt((QueryTreeNode)this.eltClass.cast(var4), var3);
      }

   }

   public final Iterator iterator() {
      return this.v.iterator();
   }
}
