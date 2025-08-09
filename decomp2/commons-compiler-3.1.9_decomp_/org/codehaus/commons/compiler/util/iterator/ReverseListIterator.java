package org.codehaus.commons.compiler.util.iterator;

import java.util.ListIterator;

public class ReverseListIterator extends FilterListIterator {
   public ReverseListIterator(ListIterator delegate) {
      super(delegate);
   }

   public boolean hasNext() {
      return super.hasPrevious();
   }

   public boolean hasPrevious() {
      return super.hasNext();
   }

   public Object next() {
      return super.previous();
   }

   public Object previous() {
      return super.next();
   }

   public int nextIndex() {
      throw new UnsupportedOperationException();
   }

   public int previousIndex() {
      throw new UnsupportedOperationException();
   }
}
