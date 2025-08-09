package javolution.xml;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javolution.lang.Reflection;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.text.TextBuilder;
import javolution.text.TextFormat;
import javolution.util.FastMap;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLStreamReader;
import javolution.xml.stream.XMLStreamWriter;

public class XMLBinding implements Reusable, XMLSerializable {
   static final XMLBinding DEFAULT = new XMLBinding();
   private QName _classAttribute = QName.valueOf("class");
   private final FastMap _classToAlias = new FastMap();
   private final FastMap _aliasToClass = new FastMap();
   static final XMLFormat OBJECT_XML = new XMLFormat(Object.class) {
      public boolean isReferenceable() {
         return false;
      }

      public Object newInstance(Class cls, XMLFormat.InputElement xml) throws XMLStreamException {
         TextFormat format = TextFormat.getInstance(cls);
         if (!format.isParsingSupported()) {
            throw new XMLStreamException("No XMLFormat or TextFormat (with parsing supported) for instances of " + cls);
         } else {
            CharArray value = xml.getAttribute("value");
            if (value == null) {
               throw new XMLStreamException("Missing value attribute (to be able to parse the instance of " + cls + ")");
            } else {
               return format.parse(value);
            }
         }
      }

      public void read(XMLFormat.InputElement xml, Object obj) throws XMLStreamException {
      }

      public void write(Object obj, XMLFormat.OutputElement xml) throws XMLStreamException {
         TextBuilder tmp = TextBuilder.newInstance();

         try {
            TextFormat.getInstance(obj.getClass()).format(obj, tmp);
            xml.setAttribute("value", (CharSequence)tmp);
         } finally {
            TextBuilder.recycle(tmp);
         }

      }
   };
   static final XMLFormat COLLECTION_XML = new XMLFormat(Collection.class) {
      public void read(XMLFormat.InputElement xml, Object obj) throws XMLStreamException {
         Collection collection = (Collection)obj;

         while(xml.hasNext()) {
            collection.add(xml.getNext());
         }

      }

      public void write(Object obj, XMLFormat.OutputElement xml) throws XMLStreamException {
         Collection collection = (Collection)obj;
         Iterator i = collection.iterator();

         while(i.hasNext()) {
            xml.add(i.next());
         }

      }
   };
   static final XMLFormat MAP_XML = new XMLFormat(Map.class) {
      public void read(XMLFormat.InputElement xml, Object obj) throws XMLStreamException {
         Map map = (Map)obj;

         while(xml.hasNext()) {
            Object key = xml.get("Key");
            Object value = xml.get("Value");
            map.put(key, value);
         }

      }

      public void write(Object obj, XMLFormat.OutputElement xml) throws XMLStreamException {
         Map map = (Map)obj;

         for(Map.Entry entry : map.entrySet()) {
            xml.add(entry.getKey(), "Key");
            xml.add(entry.getValue(), "Value");
         }

      }
   };

   public void setAlias(Class cls, QName qName) {
      this._classToAlias.put(cls, qName);
      this._aliasToClass.put(qName, cls);
   }

   public final void setAlias(Class cls, String alias) {
      this.setAlias(cls, QName.valueOf(alias));
   }

   public void setClassAttribute(QName classAttribute) {
      this._classAttribute = classAttribute;
   }

   public final void setClassAttribute(String name) {
      this.setClassAttribute(name == null ? null : QName.valueOf(name));
   }

   protected XMLFormat getFormat(Class forClass) throws XMLStreamException {
      return XMLFormat.getInstance(forClass);
   }

   protected Class readClass(XMLStreamReader reader, boolean useAttributes) throws XMLStreamException {
      QName classQName;
      if (useAttributes) {
         if (this._classAttribute == null) {
            throw new XMLStreamException("Binding has no class attribute defined, cannot retrieve class");
         }

         classQName = QName.valueOf((CharSequence)reader.getAttributeValue(this._classAttribute.getNamespaceURI(), this._classAttribute.getLocalName()));
         if (classQName == null) {
            throw new XMLStreamException("Cannot retrieve class (class attribute not found)");
         }
      } else {
         classQName = QName.valueOf(reader.getNamespaceURI(), reader.getLocalName());
      }

      Class cls = (Class)this._aliasToClass.get(classQName);
      if (cls != null) {
         return cls;
      } else {
         cls = (Class)this._aliasToClass.get(QName.valueOf(classQName.getLocalName()));
         if (cls != null) {
            return cls;
         } else {
            cls = Reflection.getInstance().getClass(classQName.getLocalName());
            if (cls == null) {
               throw new XMLStreamException("Class " + classQName.getLocalName() + " not found (see javolution.lang.Reflection to support additional class loader)");
            } else {
               this._aliasToClass.put(classQName, cls);
               return cls;
            }
         }
      }
   }

   protected void writeClass(Class cls, XMLStreamWriter writer, boolean useAttributes) throws XMLStreamException {
      QName qName = (QName)this._classToAlias.get(cls);
      String name = qName != null ? qName.toString() : cls.getName();
      if (useAttributes) {
         if (this._classAttribute == null) {
            return;
         }

         if (this._classAttribute.getNamespaceURI() == null) {
            writer.writeAttribute(this._classAttribute.getLocalName(), QName.j2meToCharSeq(name));
         } else {
            writer.writeAttribute(this._classAttribute.getNamespaceURI(), this._classAttribute.getLocalName(), QName.j2meToCharSeq(name));
         }
      } else if (qName != null) {
         if (qName.getNamespaceURI() == null) {
            writer.writeStartElement(qName.getLocalName());
         } else {
            writer.writeStartElement(qName.getNamespaceURI(), qName.getLocalName());
         }
      } else {
         writer.writeStartElement(QName.j2meToCharSeq(name));
      }

   }

   public void reset() {
      this._classAttribute = QName.valueOf("class");
      this._aliasToClass.reset();
      this._classToAlias.reset();
   }
}
