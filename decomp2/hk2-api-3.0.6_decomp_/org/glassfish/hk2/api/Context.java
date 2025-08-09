package org.glassfish.hk2.api;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface Context {
   Class getScope();

   Object findOrCreate(ActiveDescriptor var1, ServiceHandle var2);

   boolean containsKey(ActiveDescriptor var1);

   void destroyOne(ActiveDescriptor var1);

   boolean supportsNullCreation();

   boolean isActive();

   void shutdown();
}
