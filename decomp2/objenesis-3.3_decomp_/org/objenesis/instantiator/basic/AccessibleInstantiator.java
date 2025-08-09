package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.NOT_COMPLIANT)
public class AccessibleInstantiator extends ConstructorInstantiator {
   public AccessibleInstantiator(Class type) {
      super(type);
      if (this.constructor != null) {
         this.constructor.setAccessible(true);
      }

   }
}
