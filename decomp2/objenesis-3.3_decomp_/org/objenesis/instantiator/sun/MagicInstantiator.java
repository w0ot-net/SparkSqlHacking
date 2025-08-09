package org.objenesis.instantiator.sun;

import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.basic.DelegatingToExoticInstantiator;

@Instantiator(Typology.STANDARD)
public class MagicInstantiator extends DelegatingToExoticInstantiator {
   public MagicInstantiator(Class type) {
      super("org.objenesis.instantiator.exotic.MagicInstantiator", type);
   }
}
