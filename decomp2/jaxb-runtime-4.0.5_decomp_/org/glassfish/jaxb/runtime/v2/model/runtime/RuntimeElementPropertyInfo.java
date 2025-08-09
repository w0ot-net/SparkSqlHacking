package org.glassfish.jaxb.runtime.v2.model.runtime;

import java.util.Collection;
import java.util.List;
import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;

public interface RuntimeElementPropertyInfo extends ElementPropertyInfo, RuntimePropertyInfo {
   Collection ref();

   List getTypes();
}
