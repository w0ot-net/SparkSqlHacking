package org.glassfish.jersey.internal.inject;

import java.util.function.Consumer;

public class ForeignDescriptorImpl implements ForeignDescriptor {
   private static final Consumer NOOP_DISPOSE_INSTANCE = (instance) -> {
   };
   private final Object foreignDescriptor;
   private final Consumer disposeInstance;

   public ForeignDescriptorImpl(Object foreignDescriptor) {
      this(foreignDescriptor, NOOP_DISPOSE_INSTANCE);
   }

   public ForeignDescriptorImpl(Object foreignDescriptor, Consumer disposeInstance) {
      this.foreignDescriptor = foreignDescriptor;
      this.disposeInstance = disposeInstance;
   }

   public Object get() {
      return this.foreignDescriptor;
   }

   public void dispose(Object instance) {
      this.disposeInstance.accept(instance);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ForeignDescriptorImpl)) {
         return false;
      } else {
         ForeignDescriptorImpl that = (ForeignDescriptorImpl)o;
         return this.foreignDescriptor.equals(that.foreignDescriptor);
      }
   }

   public int hashCode() {
      return this.foreignDescriptor.hashCode();
   }
}
