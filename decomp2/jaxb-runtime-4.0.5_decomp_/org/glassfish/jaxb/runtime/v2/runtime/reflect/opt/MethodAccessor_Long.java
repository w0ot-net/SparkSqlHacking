package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Long extends Accessor {
   public MethodAccessor_Long() {
      super(Long.class);
   }

   public Long get(Object bean) {
      return ((Bean)bean).get_long();
   }

   public void set(Object bean, Long value) {
      ((Bean)bean).set_long(value == null ? 0L : value);
   }
}
