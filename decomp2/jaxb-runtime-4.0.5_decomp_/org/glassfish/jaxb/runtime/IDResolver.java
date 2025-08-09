package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.ValidationEventHandler;
import java.util.concurrent.Callable;
import org.xml.sax.SAXException;

public abstract class IDResolver {
   protected IDResolver() {
   }

   public void startDocument(ValidationEventHandler eventHandler) throws SAXException {
   }

   public void endDocument() throws SAXException {
   }

   public abstract void bind(String var1, Object var2) throws SAXException;

   public abstract Callable resolve(String var1, Class var2) throws SAXException;
}
