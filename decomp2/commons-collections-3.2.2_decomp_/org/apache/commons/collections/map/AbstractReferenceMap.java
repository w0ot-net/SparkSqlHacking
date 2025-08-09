package org.apache.commons.collections.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.keyvalue.DefaultMapEntry;

public abstract class AbstractReferenceMap extends AbstractHashedMap {
   public static final int HARD = 0;
   public static final int SOFT = 1;
   public static final int WEAK = 2;
   protected int keyType;
   protected int valueType;
   protected boolean purgeValues;
   private transient ReferenceQueue queue;

   protected AbstractReferenceMap() {
   }

   protected AbstractReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
      super(capacity, loadFactor);
      verify("keyType", keyType);
      verify("valueType", valueType);
      this.keyType = keyType;
      this.valueType = valueType;
      this.purgeValues = purgeValues;
   }

   protected void init() {
      this.queue = new ReferenceQueue();
   }

   private static void verify(String name, int type) {
      if (type < 0 || type > 2) {
         throw new IllegalArgumentException(name + " must be HARD, SOFT, WEAK.");
      }
   }

   public int size() {
      this.purgeBeforeRead();
      return super.size();
   }

   public boolean isEmpty() {
      this.purgeBeforeRead();
      return super.isEmpty();
   }

   public boolean containsKey(Object key) {
      this.purgeBeforeRead();
      Map.Entry entry = this.getEntry(key);
      if (entry == null) {
         return false;
      } else {
         return entry.getValue() != null;
      }
   }

   public boolean containsValue(Object value) {
      this.purgeBeforeRead();
      return value == null ? false : super.containsValue(value);
   }

   public Object get(Object key) {
      this.purgeBeforeRead();
      Map.Entry entry = this.getEntry(key);
      return entry == null ? null : entry.getValue();
   }

   public Object put(Object key, Object value) {
      if (key == null) {
         throw new NullPointerException("null keys not allowed");
      } else if (value == null) {
         throw new NullPointerException("null values not allowed");
      } else {
         this.purgeBeforeWrite();
         return super.put(key, value);
      }
   }

   public Object remove(Object key) {
      if (key == null) {
         return null;
      } else {
         this.purgeBeforeWrite();
         return super.remove(key);
      }
   }

   public void clear() {
      super.clear();

      while(this.queue.poll() != null) {
      }

   }

   public MapIterator mapIterator() {
      return new ReferenceMapIterator(this);
   }

   public Set entrySet() {
      if (this.entrySet == null) {
         this.entrySet = new ReferenceEntrySet(this);
      }

      return this.entrySet;
   }

   public Set keySet() {
      if (this.keySet == null) {
         this.keySet = new ReferenceKeySet(this);
      }

      return this.keySet;
   }

   public Collection values() {
      if (this.values == null) {
         this.values = new ReferenceValues(this);
      }

      return this.values;
   }

   protected void purgeBeforeRead() {
      this.purge();
   }

   protected void purgeBeforeWrite() {
      this.purge();
   }

   protected void purge() {
      for(Reference ref = this.queue.poll(); ref != null; ref = this.queue.poll()) {
         this.purge(ref);
      }

   }

   protected void purge(Reference ref) {
      int hash = ref.hashCode();
      int index = this.hashIndex(hash, this.data.length);
      AbstractHashedMap.HashEntry previous = null;

      for(AbstractHashedMap.HashEntry entry = this.data[index]; entry != null; entry = entry.next) {
         if (((ReferenceEntry)entry).purge(ref)) {
            if (previous == null) {
               this.data[index] = entry.next;
            } else {
               previous.next = entry.next;
            }

            --this.size;
            return;
         }

         previous = entry;
      }

   }

   protected AbstractHashedMap.HashEntry getEntry(Object key) {
      return key == null ? null : super.getEntry(key);
   }

   protected int hashEntry(Object key, Object value) {
      return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
   }

   protected boolean isEqualKey(Object key1, Object key2) {
      key2 = this.keyType > 0 ? ((Reference)key2).get() : key2;
      return key1 == key2 || key1.equals(key2);
   }

   protected AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
      return new ReferenceEntry(this, next, hashCode, key, value);
   }

   protected Iterator createEntrySetIterator() {
      return new ReferenceEntrySetIterator(this);
   }

   protected Iterator createKeySetIterator() {
      return new ReferenceKeySetIterator(this);
   }

   protected Iterator createValuesIterator() {
      return new ReferenceValuesIterator(this);
   }

   protected void doWriteObject(ObjectOutputStream out) throws IOException {
      out.writeInt(this.keyType);
      out.writeInt(this.valueType);
      out.writeBoolean(this.purgeValues);
      out.writeFloat(this.loadFactor);
      out.writeInt(this.data.length);
      MapIterator it = this.mapIterator();

      while(it.hasNext()) {
         out.writeObject(it.next());
         out.writeObject(it.getValue());
      }

      out.writeObject((Object)null);
   }

   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      this.keyType = in.readInt();
      this.valueType = in.readInt();
      this.purgeValues = in.readBoolean();
      this.loadFactor = in.readFloat();
      int capacity = in.readInt();
      this.init();
      this.data = new AbstractHashedMap.HashEntry[capacity];

      while(true) {
         Object key = in.readObject();
         if (key == null) {
            this.threshold = this.calculateThreshold(this.data.length, this.loadFactor);
            return;
         }

         Object value = in.readObject();
         this.put(key, value);
      }
   }

   static class ReferenceEntrySet extends AbstractHashedMap.EntrySet {
      protected ReferenceEntrySet(AbstractHashedMap parent) {
         super(parent);
      }

      public Object[] toArray() {
         return this.toArray(new Object[0]);
      }

      public Object[] toArray(Object[] arr) {
         ArrayList list = new ArrayList();

         for(Map.Entry e : this) {
            list.add(new DefaultMapEntry(e.getKey(), e.getValue()));
         }

         return list.toArray(arr);
      }
   }

   static class ReferenceKeySet extends AbstractHashedMap.KeySet {
      protected ReferenceKeySet(AbstractHashedMap parent) {
         super(parent);
      }

      public Object[] toArray() {
         return this.toArray(new Object[0]);
      }

      public Object[] toArray(Object[] arr) {
         List list = new ArrayList(this.parent.size());
         Iterator it = this.iterator();

         while(it.hasNext()) {
            list.add(it.next());
         }

         return list.toArray(arr);
      }
   }

   static class ReferenceValues extends AbstractHashedMap.Values {
      protected ReferenceValues(AbstractHashedMap parent) {
         super(parent);
      }

      public Object[] toArray() {
         return this.toArray(new Object[0]);
      }

      public Object[] toArray(Object[] arr) {
         List list = new ArrayList(this.parent.size());
         Iterator it = this.iterator();

         while(it.hasNext()) {
            list.add(it.next());
         }

         return list.toArray(arr);
      }
   }

   protected static class ReferenceEntry extends AbstractHashedMap.HashEntry {
      protected final AbstractReferenceMap parent;

      public ReferenceEntry(AbstractReferenceMap parent, AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
         super(next, hashCode, (Object)null, (Object)null);
         this.parent = parent;
         this.key = this.toReference(parent.keyType, key, hashCode);
         this.value = this.toReference(parent.valueType, value, hashCode);
      }

      public Object getKey() {
         return this.parent.keyType > 0 ? ((Reference)this.key).get() : this.key;
      }

      public Object getValue() {
         return this.parent.valueType > 0 ? ((Reference)this.value).get() : this.value;
      }

      public Object setValue(Object obj) {
         Object old = this.getValue();
         if (this.parent.valueType > 0) {
            ((Reference)this.value).clear();
         }

         this.value = this.toReference(this.parent.valueType, obj, this.hashCode);
         return old;
      }

      public boolean equals(Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry entry = (Map.Entry)obj;
            Object entryKey = entry.getKey();
            Object entryValue = entry.getValue();
            if (entryKey != null && entryValue != null) {
               return this.parent.isEqualKey(entryKey, this.key) && this.parent.isEqualValue(entryValue, this.getValue());
            } else {
               return false;
            }
         }
      }

      public int hashCode() {
         return this.parent.hashEntry(this.getKey(), this.getValue());
      }

      protected Object toReference(int type, Object referent, int hash) {
         switch (type) {
            case 0:
               return referent;
            case 1:
               return new SoftRef(hash, referent, this.parent.queue);
            case 2:
               return new WeakRef(hash, referent, this.parent.queue);
            default:
               throw new Error();
         }
      }

      boolean purge(Reference ref) {
         boolean r = this.parent.keyType > 0 && this.key == ref;
         r = r || this.parent.valueType > 0 && this.value == ref;
         if (r) {
            if (this.parent.keyType > 0) {
               ((Reference)this.key).clear();
            }

            if (this.parent.valueType > 0) {
               ((Reference)this.value).clear();
            } else if (this.parent.purgeValues) {
               this.value = null;
            }
         }

         return r;
      }

      protected ReferenceEntry next() {
         return (ReferenceEntry)this.next;
      }
   }

   static class ReferenceEntrySetIterator implements Iterator {
      final AbstractReferenceMap parent;
      int index;
      ReferenceEntry entry;
      ReferenceEntry previous;
      Object nextKey;
      Object nextValue;
      Object currentKey;
      Object currentValue;
      int expectedModCount;

      public ReferenceEntrySetIterator(AbstractReferenceMap parent) {
         this.parent = parent;
         this.index = parent.size() != 0 ? parent.data.length : 0;
         this.expectedModCount = parent.modCount;
      }

      public boolean hasNext() {
         this.checkMod();

         while(this.nextNull()) {
            ReferenceEntry e = this.entry;

            int i;
            for(i = this.index; e == null && i > 0; e = (ReferenceEntry)this.parent.data[i]) {
               --i;
            }

            this.entry = e;
            this.index = i;
            if (e == null) {
               this.currentKey = null;
               this.currentValue = null;
               return false;
            }

            this.nextKey = e.getKey();
            this.nextValue = e.getValue();
            if (this.nextNull()) {
               this.entry = this.entry.next();
            }
         }

         return true;
      }

      private void checkMod() {
         if (this.parent.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }

      private boolean nextNull() {
         return this.nextKey == null || this.nextValue == null;
      }

      protected ReferenceEntry nextEntry() {
         this.checkMod();
         if (this.nextNull() && !this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.previous = this.entry;
            this.entry = this.entry.next();
            this.currentKey = this.nextKey;
            this.currentValue = this.nextValue;
            this.nextKey = null;
            this.nextValue = null;
            return this.previous;
         }
      }

      protected ReferenceEntry currentEntry() {
         this.checkMod();
         return this.previous;
      }

      public Object next() {
         return this.nextEntry();
      }

      public void remove() {
         this.checkMod();
         if (this.previous == null) {
            throw new IllegalStateException();
         } else {
            this.parent.remove(this.currentKey);
            this.previous = null;
            this.currentKey = null;
            this.currentValue = null;
            this.expectedModCount = this.parent.modCount;
         }
      }
   }

   static class ReferenceKeySetIterator extends ReferenceEntrySetIterator {
      ReferenceKeySetIterator(AbstractReferenceMap parent) {
         super(parent);
      }

      public Object next() {
         return this.nextEntry().getKey();
      }
   }

   static class ReferenceValuesIterator extends ReferenceEntrySetIterator {
      ReferenceValuesIterator(AbstractReferenceMap parent) {
         super(parent);
      }

      public Object next() {
         return this.nextEntry().getValue();
      }
   }

   static class ReferenceMapIterator extends ReferenceEntrySetIterator implements MapIterator {
      protected ReferenceMapIterator(AbstractReferenceMap parent) {
         super(parent);
      }

      public Object next() {
         return this.nextEntry().getKey();
      }

      public Object getKey() {
         AbstractHashedMap.HashEntry current = this.currentEntry();
         if (current == null) {
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
         } else {
            return current.getKey();
         }
      }

      public Object getValue() {
         AbstractHashedMap.HashEntry current = this.currentEntry();
         if (current == null) {
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
         } else {
            return current.getValue();
         }
      }

      public Object setValue(Object value) {
         AbstractHashedMap.HashEntry current = this.currentEntry();
         if (current == null) {
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
         } else {
            return current.setValue(value);
         }
      }
   }

   static class SoftRef extends SoftReference {
      private int hash;

      public SoftRef(int hash, Object r, ReferenceQueue q) {
         super(r, q);
         this.hash = hash;
      }

      public int hashCode() {
         return this.hash;
      }
   }

   static class WeakRef extends WeakReference {
      private int hash;

      public WeakRef(int hash, Object r, ReferenceQueue q) {
         super(r, q);
         this.hash = hash;
      }

      public int hashCode() {
         return this.hash;
      }
   }
}
