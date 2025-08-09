package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Float extends Accessor {
   public FieldAccessor_Float() {
      super(Float.class);
   }

   public Float get(Object bean) {
      return ((Bean)bean).f_float;
   }

   public void set(Object bean, Float value) {
      ((Bean)bean).f_float = value == null ? 0.0F : value;
   }
}
