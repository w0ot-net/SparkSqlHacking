package org.glassfish.jaxb.core.v2.model.core;

import java.util.Collection;
import java.util.Set;
import javax.xml.namespace.QName;

public interface ReferencePropertyInfo extends PropertyInfo {
   Set getElements();

   Collection ref();

   QName getXmlName();

   boolean isCollectionNillable();

   boolean isCollectionRequired();

   boolean isMixed();

   WildcardMode getWildcard();

   Object getDOMHandler();

   boolean isRequired();

   Adapter getAdapter();
}
