package org.codehaus.commons.compiler.util.iterator;

import java.util.ListIterator;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class FilterListIterator implements ListIterator {
   protected final ListIterator delegate;

   public FilterListIterator(ListIterator delegate) {
      this.delegate = delegate;
   }

   public boolean hasNext() {
      return this.delegate.hasNext();
   }

   public Object next() {
      return this.delegate.next();
   }

   public boolean hasPrevious() {
      return this.delegate.hasPrevious();
   }

   public Object previous() {
      return this.delegate.previous();
   }

   public int nextIndex() {
      return this.delegate.nextIndex();
   }

   public int previousIndex() {
      return this.delegate.previousIndex();
   }

   public void remove() {
      this.delegate.remove();
   }

   public void set(@Nullable Object o) {
      this.delegate.set(o);
   }

   public void add(@Nullable Object o) {
      this.delegate.add(o);
   }
}
