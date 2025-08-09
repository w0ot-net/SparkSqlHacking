package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeTypeRef;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Transducer;
import org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer;
import org.xml.sax.SAXException;

final class ArrayElementLeafProperty extends ArrayElementProperty {
   private final Transducer xducer;

   public ArrayElementLeafProperty(JAXBContextImpl p, RuntimeElementPropertyInfo prop) {
      super(p, prop);

      assert prop.getTypes().size() == 1;

      this.xducer = ((RuntimeTypeRef)prop.getTypes().get(0)).getTransducer();

      assert this.xducer != null;

   }

   public void serializeItem(JaxBeanInfo bi, Object item, XMLSerializer w) throws SAXException, AccessorException, IOException, XMLStreamException {
      this.xducer.declareNamespace(item, w);
      w.endNamespaceDecls(item);
      w.endAttributes();
      this.xducer.writeText(w, item, this.fieldName);
   }
}
