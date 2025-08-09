package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

public interface NoFixedFacet extends TypedXmlWriter, Annotated {
   @XmlAttribute
   NoFixedFacet value(String var1);
}
