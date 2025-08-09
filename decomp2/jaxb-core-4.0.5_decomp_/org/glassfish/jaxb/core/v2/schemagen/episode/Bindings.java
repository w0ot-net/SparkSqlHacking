package org.glassfish.jaxb.core.v2.schemagen.episode;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlElement("bindings")
public interface Bindings extends TypedXmlWriter {
   @XmlElement
   Bindings bindings();

   @XmlElement("class")
   Klass klass();

   Klass typesafeEnumClass();

   @XmlElement
   SchemaBindings schemaBindings();

   @XmlAttribute
   void scd(String var1);

   @XmlAttribute
   void version(String var1);
}
