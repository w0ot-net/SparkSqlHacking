package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

public interface AttrDecls extends TypedXmlWriter {
   @XmlElement
   LocalAttribute attribute();

   @XmlElement
   Wildcard anyAttribute();
}
