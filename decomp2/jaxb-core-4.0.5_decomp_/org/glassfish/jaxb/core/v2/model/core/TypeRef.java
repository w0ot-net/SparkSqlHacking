package org.glassfish.jaxb.core.v2.model.core;

import javax.xml.namespace.QName;

public interface TypeRef extends NonElementRef {
   QName getTagName();

   boolean isNillable();

   String getDefaultValue();
}
