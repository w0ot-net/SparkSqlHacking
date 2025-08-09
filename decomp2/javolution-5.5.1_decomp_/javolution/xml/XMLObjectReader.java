package javolution.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javolution.context.ObjectFactory;
import javolution.lang.Reusable;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLStreamReader;

public class XMLObjectReader implements Reusable {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      protected Object create() {
         return new XMLObjectReader();
      }
   };
   private final XMLFormat.InputElement _xml = new XMLFormat.InputElement();
   private Reader _reader;
   private InputStream _inputStream;
   private boolean _isFactoryProduced;

   public static XMLObjectReader newInstance(InputStream in) throws XMLStreamException {
      XMLObjectReader reader = (XMLObjectReader)FACTORY.object();
      reader._isFactoryProduced = true;
      reader.setInput(in);
      return reader;
   }

   public static XMLObjectReader newInstance(InputStream in, String encoding) throws XMLStreamException {
      XMLObjectReader reader = (XMLObjectReader)FACTORY.object();
      reader._isFactoryProduced = true;
      reader.setInput(in, encoding);
      return reader;
   }

   public static XMLObjectReader newInstance(Reader in) throws XMLStreamException {
      XMLObjectReader reader = (XMLObjectReader)FACTORY.object();
      reader._isFactoryProduced = true;
      reader.setInput(in);
      return reader;
   }

   public static void recycle(XMLObjectReader that) {
      FACTORY.recycle(that);
   }

   public XMLStreamReader getStreamReader() {
      return this._xml._reader;
   }

   public XMLObjectReader setInput(InputStream in) throws XMLStreamException {
      if (this._inputStream == null && this._reader == null) {
         this._xml._reader.setInput(in);
         this._inputStream = in;
         return this;
      } else {
         throw new IllegalStateException("Reader not closed or reset");
      }
   }

   public XMLObjectReader setInput(InputStream in, String encoding) throws XMLStreamException {
      if (this._inputStream == null && this._reader == null) {
         this._xml._reader.setInput(in, encoding);
         this._inputStream = in;
         return this;
      } else {
         throw new IllegalStateException("Reader not closed or reset");
      }
   }

   public XMLObjectReader setInput(Reader in) throws XMLStreamException {
      if (this._inputStream == null && this._reader == null) {
         this._xml._reader.setInput(in);
         this._reader = in;
         return this;
      } else {
         throw new IllegalStateException("Reader not closed or reset");
      }
   }

   public XMLObjectReader setBinding(XMLBinding binding) {
      this._xml.setBinding(binding);
      return this;
   }

   public XMLObjectReader setReferenceResolver(XMLReferenceResolver referenceResolver) {
      this._xml.setReferenceResolver(referenceResolver);
      return this;
   }

   public boolean hasNext() throws XMLStreamException {
      return this._xml.hasNext();
   }

   public Object read() throws XMLStreamException {
      return this._xml.getNext();
   }

   public Object read(String name) throws XMLStreamException {
      return this._xml.get(name);
   }

   public Object read(String localName, String uri) throws XMLStreamException {
      return this._xml.get(localName, uri);
   }

   public Object read(String name, Class cls) throws XMLStreamException {
      return this._xml.get(name, cls);
   }

   public Object read(String localName, String uri, Class cls) throws XMLStreamException {
      return this._xml.get(localName, uri, cls);
   }

   public void close() throws XMLStreamException {
      try {
         if (this._inputStream != null) {
            this._inputStream.close();
            this.reset();
         } else if (this._reader != null) {
            this._reader.close();
            this.reset();
         }

         if (this._isFactoryProduced) {
            FACTORY.recycle(this);
         }

      } catch (IOException e) {
         throw new XMLStreamException(e);
      }
   }

   public void reset() {
      this._xml.reset();
      this._reader = null;
      this._inputStream = null;
   }
}
