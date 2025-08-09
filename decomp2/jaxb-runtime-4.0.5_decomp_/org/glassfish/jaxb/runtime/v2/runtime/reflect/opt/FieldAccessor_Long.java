package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Long extends Accessor {
   public FieldAccessor_Long() {
      super(Long.class);
   }

   public Long get(Object bean) {
      return ((Bean)bean).f_long;
   }

   public void set(Object bean, Long value) {
      ((Bean)bean).f_long = value == null ? 0L : value;
   }
}
