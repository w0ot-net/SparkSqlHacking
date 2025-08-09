package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

public interface TypeDefParticle extends TypedXmlWriter {
   @XmlElement
   ExplicitGroup all();

   @XmlElement
   ExplicitGroup sequence();

   @XmlElement
   ExplicitGroup choice();
}
