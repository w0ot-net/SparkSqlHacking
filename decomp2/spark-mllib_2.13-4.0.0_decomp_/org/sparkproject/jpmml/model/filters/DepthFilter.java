package org.sparkproject.jpmml.model.filters;

import org.sparkproject.dmg.pmml.PMMLObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class DepthFilter extends ElementFilter {
   private int depth = 0;
   private int limit = 0;

   public DepthFilter(String localName, int limit) {
      super(localName);
      this.setLimit(limit);
   }

   public DepthFilter(String namespaceURI, String localName, int limit) {
      super(namespaceURI, localName);
      this.setLimit(limit);
   }

   public DepthFilter(Class clazz, int limit) {
      super(clazz);
      this.setLimit(limit);
   }

   public DepthFilter(XMLReader reader, String localName, int limit) {
      super(reader, localName);
      this.setLimit(limit);
   }

   public DepthFilter(XMLReader reader, String namespaceURI, String localName, int limit) {
      super(reader, namespaceURI, localName);
      this.setLimit(limit);
   }

   public DepthFilter(XMLReader reader, Class clazz, int limit) {
      super(reader, clazz);
      this.setLimit(limit);
   }

   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
      if (this.matches(namespaceURI, localName)) {
         int limit = this.getLimit();
         ++this.depth;
         if (this.depth > limit) {
            throw new SAXException("Too deeply nested " + this.getQualifiedName() + " elements");
         }
      }

      super.startElement(namespaceURI, localName, qualifiedName, attributes);
   }

   public void endElement(String namespaceURI, String localName, String qualifiedName) throws SAXException {
      if (this.matches(namespaceURI, localName)) {
         --this.depth;
      }

      super.endElement(namespaceURI, localName, qualifiedName);
   }

   public int getLimit() {
      return this.limit;
   }

   private void setLimit(int limit) {
      if (limit < 0) {
         throw new IllegalArgumentException();
      } else {
         this.limit = limit;
      }
   }
}
