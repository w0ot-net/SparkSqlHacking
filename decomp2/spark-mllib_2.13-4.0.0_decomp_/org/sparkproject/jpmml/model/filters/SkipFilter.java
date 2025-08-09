package org.sparkproject.jpmml.model.filters;

import org.sparkproject.dmg.pmml.PMMLObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SkipFilter extends ElementFilter {
   private int depth = 0;

   public SkipFilter(String localName) {
      super(localName);
   }

   public SkipFilter(String namespaceURI, String localName) {
      super(namespaceURI, localName);
   }

   public SkipFilter(Class clazz) {
      super(clazz);
   }

   public SkipFilter(XMLReader reader, String localName) {
      super(reader, localName);
   }

   public SkipFilter(XMLReader reader, String namespaceURI, String localName) {
      super(reader, namespaceURI, localName);
   }

   public SkipFilter(XMLReader reader, Class clazz) {
      super(reader, clazz);
   }

   public Attributes filterAttributes(String namespaceURI, String localName, Attributes attributes) {
      return attributes;
   }

   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
      if (this.matches(namespaceURI, localName)) {
         ++this.depth;
      } else if (!this.isSkipping()) {
         Attributes filteredAttributes = this.filterAttributes(namespaceURI, localName, attributes);
         super.startElement(namespaceURI, localName, qualifiedName, filteredAttributes);
      }
   }

   public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
      if (this.matches(namespaceURI, localName)) {
         --this.depth;
      } else if (!this.isSkipping()) {
         super.endElement(namespaceURI, localName, qualifiedName);
      }
   }

   public void characters(char[] buffer, int index, int length) throws SAXException {
      if (!this.isSkipping()) {
         super.characters(buffer, index, length);
      }
   }

   private boolean isSkipping() {
      return this.depth > 0;
   }
}
