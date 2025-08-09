package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Integer extends Accessor {
   public FieldAccessor_Integer() {
      super(Integer.class);
   }

   public Integer get(Object bean) {
      return ((Bean)bean).f_int;
   }

   public void set(Object bean, Integer value) {
      ((Bean)bean).f_int = value == null ? 0 : value;
   }
}
