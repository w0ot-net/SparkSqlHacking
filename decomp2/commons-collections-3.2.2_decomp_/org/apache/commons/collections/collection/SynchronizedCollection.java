package org.apache.commons.collections.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class SynchronizedCollection implements Collection, Serializable {
   private static final long serialVersionUID = 2412805092710877986L;
   protected final Collection collection;
   protected final Object lock;

   public static Collection decorate(Collection coll) {
      return new SynchronizedCollection(coll);
   }

   protected SynchronizedCollection(Collection collection) {
      if (collection == null) {
         throw new IllegalArgumentException("Collection must not be null");
      } else {
         this.collection = collection;
         this.lock = this;
      }
   }

   protected SynchronizedCollection(Collection collection, Object lock) {
      if (collection == null) {
         throw new IllegalArgumentException("Collection must not be null");
      } else {
         this.collection = collection;
         this.lock = lock;
      }
   }

   public boolean add(Object object) {
      synchronized(this.lock) {
         return this.collection.add(object);
      }
   }

   public boolean addAll(Collection coll) {
      synchronized(this.lock) {
         return this.collection.addAll(coll);
      }
   }

   public void clear() {
      synchronized(this.lock) {
         this.collection.clear();
      }
   }

   public boolean contains(Object object) {
      synchronized(this.lock) {
         return this.collection.contains(object);
      }
   }

   public boolean containsAll(Collection coll) {
      synchronized(this.lock) {
         return this.collection.containsAll(coll);
      }
   }

   public boolean isEmpty() {
      synchronized(this.lock) {
         return this.collection.isEmpty();
      }
   }

   public Iterator iterator() {
      return this.collection.iterator();
   }

   public Object[] toArray() {
      synchronized(this.lock) {
         return this.collection.toArray();
      }
   }

   public Object[] toArray(Object[] object) {
      synchronized(this.lock) {
         return this.collection.toArray(object);
      }
   }

   public boolean remove(Object object) {
      synchronized(this.lock) {
         return this.collection.remove(object);
      }
   }

   public boolean removeAll(Collection coll) {
      synchronized(this.lock) {
         return this.collection.removeAll(coll);
      }
   }

   public boolean retainAll(Collection coll) {
      synchronized(this.lock) {
         return this.collection.retainAll(coll);
      }
   }

   public int size() {
      synchronized(this.lock) {
         return this.collection.size();
      }
   }

   public boolean equals(Object object) {
      synchronized(this.lock) {
         return object == this ? true : this.collection.equals(object);
      }
   }

   public int hashCode() {
      synchronized(this.lock) {
         return this.collection.hashCode();
      }
   }

   public String toString() {
      synchronized(this.lock) {
         return this.collection.toString();
      }
   }
}
