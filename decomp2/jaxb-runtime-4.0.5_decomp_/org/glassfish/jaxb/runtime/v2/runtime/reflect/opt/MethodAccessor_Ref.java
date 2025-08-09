package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class MethodAccessor_Ref extends Accessor {
   public MethodAccessor_Ref() {
      super(Ref.class);
   }

   public Ref get(Object bean) {
      return ((Bean)bean).get_ref();
   }

   public void set(Object bean, Ref value) {
      ((Bean)bean).set_ref(value);
   }
}
