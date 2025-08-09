package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.ValuePropertyInfo;

public interface RuntimeValuePropertyInfo extends ValuePropertyInfo, RuntimePropertyInfo, RuntimeNonElementRef {
   RuntimeNonElement getTarget();
}
