package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

public interface ListIterator {
   boolean hasNext();

   Object next() throws SAXException, JAXBException;
}
