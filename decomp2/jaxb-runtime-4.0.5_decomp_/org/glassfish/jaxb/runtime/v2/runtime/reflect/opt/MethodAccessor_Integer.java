package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Integer extends Accessor {
   public MethodAccessor_Integer() {
      super(Integer.class);
   }

   public Integer get(Object bean) {
      return ((Bean)bean).get_int();
   }

   public void set(Object bean, Integer value) {
      ((Bean)bean).set_int(value == null ? 0 : value);
   }
}
