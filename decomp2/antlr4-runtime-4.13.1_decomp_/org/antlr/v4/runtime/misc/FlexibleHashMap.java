package org.antlr.v4.runtime.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlexibleHashMap implements Map {
   public static final int INITAL_CAPACITY = 16;
   public static final int INITAL_BUCKET_CAPACITY = 8;
   public static final double LOAD_FACTOR = (double)0.75F;
   protected final AbstractEqualityComparator comparator;
   protected LinkedList[] buckets;
   protected int n;
   protected int currentPrime;
   protected int threshold;
   protected final int initialCapacity;
   protected final int initialBucketCapacity;

   public FlexibleHashMap() {
      this((AbstractEqualityComparator)null, 16, 8);
   }

   public FlexibleHashMap(AbstractEqualityComparator comparator) {
      this(comparator, 16, 8);
   }

   public FlexibleHashMap(AbstractEqualityComparator comparator, int initialCapacity, int initialBucketCapacity) {
      this.n = 0;
      this.currentPrime = 1;
      if (comparator == null) {
         comparator = ObjectEqualityComparator.INSTANCE;
      }

      this.comparator = comparator;
      this.initialCapacity = initialCapacity;
      this.initialBucketCapacity = initialBucketCapacity;
      this.threshold = (int)Math.floor((double)initialCapacity * (double)0.75F);
      this.buckets = createEntryListArray(initialBucketCapacity);
   }

   private static LinkedList[] createEntryListArray(int length) {
      LinkedList<Entry<K, V>>[] result = new LinkedList[length];
      return result;
   }

   protected int getBucket(Object key) {
      int hash = this.comparator.hashCode(key);
      int b = hash & this.buckets.length - 1;
      return b;
   }

   public Object get(Object key) {
      K typedKey = (K)key;
      if (key == null) {
         return null;
      } else {
         int b = this.getBucket(key);
         LinkedList<Entry<K, V>> bucket = this.buckets[b];
         if (bucket == null) {
            return null;
         } else {
            for(Entry e : bucket) {
               if (this.comparator.equals(e.key, typedKey)) {
                  return e.value;
               }
            }

            return null;
         }
      }
   }

   public Object put(Object key, Object value) {
      if (key == null) {
         return null;
      } else {
         if (this.n > this.threshold) {
            this.expand();
         }

         int b = this.getBucket(key);
         LinkedList<Entry<K, V>> bucket = this.buckets[b];
         if (bucket == null) {
            bucket = this.buckets[b] = new LinkedList();
         }

         for(Entry e : bucket) {
            if (this.comparator.equals(e.key, key)) {
               V prev = (V)e.value;
               e.value = value;
               ++this.n;
               return prev;
            }
         }

         bucket.add(new Entry(key, value));
         ++this.n;
         return null;
      }
   }

   public Object remove(Object key) {
      throw new UnsupportedOperationException();
   }

   public void putAll(Map m) {
      throw new UnsupportedOperationException();
   }

   public Set keySet() {
      throw new UnsupportedOperationException();
   }

   public Collection values() {
      List<V> a = new ArrayList(this.size());

      for(LinkedList bucket : this.buckets) {
         if (bucket != null) {
            for(Entry e : bucket) {
               a.add(e.value);
            }
         }
      }

      return a;
   }

   public Set entrySet() {
      throw new UnsupportedOperationException();
   }

   public boolean containsKey(Object key) {
      return this.get(key) != null;
   }

   public boolean containsValue(Object value) {
      throw new UnsupportedOperationException();
   }

   public int hashCode() {
      int hash = MurmurHash.initialize();

      for(LinkedList bucket : this.buckets) {
         if (bucket != null) {
            for(Entry e : bucket) {
               if (e == null) {
                  break;
               }

               hash = MurmurHash.update(hash, this.comparator.hashCode(e.key));
            }
         }
      }

      hash = MurmurHash.finish(hash, this.size());
      return hash;
   }

   public boolean equals(Object o) {
      throw new UnsupportedOperationException();
   }

   protected void expand() {
      LinkedList<Entry<K, V>>[] old = this.buckets;
      this.currentPrime += 4;
      int newCapacity = this.buckets.length * 2;
      LinkedList<Entry<K, V>>[] newTable = createEntryListArray(newCapacity);
      this.buckets = newTable;
      this.threshold = (int)((double)newCapacity * (double)0.75F);
      int oldSize = this.size();

      for(LinkedList bucket : old) {
         if (bucket != null) {
            for(Entry e : bucket) {
               if (e == null) {
                  break;
               }

               this.put(e.key, e.value);
            }
         }
      }

      this.n = oldSize;
   }

   public int size() {
      return this.n;
   }

   public boolean isEmpty() {
      return this.n == 0;
   }

   public void clear() {
      this.buckets = createEntryListArray(this.initialCapacity);
      this.n = 0;
      this.threshold = (int)Math.floor((double)this.initialCapacity * (double)0.75F);
   }

   public String toString() {
      if (this.size() == 0) {
         return "{}";
      } else {
         StringBuilder buf = new StringBuilder();
         buf.append('{');
         boolean first = true;

         for(LinkedList bucket : this.buckets) {
            if (bucket != null) {
               for(Entry e : bucket) {
                  if (e == null) {
                     break;
                  }

                  if (first) {
                     first = false;
                  } else {
                     buf.append(", ");
                  }

                  buf.append(e.toString());
               }
            }
         }

         buf.append('}');
         return buf.toString();
      }
   }

   public String toTableString() {
      StringBuilder buf = new StringBuilder();

      for(LinkedList bucket : this.buckets) {
         if (bucket == null) {
            buf.append("null\n");
         } else {
            buf.append('[');
            boolean first = true;

            for(Entry e : bucket) {
               if (first) {
                  first = false;
               } else {
                  buf.append(" ");
               }

               if (e == null) {
                  buf.append("_");
               } else {
                  buf.append(e.toString());
               }
            }

            buf.append("]\n");
         }
      }

      return buf.toString();
   }

   public static void main(String[] args) {
      FlexibleHashMap<String, Integer> map = new FlexibleHashMap();
      map.put("hi", 1);
      map.put("mom", 2);
      map.put("foo", 3);
      map.put("ach", 4);
      map.put("cbba", 5);
      map.put("d", 6);
      map.put("edf", 7);
      map.put("mom", 8);
      map.put("hi", 9);
      System.out.println(map);
      System.out.println(map.toTableString());
   }

   public static class Entry {
      public final Object key;
      public Object value;

      public Entry(Object key, Object value) {
         this.key = key;
         this.value = value;
      }

      public String toString() {
         return this.key.toString() + ":" + this.value.toString();
      }
   }
}
