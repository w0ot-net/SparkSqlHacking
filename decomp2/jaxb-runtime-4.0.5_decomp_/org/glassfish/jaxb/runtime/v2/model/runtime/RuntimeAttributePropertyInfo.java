package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.AttributePropertyInfo;

public interface RuntimeAttributePropertyInfo extends AttributePropertyInfo, RuntimePropertyInfo, RuntimeNonElementRef {
   RuntimeNonElement getTarget();
}
