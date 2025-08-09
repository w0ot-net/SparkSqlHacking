package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("import")
public interface Import extends TypedXmlWriter, Annotated {
   @XmlAttribute
   Import namespace(String var1);

   @XmlAttribute
   Import schemaLocation(String var1);
}
