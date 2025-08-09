package org.glassfish.jaxb.core.v2.model.core;

import javax.xml.namespace.QName;

public interface Element extends TypeInfo {
   QName getElementName();

   Element getSubstitutionHead();

   ClassInfo getScope();
}
