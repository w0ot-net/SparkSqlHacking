package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

public interface Occurs extends TypedXmlWriter {
   @XmlAttribute
   Occurs minOccurs(int var1);

   @XmlAttribute
   Occurs maxOccurs(String var1);

   @XmlAttribute
   Occurs maxOccurs(int var1);
}
