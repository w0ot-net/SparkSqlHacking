package org.glassfish.jaxb.core.v2.schemagen.episode;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

public interface SchemaBindings extends TypedXmlWriter {
   @XmlAttribute
   void map(boolean var1);

   @XmlElement("package")
   Package _package();
}
