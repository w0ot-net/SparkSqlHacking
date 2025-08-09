package org.glassfish.jaxb.core.v2.schemagen.episode;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

public interface Klass extends TypedXmlWriter {
   @XmlAttribute
   void ref(String var1);
}
