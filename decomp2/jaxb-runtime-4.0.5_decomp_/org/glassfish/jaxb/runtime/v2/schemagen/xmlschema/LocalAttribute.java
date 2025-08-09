package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

@XmlElement("attribute")
public interface LocalAttribute extends TypedXmlWriter, Annotated, AttributeType, FixedOrDefault {
   @XmlAttribute
   LocalAttribute form(String var1);

   @XmlAttribute
   LocalAttribute name(String var1);

   @XmlAttribute
   LocalAttribute ref(QName var1);

   @XmlAttribute
   LocalAttribute use(String var1);
}
