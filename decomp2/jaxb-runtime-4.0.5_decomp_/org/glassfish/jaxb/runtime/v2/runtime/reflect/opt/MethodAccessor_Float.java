package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Float extends Accessor {
   public MethodAccessor_Float() {
      super(Float.class);
   }

   public Float get(Object bean) {
      return ((Bean)bean).get_float();
   }

   public void set(Object bean, Float value) {
      ((Bean)bean).set_float(value == null ? 0.0F : value);
   }
}
