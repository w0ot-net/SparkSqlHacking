package javolution.xml.sax;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javolution.lang.Reflection;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLStreamReaderImpl;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class XMLReaderImpl implements XMLReader, Reusable {
   private static DefaultHandler DEFAULT_HANDLER = new DefaultHandler();
   private ContentHandler _contentHandler;
   private ErrorHandler _errorHandler;
   private final XMLStreamReaderImpl _xmlReader = new XMLStreamReaderImpl();
   private static final Reflection.Constructor NEW_URL = Reflection.getInstance().getConstructor("java.net.URL(j2me.lang.String)");
   private static final Reflection.Method OPEN_STREAM = Reflection.getInstance().getMethod("java.net.URL.openStream()");
   private static final Reflection.Constructor NEW_FILE_INPUT_STREAM = Reflection.getInstance().getConstructor("j2me.io.FileInputStream(j2me.lang.String)");
   private EntityResolver _entityResolver;
   private DTDHandler _dtdHandler;
   private static final CharArray NO_CHAR = new CharArray("");

   public XMLReaderImpl() {
      this.setContentHandler(DEFAULT_HANDLER);
      this.setErrorHandler(DEFAULT_HANDLER);
   }

   public void parse(InputStream in) throws IOException, SAXException {
      try {
         this._xmlReader.setInput(in);
         this.parseAll();
      } catch (XMLStreamException e) {
         if (e.getNestedException() instanceof IOException) {
            throw (IOException)e.getNestedException();
         }

         throw new SAXException(e.getMessage());
      } finally {
         this._xmlReader.reset();
      }

   }

   public void parse(InputStream in, String encoding) throws IOException, SAXException {
      try {
         this._xmlReader.setInput(in, encoding);
         this.parseAll();
      } catch (XMLStreamException e) {
         if (e.getNestedException() instanceof IOException) {
            throw (IOException)e.getNestedException();
         }

         throw new SAXException(e.getMessage());
      } finally {
         this._xmlReader.reset();
      }

   }

   public void parse(Reader reader) throws IOException, SAXException {
      try {
         this._xmlReader.setInput(reader);
         this.parseAll();
      } catch (XMLStreamException e) {
         if (e.getNestedException() instanceof IOException) {
            throw (IOException)e.getNestedException();
         }

         throw new SAXException(e.getMessage());
      } finally {
         this._xmlReader.reset();
      }

   }

   public void parse(InputSource input) throws IOException, SAXException {
      Reader reader = input.getCharacterStream();
      if (reader != null) {
         this.parse(reader);
      } else {
         InputStream inStream = input.getByteStream();
         if (inStream != null) {
            this.parse(inStream, input.getEncoding());
         } else {
            this.parse(input.getSystemId());
         }
      }

   }

   public void parse(String systemId) throws IOException, SAXException {
      InputStream inStream;
      try {
         Object url = NEW_URL.newInstance((Object)systemId);
         inStream = (InputStream)OPEN_STREAM.invoke(url);
      } catch (Exception var6) {
         try {
            inStream = (InputStream)NEW_FILE_INPUT_STREAM.newInstance((Object)systemId);
         } catch (Exception var5) {
            throw new UnsupportedOperationException("Cannot parse " + systemId);
         }
      }

      this.parse(inStream);
   }

   public void setContentHandler(ContentHandler handler) {
      if (handler != null) {
         this._contentHandler = handler;
      } else {
         throw new NullPointerException();
      }
   }

   public ContentHandler getContentHandler() {
      return this._contentHandler == DEFAULT_HANDLER ? null : this._contentHandler;
   }

   public void setErrorHandler(ErrorHandler handler) {
      if (handler != null) {
         this._errorHandler = handler;
      } else {
         throw new NullPointerException();
      }
   }

   public ErrorHandler getErrorHandler() {
      return this._errorHandler == DEFAULT_HANDLER ? null : this._errorHandler;
   }

   public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
      if (name.equals("http://xml.org/sax/features/namespaces")) {
         return true;
      } else if (name.equals("http://xml.org/sax/features/namespace-prefixes")) {
         return true;
      } else {
         throw new SAXNotRecognizedException("Feature " + name + " not recognized");
      }
   }

   public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
      if (!name.equals("http://xml.org/sax/features/namespaces") && !name.equals("http://xml.org/sax/features/namespace-prefixes")) {
         throw new SAXNotRecognizedException("Feature " + name + " not recognized");
      }
   }

   public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
      throw new SAXNotRecognizedException("Property " + name + " not recognized");
   }

   public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
      throw new SAXNotRecognizedException("Property " + name + " not recognized");
   }

   public void setEntityResolver(EntityResolver resolver) {
      this._entityResolver = resolver;
   }

   public EntityResolver getEntityResolver() {
      return this._entityResolver;
   }

   public void setDTDHandler(DTDHandler handler) {
      this._dtdHandler = handler;
   }

   public DTDHandler getDTDHandler() {
      return this._dtdHandler;
   }

   public void reset() {
      this.setContentHandler(DEFAULT_HANDLER);
      this.setErrorHandler(DEFAULT_HANDLER);
      this._xmlReader.reset();
   }

   private void parseAll() throws XMLStreamException, SAXException {
      int eventType = this._xmlReader.getEventType();
      if (eventType != 7) {
         throw new SAXException("Currently parsing");
      } else {
         this._contentHandler.startDocument();
         boolean doContinue = true;

         while(doContinue) {
            switch (this._xmlReader.next()) {
               case 1:
                  int i = 0;

                  for(int count = this._xmlReader.getNamespaceCount(); i < count; ++i) {
                     CharArray prefix = this._xmlReader.getNamespacePrefix(i);
                     prefix = prefix == null ? NO_CHAR : prefix;
                     CharArray uri = this._xmlReader.getNamespaceURI(i);
                     this._contentHandler.startPrefixMapping(prefix, uri);
                  }

                  CharArray uri = this._xmlReader.getNamespaceURI();
                  uri = uri == null ? NO_CHAR : uri;
                  CharArray localName = this._xmlReader.getLocalName();
                  CharArray qName = this._xmlReader.getQName();
                  this._contentHandler.startElement(uri, localName, qName, this._xmlReader.getAttributes());
                  break;
               case 2:
                  CharArray uri = this._xmlReader.getNamespaceURI();
                  uri = uri == null ? NO_CHAR : uri;
                  CharArray localName = this._xmlReader.getLocalName();
                  CharArray qName = this._xmlReader.getQName();
                  this._contentHandler.endElement(uri, localName, qName);
                  int i = 0;

                  for(int count = this._xmlReader.getNamespaceCount(); i < count; ++i) {
                     CharArray prefix = this._xmlReader.getNamespacePrefix(i);
                     prefix = prefix == null ? NO_CHAR : prefix;
                     this._contentHandler.endPrefixMapping(prefix);
                  }
                  break;
               case 3:
                  this._contentHandler.processingInstruction(this._xmlReader.getPITarget(), this._xmlReader.getPIData());
                  break;
               case 4:
               case 12:
                  CharArray text = this._xmlReader.getText();
                  this._contentHandler.characters(text.array(), text.offset(), text.length());
               case 5:
               case 7:
               case 9:
               case 10:
               case 11:
               default:
                  break;
               case 6:
                  CharArray text = this._xmlReader.getText();
                  this._contentHandler.ignorableWhitespace(text.array(), text.offset(), text.length());
                  break;
               case 8:
                  doContinue = false;
                  this._xmlReader.close();
            }
         }

      }
   }
}
