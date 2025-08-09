package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import org.glassfish.jaxb.runtime.api.AccessorException;

public class NullSafeAccessor extends Accessor {
   private final Accessor core;
   private final Lister lister;

   public NullSafeAccessor(Accessor core, Lister lister) {
      super(core.getValueType());
      this.core = core;
      this.lister = lister;
   }

   public Object get(Object bean) throws AccessorException {
      V v = (V)this.core.get(bean);
      if (v == null) {
         P pack = (P)this.lister.startPacking(bean, this.core);
         this.lister.endPacking(pack, bean, this.core);
         v = (V)this.core.get(bean);
      }

      return v;
   }

   public void set(Object bean, Object value) throws AccessorException {
      this.core.set(bean, value);
   }
}
