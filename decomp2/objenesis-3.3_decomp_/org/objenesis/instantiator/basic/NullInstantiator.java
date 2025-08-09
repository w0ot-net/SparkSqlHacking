package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.NOT_COMPLIANT)
public class NullInstantiator implements ObjectInstantiator {
   public NullInstantiator(Class type) {
   }

   public Object newInstance() {
      return null;
   }
}
