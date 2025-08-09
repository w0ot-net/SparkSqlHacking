package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("complexType")
public interface ComplexType extends TypedXmlWriter, Annotated, ComplexTypeModel {
   @XmlAttribute("final")
   ComplexType _final(String[] var1);

   @XmlAttribute("final")
   ComplexType _final(String var1);

   @XmlAttribute
   ComplexType block(String[] var1);

   @XmlAttribute
   ComplexType block(String var1);

   @XmlAttribute("abstract")
   ComplexType _abstract(boolean var1);

   @XmlAttribute
   ComplexType name(String var1);
}
