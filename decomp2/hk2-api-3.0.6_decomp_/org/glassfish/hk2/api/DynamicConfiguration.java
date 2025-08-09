package org.glassfish.hk2.api;

public interface DynamicConfiguration {
   ActiveDescriptor bind(Descriptor var1);

   ActiveDescriptor bind(Descriptor var1, boolean var2);

   FactoryDescriptors bind(FactoryDescriptors var1);

   FactoryDescriptors bind(FactoryDescriptors var1, boolean var2);

   ActiveDescriptor addActiveDescriptor(ActiveDescriptor var1) throws IllegalArgumentException;

   ActiveDescriptor addActiveDescriptor(ActiveDescriptor var1, boolean var2) throws IllegalArgumentException;

   ActiveDescriptor addActiveDescriptor(Class var1) throws MultiException, IllegalArgumentException;

   FactoryDescriptors addActiveFactoryDescriptor(Class var1) throws MultiException, IllegalArgumentException;

   void addUnbindFilter(Filter var1) throws IllegalArgumentException;

   void addIdempotentFilter(Filter... var1) throws IllegalArgumentException;

   void registerTwoPhaseResources(TwoPhaseResource... var1);

   void commit() throws MultiException;
}
