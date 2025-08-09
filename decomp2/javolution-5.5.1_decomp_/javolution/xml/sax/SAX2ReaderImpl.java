package javolution.xml.sax;

import java.io.IOException;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.text.Text;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

public final class SAX2ReaderImpl implements org.xml.sax.XMLReader, Reusable {
   private static Sax2DefaultHandler DEFAULT_HANDLER = new Sax2DefaultHandler();
   private final XMLReaderImpl _parser = new XMLReaderImpl();
   private final Proxy _proxy = new Proxy();

   public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
      return this._parser.getFeature(name);
   }

   public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
      this._parser.setFeature(name, value);
   }

   public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
      return this._parser.getProperty(name);
   }

   public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
      this._parser.setProperty(name, value);
   }

   public void setEntityResolver(EntityResolver resolver) {
      this._parser.setEntityResolver(resolver);
   }

   public EntityResolver getEntityResolver() {
      return this._parser.getEntityResolver();
   }

   public void setDTDHandler(DTDHandler handler) {
      this._parser.setDTDHandler(handler);
   }

   public DTDHandler getDTDHandler() {
      return this._parser.getDTDHandler();
   }

   public void setContentHandler(org.xml.sax.ContentHandler handler) {
      if (handler != null) {
         this._proxy._sax2Handler = handler;
         this._parser.setContentHandler(this._proxy);
      } else {
         throw new NullPointerException();
      }
   }

   public org.xml.sax.ContentHandler getContentHandler() {
      return this._proxy._sax2Handler == DEFAULT_HANDLER ? null : this._proxy._sax2Handler;
   }

   public void setErrorHandler(ErrorHandler handler) {
      this._parser.setErrorHandler(handler);
   }

   public ErrorHandler getErrorHandler() {
      return this._parser.getErrorHandler();
   }

   public void parse(InputSource input) throws IOException, SAXException {
      try {
         this._parser.parse(input);
      } finally {
         this._parser.reset();
      }

   }

   public void parse(String systemId) throws IOException, SAXException {
      try {
         this._parser.parse(systemId);
      } finally {
         this._parser.reset();
      }

   }

   public void reset() {
      this._parser.reset();
   }

   private static CharSequence toCharSequence(Object obj) {
      return (CharSequence)(obj instanceof CharSequence ? (CharSequence)obj : Text.valueOf(obj));
   }

   private static final class Proxy implements ContentHandler, org.xml.sax.Attributes {
      private org.xml.sax.ContentHandler _sax2Handler;
      private Attributes _attributes;

      public Proxy() {
         this._sax2Handler = SAX2ReaderImpl.DEFAULT_HANDLER;
      }

      public void setDocumentLocator(Locator locator) {
         this._sax2Handler.setDocumentLocator(locator);
      }

      public void startDocument() throws SAXException {
         this._sax2Handler.startDocument();
      }

      public void endDocument() throws SAXException {
         this._sax2Handler.endDocument();
         this._sax2Handler = SAX2ReaderImpl.DEFAULT_HANDLER;
      }

      public void startPrefixMapping(CharArray prefix, CharArray uri) throws SAXException {
         this._sax2Handler.startPrefixMapping(prefix.toString(), uri.toString());
      }

      public void endPrefixMapping(CharArray prefix) throws SAXException {
         this._sax2Handler.endPrefixMapping(prefix.toString());
      }

      public void startElement(CharArray namespaceURI, CharArray localName, CharArray qName, Attributes atts) throws SAXException {
         this._attributes = atts;
         this._sax2Handler.startElement(namespaceURI.toString(), localName.toString(), qName.toString(), this);
      }

      public void endElement(CharArray namespaceURI, CharArray localName, CharArray qName) throws SAXException {
         this._sax2Handler.endElement(namespaceURI.toString(), localName.toString(), qName.toString());
      }

      public void characters(char[] ch, int start, int length) throws SAXException {
         this._sax2Handler.characters(ch, start, length);
      }

      public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
         this._sax2Handler.ignorableWhitespace(ch, start, length);
      }

      public void processingInstruction(CharArray target, CharArray data) throws SAXException {
         this._sax2Handler.processingInstruction(target.toString(), data.toString());
      }

      public void skippedEntity(CharArray name) throws SAXException {
         this._sax2Handler.skippedEntity(name.toString());
      }

      public int getLength() {
         return this._attributes != null ? this._attributes.getLength() : 0;
      }

      public String getURI(int index) {
         CharSequence chars = this._attributes != null ? this._attributes.getURI(index) : null;
         return chars != null ? chars.toString() : "";
      }

      public String getLocalName(int index) {
         CharSequence chars = this._attributes != null ? this._attributes.getLocalName(index) : null;
         return chars != null ? chars.toString() : "";
      }

      public String getQName(int index) {
         CharSequence chars = this._attributes != null ? this._attributes.getQName(index) : null;
         return chars != null ? chars.toString() : "";
      }

      public String getType(int index) {
         return this._attributes != null ? this._attributes.getType(index).toString() : null;
      }

      public String getValue(int index) {
         CharSequence chars = this._attributes != null ? this._attributes.getValue(index) : null;
         return chars != null ? chars.toString() : null;
      }

      public int getIndex(String uri, String localName) {
         return uri != null && localName != null && this._attributes != null ? this._attributes.getIndex(SAX2ReaderImpl.toCharSequence(uri), SAX2ReaderImpl.toCharSequence(localName)) : -1;
      }

      public int getIndex(String qName) {
         return qName != null && this._attributes != null ? this._attributes.getIndex(SAX2ReaderImpl.toCharSequence(qName)) : -1;
      }

      public String getType(String uri, String localName) {
         return uri != null && localName != null && this._attributes != null ? this._attributes.getType(SAX2ReaderImpl.toCharSequence(uri), SAX2ReaderImpl.toCharSequence(localName)).toString() : null;
      }

      public String getType(String qName) {
         return qName != null && this._attributes != null ? this._attributes.getType(SAX2ReaderImpl.toCharSequence(qName)).toString() : null;
      }

      public String getValue(String uri, String localName) {
         return uri != null && localName != null && this._attributes != null && this._attributes.getValue(SAX2ReaderImpl.toCharSequence(uri), SAX2ReaderImpl.toCharSequence(localName)) != null ? this._attributes.getValue(SAX2ReaderImpl.toCharSequence(uri), SAX2ReaderImpl.toCharSequence(localName)).toString() : null;
      }

      public String getValue(String qName) {
         return qName != null && this._attributes != null ? this._attributes.getValue(SAX2ReaderImpl.toCharSequence(qName)).toString() : null;
      }
   }

   private static final class Sax2DefaultHandler implements EntityResolver, DTDHandler, org.xml.sax.ContentHandler, ErrorHandler {
      private Sax2DefaultHandler() {
      }

      public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
         return null;
      }

      public void notationDecl(String name, String publicId, String systemId) throws SAXException {
      }

      public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
      }

      public void setDocumentLocator(Locator locator) {
      }

      public void startDocument() throws SAXException {
      }

      public void endDocument() throws SAXException {
      }

      public void startPrefixMapping(String prefix, String uri) throws SAXException {
      }

      public void endPrefixMapping(String prefix) throws SAXException {
      }

      public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes atts) throws SAXException {
      }

      public void endElement(String uri, String localName, String qName) throws SAXException {
      }

      public void characters(char[] ch, int start, int length) throws SAXException {
      }

      public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
      }

      public void processingInstruction(String target, String data) throws SAXException {
      }

      public void skippedEntity(String name) throws SAXException {
      }

      public void warning(SAXParseException exception) throws SAXException {
      }

      public void error(SAXParseException exception) throws SAXException {
      }

      public void fatalError(SAXParseException exception) throws SAXException {
         throw exception;
      }
   }
}
