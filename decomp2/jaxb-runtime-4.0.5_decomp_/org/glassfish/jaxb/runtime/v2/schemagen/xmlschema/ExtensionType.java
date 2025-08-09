package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import javax.xml.namespace.QName;

public interface ExtensionType extends TypedXmlWriter, Annotated {
   @XmlAttribute
   ExtensionType base(QName var1);
}
