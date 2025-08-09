package jodd.util.collection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class IntHashMap extends AbstractMap implements Cloneable, Serializable {
   private transient Entry[] table;
   private transient int count;
   private int threshold;
   private float loadFactor;
   private transient int modCount;
   private transient Set keySet;
   private transient Set entrySet;
   private transient Collection values;
   private static final int KEYS = 0;
   private static final int VALUES = 1;
   private static final int ENTRIES = 2;

   public IntHashMap(int initialCapacity, float loadFactor) {
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
      } else if (loadFactor <= 0.0F) {
         throw new IllegalArgumentException("Invalid load factor: " + loadFactor);
      } else {
         if (initialCapacity == 0) {
            initialCapacity = 1;
         }

         this.loadFactor = loadFactor;
         this.table = new Entry[initialCapacity];
         this.threshold = (int)((float)initialCapacity * loadFactor);
      }
   }

   public IntHashMap(int initialCapacity) {
      this(initialCapacity, 0.75F);
   }

   public IntHashMap() {
      this(101, 0.75F);
   }

   public IntHashMap(Map t) {
      this(Math.max(2 * t.size(), 11), 0.75F);
      this.putAll(t);
   }

   public int size() {
      return this.count;
   }

   public boolean isEmpty() {
      return this.count == 0;
   }

   public boolean containsValue(Object value) {
      Entry[] tab = this.table;
      if (value == null) {
         int i = tab.length;

         while(i-- > 0) {
            for(Entry e = tab[i]; e != null; e = e.next) {
               if (e.value == null) {
                  return true;
               }
            }
         }
      } else {
         int i = tab.length;

         while(i-- > 0) {
            for(Entry e = tab[i]; e != null; e = e.next) {
               if (value.equals(e.value)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean containsKey(Object key) {
      return key instanceof Number ? this.containsKey(((Number)key).intValue()) : false;
   }

   public boolean containsKey(int key) {
      Entry[] tab = this.table;
      int index = (key & Integer.MAX_VALUE) % tab.length;

      for(Entry e = tab[index]; e != null; e = e.next) {
         if (e.key == key) {
            return true;
         }
      }

      return false;
   }

   public Object get(Object key) {
      return key instanceof Number ? this.get(((Number)key).intValue()) : null;
   }

   public Object get(int key) {
      Entry[] tab = this.table;
      int index = (key & Integer.MAX_VALUE) % tab.length;

      for(Entry e = tab[index]; e != null; e = e.next) {
         if (e.key == key) {
            return e.value;
         }
      }

      return null;
   }

   private void rehash() {
      int oldCapacity = this.table.length;
      Entry[] oldMap = this.table;
      int newCapacity = (oldCapacity << 1) + 1;
      Entry[] newMap = new Entry[newCapacity];
      ++this.modCount;
      this.threshold = (int)((float)newCapacity * this.loadFactor);
      this.table = newMap;
      int i = oldCapacity;

      Entry e;
      int index;
      while(i-- > 0) {
         for(Entry old = oldMap[i]; old != null; newMap[index] = e) {
            e = old;
            old = old.next;
            index = (e.key & Integer.MAX_VALUE) % newCapacity;
            e.next = newMap[index];
         }
      }

   }

   public Object put(Object key, Object value) {
      if (key instanceof Number) {
         return this.put(((Number)key).intValue(), value);
      } else {
         throw new UnsupportedOperationException("IntHashMap key must be a number");
      }
   }

   public Object put(int key, Object value) {
      Entry[] tab = this.table;
      int index = (key & Integer.MAX_VALUE) % tab.length;

      for(Entry e = tab[index]; e != null; e = e.next) {
         if (e.key == key) {
            Object old = e.value;
            e.value = value;
            return old;
         }
      }

      ++this.modCount;
      if (this.count >= this.threshold) {
         this.rehash();
         tab = this.table;
         index = (key & Integer.MAX_VALUE) % tab.length;
      }

      tab[index] = new Entry(key, value, tab[index]);
      ++this.count;
      return null;
   }

   public Object remove(Object key) {
      return key instanceof Number ? this.remove(((Number)key).intValue()) : null;
   }

   public Object remove(int key) {
      Entry[] tab = this.table;
      int index = (key & Integer.MAX_VALUE) % tab.length;
      Entry e = tab[index];

      for(Entry prev = null; e != null; e = e.next) {
         if (e.key == key) {
            ++this.modCount;
            if (prev != null) {
               prev.next = e.next;
            } else {
               tab[index] = e.next;
            }

            --this.count;
            Object oldValue = e.value;
            e.value = null;
            return oldValue;
         }

         prev = e;
      }

      return null;
   }

   public void putAll(Map t) {
      for(Object o : t.entrySet()) {
         Map.Entry e = (Map.Entry)o;
         this.put(e.getKey(), e.getValue());
      }

   }

   public void clear() {
      Entry[] tab = this.table;
      ++this.modCount;
      int index = tab.length;

      while(true) {
         --index;
         if (index < 0) {
            this.count = 0;
            return;
         }

         tab[index] = null;
      }
   }

   public Object clone() {
      try {
         IntHashMap t = (IntHashMap)super.clone();
         t.table = new Entry[this.table.length];

         for(int i = this.table.length; i-- > 0; t.table[i] = this.table[i] != null ? (Entry)this.table[i].clone() : null) {
         }

         t.keySet = null;
         t.entrySet = null;
         t.values = null;
         t.modCount = 0;
         return t;
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public Set keySet() {
      if (this.keySet == null) {
         this.keySet = new AbstractSet() {
            public Iterator iterator() {
               return IntHashMap.this.new IntHashIterator(0);
            }

            public int size() {
               return IntHashMap.this.count;
            }

            public boolean contains(Object o) {
               return IntHashMap.this.containsKey(o);
            }

            public boolean remove(Object o) {
               return IntHashMap.this.remove(o) != null;
            }

            public void clear() {
               IntHashMap.this.clear();
            }
         };
      }

      return this.keySet;
   }

   public Collection values() {
      if (this.values == null) {
         this.values = new AbstractCollection() {
            public Iterator iterator() {
               return IntHashMap.this.new IntHashIterator(1);
            }

            public int size() {
               return IntHashMap.this.count;
            }

            public boolean contains(Object o) {
               return IntHashMap.this.containsValue(o);
            }

            public void clear() {
               IntHashMap.this.clear();
            }
         };
      }

      return this.values;
   }

   public Set entrySet() {
      if (this.entrySet == null) {
         this.entrySet = new AbstractSet() {
            public Iterator iterator() {
               return IntHashMap.this.new IntHashIterator(2);
            }

            public boolean contains(Object o) {
               if (!(o instanceof Map.Entry)) {
                  return false;
               } else {
                  Map.Entry entry = (Map.Entry)o;
                  Object key = entry.getKey();
                  Entry[] tab = IntHashMap.this.table;
                  int hash = key == null ? 0 : key.hashCode();
                  int index = (hash & Integer.MAX_VALUE) % tab.length;

                  for(Entry e = tab[index]; e != null; e = e.next) {
                     if (e.key == hash && e.equals(entry)) {
                        return true;
                     }
                  }

                  return false;
               }
            }

            public boolean remove(Object o) {
               if (!(o instanceof Map.Entry)) {
                  return false;
               } else {
                  Map.Entry entry = (Map.Entry)o;
                  Object key = entry.getKey();
                  Entry[] tab = IntHashMap.this.table;
                  int hash = key == null ? 0 : key.hashCode();
                  int index = (hash & Integer.MAX_VALUE) % tab.length;
                  Entry e = tab[index];

                  for(Entry prev = null; e != null; e = e.next) {
                     if (e.key == hash && e.equals(entry)) {
                        IntHashMap.this.modCount++;
                        if (prev != null) {
                           prev.next = e.next;
                        } else {
                           tab[index] = e.next;
                        }

                        IntHashMap.this.count--;
                        e.value = null;
                        return true;
                     }

                     prev = e;
                  }

                  return false;
               }
            }

            public int size() {
               return IntHashMap.this.count;
            }

            public void clear() {
               IntHashMap.this.clear();
            }
         };
      }

      return this.entrySet;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      s.defaultWriteObject();
      s.writeInt(this.table.length);
      s.writeInt(this.count);

      for(int index = this.table.length - 1; index >= 0; --index) {
         for(Entry entry = this.table[index]; entry != null; entry = entry.next) {
            s.writeInt(entry.key);
            s.writeObject(entry.value);
         }
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      int numBuckets = s.readInt();
      this.table = new Entry[numBuckets];
      int size = s.readInt();

      for(int i = 0; i < size; ++i) {
         int key = s.readInt();
         Object value = s.readObject();
         this.put(key, value);
      }

   }

   int capacity() {
      return this.table.length;
   }

   float loadFactor() {
      return this.loadFactor;
   }

   private static class Entry implements Map.Entry, Cloneable {
      int key;
      Object value;
      Entry next;
      private Integer objectKey;

      Entry(int key, Object value, Entry next) {
         this.key = key;
         this.value = value;
         this.next = next;
      }

      protected Object clone() {
         return new Entry(this.key, this.value, this.next == null ? null : (Entry)this.next.clone());
      }

      public Object getKey() {
         return this.objectKey != null ? this.objectKey : (this.objectKey = new Integer(this.key));
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         Object oldValue = this.value;
         this.value = value;
         return oldValue;
      }

      public boolean equals(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            boolean var10000;
            label31: {
               Map.Entry e = (Map.Entry)o;
               if (this.getKey().equals(e.getKey())) {
                  if (this.value == null) {
                     if (e.getValue() == null) {
                        break label31;
                     }
                  } else if (this.value.equals(e.getValue())) {
                     break label31;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public int hashCode() {
         return this.key ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public String toString() {
         return Integer.toString(this.key) + '=' + this.value;
      }
   }

   private class IntHashIterator implements Iterator {
      Entry[] _table;
      int index;
      Entry entry;
      Entry lastReturned;
      int type;
      private int expectedModCount;

      IntHashIterator(int type) {
         this._table = IntHashMap.this.table;
         this.index = this._table.length;
         this.expectedModCount = IntHashMap.this.modCount;
         this.type = type;
      }

      public boolean hasNext() {
         while(this.entry == null && this.index > 0) {
            this.entry = this._table[--this.index];
         }

         return this.entry != null;
      }

      public Object next() {
         if (IntHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            while(this.entry == null && this.index > 0) {
               this.entry = this._table[--this.index];
            }

            if (this.entry != null) {
               Entry e = this.lastReturned = this.entry;
               this.entry = e.next;
               return this.type == 0 ? e.getKey() : (this.type == 1 ? e.value : e);
            } else {
               throw new NoSuchElementException();
            }
         }
      }

      public void remove() {
         if (this.lastReturned == null) {
            throw new IllegalStateException();
         } else if (IntHashMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            Entry[] tab = IntHashMap.this.table;
            int ndx = (this.lastReturned.key & Integer.MAX_VALUE) % tab.length;
            Entry e = tab[ndx];

            for(Entry prev = null; e != null; e = e.next) {
               if (e == this.lastReturned) {
                  IntHashMap.this.modCount++;
                  ++this.expectedModCount;
                  if (prev == null) {
                     tab[ndx] = e.next;
                  } else {
                     prev.next = e.next;
                  }

                  IntHashMap.this.count--;
                  this.lastReturned = null;
                  return;
               }

               prev = e;
            }

            throw new ConcurrentModificationException();
         }
      }
   }
}
