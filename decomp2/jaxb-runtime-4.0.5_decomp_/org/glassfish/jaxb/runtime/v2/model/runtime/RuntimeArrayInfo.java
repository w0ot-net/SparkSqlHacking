package org.glassfish.jaxb.runtime.v2.model.runtime;

import org.glassfish.jaxb.core.v2.model.core.ArrayInfo;

public interface RuntimeArrayInfo extends ArrayInfo, RuntimeNonElement {
   Class getType();

   RuntimeNonElement getItemType();
}
