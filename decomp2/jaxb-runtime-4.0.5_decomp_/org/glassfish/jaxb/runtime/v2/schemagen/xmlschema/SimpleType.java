package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("simpleType")
public interface SimpleType extends TypedXmlWriter, Annotated, SimpleDerivation {
   @XmlAttribute("final")
   SimpleType _final(String var1);

   @XmlAttribute("final")
   SimpleType _final(String[] var1);

   @XmlAttribute
   SimpleType name(String var1);
}
