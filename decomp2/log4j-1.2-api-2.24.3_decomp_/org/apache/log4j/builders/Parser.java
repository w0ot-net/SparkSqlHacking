package org.apache.log4j.builders;

import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.w3c.dom.Element;

public interface Parser extends Builder {
   Object parse(Element element, XmlConfiguration config);

   Object parse(PropertiesConfiguration config);
}
