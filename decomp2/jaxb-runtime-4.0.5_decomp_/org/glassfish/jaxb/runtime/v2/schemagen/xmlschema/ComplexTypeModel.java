package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

public interface ComplexTypeModel extends TypedXmlWriter, AttrDecls, TypeDefParticle {
   @XmlElement
   SimpleContent simpleContent();

   @XmlElement
   ComplexContent complexContent();

   @XmlAttribute
   ComplexTypeModel mixed(boolean var1);
}
