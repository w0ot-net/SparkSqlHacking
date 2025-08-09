package org.apache.curator.shaded.com.google.common.collect;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

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
