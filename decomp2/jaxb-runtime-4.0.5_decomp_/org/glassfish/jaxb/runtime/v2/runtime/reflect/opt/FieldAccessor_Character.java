package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Character extends Accessor {
   public FieldAccessor_Character() {
      super(Character.class);
   }

   public Character get(Object bean) {
      return ((Bean)bean).f_char;
   }

   public void set(Object bean, Character value) {
      ((Bean)bean).f_char = value == null ? 0 : value;
   }
}
