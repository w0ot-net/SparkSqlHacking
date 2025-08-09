package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

public interface Wildcard extends TypedXmlWriter, Annotated {
   @XmlAttribute
   Wildcard processContents(String var1);

   @XmlAttribute
   Wildcard namespace(String var1);

   @XmlAttribute
   Wildcard namespace(String[] var1);
}
