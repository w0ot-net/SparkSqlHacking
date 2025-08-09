package org.apache.orc.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap extends AbstractMap {
   private final int maxArraySize;
   private List entryList;
   private Map overflowEntries;
   private boolean isImmutable;
   private volatile EntrySet lazyEntrySet;
   private Map overflowEntriesDescending;
   private volatile DescendingEntrySet lazyDescendingEntrySet;

   static SmallSortedMap newFieldMap(int arraySize) {
      return new SmallSortedMap(arraySize) {
         public void makeImmutable() {
            if (!this.isImmutable()) {
               for(int i = 0; i < this.getNumArrayEntries(); ++i) {
                  Map.Entry<FieldDescriptorType, Object> entry = this.getArrayEntryAt(i);
                  if (((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated()) {
                     List value = (List)entry.getValue();
                     entry.setValue(Collections.unmodifiableList(value));
                  }
               }

               for(Map.Entry entry : this.getOverflowEntries()) {
                  if (((FieldSet.FieldDescriptorLite)entry.getKey()).isRepeated()) {
                     List value = (List)entry.getValue();
                     entry.setValue(Collections.unmodifiableList(value));
                  }
               }
            }

            super.makeImmutable();
         }
      };
   }

   static SmallSortedMap newInstanceForTest(int arraySize) {
      return new SmallSortedMap(arraySize);
   }

   private SmallSortedMap(int arraySize) {
      this.maxArraySize = arraySize;
      this.entryList = Collections.emptyList();
      this.overflowEntries = Collections.emptyMap();
      this.overflowEntriesDescending = Collections.emptyMap();
   }

   public void makeImmutable() {
      if (!this.isImmutable) {
         this.overflowEntries = this.overflowEntries.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.overflowEntries);
         this.overflowEntriesDescending = this.overflowEntriesDescending.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.overflowEntriesDescending);
         this.isImmutable = true;
      }

   }

   public boolean isImmutable() {
      return this.isImmutable;
   }

   public int getNumArrayEntries() {
      return this.entryList.size();
   }

   public Map.Entry getArrayEntryAt(int index) {
      return (Map.Entry)this.entryList.get(index);
   }

   public int getNumOverflowEntries() {
      return this.overflowEntries.size();
   }

   public Iterable getOverflowEntries() {
      return (Iterable)(this.overflowEntries.isEmpty() ? SmallSortedMap.EmptySet.iterable() : this.overflowEntries.entrySet());
   }

   Iterable getOverflowEntriesDescending() {
      return (Iterable)(this.overflowEntriesDescending.isEmpty() ? SmallSortedMap.EmptySet.iterable() : this.overflowEntriesDescending.entrySet());
   }

   public int size() {
      return this.entryList.size() + this.overflowEntries.size();
   }

   public boolean containsKey(Object o) {
      K key = (K)((Comparable)o);
      return this.binarySearchInArray(key) >= 0 || this.overflowEntries.containsKey(key);
   }

   public Object get(Object o) {
      K key = (K)((Comparable)o);
      int index = this.binarySearchInArray(key);
      return index >= 0 ? ((Entry)this.entryList.get(index)).getValue() : this.overflowEntries.get(key);
   }

   public Object put(Comparable key, Object value) {
      this.checkMutable();
      int index = this.binarySearchInArray(key);
      if (index >= 0) {
         return ((Entry)this.entryList.get(index)).setValue(value);
      } else {
         this.ensureEntryArrayMutable();
         int insertionPoint = -(index + 1);
         if (insertionPoint >= this.maxArraySize) {
            return this.getOverflowEntriesMutable().put(key, value);
         } else {
            if (this.entryList.size() == this.maxArraySize) {
               SmallSortedMap<K, V>.Entry lastEntryInArray = (Entry)this.entryList.remove(this.maxArraySize - 1);
               this.getOverflowEntriesMutable().put(lastEntryInArray.getKey(), lastEntryInArray.getValue());
            }

            this.entryList.add(insertionPoint, new Entry(key, value));
            return null;
         }
      }
   }

   public void clear() {
      this.checkMutable();
      if (!this.entryList.isEmpty()) {
         this.entryList.clear();
      }

      if (!this.overflowEntries.isEmpty()) {
         this.overflowEntries.clear();
      }

   }

   public Object remove(Object o) {
      this.checkMutable();
      K key = (K)((Comparable)o);
      int index = this.binarySearchInArray(key);
      if (index >= 0) {
         return this.removeArrayEntryAt(index);
      } else {
         return this.overflowEntries.isEmpty() ? null : this.overflowEntries.remove(key);
      }
   }

   private Object removeArrayEntryAt(int index) {
      this.checkMutable();
      V removed = (V)((Entry)this.entryList.remove(index)).getValue();
      if (!this.overflowEntries.isEmpty()) {
         Iterator<Map.Entry<K, V>> iterator = this.getOverflowEntriesMutable().entrySet().iterator();
         this.entryList.add(new Entry((Map.Entry)iterator.next()));
         iterator.remove();
      }

      return removed;
   }

   private int binarySearchInArray(Comparable key) {
      int left = 0;
      int right = this.entryList.size() - 1;
      if (right >= 0) {
         int cmp = key.compareTo(((Entry)this.entryList.get(right)).getKey());
         if (cmp > 0) {
            return -(right + 2);
         }

         if (cmp == 0) {
            return right;
         }
      }

      while(left <= right) {
         int mid = (left + right) / 2;
         int cmp = key.compareTo(((Entry)this.entryList.get(mid)).getKey());
         if (cmp < 0) {
            right = mid - 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            left = mid + 1;
         }
      }

      return -(left + 1);
   }

   public Set entrySet() {
      if (this.lazyEntrySet == null) {
         this.lazyEntrySet = new EntrySet();
      }

      return this.lazyEntrySet;
   }

   Set descendingEntrySet() {
      if (this.lazyDescendingEntrySet == null) {
         this.lazyDescendingEntrySet = new DescendingEntrySet();
      }

      return this.lazyDescendingEntrySet;
   }

   private void checkMutable() {
      if (this.isImmutable) {
         throw new UnsupportedOperationException();
      }
   }

   private SortedMap getOverflowEntriesMutable() {
      this.checkMutable();
      if (this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap)) {
         this.overflowEntries = new TreeMap();
         this.overflowEntriesDescending = ((TreeMap)this.overflowEntries).descendingMap();
      }

      return (SortedMap)this.overflowEntries;
   }

   private void ensureEntryArrayMutable() {
      this.checkMutable();
      if (this.entryList.isEmpty() && !(this.entryList instanceof ArrayList)) {
         this.entryList = new ArrayList(this.maxArraySize);
      }

   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof SmallSortedMap)) {
         return super.equals(o);
      } else {
         SmallSortedMap<?, ?> other = (SmallSortedMap)o;
         int size = this.size();
         if (size != other.size()) {
            return false;
         } else {
            int numArrayEntries = this.getNumArrayEntries();
            if (numArrayEntries != other.getNumArrayEntries()) {
               return this.entrySet().equals(other.entrySet());
            } else {
               for(int i = 0; i < numArrayEntries; ++i) {
                  if (!this.getArrayEntryAt(i).equals(other.getArrayEntryAt(i))) {
                     return false;
                  }
               }

               if (numArrayEntries != size) {
                  return this.overflowEntries.equals(other.overflowEntries);
               } else {
                  return true;
               }
            }
         }
      }
   }

   public int hashCode() {
      int h = 0;
      int listSize = this.getNumArrayEntries();

      for(int i = 0; i < listSize; ++i) {
         h += ((Entry)this.entryList.get(i)).hashCode();
      }

      if (this.getNumOverflowEntries() > 0) {
         h += this.overflowEntries.hashCode();
      }

      return h;
   }

   private class Entry implements Map.Entry, Comparable {
      private final Comparable key;
      private Object value;

      Entry(Map.Entry copy) {
         this((Comparable)copy.getKey(), copy.getValue());
      }

      Entry(Comparable key, Object value) {
         this.key = key;
         this.value = value;
      }

      public Comparable getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public int compareTo(Entry other) {
         return this.getKey().compareTo(other.getKey());
      }

      public Object setValue(Object newValue) {
         SmallSortedMap.this.checkMutable();
         V oldValue = (V)this.value;
         this.value = newValue;
         return oldValue;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> other = (Map.Entry)o;
            return this.equals(this.key, other.getKey()) && this.equals(this.value, other.getValue());
         }
      }

      public int hashCode() {
         return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public String toString() {
         return this.key + "=" + this.value;
      }

      private boolean equals(Object o1, Object o2) {
         return o1 == null ? o2 == null : o1.equals(o2);
      }
   }

   private class EntrySet extends AbstractSet {
      private EntrySet() {
      }

      public Iterator iterator() {
         return SmallSortedMap.this.new EntryIterator();
      }

      public int size() {
         return SmallSortedMap.this.size();
      }

      public boolean contains(Object o) {
         Map.Entry<K, V> entry = (Map.Entry)o;
         V existing = (V)SmallSortedMap.this.get(entry.getKey());
         V value = (V)entry.getValue();
         return existing == value || existing != null && existing.equals(value);
      }

      public boolean add(Map.Entry entry) {
         if (!this.contains(entry)) {
            SmallSortedMap.this.put((Comparable)entry.getKey(), entry.getValue());
            return true;
         } else {
            return false;
         }
      }

      public boolean remove(Object o) {
         Map.Entry<K, V> entry = (Map.Entry)o;
         if (this.contains(entry)) {
            SmallSortedMap.this.remove(entry.getKey());
            return true;
         } else {
            return false;
         }
      }

      public void clear() {
         SmallSortedMap.this.clear();
      }
   }

   private class DescendingEntrySet extends EntrySet {
      private DescendingEntrySet() {
      }

      public Iterator iterator() {
         return SmallSortedMap.this.new DescendingEntryIterator();
      }
   }

   private class EntryIterator implements Iterator {
      private int pos;
      private boolean nextCalledBeforeRemove;
      private Iterator lazyOverflowIterator;

      private EntryIterator() {
         this.pos = -1;
      }

      public boolean hasNext() {
         return this.pos + 1 < SmallSortedMap.this.entryList.size() || !SmallSortedMap.this.overflowEntries.isEmpty() && this.getOverflowIterator().hasNext();
      }

      public Map.Entry next() {
         this.nextCalledBeforeRemove = true;
         return ++this.pos < SmallSortedMap.this.entryList.size() ? (Map.Entry)SmallSortedMap.this.entryList.get(this.pos) : (Map.Entry)this.getOverflowIterator().next();
      }

      public void remove() {
         if (!this.nextCalledBeforeRemove) {
            throw new IllegalStateException("remove() was called before next()");
         } else {
            this.nextCalledBeforeRemove = false;
            SmallSortedMap.this.checkMutable();
            if (this.pos < SmallSortedMap.this.entryList.size()) {
               SmallSortedMap.this.removeArrayEntryAt(this.pos--);
            } else {
               this.getOverflowIterator().remove();
            }

         }
      }

      private Iterator getOverflowIterator() {
         if (this.lazyOverflowIterator == null) {
            this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
         }

         return this.lazyOverflowIterator;
      }
   }

   private class DescendingEntryIterator implements Iterator {
      private int pos;
      private Iterator lazyOverflowIterator;

      private DescendingEntryIterator() {
         this.pos = SmallSortedMap.this.entryList.size();
      }

      public boolean hasNext() {
         return this.pos > 0 && this.pos <= SmallSortedMap.this.entryList.size() || this.getOverflowIterator().hasNext();
      }

      public Map.Entry next() {
         return this.getOverflowIterator().hasNext() ? (Map.Entry)this.getOverflowIterator().next() : (Map.Entry)SmallSortedMap.this.entryList.get(--this.pos);
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }

      private Iterator getOverflowIterator() {
         if (this.lazyOverflowIterator == null) {
            this.lazyOverflowIterator = SmallSortedMap.this.overflowEntriesDescending.entrySet().iterator();
         }

         return this.lazyOverflowIterator;
      }
   }

   private static class EmptySet {
      private static final Iterator ITERATOR = new Iterator() {
         public boolean hasNext() {
            return false;
         }

         public Object next() {
            throw new NoSuchElementException();
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
      private static final Iterable ITERABLE = new Iterable() {
         public Iterator iterator() {
            return SmallSortedMap.EmptySet.ITERATOR;
         }
      };

      static Iterable iterable() {
         return ITERABLE;
      }
   }
}
