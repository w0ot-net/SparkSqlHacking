package org.glassfish.jaxb.core.v2.schemagen.episode;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

public interface Package extends TypedXmlWriter {
   @XmlAttribute
   void name(String var1);
}
