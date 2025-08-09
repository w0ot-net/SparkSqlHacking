package org.glassfish.jaxb.core.v2.model.core;

import java.util.List;
import javax.xml.namespace.QName;

public interface ElementPropertyInfo extends PropertyInfo {
   List getTypes();

   QName getXmlName();

   boolean isCollectionRequired();

   boolean isCollectionNillable();

   boolean isValueList();

   boolean isRequired();

   Adapter getAdapter();
}
