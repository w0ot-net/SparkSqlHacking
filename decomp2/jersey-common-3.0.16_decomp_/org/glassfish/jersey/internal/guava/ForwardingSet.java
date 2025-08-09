package org.glassfish.jersey.internal.guava;

import java.util.Set;

public abstract class ForwardingSet extends ForwardingCollection implements Set {
   ForwardingSet() {
   }

   protected abstract Set delegate();

   public boolean equals(Object object) {
      return object == this || this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }
}
