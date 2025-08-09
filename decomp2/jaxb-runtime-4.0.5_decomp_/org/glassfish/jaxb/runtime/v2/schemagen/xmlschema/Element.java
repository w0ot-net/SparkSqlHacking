package org.glassfish.jaxb.runtime.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import javax.xml.namespace.QName;

public interface Element extends TypedXmlWriter, Annotated, ComplexTypeHost, FixedOrDefault, SimpleTypeHost {
   @XmlAttribute
   Element type(QName var1);

   @XmlAttribute
   Element block(String var1);

   @XmlAttribute
   Element block(String[] var1);

   @XmlAttribute
   Element nillable(boolean var1);
}
