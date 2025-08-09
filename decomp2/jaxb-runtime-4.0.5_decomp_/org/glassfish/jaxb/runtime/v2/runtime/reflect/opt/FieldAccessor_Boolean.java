package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Boolean extends Accessor {
   public FieldAccessor_Boolean() {
      super(Boolean.class);
   }

   public Boolean get(Object bean) {
      return ((Bean)bean).f_boolean;
   }

   public void set(Object bean, Boolean value) {
      ((Bean)bean).f_boolean = value == null ? false : value;
   }
}
