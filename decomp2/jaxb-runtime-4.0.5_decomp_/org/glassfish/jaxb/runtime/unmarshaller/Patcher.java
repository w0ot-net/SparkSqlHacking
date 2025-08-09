package org.glassfish.jaxb.runtime.unmarshaller;

import org.xml.sax.SAXException;

public interface Patcher {
   void run() throws SAXException;
}
