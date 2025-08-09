package org.sparkproject.jpmml.model.filters;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public abstract class NamespaceFilter extends XMLFilterImpl {
   public NamespaceFilter() {
   }

   public NamespaceFilter(XMLReader reader) {
      super(reader);
   }

   public abstract String filterNamespaceURI(String var1);

   public void startPrefixMapping(String prefix, String namespaceURI) throws SAXException {
      namespaceURI = this.filterNamespaceURI(namespaceURI);
      super.startPrefixMapping(prefix, namespaceURI);
   }

   public void endPrefixMapping(String prefix) throws SAXException {
      super.endPrefixMapping(prefix);
   }

   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
      namespaceURI = this.filterNamespaceURI(namespaceURI);
      super.startElement(namespaceURI, localName, qualifiedName, attributes);
   }

   public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
      namespaceURI = this.filterNamespaceURI(namespaceURI);
      super.endElement(namespaceURI, localName, qualifiedName);
   }
}
