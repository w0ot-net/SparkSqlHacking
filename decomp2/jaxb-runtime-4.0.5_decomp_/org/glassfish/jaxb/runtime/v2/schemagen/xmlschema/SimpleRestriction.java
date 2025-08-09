package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("restriction")
public interface SimpleRestriction extends TypedXmlWriter, Annotated, AttrDecls, SimpleRestrictionModel {
}
