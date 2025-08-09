package org.glassfish.jaxb.core.v2.model.core;

import javax.xml.namespace.QName;

public interface MapPropertyInfo extends PropertyInfo {
   QName getXmlName();

   boolean isCollectionNillable();

   NonElement getKeyType();

   NonElement getValueType();
}
