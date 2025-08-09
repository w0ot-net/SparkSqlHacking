package org.glassfish.jaxb.core.v2.model.core;

import javax.xml.namespace.QName;

public interface NonElement extends TypeInfo {
   QName ANYTYPE_NAME = new QName("http://www.w3.org/2001/XMLSchema", "anyType");

   QName getTypeName();

   boolean isSimpleType();
}
