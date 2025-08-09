package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("simpleContent")
public interface SimpleContent extends TypedXmlWriter, Annotated {
   @XmlElement
   SimpleExtension extension();

   @XmlElement
   SimpleRestriction restriction();
}
