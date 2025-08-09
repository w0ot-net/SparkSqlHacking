package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Double extends Accessor {
   public MethodAccessor_Double() {
      super(Double.class);
   }

   public Double get(Object bean) {
      return ((Bean)bean).get_double();
   }

   public void set(Object bean, Double value) {
      ((Bean)bean).set_double(value == null ? (double)0.0F : value);
   }
}
