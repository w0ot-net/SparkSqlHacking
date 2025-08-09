package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

@XmlElement("union")
public interface Union extends TypedXmlWriter, Annotated, SimpleTypeHost {
   @XmlAttribute
   Union memberTypes(QName[] var1);
}
