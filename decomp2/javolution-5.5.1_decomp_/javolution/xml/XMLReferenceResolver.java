package javolution.xml;

import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.text.TextBuilder;
import javolution.util.FastComparator;
import javolution.util.FastMap;
import javolution.util.FastTable;
import javolution.util.Index;
import javolution.xml.stream.XMLStreamException;

public class XMLReferenceResolver implements Reusable {
   private FastMap _objectToId;
   private FastTable _idToObject;
   private int _counter;
   private String _idName;
   private String _idURI;
   private String _refName;
   private String _refURI;
   private TextBuilder _tmp;

   public XMLReferenceResolver() {
      this._objectToId = (new FastMap()).setKeyComparator(FastComparator.IDENTITY);
      this._idToObject = new FastTable();
      this._idName = "id";
      this._idURI = null;
      this._refName = "ref";
      this._refURI = null;
      this._tmp = new TextBuilder();
   }

   public void setIdentifierAttribute(String name) {
      this.setIdentifierAttribute(name, (String)null);
   }

   public void setIdentifierAttribute(String localName, String uri) {
      this._idName = localName;
      this._idURI = uri;
   }

   public void setReferenceAttribute(String name) {
      this.setReferenceAttribute(name, (String)null);
   }

   public void setReferenceAttribute(String localName, String uri) {
      this._refName = localName;
      this._refURI = uri;
   }

   public boolean writeReference(Object obj, XMLFormat.OutputElement xml) throws XMLStreamException {
      Index id = (Index)this._objectToId.get(obj);
      if (id == null) {
         id = Index.valueOf(this._counter++);
         this._objectToId.put(obj, id);
         this._tmp.clear().append(id.intValue());
         if (this._idURI == null) {
            xml.getStreamWriter().writeAttribute(toCsq(this._idName), this._tmp);
         } else {
            xml.getStreamWriter().writeAttribute(toCsq(this._idURI), toCsq(this._idName), this._tmp);
         }

         return false;
      } else {
         this._tmp.clear().append(id.intValue());
         if (this._refURI == null) {
            xml._writer.writeAttribute(toCsq(this._refName), this._tmp);
         } else {
            xml._writer.writeAttribute(toCsq(this._refURI), toCsq(this._refName), this._tmp);
         }

         return true;
      }
   }

   public Object readReference(XMLFormat.InputElement xml) throws XMLStreamException {
      CharArray value = xml._reader.getAttributeValue(toCsq(this._refURI), toCsq(this._refName));
      if (value == null) {
         return null;
      } else {
         int ref = value.toInt();
         if (ref >= this._idToObject.size()) {
            throw new XMLStreamException("Reference: " + value + " not found");
         } else {
            return this._idToObject.get(ref);
         }
      }
   }

   public void createReference(Object obj, XMLFormat.InputElement xml) throws XMLStreamException {
      CharArray value = xml._reader.getAttributeValue(toCsq(this._idURI), toCsq(this._idName));
      if (value != null) {
         int i = value.toInt();
         if (this._idToObject.size() != i) {
            throw new XMLStreamException("Identifier discontinuity detected (expected " + this._idToObject.size() + " found " + i + ")");
         } else {
            this._idToObject.add(obj);
         }
      }
   }

   public void reset() {
      this._idName = "id";
      this._idURI = null;
      this._refName = "ref";
      this._refURI = null;
      this._idToObject.clear();
      this._objectToId.clear();
      this._counter = 0;
   }

   private static CharSequence toCsq(Object str) {
      return QName.j2meToCharSeq(str);
   }
}
