package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.MapPropertyInfo;

public interface RuntimeMapPropertyInfo extends RuntimePropertyInfo, MapPropertyInfo {
   RuntimeNonElement getKeyType();

   RuntimeNonElement getValueType();
}
