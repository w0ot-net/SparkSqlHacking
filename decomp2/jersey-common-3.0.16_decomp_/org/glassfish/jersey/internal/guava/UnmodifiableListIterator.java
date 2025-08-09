package org.glassfish.jersey.internal.guava;

import java.util.ListIterator;

public abstract class UnmodifiableListIterator extends UnmodifiableIterator implements ListIterator {
   UnmodifiableListIterator() {
   }

   /** @deprecated */
   @Deprecated
   public final void add(Object e) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   public final void set(Object e) {
      throw new UnsupportedOperationException();
   }
}
