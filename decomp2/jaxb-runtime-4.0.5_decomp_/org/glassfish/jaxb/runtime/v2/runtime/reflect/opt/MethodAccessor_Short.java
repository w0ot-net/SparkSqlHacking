package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Short extends Accessor {
   public MethodAccessor_Short() {
      super(Short.class);
   }

   public Short get(Object bean) {
      return ((Bean)bean).get_short();
   }

   public void set(Object bean, Short value) {
      ((Bean)bean).set_short(value == null ? 0 : value);
   }
}
