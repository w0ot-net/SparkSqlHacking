package org.antlr.v4.runtime.misc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class Array2DHashSet implements Set {
   public static final int INITAL_CAPACITY = 16;
   public static final int INITAL_BUCKET_CAPACITY = 8;
   public static final double LOAD_FACTOR = (double)0.75F;
   protected final AbstractEqualityComparator comparator;
   protected Object[][] buckets;
   protected int n;
   protected int currentPrime;
   protected int threshold;
   protected final int initialCapacity;
   protected final int initialBucketCapacity;

   public Array2DHashSet() {
      this((AbstractEqualityComparator)null, 16, 8);
   }

   public Array2DHashSet(AbstractEqualityComparator comparator) {
      this(comparator, 16, 8);
   }

   public Array2DHashSet(AbstractEqualityComparator comparator, int initialCapacity, int initialBucketCapacity) {
      this.n = 0;
      this.currentPrime = 1;
      if (comparator == null) {
         comparator = ObjectEqualityComparator.INSTANCE;
      }

      this.comparator = comparator;
      this.initialCapacity = initialCapacity;
      this.initialBucketCapacity = initialBucketCapacity;
      this.buckets = this.createBuckets(initialCapacity);
      this.threshold = (int)Math.floor((double)initialCapacity * (double)0.75F);
   }

   public final Object getOrAdd(Object o) {
      if (this.n > this.threshold) {
         this.expand();
      }

      return this.getOrAddImpl(o);
   }

   protected Object getOrAddImpl(Object o) {
      int b = this.getBucket(o);
      T[] bucket = (T[])this.buckets[b];
      if (bucket == null) {
         bucket = (T[])this.createBucket(this.initialBucketCapacity);
         bucket[0] = o;
         this.buckets[b] = bucket;
         ++this.n;
         return o;
      } else {
         for(int i = 0; i < bucket.length; ++i) {
            T existing = (T)bucket[i];
            if (existing == null) {
               bucket[i] = o;
               ++this.n;
               return o;
            }

            if (this.comparator.equals(existing, o)) {
               return existing;
            }
         }

         int oldLength = bucket.length;
         bucket = (T[])Arrays.copyOf(bucket, bucket.length * 2);
         this.buckets[b] = bucket;
         bucket[oldLength] = o;
         ++this.n;
         return o;
      }
   }

   public Object get(Object o) {
      if (o == null) {
         return o;
      } else {
         int b = this.getBucket(o);
         T[] bucket = (T[])this.buckets[b];
         if (bucket == null) {
            return null;
         } else {
            for(Object e : bucket) {
               if (e == null) {
                  return null;
               }

               if (this.comparator.equals(e, o)) {
                  return e;
               }
            }

            return null;
         }
      }
   }

   protected final int getBucket(Object o) {
      int hash = this.comparator.hashCode(o);
      int b = hash & this.buckets.length - 1;
      return b;
   }

   public int hashCode() {
      int hash = MurmurHash.initialize();

      for(Object[] bucket : this.buckets) {
         if (bucket != null) {
            for(Object o : bucket) {
               if (o == null) {
                  break;
               }

               hash = MurmurHash.update(hash, this.comparator.hashCode(o));
            }
         }
      }

      hash = MurmurHash.finish(hash, this.size());
      return hash;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Array2DHashSet)) {
         return false;
      } else {
         Array2DHashSet<?> other = (Array2DHashSet)o;
         if (other.size() != this.size()) {
            return false;
         } else {
            boolean same = this.containsAll(other);
            return same;
         }
      }
   }

   protected void expand() {
      T[][] old = (T[][])this.buckets;
      this.currentPrime += 4;
      int newCapacity = this.buckets.length * 2;
      T[][] newTable = (T[][])this.createBuckets(newCapacity);
      int[] newBucketLengths = new int[newTable.length];
      this.buckets = newTable;
      this.threshold = (int)((double)newCapacity * (double)0.75F);
      int oldSize = this.size();

      for(Object[] bucket : old) {
         if (bucket != null) {
            for(Object o : bucket) {
               if (o == null) {
                  break;
               }

               int b = this.getBucket(o);
               int bucketLength = newBucketLengths[b];
               T[] newBucket;
               if (bucketLength == 0) {
                  newBucket = (T[])this.createBucket(this.initialBucketCapacity);
                  newTable[b] = newBucket;
               } else {
                  newBucket = (T[])newTable[b];
                  if (bucketLength == newBucket.length) {
                     newBucket = (T[])Arrays.copyOf(newBucket, newBucket.length * 2);
                     newTable[b] = newBucket;
                  }
               }

               newBucket[bucketLength] = o;
               int var10002 = newBucketLengths[b]++;
            }
         }
      }

      assert this.n == oldSize;

   }

   public final boolean add(Object t) {
      T existing = (T)this.getOrAdd(t);
      return existing == t;
   }

   public final int size() {
      return this.n;
   }

   public final boolean isEmpty() {
      return this.n == 0;
   }

   public final boolean contains(Object o) {
      return this.containsFast(this.asElementType(o));
   }

   public boolean containsFast(Object obj) {
      if (obj == null) {
         return false;
      } else {
         return this.get(obj) != null;
      }
   }

   public Iterator iterator() {
      return new SetIterator(this.toArray());
   }

   public Object[] toArray() {
      T[] a = (T[])this.createBucket(this.size());
      int i = 0;

      for(Object[] bucket : this.buckets) {
         if (bucket != null) {
            for(Object o : bucket) {
               if (o == null) {
                  break;
               }

               a[i++] = o;
            }
         }
      }

      return a;
   }

   public Object[] toArray(Object[] a) {
      if (a.length < this.size()) {
         a = (U[])Arrays.copyOf(a, this.size());
      }

      int i = 0;

      for(Object[] bucket : this.buckets) {
         if (bucket != null) {
            for(Object o : bucket) {
               if (o == null) {
                  break;
               }

               a[i++] = o;
            }
         }
      }

      return a;
   }

   public final boolean remove(Object o) {
      return this.removeFast(this.asElementType(o));
   }

   public boolean removeFast(Object obj) {
      if (obj == null) {
         return false;
      } else {
         int b = this.getBucket(obj);
         T[] bucket = (T[])this.buckets[b];
         if (bucket == null) {
            return false;
         } else {
            for(int i = 0; i < bucket.length; ++i) {
               T e = (T)bucket[i];
               if (e == null) {
                  return false;
               }

               if (this.comparator.equals(e, obj)) {
                  System.arraycopy(bucket, i + 1, bucket, i, bucket.length - i - 1);
                  bucket[bucket.length - 1] = null;
                  --this.n;
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean containsAll(Collection collection) {
      if (collection instanceof Array2DHashSet) {
         Array2DHashSet<?> s = (Array2DHashSet)collection;

         for(Object[] bucket : s.buckets) {
            if (bucket != null) {
               for(Object o : bucket) {
                  if (o == null) {
                     break;
                  }

                  if (!this.containsFast(this.asElementType(o))) {
                     return false;
                  }
               }
            }
         }
      } else {
         for(Object o : collection) {
            if (!this.containsFast(this.asElementType(o))) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean addAll(Collection c) {
      boolean changed = false;

      for(Object o : c) {
         T existing = (T)this.getOrAdd(o);
         if (existing != o) {
            changed = true;
         }
      }

      return changed;
   }

   public boolean retainAll(Collection c) {
      int newsize = 0;

      for(Object[] bucket : this.buckets) {
         if (bucket != null) {
            int i = 0;

            int j;
            for(j = 0; i < bucket.length && bucket[i] != null; ++i) {
               if (c.contains(bucket[i])) {
                  if (i != j) {
                     bucket[j] = bucket[i];
                  }

                  ++j;
                  ++newsize;
               }
            }

            for(newsize += j; j < i; ++j) {
               bucket[j] = null;
            }
         }
      }

      boolean changed = newsize != this.n;
      this.n = newsize;
      return changed;
   }

   public boolean removeAll(Collection c) {
      boolean changed = false;

      for(Object o : c) {
         changed |= this.removeFast(this.asElementType(o));
      }

      return changed;
   }

   public void clear() {
      this.n = 0;
      this.buckets = this.createBuckets(this.initialCapacity);
      this.threshold = (int)Math.floor((double)this.initialCapacity * (double)0.75F);
   }

   public String toString() {
      if (this.size() == 0) {
         return "{}";
      } else {
         StringBuilder buf = new StringBuilder();
         buf.append('{');
         boolean first = true;

         for(Object[] bucket : this.buckets) {
            if (bucket != null) {
               for(Object o : bucket) {
                  if (o == null) {
                     break;
                  }

                  if (first) {
                     first = false;
                  } else {
                     buf.append(", ");
                  }

                  buf.append(o.toString());
               }
            }
         }

         buf.append('}');
         return buf.toString();
      }
   }

   public String toTableString() {
      StringBuilder buf = new StringBuilder();

      for(Object[] bucket : this.buckets) {
         if (bucket == null) {
            buf.append("null\n");
         } else {
            buf.append('[');
            boolean first = true;

            for(Object o : bucket) {
               if (first) {
                  first = false;
               } else {
                  buf.append(" ");
               }

               if (o == null) {
                  buf.append("_");
               } else {
                  buf.append(o.toString());
               }
            }

            buf.append("]\n");
         }
      }

      return buf.toString();
   }

   protected Object asElementType(Object o) {
      return o;
   }

   protected Object[][] createBuckets(int capacity) {
      return new Object[capacity][];
   }

   protected Object[] createBucket(int capacity) {
      return new Object[capacity];
   }

   protected class SetIterator implements Iterator {
      final Object[] data;
      int nextIndex = 0;
      boolean removed = true;

      public SetIterator(Object[] data) {
         this.data = data;
      }

      public boolean hasNext() {
         return this.nextIndex < this.data.length;
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.removed = false;
            return this.data[this.nextIndex++];
         }
      }

      public void remove() {
         if (this.removed) {
            throw new IllegalStateException();
         } else {
            Array2DHashSet.this.remove(this.data[this.nextIndex - 1]);
            this.removed = true;
         }
      }
   }
}
