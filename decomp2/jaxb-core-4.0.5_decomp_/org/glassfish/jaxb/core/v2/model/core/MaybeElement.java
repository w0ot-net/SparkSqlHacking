package org.glassfish.jaxb.core.v2.model.core;

import javax.xml.namespace.QName;

public interface MaybeElement extends NonElement {
   boolean isElement();

   QName getElementName();

   Element asElement();
}
