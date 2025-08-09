package org.glassfish.jaxb.core.v2.model.core;

import org.glassfish.jaxb.core.v2.model.annotation.Locatable;

public interface TypeInfo extends Locatable {
   Object getType();

   boolean canBeReferencedByIDREF();
}
