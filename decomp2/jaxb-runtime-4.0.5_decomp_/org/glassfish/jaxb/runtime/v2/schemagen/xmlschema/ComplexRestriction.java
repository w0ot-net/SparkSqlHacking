package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

@XmlElement("restriction")
public interface ComplexRestriction extends TypedXmlWriter, Annotated, AttrDecls, TypeDefParticle {
   @XmlAttribute
   ComplexRestriction base(QName var1);
}
