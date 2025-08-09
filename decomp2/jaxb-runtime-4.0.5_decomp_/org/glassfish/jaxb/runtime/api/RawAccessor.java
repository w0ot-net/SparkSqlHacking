package org.glassfish.jaxb.runtime.api;

public abstract class RawAccessor {
   protected RawAccessor() {
   }

   public abstract Object get(Object var1) throws AccessorException;

   public abstract void set(Object var1, Object var2) throws AccessorException;
}
