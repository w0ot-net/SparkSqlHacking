package javolution.xml.stream;

import javax.realtime.MemoryArea;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.text.Text;
import javolution.xml.sax.Attributes;

final class AttributesImpl implements Attributes, Reusable {
   private CharArray[] _localNames = new CharArray[16];
   private CharArray[] _prefixes = new CharArray[16];
   private CharArray[] _qNames = new CharArray[16];
   private CharArray[] _values = new CharArray[16];
   private final NamespacesImpl _namespaces;
   private int _length;
   private static final CharArray CDATA = new CharArray("CDATA");

   public AttributesImpl(NamespacesImpl namespaces) {
      this._namespaces = namespaces;
   }

   public int getLength() {
      return this._length;
   }

   public CharArray getURI(int index) {
      return index >= 0 && index < this._length ? this._namespaces.getNamespaceURINullAllowed(this._prefixes[index]) : null;
   }

   public CharArray getLocalName(int index) {
      return index >= 0 && index < this._length ? this._localNames[index] : null;
   }

   public CharArray getPrefix(int index) {
      return index >= 0 && index < this._length ? this._prefixes[index] : null;
   }

   public CharArray getQName(int index) {
      return index >= 0 && index < this._length ? this._qNames[index] : null;
   }

   public CharArray getType(int index) {
      return index >= 0 && index < this._length ? CDATA : null;
   }

   public CharArray getValue(int index) {
      return index >= 0 && index < this._length ? this._values[index] : null;
   }

   public int getIndex(CharSequence uri, CharSequence localName) {
      if (uri == null) {
         throw new IllegalArgumentException("null namespace URI is not allowed");
      } else {
         for(int i = 0; i < this._length; ++i) {
            if (this._localNames[i].equals((Object)localName)) {
               CharArray ns = this._namespaces.getNamespaceURINullAllowed(this._prefixes[i]);
               if (ns != null && ns.equals((Object)uri)) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   public int getIndex(CharSequence qName) {
      for(int i = 0; i < this._length; ++i) {
         if (this._qNames[i].equals((Object)qName)) {
            return i;
         }
      }

      return -1;
   }

   public CharArray getType(CharSequence uri, CharSequence localName) {
      int index = this.getIndex(uri, localName);
      return index >= 0 ? CDATA : null;
   }

   public CharArray getType(CharSequence qName) {
      int index = this.getIndex(qName);
      return index >= 0 ? CDATA : null;
   }

   public CharArray getValue(CharSequence uri, CharSequence localName) {
      int index = this.getIndex(uri, localName);
      return index >= 0 ? this._values[index] : null;
   }

   public CharArray getValue(CharSequence qName) {
      int index = this.getIndex(qName);
      return index >= 0 ? this._values[index] : null;
   }

   public void reset() {
      this._length = 0;
   }

   public void addAttribute(CharArray localName, CharArray prefix, CharArray qName, CharArray value) {
      if (this._length >= this._localNames.length) {
         this.increaseCapacity();
      }

      this._localNames[this._length] = localName;
      this._prefixes[this._length] = prefix;
      this._qNames[this._length] = qName;
      this._values[this._length++] = value;
   }

   public String toString() {
      Text text = Text.valueOf('[');
      Text equ = Text.valueOf('=');
      Text sep = Text.valueOf((Object)", ");
      int i = 0;

      while(i < this._length) {
         text = text.concat(Text.valueOf((Object)this._qNames[i]).concat(equ).concat(Text.valueOf((Object)this._values[i])));
         ++i;
         if (i != this._length) {
            text = text.concat(sep);
         }
      }

      return text.concat(Text.valueOf(']')).toString();
   }

   private void increaseCapacity() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            int newCapacity = AttributesImpl.this._length * 2;
            CharArray[] tmp = new CharArray[newCapacity];
            System.arraycopy(AttributesImpl.this._localNames, 0, tmp, 0, AttributesImpl.this._length);
            AttributesImpl.this._localNames = tmp;
            tmp = new CharArray[newCapacity];
            System.arraycopy(AttributesImpl.this._prefixes, 0, tmp, 0, AttributesImpl.this._length);
            AttributesImpl.this._prefixes = tmp;
            tmp = new CharArray[newCapacity];
            System.arraycopy(AttributesImpl.this._qNames, 0, tmp, 0, AttributesImpl.this._length);
            AttributesImpl.this._qNames = tmp;
            tmp = new CharArray[newCapacity];
            System.arraycopy(AttributesImpl.this._values, 0, tmp, 0, AttributesImpl.this._length);
            AttributesImpl.this._values = tmp;
         }
      });
   }
}
