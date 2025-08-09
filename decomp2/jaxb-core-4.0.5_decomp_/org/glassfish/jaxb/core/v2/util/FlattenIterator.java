package org.glassfish.jaxb.core.v2.util;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public final class FlattenIterator implements Iterator {
   private final Iterator parent;
   private Iterator child = null;
   private Object next;

   public FlattenIterator(Iterable core) {
      this.parent = core.iterator();
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   public boolean hasNext() {
      this.getNext();
      return this.next != null;
   }

   public Object next() {
      T r = (T)this.next;
      this.next = null;
      if (r == null) {
         throw new NoSuchElementException();
      } else {
         return r;
      }
   }

   private void getNext() {
      if (this.next == null) {
         if (this.child != null && this.child.hasNext()) {
            this.next = this.child.next();
         } else {
            if (this.parent.hasNext()) {
               this.child = ((Map)this.parent.next()).values().iterator();
               this.getNext();
            }

         }
      }
   }
}
