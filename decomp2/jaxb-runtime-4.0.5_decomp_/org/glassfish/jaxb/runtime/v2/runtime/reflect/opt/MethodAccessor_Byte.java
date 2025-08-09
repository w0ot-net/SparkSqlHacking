package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Byte extends Accessor {
   public MethodAccessor_Byte() {
      super(Byte.class);
   }

   public Byte get(Object bean) {
      return ((Bean)bean).get_byte();
   }

   public void set(Object bean, Byte value) {
      ((Bean)bean).set_byte(value == null ? 0 : value);
   }
}
