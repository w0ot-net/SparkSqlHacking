package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("documentation")
public interface Documentation extends TypedXmlWriter {
   @XmlAttribute
   Documentation source(String var1);

   @XmlAttribute(
      ns = "http://www.w3.org/XML/1998/namespace"
   )
   Documentation lang(String var1);
}
