package javolution.xml.stream;

import java.io.OutputStream;
import java.io.Writer;
import javolution.context.ObjectFactory;
import javolution.lang.Configurable;

public abstract class XMLOutputFactory {
   public static final Configurable CLASS = new Configurable(Default.class) {
   };
   public static final String IS_REPAIRING_NAMESPACES = "javolution.xml.stream.isRepairingNamespaces";
   public static final String REPAIRING_PREFIX = "javolution.xml.stream.repairingPrefix";
   public static final String INDENTATION = "javolution.xml.stream.indentation";
   public static final String LINE_SEPARATOR = "javolution.xml.stream.lineSeparator";
   public static final String AUTOMATIC_EMPTY_ELEMENTS = "javolution.xml.stream.automaticEmptyElements";
   public static final String NO_EMPTY_ELEMENT_TAG = "javolution.xml.stream.noEmptyElementTag";
   private static final ObjectFactory XML_WRITER_FACTORY = new ObjectFactory() {
      protected Object create() {
         return new XMLStreamWriterImpl();
      }

      protected void cleanup(Object obj) {
         ((XMLStreamWriterImpl)obj).reset();
      }
   };

   protected XMLOutputFactory() {
   }

   public static XMLOutputFactory newInstance() {
      Class cls = (Class)CLASS.get();
      return (XMLOutputFactory)ObjectFactory.getInstance(cls).object();
   }

   public abstract XMLStreamWriter createXMLStreamWriter(Writer var1) throws XMLStreamException;

   public abstract XMLStreamWriter createXMLStreamWriter(OutputStream var1) throws XMLStreamException;

   public abstract XMLStreamWriter createXMLStreamWriter(OutputStream var1, String var2) throws XMLStreamException;

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

   private static final class Default extends XMLOutputFactory {
      private Boolean _isRepairingNamespaces;
      private String _repairingPrefix;
      private Boolean _automaticEmptyElements;
      private Boolean _noEmptyElementTag;
      private String _indentation;
      private String _lineSeparator;

      private Default() {
         this._isRepairingNamespaces = Boolean.FALSE;
         this._repairingPrefix = "ns";
         this._automaticEmptyElements = Boolean.FALSE;
         this._noEmptyElementTag = Boolean.FALSE;
         this._lineSeparator = "\n";
      }

      public XMLStreamWriter createXMLStreamWriter(Writer writer) throws XMLStreamException {
         XMLStreamWriterImpl xmlWriter = this.newWriter();
         xmlWriter.setOutput(writer);
         return xmlWriter;
      }

      public XMLStreamWriter createXMLStreamWriter(OutputStream stream) throws XMLStreamException {
         XMLStreamWriterImpl xmlWriter = this.newWriter();
         xmlWriter.setOutput(stream);
         return xmlWriter;
      }

      public XMLStreamWriter createXMLStreamWriter(OutputStream stream, String encoding) throws XMLStreamException {
         if (encoding != null && !encoding.equals("UTF-8") && !encoding.equals("utf-8")) {
            XMLStreamWriterImpl xmlWriter = this.newWriter();
            xmlWriter.setOutput(stream, encoding);
            return xmlWriter;
         } else {
            return this.createXMLStreamWriter(stream);
         }
      }

      private XMLStreamWriterImpl newWriter() {
         XMLStreamWriterImpl xmlWriter = (XMLStreamWriterImpl)XMLOutputFactory.XML_WRITER_FACTORY.object();
         xmlWriter._objectFactory = XMLOutputFactory.XML_WRITER_FACTORY;
         xmlWriter.setRepairingNamespaces(this._isRepairingNamespaces);
         xmlWriter.setRepairingPrefix(this._repairingPrefix);
         xmlWriter.setIndentation(this._indentation);
         xmlWriter.setLineSeparator(this._lineSeparator);
         xmlWriter.setAutomaticEmptyElements(this._automaticEmptyElements);
         xmlWriter.setNoEmptyElementTag(this._noEmptyElementTag);
         return xmlWriter;
      }

      public void setProperty(String name, Object value) throws IllegalArgumentException {
         if (name.equals("javolution.xml.stream.isRepairingNamespaces")) {
            this._isRepairingNamespaces = (Boolean)value;
         } else if (name.equals("javolution.xml.stream.repairingPrefix")) {
            this._repairingPrefix = (String)value;
         } else if (name.equals("javolution.xml.stream.automaticEmptyElements")) {
            this._automaticEmptyElements = (Boolean)value;
         } else if (name.equals("javolution.xml.stream.noEmptyElementTag")) {
            this._noEmptyElementTag = (Boolean)value;
         } else if (name.equals("javolution.xml.stream.indentation")) {
            this._indentation = (String)value;
         } else {
            if (!name.equals("javolution.xml.stream.lineSeparator")) {
               throw new IllegalArgumentException("Property: " + name + " not supported");
            }

            this._lineSeparator = (String)value;
         }

      }

      public Object getProperty(String name) throws IllegalArgumentException {
         if (name.equals("javolution.xml.stream.isRepairingNamespaces")) {
            return this._isRepairingNamespaces;
         } else if (name.equals("javolution.xml.stream.repairingPrefix")) {
            return this._repairingPrefix;
         } else if (name.equals("javolution.xml.stream.automaticEmptyElements")) {
            return this._automaticEmptyElements;
         } else if (name.equals("javolution.xml.stream.noEmptyElementTag")) {
            return this._noEmptyElementTag;
         } else if (name.equals("javolution.xml.stream.indentation")) {
            return this._indentation;
         } else if (name.equals("javolution.xml.stream.lineSeparator")) {
            return this._lineSeparator;
         } else {
            throw new IllegalArgumentException("Property: " + name + " not supported");
         }
      }

      public boolean isPropertySupported(String name) {
         return name.equals("javolution.xml.stream.isRepairingNamespaces") || name.equals("javolution.xml.stream.repairingPrefix") || name.equals("javolution.xml.stream.automaticEmptyElements") || name.equals("javolution.xml.stream.noEmptyElementTag") || name.equals("javolution.xml.stream.indentation") || name.equals("javolution.xml.stream.lineSeparator");
      }
   }
}
