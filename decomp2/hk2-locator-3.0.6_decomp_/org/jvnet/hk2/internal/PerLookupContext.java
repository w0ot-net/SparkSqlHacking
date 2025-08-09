package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ServiceHandle;

public class PerLookupContext implements Context {
   public Class getScope() {
      return PerLookup.class;
   }

   public Object findOrCreate(ActiveDescriptor activeDescriptor, ServiceHandle root) {
      return activeDescriptor.create(root);
   }

   public boolean containsKey(ActiveDescriptor descriptor) {
      return false;
   }

   public boolean isActive() {
      return true;
   }

   public boolean supportsNullCreation() {
      return true;
   }

   public void shutdown() {
   }

   public void destroyOne(ActiveDescriptor descriptor) {
   }
}
