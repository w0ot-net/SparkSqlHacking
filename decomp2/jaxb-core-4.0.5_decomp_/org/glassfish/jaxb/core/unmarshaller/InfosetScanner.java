package org.glassfish.jaxb.core.unmarshaller;

import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface InfosetScanner {
   void scan(Object var1) throws SAXException;

   void setContentHandler(ContentHandler var1);

   ContentHandler getContentHandler();

   Object getCurrentElement();

   LocatorEx getLocator();
}
