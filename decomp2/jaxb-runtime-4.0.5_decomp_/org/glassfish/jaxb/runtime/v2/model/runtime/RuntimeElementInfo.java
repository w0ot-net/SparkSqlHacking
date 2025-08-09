package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.ElementInfo;

public interface RuntimeElementInfo extends ElementInfo, RuntimeElement {
   RuntimeClassInfo getScope();

   RuntimeElementPropertyInfo getProperty();

   Class getType();

   RuntimeNonElement getContentType();
}
