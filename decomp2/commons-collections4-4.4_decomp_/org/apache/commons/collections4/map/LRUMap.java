package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.BoundedMap;

public class LRUMap extends AbstractLinkedMap implements BoundedMap, Serializable, Cloneable {
   private static final long serialVersionUID = -612114643488955218L;
   protected static final int DEFAULT_MAX_SIZE = 100;
   private transient int maxSize;
   private boolean scanUntilRemovable;

   public LRUMap() {
      this(100, 0.75F, false);
   }

   public LRUMap(int maxSize) {
      this(maxSize, 0.75F);
   }

   public LRUMap(int maxSize, int initialSize) {
      this(maxSize, initialSize, 0.75F);
   }

   public LRUMap(int maxSize, boolean scanUntilRemovable) {
      this(maxSize, 0.75F, scanUntilRemovable);
   }

   public LRUMap(int maxSize, float loadFactor) {
      this(maxSize, loadFactor, false);
   }

   public LRUMap(int maxSize, int initialSize, float loadFactor) {
      this(maxSize, initialSize, loadFactor, false);
   }

   public LRUMap(int maxSize, float loadFactor, boolean scanUntilRemovable) {
      this(maxSize, maxSize, loadFactor, scanUntilRemovable);
   }

   public LRUMap(int maxSize, int initialSize, float loadFactor, boolean scanUntilRemovable) {
      super(initialSize, loadFactor);
      if (maxSize < 1) {
         throw new IllegalArgumentException("LRUMap max size must be greater than 0");
      } else if (initialSize > maxSize) {
         throw new IllegalArgumentException("LRUMap initial size must not be greather than max size");
      } else {
         this.maxSize = maxSize;
         this.scanUntilRemovable = scanUntilRemovable;
      }
   }

   public LRUMap(Map map) {
      this(map, false);
   }

   public LRUMap(Map map, boolean scanUntilRemovable) {
      this(map.size(), 0.75F, scanUntilRemovable);
      this.putAll(map);
   }

   public Object get(Object key) {
      return this.get(key, true);
   }

   public Object get(Object key, boolean updateToMRU) {
      AbstractLinkedMap.LinkEntry<K, V> entry = this.getEntry(key);
      if (entry == null) {
         return null;
      } else {
         if (updateToMRU) {
            this.moveToMRU(entry);
         }

         return entry.getValue();
      }
   }

   protected void moveToMRU(AbstractLinkedMap.LinkEntry entry) {
      if (entry.after != this.header) {
         ++this.modCount;
         if (entry.before == null) {
            throw new IllegalStateException("Entry.before is null. This should not occur if your keys are immutable, and you have used synchronization properly.");
         }

         entry.before.after = entry.after;
         entry.after.before = entry.before;
         entry.after = this.header;
         entry.before = this.header.before;
         this.header.before.after = entry;
         this.header.before = entry;
      } else if (entry == this.header) {
         throw new IllegalStateException("Can't move header to MRU This should not occur if your keys are immutable, and you have used synchronization properly.");
      }

   }

   protected void updateEntry(AbstractHashedMap.HashEntry entry, Object newValue) {
      this.moveToMRU((AbstractLinkedMap.LinkEntry)entry);
      entry.setValue(newValue);
   }

   protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
      if (this.isFull()) {
         AbstractLinkedMap.LinkEntry<K, V> reuse = this.header.after;
         boolean removeLRUEntry = false;
         if (!this.scanUntilRemovable) {
            removeLRUEntry = this.removeLRU(reuse);
         } else {
            while(reuse != this.header && reuse != null) {
               if (this.removeLRU(reuse)) {
                  removeLRUEntry = true;
                  break;
               }

               reuse = reuse.after;
            }

            if (reuse == null) {
               throw new IllegalStateException("Entry.after=null, header.after=" + this.header.after + " header.before=" + this.header.before + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
            }
         }

         if (removeLRUEntry) {
            if (reuse == null) {
               throw new IllegalStateException("reuse=null, header.after=" + this.header.after + " header.before=" + this.header.before + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
            }

            this.reuseMapping(reuse, hashIndex, hashCode, key, value);
         } else {
            super.addMapping(hashIndex, hashCode, key, value);
         }
      } else {
         super.addMapping(hashIndex, hashCode, key, value);
      }

   }

   protected void reuseMapping(AbstractLinkedMap.LinkEntry entry, int hashIndex, int hashCode, Object key, Object value) {
      try {
         int removeIndex = this.hashIndex(entry.hashCode, this.data.length);
         AbstractHashedMap.HashEntry<K, V>[] tmp = this.data;
         AbstractHashedMap.HashEntry<K, V> loop = tmp[removeIndex];

         AbstractHashedMap.HashEntry<K, V> previous;
         for(previous = null; loop != entry && loop != null; loop = loop.next) {
            previous = loop;
         }

         if (loop == null) {
            throw new IllegalStateException("Entry.next=null, data[removeIndex]=" + this.data[removeIndex] + " previous=" + previous + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
         } else {
            ++this.modCount;
            this.removeEntry(entry, removeIndex, previous);
            this.reuseEntry(entry, hashIndex, hashCode, key, value);
            this.addEntry(entry, hashIndex);
         }
      } catch (NullPointerException var10) {
         throw new IllegalStateException("NPE, entry=" + entry + " entryIsHeader=" + (entry == this.header) + " key=" + key + " value=" + value + " size=" + this.size + " maxSize=" + this.maxSize + " This should not occur if your keys are immutable, and you have used synchronization properly.");
      }
   }

   protected boolean removeLRU(AbstractLinkedMap.LinkEntry entry) {
      return true;
   }

   public boolean isFull() {
      return this.size >= this.maxSize;
   }

   public int maxSize() {
      return this.maxSize;
   }

   public boolean isScanUntilRemovable() {
      return this.scanUntilRemovable;
   }

   public LRUMap clone() {
      return (LRUMap)super.clone();
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
      this.doWriteObject(out);
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.doReadObject(in);
   }

   protected void doWriteObject(ObjectOutputStream out) throws IOException {
      out.writeInt(this.maxSize);
      super.doWriteObject(out);
   }

   protected void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      this.maxSize = in.readInt();
      super.doReadObject(in);
   }
}
