package org.apache.commons.collections;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections.list.UnmodifiableList;

/** @deprecated */
public class SequencedHashMap implements Map, Cloneable, Externalizable {
   private Entry sentinel;
   private HashMap entries;
   private transient long modCount;
   private static final int KEY = 0;
   private static final int VALUE = 1;
   private static final int ENTRY = 2;
   private static final int REMOVED_MASK = Integer.MIN_VALUE;
   private static final long serialVersionUID = 3380552487888102930L;

   private static final Entry createSentinel() {
      Entry s = new Entry((Object)null, (Object)null);
      s.prev = s;
      s.next = s;
      return s;
   }

   public SequencedHashMap() {
      this.modCount = 0L;
      this.sentinel = createSentinel();
      this.entries = new HashMap();
   }

   public SequencedHashMap(int initialSize) {
      this.modCount = 0L;
      this.sentinel = createSentinel();
      this.entries = new HashMap(initialSize);
   }

   public SequencedHashMap(int initialSize, float loadFactor) {
      this.modCount = 0L;
      this.sentinel = createSentinel();
      this.entries = new HashMap(initialSize, loadFactor);
   }

   public SequencedHashMap(Map m) {
      this();
      this.putAll(m);
   }

   private void removeEntry(Entry entry) {
      entry.next.prev = entry.prev;
      entry.prev.next = entry.next;
   }

   private void insertEntry(Entry entry) {
      entry.next = this.sentinel;
      entry.prev = this.sentinel.prev;
      this.sentinel.prev.next = entry;
      this.sentinel.prev = entry;
   }

   public int size() {
      return this.entries.size();
   }

   public boolean isEmpty() {
      return this.sentinel.next == this.sentinel;
   }

   public boolean containsKey(Object key) {
      return this.entries.containsKey(key);
   }

   public boolean containsValue(Object value) {
      if (value == null) {
         for(Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
            if (pos.getValue() == null) {
               return true;
            }
         }
      } else {
         for(Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
            if (value.equals(pos.getValue())) {
               return true;
            }
         }
      }

      return false;
   }

   public Object get(Object o) {
      Entry entry = (Entry)this.entries.get(o);
      return entry == null ? null : entry.getValue();
   }

   public Map.Entry getFirst() {
      return this.isEmpty() ? null : this.sentinel.next;
   }

   public Object getFirstKey() {
      return this.sentinel.next.getKey();
   }

   public Object getFirstValue() {
      return this.sentinel.next.getValue();
   }

   public Map.Entry getLast() {
      return this.isEmpty() ? null : this.sentinel.prev;
   }

   public Object getLastKey() {
      return this.sentinel.prev.getKey();
   }

   public Object getLastValue() {
      return this.sentinel.prev.getValue();
   }

   public Object put(Object key, Object value) {
      ++this.modCount;
      Object oldValue = null;
      Entry e = (Entry)this.entries.get(key);
      if (e != null) {
         this.removeEntry(e);
         oldValue = e.setValue(value);
      } else {
         e = new Entry(key, value);
         this.entries.put(key, e);
      }

      this.insertEntry(e);
      return oldValue;
   }

   public Object remove(Object key) {
      Entry e = this.removeImpl(key);
      return e == null ? null : e.getValue();
   }

   private Entry removeImpl(Object key) {
      Entry e = (Entry)this.entries.remove(key);
      if (e == null) {
         return null;
      } else {
         ++this.modCount;
         this.removeEntry(e);
         return e;
      }
   }

   public void putAll(Map t) {
      for(Map.Entry entry : t.entrySet()) {
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public void clear() {
      ++this.modCount;
      this.entries.clear();
      this.sentinel.next = this.sentinel;
      this.sentinel.prev = this.sentinel;
   }

   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      } else if (obj == this) {
         return true;
      } else {
         return !(obj instanceof Map) ? false : this.entrySet().equals(((Map)obj).entrySet());
      }
   }

   public int hashCode() {
      return this.entrySet().hashCode();
   }

   public String toString() {
      StringBuffer buf = new StringBuffer();
      buf.append('[');

      for(Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
         buf.append(pos.getKey());
         buf.append('=');
         buf.append(pos.getValue());
         if (pos.next != this.sentinel) {
            buf.append(',');
         }
      }

      buf.append(']');
      return buf.toString();
   }

   public Set keySet() {
      return new AbstractSet() {
         public Iterator iterator() {
            return SequencedHashMap.this.new OrderedIterator(0);
         }

         public boolean remove(Object o) {
            Entry e = SequencedHashMap.this.removeImpl(o);
            return e != null;
         }

         public void clear() {
            SequencedHashMap.this.clear();
         }

         public int size() {
            return SequencedHashMap.this.size();
         }

         public boolean isEmpty() {
            return SequencedHashMap.this.isEmpty();
         }

         public boolean contains(Object o) {
            return SequencedHashMap.this.containsKey(o);
         }
      };
   }

   public Collection values() {
      return new AbstractCollection() {
         public Iterator iterator() {
            return SequencedHashMap.this.new OrderedIterator(1);
         }

         public boolean remove(Object value) {
            if (value == null) {
               for(Entry pos = SequencedHashMap.this.sentinel.next; pos != SequencedHashMap.this.sentinel; pos = pos.next) {
                  if (pos.getValue() == null) {
                     SequencedHashMap.this.removeImpl(pos.getKey());
                     return true;
                  }
               }
            } else {
               for(Entry pos = SequencedHashMap.this.sentinel.next; pos != SequencedHashMap.this.sentinel; pos = pos.next) {
                  if (value.equals(pos.getValue())) {
                     SequencedHashMap.this.removeImpl(pos.getKey());
                     return true;
                  }
               }
            }

            return false;
         }

         public void clear() {
            SequencedHashMap.this.clear();
         }

         public int size() {
            return SequencedHashMap.this.size();
         }

         public boolean isEmpty() {
            return SequencedHashMap.this.isEmpty();
         }

         public boolean contains(Object o) {
            return SequencedHashMap.this.containsValue(o);
         }
      };
   }

   public Set entrySet() {
      return new AbstractSet() {
         private Entry findEntry(Object o) {
            if (o == null) {
               return null;
            } else if (!(o instanceof Map.Entry)) {
               return null;
            } else {
               Map.Entry e = (Map.Entry)o;
               Entry entry = (Entry)SequencedHashMap.this.entries.get(e.getKey());
               return entry != null && entry.equals(e) ? entry : null;
            }
         }

         public Iterator iterator() {
            return SequencedHashMap.this.new OrderedIterator(2);
         }

         public boolean remove(Object o) {
            Entry e = this.findEntry(o);
            if (e == null) {
               return false;
            } else {
               return SequencedHashMap.this.removeImpl(e.getKey()) != null;
            }
         }

         public void clear() {
            SequencedHashMap.this.clear();
         }

         public int size() {
            return SequencedHashMap.this.size();
         }

         public boolean isEmpty() {
            return SequencedHashMap.this.isEmpty();
         }

         public boolean contains(Object o) {
            return this.findEntry(o) != null;
         }
      };
   }

   public Object clone() throws CloneNotSupportedException {
      SequencedHashMap map = (SequencedHashMap)super.clone();
      map.sentinel = createSentinel();
      map.entries = new HashMap();
      map.putAll(this);
      return map;
   }

   private Map.Entry getEntry(int index) {
      Entry pos = this.sentinel;
      if (index < 0) {
         throw new ArrayIndexOutOfBoundsException(index + " < 0");
      } else {
         int i;
         for(i = -1; i < index - 1 && pos.next != this.sentinel; pos = pos.next) {
            ++i;
         }

         if (pos.next == this.sentinel) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + (i + 1));
         } else {
            return pos.next;
         }
      }
   }

   public Object get(int index) {
      return this.getEntry(index).getKey();
   }

   public Object getValue(int index) {
      return this.getEntry(index).getValue();
   }

   public int indexOf(Object key) {
      Entry e = (Entry)this.entries.get(key);
      if (e == null) {
         return -1;
      } else {
         int pos;
         for(pos = 0; e.prev != this.sentinel; e = e.prev) {
            ++pos;
         }

         return pos;
      }
   }

   public Iterator iterator() {
      return this.keySet().iterator();
   }

   public int lastIndexOf(Object key) {
      return this.indexOf(key);
   }

   public List sequence() {
      List l = new ArrayList(this.size());
      Iterator iter = this.keySet().iterator();

      while(iter.hasNext()) {
         l.add(iter.next());
      }

      return UnmodifiableList.decorate(l);
   }

   public Object remove(int index) {
      return this.remove(this.get(index));
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      int size = in.readInt();

      for(int i = 0; i < size; ++i) {
         Object key = in.readObject();
         Object value = in.readObject();
         this.put(key, value);
      }

   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeInt(this.size());

      for(Entry pos = this.sentinel.next; pos != this.sentinel; pos = pos.next) {
         out.writeObject(pos.getKey());
         out.writeObject(pos.getValue());
      }

   }

   private static class Entry implements Map.Entry, KeyValue {
      private final Object key;
      private Object value;
      Entry next = null;
      Entry prev = null;

      public Entry(Object key, Object value) {
         this.key = key;
         this.value = value;
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         Object oldValue = this.value;
         this.value = value;
         return oldValue;
      }

      public int hashCode() {
         return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (obj == this) {
            return true;
         } else if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            boolean var10000;
            label48: {
               label32: {
                  Map.Entry other = (Map.Entry)obj;
                  if (this.getKey() == null) {
                     if (other.getKey() != null) {
                        break label32;
                     }
                  } else if (!this.getKey().equals(other.getKey())) {
                     break label32;
                  }

                  if (this.getValue() == null) {
                     if (other.getValue() == null) {
                        break label48;
                     }
                  } else if (this.getValue().equals(other.getValue())) {
                     break label48;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public String toString() {
         return "[" + this.getKey() + "=" + this.getValue() + "]";
      }
   }

   private class OrderedIterator implements Iterator {
      private int returnType;
      private Entry pos;
      private transient long expectedModCount;

      public OrderedIterator(int returnType) {
         this.pos = SequencedHashMap.this.sentinel;
         this.expectedModCount = SequencedHashMap.this.modCount;
         this.returnType = returnType | Integer.MIN_VALUE;
      }

      public boolean hasNext() {
         return this.pos.next != SequencedHashMap.this.sentinel;
      }

      public Object next() {
         if (SequencedHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else if (this.pos.next == SequencedHashMap.this.sentinel) {
            throw new NoSuchElementException();
         } else {
            this.returnType &= Integer.MAX_VALUE;
            this.pos = this.pos.next;
            switch (this.returnType) {
               case 0:
                  return this.pos.getKey();
               case 1:
                  return this.pos.getValue();
               case 2:
                  return this.pos;
               default:
                  throw new Error("bad iterator type: " + this.returnType);
            }
         }
      }

      public void remove() {
         if ((this.returnType & Integer.MIN_VALUE) != 0) {
            throw new IllegalStateException("remove() must follow next()");
         } else if (SequencedHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            SequencedHashMap.this.removeImpl(this.pos.getKey());
            ++this.expectedModCount;
            this.returnType |= Integer.MIN_VALUE;
         }
      }
   }
}
