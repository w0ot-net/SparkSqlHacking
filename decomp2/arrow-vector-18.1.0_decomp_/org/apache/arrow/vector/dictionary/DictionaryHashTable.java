package org.apache.arrow.vector.dictionary;

import java.util.function.BiFunction;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.compare.Range;
import org.apache.arrow.vector.compare.RangeEqualsVisitor;

public class DictionaryHashTable {
   static final int NULL_VALUE = -1;
   static final int DEFAULT_INITIAL_CAPACITY = 16;
   static final int MAXIMUM_CAPACITY = 1073741824;
   static final float DEFAULT_LOAD_FACTOR = 0.75F;
   static final Entry[] EMPTY_TABLE = new Entry[0];
   transient Entry[] table;
   transient int size;
   int threshold;
   final float loadFactor;
   private final ValueVector dictionary;
   private final ArrowBufHasher hasher;

   public DictionaryHashTable(int initialCapacity, ValueVector dictionary, ArrowBufHasher hasher) {
      this.table = EMPTY_TABLE;
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
      } else {
         if (initialCapacity > 1073741824) {
            initialCapacity = 1073741824;
         }

         this.loadFactor = 0.75F;
         this.threshold = initialCapacity;
         this.dictionary = dictionary;
         this.hasher = hasher;

         for(int i = 0; i < this.dictionary.getValueCount(); ++i) {
            this.put(i);
         }

      }
   }

   public DictionaryHashTable(ValueVector dictionary, ArrowBufHasher hasher) {
      this(16, dictionary, hasher);
   }

   public DictionaryHashTable(ValueVector dictionary) {
      this(dictionary, SimpleHasher.INSTANCE);
   }

   private void inflateTable(int threshold) {
      int capacity = roundUpToPowerOf2(threshold);
      this.threshold = (int)Math.min((float)capacity * this.loadFactor, 1.07374182E9F);
      this.table = new Entry[capacity];
   }

   static int indexFor(int h, int length) {
      return h & length - 1;
   }

   static final int roundUpToPowerOf2(int size) {
      int n = size - 1;
      n |= n >>> 1;
      n |= n >>> 2;
      n |= n >>> 4;
      n |= n >>> 8;
      n |= n >>> 16;
      return n < 0 ? 1 : (n >= 1073741824 ? 1073741824 : n + 1);
   }

   public int getIndex(int indexInArray, ValueVector toEncode) {
      int hash = toEncode.hashCode(indexInArray, this.hasher);
      int index = indexFor(hash, this.table.length);
      RangeEqualsVisitor equalVisitor = new RangeEqualsVisitor(this.dictionary, toEncode, (BiFunction)null);
      Range range = new Range(0, 0, 1);

      for(Entry e = this.table[index]; e != null; e = e.next) {
         if (e.hash == hash) {
            int dictIndex = e.index;
            range = range.setRightStart(indexInArray).setLeftStart(dictIndex);
            if (equalVisitor.rangeEquals(range)) {
               return dictIndex;
            }
         }
      }

      return -1;
   }

   private void put(int indexInDictionary) {
      if (this.table == EMPTY_TABLE) {
         this.inflateTable(this.threshold);
      }

      int hash = this.dictionary.hashCode(indexInDictionary, this.hasher);
      int i = indexFor(hash, this.table.length);

      for(Entry e = this.table[i]; e != null; e = e.next) {
         if (e.hash == hash && e.index == indexInDictionary) {
            return;
         }
      }

      this.addEntry(hash, indexInDictionary, i);
   }

   void createEntry(int hash, int index, int bucketIndex) {
      Entry e = this.table[bucketIndex];
      this.table[bucketIndex] = new Entry(hash, index, e);
      ++this.size;
   }

   void addEntry(int hash, int index, int bucketIndex) {
      if (this.size >= this.threshold && null != this.table[bucketIndex]) {
         this.resize(2 * this.table.length);
         bucketIndex = indexFor(hash, this.table.length);
      }

      this.createEntry(hash, index, bucketIndex);
   }

   void resize(int newCapacity) {
      Entry[] oldTable = this.table;
      int oldCapacity = oldTable.length;
      if (oldCapacity == 1073741824) {
         this.threshold = Integer.MAX_VALUE;
      } else {
         Entry[] newTable = new Entry[newCapacity];
         this.transfer(newTable);
         this.table = newTable;
         this.threshold = (int)Math.min((float)newCapacity * this.loadFactor, 1.07374182E9F);
      }
   }

   void transfer(Entry[] newTable) {
      int newCapacity = newTable.length;
      Entry[] var3 = this.table;
      int var4 = var3.length;

      Entry next;
      for(int var5 = 0; var5 < var4; ++var5) {
         for(Entry e = var3[var5]; null != e; e = next) {
            next = e.next;
            int i = indexFor(e.hash, newCapacity);
            e.next = newTable[i];
            newTable[i] = e;
         }
      }

   }

   public int size() {
      return this.size;
   }

   public void clear() {
      this.size = 0;

      for(int i = 0; i < this.table.length; ++i) {
         this.table[i] = null;
      }

   }

   static class Entry {
      int index;
      Entry next;
      int hash;

      Entry(int hash, int index, Entry next) {
         this.index = index;
         this.hash = hash;
         this.next = next;
      }

      public final int getIndex() {
         return this.index;
      }

      public int hashCode() {
         return this.hash;
      }

      public final boolean equals(Object o) {
         if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry e = (Entry)o;
            return this.index == e.getIndex();
         }
      }
   }
}
