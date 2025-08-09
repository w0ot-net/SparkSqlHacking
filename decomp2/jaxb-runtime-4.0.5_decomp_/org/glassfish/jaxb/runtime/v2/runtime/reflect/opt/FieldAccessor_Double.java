package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Double extends Accessor {
   public FieldAccessor_Double() {
      super(Double.class);
   }

   public Double get(Object bean) {
      return ((Bean)bean).f_double;
   }

   public void set(Object bean, Double value) {
      ((Bean)bean).f_double = value == null ? (double)0.0F : value;
   }
}
