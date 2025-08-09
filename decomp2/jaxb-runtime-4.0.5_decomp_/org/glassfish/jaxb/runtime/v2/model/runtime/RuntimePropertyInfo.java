package org.glassfish.jaxb.runtime.v2.model.runtime;

import java.lang.reflect.Type;
import java.util.Collection;
import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public interface RuntimePropertyInfo extends PropertyInfo {
   Collection ref();

   Accessor getAccessor();

   boolean elementOnlyContent();

   Type getRawType();

   Type getIndividualType();
}
