package org.glassfish.jaxb.runtime.v2;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import java.util.Map;

public class JAXBContextFactory implements jakarta.xml.bind.JAXBContextFactory {
   public JAXBContext createContext(Class[] classesToBeBound, Map properties) throws JAXBException {
      return ContextFactory.createContext(classesToBeBound, properties);
   }

   public JAXBContext createContext(String contextPath, ClassLoader classLoader, Map properties) throws JAXBException {
      return ContextFactory.createContext(contextPath, classLoader, properties);
   }
}
