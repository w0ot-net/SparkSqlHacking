package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Short extends Accessor {
   public FieldAccessor_Short() {
      super(Short.class);
   }

   public Short get(Object bean) {
      return ((Bean)bean).f_short;
   }

   public void set(Object bean, Short value) {
      ((Bean)bean).f_short = value == null ? 0 : value;
   }
}
