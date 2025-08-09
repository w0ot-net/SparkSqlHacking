package org.sparkproject.guava.collect;

import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingObject {
   protected ForwardingObject() {
   }

   protected abstract Object delegate();

   public String toString() {
      return this.delegate().toString();
   }
}
