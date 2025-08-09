package org.codehaus.commons.compiler.util.iterator;

import java.util.Iterator;

public abstract class TransformingIterator implements Iterator {
   private final Iterator delegate;

   public TransformingIterator(Iterator delegate) {
      this.delegate = delegate;
   }

   public boolean hasNext() {
      return this.delegate.hasNext();
   }

   public final Object next() {
      return this.transform(this.delegate.next());
   }

   public void remove() {
      this.delegate.remove();
   }

   protected abstract Object transform(Object var1);
}
