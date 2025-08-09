package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class FieldAccessor_Ref extends Accessor {
   public FieldAccessor_Ref() {
      super(Ref.class);
   }

   public Ref get(Object bean) {
      return ((Bean)bean).f_ref;
   }

   public void set(Object bean, Ref value) {
      ((Bean)bean).f_ref = value;
   }
}
