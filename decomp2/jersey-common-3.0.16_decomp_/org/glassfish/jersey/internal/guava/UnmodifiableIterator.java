package org.glassfish.jersey.internal.guava;

import java.util.Iterator;

public abstract class UnmodifiableIterator implements Iterator {
   UnmodifiableIterator() {
   }

   /** @deprecated */
   @Deprecated
   public final void remove() {
      throw new UnsupportedOperationException();
   }
}
