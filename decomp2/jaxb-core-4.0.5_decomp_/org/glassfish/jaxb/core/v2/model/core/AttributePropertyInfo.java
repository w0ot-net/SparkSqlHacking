package org.glassfish.jaxb.core.v2.model.core;

import javax.xml.namespace.QName;

public interface AttributePropertyInfo extends PropertyInfo, NonElementRef {
   NonElement getTarget();

   boolean isRequired();

   QName getXmlName();

   Adapter getAdapter();
}
