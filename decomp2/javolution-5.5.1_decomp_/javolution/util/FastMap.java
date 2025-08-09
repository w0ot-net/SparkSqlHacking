package javolution.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.context.PersistentContext;
import javolution.lang.MathLib;
import javolution.lang.Realtime;
import javolution.lang.Reusable;
import javolution.text.Text;
import javolution.xml.XMLSerializable;

public class FastMap implements Map, Reusable, XMLSerializable, Realtime {
   private static final int B0 = 4;
   private static final int C0 = 16;
   private static final int B1 = 10;
   private static final int C1 = 1024;
   private static final int B2 = 6;
   private static final int C2 = 64;
   private transient Entry _head;
   private transient Entry _tail;
   private transient Entry[] _entries;
   private transient int _entryCount;
   private transient int _nullCount;
   private transient FastMap[] _subMaps;
   private transient boolean _useSubMaps;
   private transient int _keyShift;
   private transient Values _values;
   private transient KeySet _keySet;
   private transient EntrySet _entrySet;
   private transient Map _unmodifiable;
   private transient FastComparator _keyComparator;
   private transient boolean _isDirectKeyComparator;
   private transient FastComparator _valueComparator;
   private transient boolean _isShared;
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      public Object create() {
         return new FastMap();
      }
   };
   private static final Entry[] NULL_ENTRIES = new Entry[1024];
   static volatile int ONE_VOLATILE = 1;
   private static final long serialVersionUID = 1L;

   public FastMap() {
      this(4);
   }

   public FastMap(String id) {
      // $FF: Couldn't be decompiled
   }

   public FastMap(int capacity) {
      this.setKeyComparator(FastComparator.DEFAULT);
      this.setValueComparator(FastComparator.DEFAULT);
      this.setup(capacity);
   }

   private void setup(int capacity) {
      int tableLength;
      for(tableLength = 16; tableLength < capacity; tableLength <<= 1) {
      }

      this._entries = new Entry[tableLength << 1];
      this._head = this.newEntry();
      this._tail = this.newEntry();
      this._head._next = this._tail;
      this._tail._previous = this._head;
      Entry previous = this._tail;

      Entry newEntry;
      for(int i = 0; i++ < capacity; previous = newEntry) {
         newEntry = this.newEntry();
         newEntry._previous = previous;
         previous._next = newEntry;
      }

   }

   public FastMap(Map map) {
      this(map.size());
      this.putAll(map);
   }

   private FastMap(Entry[] entries) {
      this._entries = entries;
   }

   public static FastMap newInstance() {
      return (FastMap)FACTORY.object();
   }

   public static void recycle(FastMap instance) {
      FACTORY.recycle(instance);
   }

   public final FastMap reverse() {
      FastMap<V, K> map = (FastMap)FACTORY.object();
      Entry<K, V> e = this._head;
      Entry<K, V> n = this._tail;

      while((e = e._next) != n) {
         map.put(e._value, e._key);
      }

      return map;
   }

   public final Entry head() {
      return this._head;
   }

   public final Entry tail() {
      return this._tail;
   }

   public final int size() {
      if (!this._useSubMaps) {
         return this._entryCount;
      } else {
         int sum = 0;

         for(int i = 0; i < this._subMaps.length; sum += this._subMaps[i++].size()) {
         }

         return sum;
      }
   }

   public final boolean isEmpty() {
      return this._head._next == this._tail;
   }

   public final boolean containsKey(Object key) {
      return this.getEntry(key) != null;
   }

   public final boolean containsValue(Object value) {
      return this.values().contains(value);
   }

   public final Object get(Object key) {
      Entry<K, V> entry = this.getEntry(key);
      return entry != null ? entry._value : null;
   }

   public final Entry getEntry(Object key) {
      return this.getEntry(key, this._isDirectKeyComparator ? key.hashCode() : this._keyComparator.hashCodeOf(key));
   }

   private final Entry getEntry(Object key, int keyHash) {
      FastMap map = this.getSubMap(keyHash);
      Entry[] entries = map._entries;
      int mask = entries.length - 1;
      int i = keyHash >> map._keyShift;

      Entry entry;
      while(true) {
         entry = entries[i & mask];
         if (entry == null) {
            return null;
         }

         if (key == entry._key) {
            break;
         }

         if (keyHash == entry._keyHash) {
            if (this._isDirectKeyComparator) {
               if (key.equals(entry._key)) {
                  break;
               }
            } else if (this._keyComparator.areEqual(key, entry._key)) {
               break;
            }
         }

         ++i;
      }

      return entry;
   }

   private final FastMap getSubMap(int keyHash) {
      return this._useSubMaps ? this._subMaps[keyHash & 63].getSubMap(keyHash >> 6) : this;
   }

   public final Object put(Object key, Object value) {
      return this.put(key, value, this._isDirectKeyComparator ? key.hashCode() : this._keyComparator.hashCodeOf(key), this._isShared, false, false);
   }

   private final Object put(Object key, Object value, int keyHash, boolean concurrent, boolean noReplace, boolean returnEntry) {
      FastMap map = this.getSubMap(keyHash);
      Entry[] entries = map._entries;
      int mask = entries.length - 1;
      int slot = -1;
      int i = keyHash >> map._keyShift;

      Entry entry;
      while(true) {
         entry = entries[i & mask];
         if (entry == null) {
            slot = slot < 0 ? i & mask : slot;
            if (concurrent) {
               synchronized(this) {
                  return this.put(key, value, keyHash, false, noReplace, returnEntry);
               }
            }

            Entry entry;
            if (!this._isShared) {
               entry = this._tail;
               entry._key = key;
               entry._value = value;
               entry._keyHash = keyHash;
               if (entry._next == null) {
                  this.createNewEntries();
               }

               entries[slot] = entry;
               map._entryCount += ONE_VOLATILE;
               this._tail = this._tail._next;
            } else {
               if (this._tail._next == null) {
                  this.createNewEntries();
               }

               entry = this._tail._next;
               this._tail._next = entry._next;
               entry._key = key;
               entry._value = value;
               entry._keyHash = keyHash;
               entry._next = this._tail;
               entry._previous = this._tail._previous;
               entries[slot] = entry;
               map._entryCount += ONE_VOLATILE;
               entry._next._previous = entry;
               entry._previous._next = entry;
            }

            if (map._entryCount + map._nullCount > entries.length >> 1) {
               map.resizeTable(this._isShared);
            }

            return returnEntry ? entry : null;
         }

         if (entry == FastMap.Entry.NULL) {
            slot = slot < 0 ? i & mask : slot;
         } else {
            if (key == entry._key) {
               break;
            }

            if (keyHash == entry._keyHash) {
               if (this._isDirectKeyComparator) {
                  if (key.equals(entry._key)) {
                     break;
                  }
               } else if (this._keyComparator.areEqual(key, entry._key)) {
                  break;
               }
            }
         }

         ++i;
      }

      if (noReplace) {
         return returnEntry ? entry : entry._value;
      } else {
         Object prevValue = entry._value;
         entry._value = value;
         return returnEntry ? entry : prevValue;
      }
   }

   private void createNewEntries() {
      // $FF: Couldn't be decompiled
   }

   private void resizeTable(boolean isShared) {
      // $FF: Couldn't be decompiled
   }

   private FastMap[] newSubMaps(int capacity) {
      FastMap[] subMaps = new FastMap[64];

      for(int i = 0; i < 64; ++i) {
         FastMap subMap = new FastMap(new Entry[capacity]);
         subMap._keyShift = 6 + this._keyShift;
         subMaps[i] = subMap;
      }

      return subMaps;
   }

   private void mapEntry(Entry entry) {
      int mask = this._entries.length - 1;
      int i = entry._keyHash >> this._keyShift;

      while(true) {
         Entry e = this._entries[i & mask];
         if (e == null) {
            this._entries[i & mask] = entry;
            ++this._entryCount;
            return;
         }

         ++i;
      }
   }

   private void copyEntries(Object[] from, Entry[] to, int count) {
      int mask = to.length - 1;
      int i = 0;

      while(i < count) {
         Entry entry = (Entry)from[i++];
         if (entry != null && entry != FastMap.Entry.NULL) {
            int j = entry._keyHash >> this._keyShift;

            while(true) {
               Entry tmp = to[j & mask];
               if (tmp == null) {
                  to[j & mask] = entry;
                  break;
               }

               ++j;
            }
         }
      }

   }

   public final Entry putEntry(Object key, Object value) {
      return (Entry)this.put(key, value, this._isDirectKeyComparator ? key.hashCode() : this._keyComparator.hashCodeOf(key), this._isShared, false, true);
   }

   public final void putAll(Map map) {
      for(Map.Entry e : map.entrySet()) {
         this.put(e.getKey(), e.getValue());
      }

   }

   public final Object putIfAbsent(Object key, Object value) {
      return this.put(key, value, this._isDirectKeyComparator ? key.hashCode() : this._keyComparator.hashCodeOf(key), this._isShared, true, false);
   }

   public final Object remove(Object key) {
      return this.remove(key, this._isDirectKeyComparator ? key.hashCode() : this._keyComparator.hashCodeOf(key), this._isShared);
   }

   private final Object remove(Object key, int keyHash, boolean concurrent) {
      FastMap map = this.getSubMap(keyHash);
      Entry[] entries = map._entries;
      int mask = entries.length - 1;
      int i = keyHash >> map._keyShift;

      Entry entry;
      while(true) {
         entry = entries[i & mask];
         if (entry == null) {
            return null;
         }

         if (key == entry._key) {
            break;
         }

         if (keyHash == entry._keyHash) {
            if (this._isDirectKeyComparator) {
               if (key.equals(entry._key)) {
                  break;
               }
            } else if (this._keyComparator.areEqual(key, entry._key)) {
               break;
            }
         }

         ++i;
      }

      if (concurrent) {
         synchronized(this) {
            return this.remove(key, keyHash, false);
         }
      } else {
         entry._previous._next = entry._next;
         entry._next._previous = entry._previous;
         entries[i & mask] = FastMap.Entry.NULL;
         ++map._nullCount;
         --map._entryCount;
         Object prevValue = entry._value;
         if (!this._isShared) {
            entry._key = null;
            entry._value = null;
            Entry next = this._tail._next;
            entry._previous = this._tail;
            entry._next = next;
            this._tail._next = entry;
            if (next != null) {
               next._previous = entry;
            }
         }

         return prevValue;
      }
   }

   public FastMap shared() {
      this._isShared = true;
      return this;
   }

   /** @deprecated */
   public FastMap setShared(boolean isShared) {
      this._isShared = isShared;
      return this;
   }

   public boolean isShared() {
      return this._isShared;
   }

   public FastMap setKeyComparator(FastComparator keyComparator) {
      this._keyComparator = keyComparator;
      this._isDirectKeyComparator = keyComparator == FastComparator.DIRECT || this._keyComparator == FastComparator.DEFAULT && !(Boolean)FastComparator.REHASH_SYSTEM_HASHCODE.get();
      return this;
   }

   public FastComparator getKeyComparator() {
      return this._keyComparator;
   }

   public FastMap setValueComparator(FastComparator valueComparator) {
      this._valueComparator = valueComparator;
      return this;
   }

   public FastComparator getValueComparator() {
      return this._valueComparator;
   }

   public final void clear() {
      if (this._isShared) {
         this.clearShared();
      } else {
         Entry e = this._head;
         Entry end = this._tail;

         while((e = e._next) != end) {
            e._key = null;
            e._value = null;
         }

         this._tail = this._head._next;
         this.clearTables();
      }
   }

   private void clearTables() {
      if (this._useSubMaps) {
         int i = 0;

         while(i < 64) {
            this._subMaps[i++].clearTables();
         }

         this._useSubMaps = false;
      }

      reset(this._entries);
      this._nullCount = 0;
      this._entryCount = 0;
   }

   private synchronized void clearShared() {
      // $FF: Couldn't be decompiled
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj instanceof Map) {
         Map<?, ?> that = (Map)obj;
         return this.entrySet().equals(that.entrySet());
      } else {
         return false;
      }
   }

   public int hashCode() {
      int code = 0;
      Entry e = this._head;

      for(Entry end = this._tail; (e = e._next) != end; code += e.hashCode()) {
      }

      return code;
   }

   public Text toText() {
      return Text.valueOf((Object)this.entrySet());
   }

   public final String toString() {
      return this.toText().toString();
   }

   protected Entry newEntry() {
      return new Entry();
   }

   public void printStatistics(PrintStream out) {
      long sum = this.getSumDistance();
      int size = this.size();
      int avgDistancePercent = size != 0 ? (int)(100L * sum / (long)size) : 0;
      synchronized(out) {
         out.print("SIZE: " + size);
         out.print(", ENTRIES: " + this.getCapacity());
         out.print(", SLOTS: " + this.getTableLength());
         out.print(", USE SUB-MAPS: " + this._useSubMaps);
         out.print(", SUB-MAPS DEPTH: " + this.getSubMapDepth());
         out.print(", NULL COUNT: " + this._nullCount);
         out.print(", IS SHARED: " + this._isShared);
         out.print(", AVG DISTANCE: " + avgDistancePercent + "%");
         out.print(", MAX DISTANCE: " + this.getMaximumDistance());
         out.println();
      }
   }

   private int getTableLength() {
      if (!this._useSubMaps) {
         return this._entries.length;
      } else {
         int sum = 0;

         for(int i = 0; i < 64; ++i) {
            sum += this._subMaps[i].getTableLength();
         }

         return sum;
      }
   }

   private int getCapacity() {
      int capacity = 0;

      for(Entry e = this._head; (e = e._next) != null; ++capacity) {
      }

      return capacity - 1;
   }

   private int getMaximumDistance() {
      int max = 0;
      if (!this._useSubMaps) {
         for(int i = 0; i < this._entries.length; ++i) {
            Entry entry = this._entries[i];
            if (entry != null && entry != FastMap.Entry.NULL) {
               int slot = entry._keyHash >> this._keyShift & this._entries.length - 1;
               int distanceToSlot = i - slot;
               if (distanceToSlot < 0) {
                  distanceToSlot += this._entries.length;
               }

               if (distanceToSlot > max) {
                  max = distanceToSlot;
               }
            }
         }

         return max;
      } else {
         for(int i = 0; i < 64; ++i) {
            int subMapMax = this._subMaps[i].getMaximumDistance();
            max = MathLib.max(max, subMapMax);
         }

         return max;
      }
   }

   private long getSumDistance() {
      long sum = 0L;
      if (!this._useSubMaps) {
         for(int i = 0; i < this._entries.length; ++i) {
            Entry entry = this._entries[i];
            if (entry != null && entry != FastMap.Entry.NULL) {
               int slot = entry._keyHash >> this._keyShift & this._entries.length - 1;
               int distanceToSlot = i - slot;
               if (distanceToSlot < 0) {
                  distanceToSlot += this._entries.length;
               }

               sum += (long)distanceToSlot;
            }
         }

         return sum;
      } else {
         for(int i = 0; i < 64; ++i) {
            sum += this._subMaps[i].getSumDistance();
         }

         return sum;
      }
   }

   private int getSubMapDepth() {
      if (!this._useSubMaps) {
         return 0;
      } else {
         int depth = 0;

         for(int i = 0; i < 64; ++i) {
            int subMapDepth = this._subMaps[i].getSubMapDepth();
            depth = MathLib.max(depth, subMapDepth);
         }

         return depth + 1;
      }
   }

   public final Collection values() {
      // $FF: Couldn't be decompiled
   }

   public final Set entrySet() {
      // $FF: Couldn't be decompiled
   }

   public final Set keySet() {
      // $FF: Couldn't be decompiled
   }

   public final Map unmodifiable() {
      // $FF: Couldn't be decompiled
   }

   public void reset() {
      this._isShared = false;
      this.clear();
      this.setKeyComparator(FastComparator.DEFAULT);
      this.setValueComparator(FastComparator.DEFAULT);
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      this.setKeyComparator((FastComparator)stream.readObject());
      this.setValueComparator((FastComparator)stream.readObject());
      this._isShared = stream.readBoolean();
      int size = stream.readInt();
      this.setup(size);

      for(int i = 0; i < size; ++i) {
         K key = (K)stream.readObject();
         V value = (V)stream.readObject();
         this.put(key, value);
      }

   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.writeObject(this.getKeyComparator());
      stream.writeObject(this.getValueComparator());
      stream.writeBoolean(this._isShared);
      stream.writeInt(this.size());
      Entry e = this._head;
      Entry end = this._tail;

      while((e = e._next) != end) {
         stream.writeObject(e._key);
         stream.writeObject(e._value);
      }

   }

   private static void reset(Object[] entries) {
      int count;
      for(int i = 0; i < entries.length; i += count) {
         count = MathLib.min(entries.length - i, 1024);
         System.arraycopy(NULL_ENTRIES, 0, entries, i, count);
      }

   }

   // $FF: synthetic method
   static int access$600(FastMap x0) {
      return x0._nullCount;
   }

   // $FF: synthetic method
   static int access$602(FastMap x0, int x1) {
      return x0._nullCount = x1;
   }

   // $FF: synthetic method
   static int access$700(FastMap x0) {
      return x0._entryCount;
   }

   // $FF: synthetic method
   static Entry[] access$800(FastMap x0) {
      return x0._entries;
   }

   // $FF: synthetic method
   static void access$900(FastMap x0, Object[] x1, Entry[] x2, int x3) {
      x0.copyEntries(x1, x2, x3);
   }

   // $FF: synthetic method
   static Entry[] access$802(FastMap x0, Entry[] x1) {
      return x0._entries = x1;
   }

   // $FF: synthetic method
   static void access$1000(Object[] x0) {
      reset(x0);
   }

   // $FF: synthetic method
   static FastMap[] access$1100(FastMap x0) {
      return x0._subMaps;
   }

   // $FF: synthetic method
   static FastMap[] access$1102(FastMap x0, FastMap[] x1) {
      return x0._subMaps = x1;
   }

   // $FF: synthetic method
   static FastMap[] access$1200(FastMap x0, int x1) {
      return x0.newSubMaps(x1);
   }

   // $FF: synthetic method
   static int access$1300(FastMap x0) {
      return x0._keyShift;
   }

   // $FF: synthetic method
   static void access$1400(FastMap x0, Entry x1) {
      x0.mapEntry(x1);
   }

   // $FF: synthetic method
   static int access$702(FastMap x0, int x1) {
      return x0._entryCount = x1;
   }

   // $FF: synthetic method
   static boolean access$1502(FastMap x0, boolean x1) {
      return x0._useSubMaps = x1;
   }

   // $FF: synthetic method
   static boolean access$1500(FastMap x0) {
      return x0._useSubMaps;
   }

   // $FF: synthetic method
   static Values access$1602(FastMap x0, Values x1) {
      return x0._values = x1;
   }

   // $FF: synthetic method
   static EntrySet access$2502(FastMap x0, EntrySet x1) {
      return x0._entrySet = x1;
   }

   // $FF: synthetic method
   static KeySet access$3302(FastMap x0, KeySet x1) {
      return x0._keySet = x1;
   }

   // $FF: synthetic method
   static Map access$4002(FastMap x0, Map x1) {
      return x0._unmodifiable = x1;
   }

   private final class Values extends FastCollection {
      private Values() {
      }

      public int size() {
         return FastMap.this.size();
      }

      public void clear() {
         FastMap.this.clear();
      }

      public FastCollection.Record head() {
         return FastMap.this._head;
      }

      public FastCollection.Record tail() {
         return FastMap.this._tail;
      }

      public Object valueOf(FastCollection.Record record) {
         return ((Entry)record)._value;
      }

      public void delete(FastCollection.Record record) {
         FastMap.this.remove(((Entry)record).getKey());
      }

      public FastComparator getValueComparator() {
         return FastMap.this._valueComparator;
      }

      public Iterator iterator() {
         return FastMap.ValueIterator.valueOf(FastMap.this);
      }
   }

   private static final class ValueIterator implements Iterator {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new ValueIterator();
         }

         protected void cleanup(Object obj) {
            ValueIterator iterator = (ValueIterator)obj;
            iterator._map = null;
            iterator._current = null;
            iterator._next = null;
            iterator._tail = null;
         }
      };
      private FastMap _map;
      private Entry _current;
      private Entry _next;
      private Entry _tail;

      public static ValueIterator valueOf(FastMap map) {
         ValueIterator iterator = (ValueIterator)FACTORY.object();
         iterator._map = map;
         iterator._next = map._head._next;
         iterator._tail = map._tail;
         return iterator;
      }

      private ValueIterator() {
      }

      public boolean hasNext() {
         return this._next != this._tail;
      }

      public Object next() {
         if (this._next == this._tail) {
            throw new NoSuchElementException();
         } else {
            this._current = this._next;
            this._next = this._next._next;
            return this._current._value;
         }
      }

      public void remove() {
         if (this._current != null) {
            this._next = this._current._next;
            this._map.remove(this._current._key);
            this._current = null;
         } else {
            throw new IllegalStateException();
         }
      }
   }

   private final class EntrySet extends FastCollection implements Set {
      private final FastComparator _entryComparator;

      private EntrySet() {
         // $FF: Couldn't be decompiled
      }

      public int size() {
         return FastMap.this.size();
      }

      public void clear() {
         FastMap.this.clear();
      }

      public boolean contains(Object obj) {
         if (obj instanceof Map.Entry) {
            Map.Entry thatEntry = (Map.Entry)obj;
            Entry thisEntry = FastMap.this.getEntry(thatEntry.getKey());
            return thisEntry == null ? false : FastMap.this._valueComparator.areEqual(thisEntry.getValue(), thatEntry.getValue());
         } else {
            return false;
         }
      }

      public FastCollection.Record head() {
         return FastMap.this._head;
      }

      public FastCollection.Record tail() {
         return FastMap.this._tail;
      }

      public Object valueOf(FastCollection.Record record) {
         return (Map.Entry)record;
      }

      public void delete(FastCollection.Record record) {
         FastMap.this.remove(((Entry)record).getKey());
      }

      public FastComparator getValueComparator() {
         return this._entryComparator;
      }

      public Iterator iterator() {
         return FastMap.EntryIterator.valueOf(FastMap.this);
      }
   }

   private static final class EntryIterator implements Iterator {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new EntryIterator();
         }

         protected void cleanup(Object obj) {
            EntryIterator iterator = (EntryIterator)obj;
            iterator._map = null;
            iterator._current = null;
            iterator._next = null;
            iterator._tail = null;
         }
      };
      private FastMap _map;
      private Entry _current;
      private Entry _next;
      private Entry _tail;

      public static EntryIterator valueOf(FastMap map) {
         EntryIterator iterator = (EntryIterator)FACTORY.object();
         iterator._map = map;
         iterator._next = map._head._next;
         iterator._tail = map._tail;
         return iterator;
      }

      private EntryIterator() {
      }

      public boolean hasNext() {
         return this._next != this._tail;
      }

      public Object next() {
         if (this._next == this._tail) {
            throw new NoSuchElementException();
         } else {
            this._current = this._next;
            this._next = this._next._next;
            return this._current;
         }
      }

      public void remove() {
         if (this._current != null) {
            this._next = this._current._next;
            this._map.remove(this._current._key);
            this._current = null;
         } else {
            throw new IllegalStateException();
         }
      }
   }

   private final class KeySet extends FastCollection implements Set {
      private KeySet() {
      }

      public int size() {
         return FastMap.this.size();
      }

      public void clear() {
         FastMap.this.clear();
      }

      public boolean contains(Object obj) {
         return FastMap.this.containsKey(obj);
      }

      public boolean remove(Object obj) {
         return FastMap.this.remove(obj) != null;
      }

      public FastCollection.Record head() {
         return FastMap.this._head;
      }

      public FastCollection.Record tail() {
         return FastMap.this._tail;
      }

      public Object valueOf(FastCollection.Record record) {
         return ((Entry)record)._key;
      }

      public void delete(FastCollection.Record record) {
         FastMap.this.remove(((Entry)record).getKey());
      }

      public FastComparator getValueComparator() {
         return FastMap.this._keyComparator;
      }

      public Iterator iterator() {
         return FastMap.KeyIterator.valueOf(FastMap.this);
      }
   }

   private static final class KeyIterator implements Iterator {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new KeyIterator();
         }

         protected void cleanup(Object obj) {
            KeyIterator iterator = (KeyIterator)obj;
            iterator._map = null;
            iterator._current = null;
            iterator._next = null;
            iterator._tail = null;
         }
      };
      private FastMap _map;
      private Entry _current;
      private Entry _next;
      private Entry _tail;

      public static KeyIterator valueOf(FastMap map) {
         KeyIterator iterator = (KeyIterator)FACTORY.object();
         iterator._map = map;
         iterator._next = map._head._next;
         iterator._tail = map._tail;
         return iterator;
      }

      private KeyIterator() {
      }

      public boolean hasNext() {
         return this._next != this._tail;
      }

      public Object next() {
         if (this._next == this._tail) {
            throw new NoSuchElementException();
         } else {
            this._current = this._next;
            this._next = this._next._next;
            return this._current._key;
         }
      }

      public void remove() {
         if (this._current != null) {
            this._next = this._current._next;
            this._map.remove(this._current._key);
            this._current = null;
         } else {
            throw new IllegalStateException();
         }
      }
   }

   public static class Entry implements Map.Entry, FastCollection.Record, Realtime {
      public static final Entry NULL = new Entry();
      private Entry _next;
      private Entry _previous;
      private Object _key;
      private Object _value;
      private int _keyHash;

      protected Entry() {
      }

      public final Entry getNext() {
         return this._next;
      }

      public final Entry getPrevious() {
         return this._previous;
      }

      public final Object getKey() {
         return this._key;
      }

      public final Object getValue() {
         return this._value;
      }

      public final Object setValue(Object value) {
         V old = (V)this._value;
         this._value = value;
         return old;
      }

      public boolean equals(Object that) {
         if (!(that instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry entry = (Map.Entry)that;
            return FastComparator.DEFAULT.areEqual(this._key, entry.getKey()) && FastComparator.DEFAULT.areEqual(this._value, entry.getValue());
         }
      }

      public int hashCode() {
         return (this._key != null ? this._key.hashCode() : 0) ^ (this._value != null ? this._value.hashCode() : 0);
      }

      public Text toText() {
         return Text.valueOf(this._key).plus("=").plus(this._value);
      }
   }

   private final class Unmodifiable implements Map, Serializable {
      private Unmodifiable() {
      }

      public boolean equals(Object obj) {
         return FastMap.this.equals(obj);
      }

      public int hashCode() {
         return FastMap.this.hashCode();
      }

      public Text toText() {
         return FastMap.this.toText();
      }

      public int size() {
         return FastMap.this.size();
      }

      public boolean isEmpty() {
         return FastMap.this.isEmpty();
      }

      public boolean containsKey(Object key) {
         return FastMap.this.containsKey(key);
      }

      public boolean containsValue(Object value) {
         return FastMap.this.containsValue(value);
      }

      public Object get(Object key) {
         return FastMap.this.get(key);
      }

      public Object put(Object key, Object value) {
         throw new UnsupportedOperationException("Unmodifiable map");
      }

      public Object remove(Object key) {
         throw new UnsupportedOperationException("Unmodifiable map");
      }

      public void putAll(Map map) {
         throw new UnsupportedOperationException("Unmodifiable map");
      }

      public void clear() {
         throw new UnsupportedOperationException("Unmodifiable map");
      }

      public Set keySet() {
         return (Set)((KeySet)FastMap.this.keySet()).unmodifiable();
      }

      public Collection values() {
         return ((Values)FastMap.this.values()).unmodifiable();
      }

      public Set entrySet() {
         throw new UnsupportedOperationException("Direct view over unmodifiable map entries is not supported  (to prevent access to Entry.setValue(Object) method). To iterate over unmodifiable map entries, applications may use the keySet() and values() fast collection views in conjonction.");
      }
   }
}
