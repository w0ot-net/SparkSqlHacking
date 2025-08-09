package org.glassfish.jaxb.runtime.v2.runtime;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.glassfish.jaxb.runtime.api.Bridge;
import org.xml.sax.SAXException;

abstract class InternalBridge extends Bridge {
   protected InternalBridge(JAXBContextImpl context) {
      super(context);
   }

   public JAXBContextImpl getContext() {
      return this.context;
   }

   abstract void marshal(Object var1, XMLSerializer var2) throws IOException, SAXException, XMLStreamException;
}
