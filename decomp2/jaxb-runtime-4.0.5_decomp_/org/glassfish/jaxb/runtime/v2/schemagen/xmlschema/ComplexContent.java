package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("complexContent")
public interface ComplexContent extends TypedXmlWriter, Annotated {
   @XmlElement
   ComplexExtension extension();

   @XmlElement
   ComplexRestriction restriction();

   @XmlAttribute
   ComplexContent mixed(boolean var1);
}
