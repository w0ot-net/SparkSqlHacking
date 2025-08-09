package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import javax.xml.namespace.NamespaceContext;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.xml.sax.SAXException;

public interface XmlVisitor {
   void startDocument(LocatorEx var1, NamespaceContext var2) throws SAXException;

   void endDocument() throws SAXException;

   void startElement(TagName var1) throws SAXException;

   void endElement(TagName var1) throws SAXException;

   void startPrefixMapping(String var1, String var2) throws SAXException;

   void endPrefixMapping(String var1) throws SAXException;

   void text(CharSequence var1) throws SAXException;

   UnmarshallingContext getContext();

   TextPredictor getPredictor();

   public interface TextPredictor {
      boolean expectText();
   }
}
