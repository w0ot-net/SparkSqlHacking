package javolution.xml;

import javolution.lang.Reflection;
import javolution.text.CharArray;
import javolution.text.TextBuilder;
import javolution.text.TextFormat;
import javolution.xml.sax.Attributes;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLStreamReader;
import javolution.xml.stream.XMLStreamReaderImpl;
import javolution.xml.stream.XMLStreamWriter;
import javolution.xml.stream.XMLStreamWriterImpl;

public abstract class XMLFormat {
   private static final String NULL = "Null";
   private final Class _class;

   protected XMLFormat(Class forClass) {
      this._class = forClass;
      if (forClass != null) {
         Reflection.getInstance().setField(this, forClass, XMLFormat.class);
      }
   }

   public static XMLFormat getInstance(Class forClass) {
      XMLFormat objectFormat = XMLBinding.OBJECT_XML;
      XMLFormat xmlFormat = (XMLFormat)Reflection.getInstance().getField(forClass, XMLFormat.class, true);
      return xmlFormat != null ? xmlFormat : objectFormat;
   }

   public final Class getBoundClass() {
      return this._class;
   }

   public boolean isReferenceable() {
      return true;
   }

   public Object newInstance(Class cls, InputElement xml) throws XMLStreamException {
      try {
         return cls.newInstance();
      } catch (InstantiationException e) {
         throw new XMLStreamException(e);
      } catch (IllegalAccessException e) {
         throw new XMLStreamException(e);
      }
   }

   public abstract void write(Object var1, OutputElement var2) throws XMLStreamException;

   public abstract void read(InputElement var1, Object var2) throws XMLStreamException;

   public String toString() {
      Class boundClass = this.getBoundClass();
      return boundClass != null ? "Default XMLFormat for " + boundClass.getName() : "Dynamic XMLtFormat (" + this.hashCode() + ")";
   }

   private static CharSequence toCsq(Object str) {
      return (CharSequence)str;
   }

   public static final class InputElement {
      final XMLStreamReaderImpl _reader = new XMLStreamReaderImpl();
      private XMLBinding _binding;
      private XMLReferenceResolver _referenceResolver;
      private boolean _isReaderAtNext;

      InputElement() {
         this.reset();
      }

      public XMLStreamReader getStreamReader() {
         return this._reader;
      }

      public boolean hasNext() throws XMLStreamException {
         if (!this._isReaderAtNext) {
            this._isReaderAtNext = true;
            this._reader.nextTag();
         }

         return this._reader.getEventType() == 1;
      }

      public Object getNext() throws XMLStreamException {
         if (!this.hasNext()) {
            throw new XMLStreamException("No more element to read", this._reader.getLocation());
         } else if (this._reader.getLocalName().equals("Null")) {
            if (this._reader.next() != 2) {
               throw new XMLStreamException("Non Empty Null Element");
            } else {
               this._isReaderAtNext = false;
               return null;
            }
         } else {
            Object ref = this.readReference();
            if (ref != null) {
               return ref;
            } else {
               Class cls = this._binding.readClass(this._reader, false);
               return this.readInstanceOf(cls);
            }
         }
      }

      public Object get(String name) throws XMLStreamException {
         if (this.hasNext() && this._reader.getLocalName().equals(name)) {
            Object ref = this.readReference();
            if (ref != null) {
               return ref;
            } else {
               Class cls = this._binding.readClass(this._reader, true);
               return this.readInstanceOf(cls);
            }
         } else {
            return null;
         }
      }

      public Object get(String localName, String uri) throws XMLStreamException {
         if (uri == null) {
            return this.get(localName);
         } else if (this.hasNext() && this._reader.getLocalName().equals(localName) && this._reader.getNamespaceURI().equals(uri)) {
            Object ref = this.readReference();
            if (ref != null) {
               return ref;
            } else {
               Class cls = this._binding.readClass(this._reader, true);
               return this.readInstanceOf(cls);
            }
         } else {
            return null;
         }
      }

      public Object get(String name, Class cls) throws XMLStreamException {
         if (this.hasNext() && this._reader.getLocalName().equals(name)) {
            Object ref = this.readReference();
            return ref != null ? ref : this.readInstanceOf(cls);
         } else {
            return null;
         }
      }

      public Object get(String localName, String uri, Class cls) throws XMLStreamException {
         if (uri == null) {
            return this.get(localName, cls);
         } else if (this.hasNext() && this._reader.getLocalName().equals(localName) && this._reader.getNamespaceURI().equals(uri)) {
            Object ref = this.readReference();
            return ref != null ? ref : this.readInstanceOf(cls);
         } else {
            return null;
         }
      }

      private Object readReference() throws XMLStreamException {
         if (this._referenceResolver == null) {
            return null;
         } else {
            Object ref = this._referenceResolver.readReference(this);
            if (ref == null) {
               return null;
            } else if (this._reader.next() != 2) {
               throw new XMLStreamException("Non Empty Reference Element");
            } else {
               this._isReaderAtNext = false;
               return ref;
            }
         }
      }

      private Object readInstanceOf(Class cls) throws XMLStreamException {
         XMLFormat xmlFormat = this._binding.getFormat(cls);
         this._isReaderAtNext = false;
         Object obj = xmlFormat.newInstance(cls, this);
         if (this._referenceResolver != null) {
            this._referenceResolver.createReference(obj, this);
         }

         xmlFormat.read(this, obj);
         if (this.hasNext()) {
            throw new XMLStreamException("Incomplete element reading", this._reader.getLocation());
         } else {
            this._isReaderAtNext = false;
            return obj;
         }
      }

      public CharArray getText() throws XMLStreamException {
         CharArray txt = this._reader.getElementText();
         this._isReaderAtNext = true;
         return txt;
      }

      public Attributes getAttributes() throws XMLStreamException {
         if (this._isReaderAtNext) {
            throw new XMLStreamException("Attributes should be read before content");
         } else {
            return this._reader.getAttributes();
         }
      }

      public CharArray getAttribute(String name) throws XMLStreamException {
         if (this._isReaderAtNext) {
            throw new XMLStreamException("Attributes should be read before reading content");
         } else {
            return this._reader.getAttributeValue((CharSequence)null, XMLFormat.toCsq(name));
         }
      }

      public String getAttribute(String name, String defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? value.toString() : defaultValue;
      }

      public boolean getAttribute(String name, boolean defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? value.toBoolean() : defaultValue;
      }

      public char getAttribute(String name, char defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         if (value == null) {
            return defaultValue;
         } else if (value.length() != 1) {
            throw new XMLStreamException("Single character expected (read '" + value + "')");
         } else {
            return value.charAt(0);
         }
      }

      public byte getAttribute(String name, byte defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? (byte)value.toInt() : defaultValue;
      }

      public short getAttribute(String name, short defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? (short)value.toInt() : defaultValue;
      }

      public int getAttribute(String name, int defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? value.toInt() : defaultValue;
      }

      public long getAttribute(String name, long defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? value.toLong() : defaultValue;
      }

      public float getAttribute(String name, float defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? value.toFloat() : defaultValue;
      }

      public double getAttribute(String name, double defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         return value != null ? value.toDouble() : defaultValue;
      }

      public Object getAttribute(String name, Object defaultValue) throws XMLStreamException {
         CharArray value = this.getAttribute(name);
         if (value == null) {
            return defaultValue;
         } else {
            Class type = defaultValue.getClass();
            TextFormat format = TextFormat.getInstance(type);
            if (!format.isParsingSupported()) {
               throw new XMLStreamException("No TextFormat instance for " + type);
            } else {
               return format.parse(value);
            }
         }
      }

      void setBinding(XMLBinding xmlBinding) {
         this._binding = xmlBinding;
      }

      void setReferenceResolver(XMLReferenceResolver xmlReferenceResolver) {
         this._referenceResolver = xmlReferenceResolver;
      }

      void reset() {
         this._binding = XMLBinding.DEFAULT;
         this._isReaderAtNext = false;
         this._reader.reset();
         this._referenceResolver = null;
      }
   }

   public static final class OutputElement {
      final XMLStreamWriterImpl _writer = new XMLStreamWriterImpl();
      private XMLBinding _binding;
      private XMLReferenceResolver _referenceResolver;
      private TextBuilder _tmpTextBuilder = new TextBuilder();

      OutputElement() {
         this.reset();
      }

      public XMLStreamWriter getStreamWriter() {
         return this._writer;
      }

      public void add(Object obj) throws XMLStreamException {
         if (obj == null) {
            this._writer.writeEmptyElement(XMLFormat.toCsq("Null"));
         } else {
            Class cls = obj.getClass();
            this._binding.writeClass(obj.getClass(), this._writer, false);
            XMLFormat xmlFormat = this._binding.getFormat(cls);
            if (!xmlFormat.isReferenceable() || !this.writeReference(obj)) {
               xmlFormat.write(obj, this);
               this._writer.writeEndElement();
            }
         }
      }

      public void add(Object obj, String name) throws XMLStreamException {
         if (obj != null) {
            this._writer.writeStartElement(XMLFormat.toCsq(name));
            Class cls = obj.getClass();
            this._binding.writeClass(cls, this._writer, true);
            XMLFormat xmlFormat = this._binding.getFormat(cls);
            if (!xmlFormat.isReferenceable() || !this.writeReference(obj)) {
               xmlFormat.write(obj, this);
               this._writer.writeEndElement();
            }
         }
      }

      public void add(Object obj, String localName, String uri) throws XMLStreamException {
         if (obj != null) {
            this._writer.writeStartElement(XMLFormat.toCsq(uri), XMLFormat.toCsq(localName));
            Class cls = obj.getClass();
            this._binding.writeClass(cls, this._writer, true);
            XMLFormat xmlFormat = this._binding.getFormat(cls);
            if (!xmlFormat.isReferenceable() || !this.writeReference(obj)) {
               xmlFormat.write(obj, this);
               this._writer.writeEndElement();
            }
         }
      }

      public void add(Object obj, String name, Class cls) throws XMLStreamException {
         if (obj != null) {
            this._writer.writeStartElement(XMLFormat.toCsq(name));
            XMLFormat xmlFormat = this._binding.getFormat(cls);
            if (!xmlFormat.isReferenceable() || !this.writeReference(obj)) {
               xmlFormat.write(obj, this);
               this._writer.writeEndElement();
            }
         }
      }

      public void add(Object obj, String localName, String uri, Class cls) throws XMLStreamException {
         if (obj != null) {
            this._writer.writeStartElement(XMLFormat.toCsq(uri), XMLFormat.toCsq(localName));
            XMLFormat xmlFormat = this._binding.getFormat(cls);
            if (!xmlFormat.isReferenceable() || !this.writeReference(obj)) {
               xmlFormat.write(obj, this);
               this._writer.writeEndElement();
            }
         }
      }

      private boolean writeReference(Object obj) throws XMLStreamException {
         if (this._referenceResolver != null && this._referenceResolver.writeReference(obj, this)) {
            this._writer.writeEndElement();
            return true;
         } else {
            return false;
         }
      }

      public void addText(CharSequence text) throws XMLStreamException {
         this._writer.writeCharacters(text);
      }

      public void addText(String text) throws XMLStreamException {
         this._writer.writeCharacters(XMLFormat.toCsq(text));
      }

      public void setAttribute(String name, CharSequence value) throws XMLStreamException {
         if (value != null) {
            this._writer.writeAttribute(XMLFormat.toCsq(name), value);
         }
      }

      public void setAttribute(String name, String value) throws XMLStreamException {
         if (value != null) {
            this._writer.writeAttribute(XMLFormat.toCsq(name), XMLFormat.toCsq(value));
         }
      }

      public void setAttribute(String name, boolean value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append(value));
      }

      public void setAttribute(String name, char value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append(value));
      }

      public void setAttribute(String name, byte value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append((int)value));
      }

      public void setAttribute(String name, short value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append((int)value));
      }

      public void setAttribute(String name, int value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append(value));
      }

      public void setAttribute(String name, long value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append(value));
      }

      public void setAttribute(String name, float value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append(value));
      }

      public void setAttribute(String name, double value) throws XMLStreamException {
         this.setAttribute(name, (CharSequence)this._tmpTextBuilder.clear().append(value));
      }

      public void setAttribute(String name, Object value) throws XMLStreamException {
         if (value != null) {
            Class type = value.getClass();
            TextFormat format = TextFormat.getInstance(type);
            this.setAttribute(name, (CharSequence)format.format(value, this._tmpTextBuilder.clear()));
         }
      }

      void setBinding(XMLBinding xmlBinding) {
         this._binding = xmlBinding;
      }

      void setReferenceResolver(XMLReferenceResolver xmlReferenceResolver) {
         this._referenceResolver = xmlReferenceResolver;
      }

      void reset() {
         this._binding = XMLBinding.DEFAULT;
         this._writer.reset();
         this._writer.setRepairingNamespaces(true);
         this._writer.setAutomaticEmptyElements(true);
         this._referenceResolver = null;
      }
   }
}
