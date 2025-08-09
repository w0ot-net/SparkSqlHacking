package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.xml.sax.SAXException;

final class ArrayElementNodeProperty extends ArrayElementProperty {
   public ArrayElementNodeProperty(JAXBContextImpl p, RuntimeElementPropertyInfo prop) {
      super(p, prop);
   }

   public void serializeItem(JaxBeanInfo expected, Object item, XMLSerializer w) throws SAXException, IOException, XMLStreamException {
      if (item == null) {
         w.writeXsiNilTrue();
      } else {
         w.childAsXsiType(item, this.fieldName, expected, false);
      }

   }
}
