package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

public interface FixedOrDefault extends TypedXmlWriter {
   @XmlAttribute("default")
   FixedOrDefault _default(String var1);

   @XmlAttribute
   FixedOrDefault fixed(String var1);
}
