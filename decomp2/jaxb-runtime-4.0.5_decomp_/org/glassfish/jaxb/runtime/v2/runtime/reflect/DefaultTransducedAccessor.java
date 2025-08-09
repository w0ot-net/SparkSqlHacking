package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.runtime.Name;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.xml.sax.SAXException;

public abstract class DefaultTransducedAccessor extends TransducedAccessor {
   protected DefaultTransducedAccessor() {
   }

   public abstract String print(Object var1) throws AccessorException, SAXException;

   public void writeLeafElement(XMLSerializer w, Name tagName, Object o, String fieldName) throws SAXException, AccessorException, IOException, XMLStreamException {
      w.leafElement(tagName, this.print(o), fieldName);
   }

   public void writeText(XMLSerializer w, Object o, String fieldName) throws AccessorException, SAXException, IOException, XMLStreamException {
      w.text(this.print(o), fieldName);
   }
}
