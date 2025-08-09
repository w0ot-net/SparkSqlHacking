package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Boolean extends Accessor {
   public MethodAccessor_Boolean() {
      super(Boolean.class);
   }

   public Boolean get(Object bean) {
      return ((Bean)bean).get_boolean();
   }

   public void set(Object bean, Boolean value) {
      ((Bean)bean).set_boolean(value == null ? false : value);
   }
}
