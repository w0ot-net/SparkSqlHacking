package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

public interface SimpleDerivation extends TypedXmlWriter {
   @XmlElement
   SimpleRestriction restriction();

   @XmlElement
   Union union();

   @XmlElement
   List list();
}
