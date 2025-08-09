package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class ProxyingInstantiator extends DelegatingToExoticInstantiator {
   public ProxyingInstantiator(Class type) {
      super("org.objenesis.instantiator.exotic.ProxyingInstantiator", type);
   }
}
