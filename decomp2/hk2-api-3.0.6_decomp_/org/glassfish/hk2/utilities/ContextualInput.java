package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.ServiceHandle;

public class ContextualInput {
   private final ActiveDescriptor descriptor;
   private final ServiceHandle root;

   public ContextualInput(ActiveDescriptor descriptor, ServiceHandle root) {
      this.descriptor = descriptor;
      this.root = root;
   }

   public ActiveDescriptor getDescriptor() {
      return this.descriptor;
   }

   public ServiceHandle getRoot() {
      return this.root;
   }

   public int hashCode() {
      return this.descriptor.hashCode();
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (!(o instanceof ContextualInput)) {
         return false;
      } else {
         ContextualInput<T> other = (ContextualInput)o;
         return this.descriptor.equals(other.descriptor);
      }
   }

   public String toString() {
      String var10000 = this.descriptor.getImplementation();
      return "ContextualInput(" + var10000 + "," + this.root + "," + System.identityHashCode(this) + ")";
   }
}
