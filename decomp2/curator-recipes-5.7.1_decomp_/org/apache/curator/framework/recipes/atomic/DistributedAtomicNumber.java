package org.apache.curator.framework.recipes.atomic;

public interface DistributedAtomicNumber {
   AtomicValue get() throws Exception;

   AtomicValue compareAndSet(Object var1, Object var2) throws Exception;

   AtomicValue trySet(Object var1) throws Exception;

   boolean initialize(Object var1) throws Exception;

   void forceSet(Object var1) throws Exception;

   AtomicValue increment() throws Exception;

   AtomicValue decrement() throws Exception;

   AtomicValue add(Object var1) throws Exception;

   AtomicValue subtract(Object var1) throws Exception;
}
