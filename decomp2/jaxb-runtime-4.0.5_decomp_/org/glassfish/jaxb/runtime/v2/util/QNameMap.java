package org.glassfish.jaxb.runtime.v2.util;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.runtime.v2.runtime.Name;

public final class QNameMap {
   private static final int DEFAULT_INITIAL_CAPACITY = 16;
   private static final int MAXIMUM_CAPACITY = 1073741824;
   transient Entry[] table = new Entry[16];
   transient int size;
   private int threshold = 12;
   private static final float DEFAULT_LOAD_FACTOR = 0.75F;
   private Set entrySet = null;

   public QNameMap() {
      this.table = new Entry[16];
   }

   public void put(String namespaceUri, String localname, Object value) {
      assert localname != null;

      assert namespaceUri != null;

      assert localname == localname.intern();

      assert namespaceUri == namespaceUri.intern();

      int hash = hash(localname);
      int i = indexFor(hash, this.table.length);

      for(Entry<V> e = this.table[i]; e != null; e = e.next) {
         if (e.hash == hash && localname == e.localName && namespaceUri == e.nsUri) {
            e.value = value;
            return;
         }
      }

      this.addEntry(hash, namespaceUri, localname, value, i);
   }

   public void put(QName name, Object value) {
      this.put(name.getNamespaceURI(), name.getLocalPart(), value);
   }

   public void put(Name name, Object value) {
      this.put(name.nsUri, name.localName, value);
   }

   public Object get(String nsUri, String localPart) {
      Entry<V> e = this.getEntry(nsUri, localPart);
      return e == null ? null : e.value;
   }

   public Object get(QName name) {
      return this.get(name.getNamespaceURI(), name.getLocalPart());
   }

   public int size() {
      return this.size;
   }

   public QNameMap putAll(QNameMap map) {
      int numKeysToBeAdded = map.size();
      if (numKeysToBeAdded == 0) {
         return this;
      } else {
         if (numKeysToBeAdded > this.threshold) {
            int targetCapacity = numKeysToBeAdded;
            if (numKeysToBeAdded > 1073741824) {
               targetCapacity = 1073741824;
            }

            int newCapacity;
            for(newCapacity = this.table.length; newCapacity < targetCapacity; newCapacity <<= 1) {
            }

            if (newCapacity > this.table.length) {
               this.resize(newCapacity);
            }
         }

         for(Entry e : map.entrySet()) {
            this.put(e.nsUri, e.localName, e.getValue());
         }

         return this;
      }
   }

   private static int hash(String x) {
      int h = x.hashCode();
      h += ~(h << 9);
      h ^= h >>> 14;
      h += h << 4;
      h ^= h >>> 10;
      return h;
   }

   private static int indexFor(int h, int length) {
      return h & length - 1;
   }

   private void addEntry(int hash, String nsUri, String localName, Object value, int bucketIndex) {
      Entry<V> e = this.table[bucketIndex];
      this.table[bucketIndex] = new Entry(hash, nsUri, localName, value, e);
      if (this.size++ >= this.threshold) {
         this.resize(2 * this.table.length);
      }

   }

   private void resize(int newCapacity) {
      Entry<V>[] oldTable = this.table;
      int oldCapacity = oldTable.length;
      if (oldCapacity == 1073741824) {
         this.threshold = Integer.MAX_VALUE;
      } else {
         Entry<V>[] newTable = new Entry[newCapacity];
         this.transfer(newTable);
         this.table = newTable;
         this.threshold = newCapacity;
      }
   }

   private void transfer(Entry[] newTable) {
      Entry<V>[] src = this.table;
      int newCapacity = newTable.length;

      for(int j = 0; j < src.length; ++j) {
         Entry<V> e = src[j];
         if (e != null) {
            src[j] = null;

            while(true) {
               Entry<V> next = e.next;
               int i = indexFor(e.hash, newCapacity);
               e.next = newTable[i];
               newTable[i] = e;
               e = next;
               if (next == null) {
                  break;
               }
            }
         }
      }

   }

   public Entry getOne() {
      for(Entry e : this.table) {
         if (e != null) {
            return e;
         }
      }

      return null;
   }

   public Collection keySet() {
      Set<QName> r = new HashSet();

      for(Entry e : this.entrySet()) {
         r.add(e.createQName());
      }

      return r;
   }

   public boolean containsKey(String nsUri, String localName) {
      return this.getEntry(nsUri, localName) != null;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public Set entrySet() {
      Set<Entry<V>> es = this.entrySet;
      return es != null ? es : (this.entrySet = new EntrySet());
   }

   private Iterator newEntryIterator() {
      return new EntryIterator();
   }

   private Entry getEntry(String nsUri, String localName) {
      assert nsUri == nsUri.intern();

      assert localName == localName.intern();

      int hash = hash(localName);
      int i = indexFor(hash, this.table.length);

      Entry<V> e;
      for(e = this.table[i]; e != null && (localName != e.localName || nsUri != e.nsUri); e = e.next) {
      }

      return e;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append('{');

      for(Entry e : this.entrySet()) {
         if (buf.length() > 1) {
            buf.append(',');
         }

         buf.append('[');
         buf.append(e);
         buf.append(']');
      }

      buf.append('}');
      return buf.toString();
   }

   private abstract class HashIterator implements Iterator {
      Entry next;
      int index;

      HashIterator() {
         Entry<V>[] t = QNameMap.this.table;
         int i = t.length;
         Entry<V> n = null;
         if (QNameMap.this.size != 0) {
            while(i > 0) {
               --i;
               if ((n = t[i]) != null) {
                  break;
               }
            }
         }

         this.next = n;
         this.index = i;
      }

      public boolean hasNext() {
         return this.next != null;
      }

      Entry nextEntry() {
         Entry<V> e = this.next;
         if (e == null) {
            throw new NoSuchElementException();
         } else {
            Entry<V> n = e.next;
            Entry<V>[] t = QNameMap.this.table;

            int i;
            for(i = this.index; n == null && i > 0; n = t[i]) {
               --i;
            }

            this.index = i;
            this.next = n;
            return e;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   public static final class Entry {
      public final String nsUri;
      public final String localName;
      Object value;
      final int hash;
      Entry next;

      Entry(int h, String nsUri, String localName, Object v, Entry n) {
         this.value = v;
         this.next = n;
         this.nsUri = nsUri;
         this.localName = localName;
         this.hash = h;
      }

      public QName createQName() {
         return new QName(this.nsUri, this.localName);
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object newValue) {
         V oldValue = (V)this.value;
         this.value = newValue;
         return oldValue;
      }

      public boolean equals(Object o) {
         if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry e = (Entry)o;
            String k1 = this.nsUri;
            String k2 = e.nsUri;
            String k3 = this.localName;
            String k4 = e.localName;
            if (k1 != k2 && (k1 == null || !k1.equals(k2) || !Objects.equals(k3, k4))) {
               return false;
            } else {
               Object v1 = this.getValue();
               Object v2 = e.getValue();
               return Objects.equals(v1, v2);
            }
         }
      }

      public int hashCode() {
         return this.localName.hashCode() ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public String toString() {
         String var10000 = this.nsUri;
         return "\"" + var10000 + "\",\"" + this.localName + "\"=" + this.getValue();
      }
   }

   private class EntryIterator extends HashIterator {
      public Entry next() {
         return this.nextEntry();
      }
   }

   private class EntrySet extends AbstractSet {
      public Iterator iterator() {
         return QNameMap.this.newEntryIterator();
      }

      public boolean contains(Object o) {
         if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry<V> e = (Entry)o;
            Entry<V> candidate = QNameMap.this.getEntry(e.nsUri, e.localName);
            return candidate != null && candidate.equals(e);
         }
      }

      public boolean remove(Object o) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return QNameMap.this.size;
      }
   }
}
