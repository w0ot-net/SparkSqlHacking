package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import org.xml.sax.SAXException;

public interface Receiver {
   void receive(UnmarshallingContext.State var1, Object var2) throws SAXException;
}
