package javolution.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import javolution.context.ObjectFactory;
import javolution.lang.Configurable;

public abstract class XMLInputFactory {
   public static final Configurable CLASS = new Configurable(Default.class) {
   };
   public static final String IS_COALESCING = "javolution.xml.stream.isCoalescing";
   public static final String ENTITIES = "javolution.xml.stream.entities";
   private static ObjectFactory XML_READER_FACTORY = new ObjectFactory() {
      protected Object create() {
         return new XMLStreamReaderImpl();
      }

      protected void cleanup(Object obj) {
         ((XMLStreamReaderImpl)obj).reset();
      }
   };

   protected XMLInputFactory() {
   }

   public static XMLInputFactory newInstance() {
      Class cls = (Class)CLASS.get();
      return (XMLInputFactory)ObjectFactory.getInstance(cls).object();
   }

   public abstract XMLStreamReader createXMLStreamReader(Reader var1) throws XMLStreamException;

   public abstract XMLStreamReader createXMLStreamReader(InputStream var1) throws XMLStreamException;

   public abstract XMLStreamReader createXMLStreamReader(InputStream var1, String var2) throws XMLStreamException;

   public abstract void setProperty(String var1, Object var2) throws IllegalArgumentException;

   public abstract Object getProperty(String var1) throws IllegalArgumentException;

   public abstract boolean isPropertySupported(String var1);

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Default();
         }
      }, Default.class);
   }

   private static final class Default extends XMLInputFactory {
      Map _entities;

      private Default() {
         this._entities = null;
      }

      public XMLStreamReader createXMLStreamReader(Reader reader) throws XMLStreamException {
         XMLStreamReaderImpl xmlReader = this.newReader();
         xmlReader.setInput(reader);
         return xmlReader;
      }

      public XMLStreamReader createXMLStreamReader(InputStream stream) throws XMLStreamException {
         XMLStreamReaderImpl xmlReader = this.newReader();
         xmlReader.setInput(stream);
         return xmlReader;
      }

      public XMLStreamReader createXMLStreamReader(InputStream stream, String encoding) throws XMLStreamException {
         XMLStreamReaderImpl xmlReader = this.newReader();
         xmlReader.setInput(stream, encoding);
         return xmlReader;
      }

      public void setProperty(String name, Object value) throws IllegalArgumentException {
         if (!name.equals("javolution.xml.stream.isCoalescing")) {
            if (!name.equals("javolution.xml.stream.entities")) {
               throw new IllegalArgumentException("Property: " + name + " not supported");
            }

            this._entities = (Map)value;
         }

      }

      public Object getProperty(String name) throws IllegalArgumentException {
         if (name.equals("javolution.xml.stream.isCoalescing")) {
            return Boolean.TRUE;
         } else if (name.equals("javolution.xml.stream.entities")) {
            return this._entities;
         } else {
            throw new IllegalArgumentException("Property: " + name + " not supported");
         }
      }

      public boolean isPropertySupported(String name) {
         return name.equals("javolution.xml.stream.isCoalescing") || name.equals("javolution.xml.stream.entities");
      }

      private XMLStreamReaderImpl newReader() {
         XMLStreamReaderImpl xmlReader = (XMLStreamReaderImpl)XMLInputFactory.XML_READER_FACTORY.object();
         if (this._entities != null) {
            xmlReader.setEntities(this._entities);
         }

         xmlReader._objectFactory = XMLInputFactory.XML_READER_FACTORY;
         return xmlReader;
      }
   }
}
