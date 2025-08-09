package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.ClassUtils;

@Instantiator(Typology.NOT_COMPLIANT)
public class NewInstanceInstantiator implements ObjectInstantiator {
   private final Class type;

   public NewInstanceInstantiator(Class type) {
      this.type = type;
   }

   public Object newInstance() {
      return ClassUtils.newInstance(this.type);
   }
}
