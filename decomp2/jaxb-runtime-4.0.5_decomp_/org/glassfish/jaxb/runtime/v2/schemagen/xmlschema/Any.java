package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("any")
public interface Any extends TypedXmlWriter, Occurs, Wildcard {
}
