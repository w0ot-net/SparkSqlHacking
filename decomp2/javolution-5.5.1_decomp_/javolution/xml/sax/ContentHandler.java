package javolution.xml.sax;

import javolution.text.CharArray;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public interface ContentHandler {
   void setDocumentLocator(Locator var1);

   void startDocument() throws SAXException;

   void endDocument() throws SAXException;

   void startPrefixMapping(CharArray var1, CharArray var2) throws SAXException;

   void endPrefixMapping(CharArray var1) throws SAXException;

   void startElement(CharArray var1, CharArray var2, CharArray var3, Attributes var4) throws SAXException;

   void endElement(CharArray var1, CharArray var2, CharArray var3) throws SAXException;

   void characters(char[] var1, int var2, int var3) throws SAXException;

   void ignorableWhitespace(char[] var1, int var2, int var3) throws SAXException;

   void processingInstruction(CharArray var1, CharArray var2) throws SAXException;

   void skippedEntity(CharArray var1) throws SAXException;
}
