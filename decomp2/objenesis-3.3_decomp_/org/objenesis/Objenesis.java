package org.objenesis;

import org.objenesis.instantiator.ObjectInstantiator;

public interface Objenesis {
   Object newInstance(Class var1);

   ObjectInstantiator getInstantiatorOf(Class var1);
}
