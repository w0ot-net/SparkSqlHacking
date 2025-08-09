package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

public interface SimpleRestrictionModel extends TypedXmlWriter, SimpleTypeHost {
   @XmlAttribute
   SimpleRestrictionModel base(QName var1);

   @XmlElement
   NoFixedFacet enumeration();
}
