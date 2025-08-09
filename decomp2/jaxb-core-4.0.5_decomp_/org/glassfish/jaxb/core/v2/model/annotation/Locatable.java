package org.glassfish.jaxb.core.v2.model.annotation;

import org.glassfish.jaxb.core.v2.runtime.Location;

public interface Locatable {
   Locatable getUpstream();

   Location getLocation();
}
