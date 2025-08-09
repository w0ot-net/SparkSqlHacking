package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class SynchronizedCollection implements Collection, Serializable {
   private static final long serialVersionUID = 2412805092710877986L;
   private final Collection collection;
   protected final Object lock;

   public static SynchronizedCollection synchronizedCollection(Collection coll) {
      return new SynchronizedCollection(coll);
   }

   protected SynchronizedCollection(Collection collection) {
      if (collection == null) {
         throw new NullPointerException("Collection must not be null.");
      } else {
         this.collection = collection;
         this.lock = this;
      }
   }

   protected SynchronizedCollection(Collection collection, Object lock) {
      if (collection == null) {
         throw new NullPointerException("Collection must not be null.");
      } else if (lock == null) {
         throw new NullPointerException("Lock must not be null.");
      } else {
         this.collection = collection;
         this.lock = lock;
      }
   }

   protected Collection decorated() {
      return this.collection;
   }

   public boolean add(Object object) {
      synchronized(this.lock) {
         return this.decorated().add(object);
      }
   }

   public boolean addAll(Collection coll) {
      synchronized(this.lock) {
         return this.decorated().addAll(coll);
      }
   }

   public void clear() {
      synchronized(this.lock) {
         this.decorated().clear();
      }
   }

   public boolean contains(Object object) {
      synchronized(this.lock) {
         return this.decorated().contains(object);
      }
   }

   public boolean containsAll(Collection coll) {
      synchronized(this.lock) {
         return this.decorated().containsAll(coll);
      }
   }

   public boolean isEmpty() {
      synchronized(this.lock) {
         return this.decorated().isEmpty();
      }
   }

   public Iterator iterator() {
      return this.decorated().iterator();
   }

   public Object[] toArray() {
      synchronized(this.lock) {
         return this.decorated().toArray();
      }
   }

   public Object[] toArray(Object[] object) {
      synchronized(this.lock) {
         return this.decorated().toArray(object);
      }
   }

   public boolean remove(Object object) {
      synchronized(this.lock) {
         return this.decorated().remove(object);
      }
   }

   public boolean removeIf(Predicate filter) {
      synchronized(this.lock) {
         return this.decorated().removeIf(filter);
      }
   }

   public boolean removeAll(Collection coll) {
      synchronized(this.lock) {
         return this.decorated().removeAll(coll);
      }
   }

   public boolean retainAll(Collection coll) {
      synchronized(this.lock) {
         return this.decorated().retainAll(coll);
      }
   }

   public int size() {
      synchronized(this.lock) {
         return this.decorated().size();
      }
   }

   public boolean equals(Object object) {
      synchronized(this.lock) {
         if (object == this) {
            return true;
         } else {
            return object == this || this.decorated().equals(object);
         }
      }
   }

   public int hashCode() {
      synchronized(this.lock) {
         return this.decorated().hashCode();
      }
   }

   public String toString() {
      synchronized(this.lock) {
         return this.decorated().toString();
      }
   }
}
