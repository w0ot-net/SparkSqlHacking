package org.apache.ivy.util;

import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContextualSAXHandler extends DefaultHandler {
   private Stack contextStack = new Stack();
   private StringBuilder buffer = new StringBuilder();

   public void characters(char[] ch, int start, int length) throws SAXException {
      this.buffer.append(ch, start, length);
   }

   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      this.contextStack.push(qName);
      this.buffer.setLength(0);
   }

   public void endElement(String uri, String localName, String qName) throws SAXException {
      this.contextStack.pop();
      this.buffer.setLength(0);
   }

   protected String getContext() {
      return StringUtils.joinArray((String[])this.contextStack.toArray(new String[this.contextStack.size()]), "/");
   }

   protected String getText() {
      return this.buffer.toString();
   }
}
