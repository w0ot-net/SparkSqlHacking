package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

public interface SimpleTypeHost extends TypedXmlWriter, TypeHost {
   @XmlElement
   SimpleType simpleType();
}
