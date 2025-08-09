package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Character extends Accessor {
   public MethodAccessor_Character() {
      super(Character.class);
   }

   public Character get(Object bean) {
      return ((Bean)bean).get_char();
   }

   public void set(Object bean, Character value) {
      ((Bean)bean).set_char(value == null ? '\u0000' : value);
   }
}
