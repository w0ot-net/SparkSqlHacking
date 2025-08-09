package org.glassfish.jaxb.runtime.v2.model.impl;

import org.glassfish.jaxb.core.v2.model.annotation.AnnotationSource;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

public interface PropertySeed extends Locatable, AnnotationSource {
   String getName();

   Object getRawType();
}
