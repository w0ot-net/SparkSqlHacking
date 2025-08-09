package javolution.xml;

import java.io.ObjectStreamException;
import javolution.lang.Immutable;
import javolution.text.CharArray;
import javolution.text.TextBuilder;
import javolution.util.FastComparator;
import javolution.util.FastMap;

public final class QName implements XMLSerializable, Immutable, CharSequence {
   private final transient CharArray _localName;
   private final transient CharArray _namespaceURI;
   private final String _toString;
   private static final FastMap FULL_NAME_TO_QNAME;

   private QName(String namespaceURI, String localName, String toString) {
      this._namespaceURI = namespaceURI == null ? null : new CharArray(namespaceURI);
      this._localName = new CharArray(localName);
      this._toString = toString;
   }

   public static QName valueOf(CharSequence name) {
      QName qName = (QName)FULL_NAME_TO_QNAME.get(name);
      return qName != null ? qName : createNoNamespace(name.toString());
   }

   private static QName createNoNamespace(String name) {
      String localName = name;
      String namespaceURI = null;
      if (name.length() > 0 && name.charAt(0) == '{') {
         int index = name.lastIndexOf(125);
         localName = name.substring(index + 1);
         namespaceURI = name.substring(1, index);
      }

      QName qName = new QName(namespaceURI, localName, name);
      synchronized(FULL_NAME_TO_QNAME) {
         QName tmp = (QName)FULL_NAME_TO_QNAME.putIfAbsent(name, qName);
         return tmp == null ? qName : tmp;
      }
   }

   public static QName valueOf(String name) {
      QName qName = (QName)FULL_NAME_TO_QNAME.get(name);
      return qName != null ? qName : createNoNamespace(name);
   }

   public static QName valueOf(CharSequence namespaceURI, CharSequence localName) {
      if (namespaceURI == null) {
         return valueOf(localName);
      } else {
         TextBuilder tmp = TextBuilder.newInstance();

         QName var3;
         try {
            tmp.append('{');
            tmp.append(namespaceURI);
            tmp.append('}');
            tmp.append(localName);
            var3 = valueOf((CharSequence)tmp);
         } finally {
            TextBuilder.recycle(tmp);
         }

         return var3;
      }
   }

   public CharSequence getLocalName() {
      return this._localName;
   }

   public CharSequence getNamespaceURI() {
      return this._namespaceURI;
   }

   public boolean equals(Object obj) {
      return this == obj;
   }

   public String toString() {
      return this._toString;
   }

   public int hashCode() {
      return this._toString.hashCode();
   }

   public char charAt(int index) {
      return this._toString.charAt(index);
   }

   public int length() {
      return this._toString.length();
   }

   public CharSequence subSequence(int start, int end) {
      return j2meToCharSeq(this._toString.substring(start, end));
   }

   private Object readResolve() throws ObjectStreamException {
      return valueOf(this._toString);
   }

   static CharSequence j2meToCharSeq(Object str) {
      return (CharSequence)str;
   }

   static {
      FULL_NAME_TO_QNAME = (new FastMap()).setKeyComparator(FastComparator.LEXICAL).shared();
   }
}
