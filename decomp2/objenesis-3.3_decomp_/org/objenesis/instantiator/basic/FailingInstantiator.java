package org.objenesis.instantiator.basic;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.NOT_COMPLIANT)
public class FailingInstantiator implements ObjectInstantiator {
   public FailingInstantiator(Class type) {
   }

   public Object newInstance() {
      throw new ObjenesisException("Always failing");
   }
}
