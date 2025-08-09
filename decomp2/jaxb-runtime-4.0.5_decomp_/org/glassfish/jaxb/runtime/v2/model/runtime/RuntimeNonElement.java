package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.NonElement;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;

public interface RuntimeNonElement extends NonElement, RuntimeTypeInfo {
   Transducer getTransducer();
}
