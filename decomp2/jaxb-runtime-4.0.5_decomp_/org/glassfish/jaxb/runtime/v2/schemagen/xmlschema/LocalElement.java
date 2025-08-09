package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

@XmlElement("element")
public interface LocalElement extends TypedXmlWriter, Element, Occurs {
   @XmlAttribute
   LocalElement form(String var1);

   @XmlAttribute
   LocalElement name(String var1);

   @XmlAttribute
   LocalElement ref(QName var1);
}
