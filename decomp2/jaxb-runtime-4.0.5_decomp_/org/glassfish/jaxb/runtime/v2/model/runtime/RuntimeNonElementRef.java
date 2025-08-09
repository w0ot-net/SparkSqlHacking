package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.NonElementRef;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;

public interface RuntimeNonElementRef extends NonElementRef {
   RuntimeNonElement getTarget();

   RuntimePropertyInfo getSource();

   Transducer getTransducer();
}
