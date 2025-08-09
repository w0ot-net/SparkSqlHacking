package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("attribute")
public interface TopLevelAttribute extends TypedXmlWriter, Annotated, AttributeType, FixedOrDefault {
   @XmlAttribute
   TopLevelAttribute name(String var1);
}
