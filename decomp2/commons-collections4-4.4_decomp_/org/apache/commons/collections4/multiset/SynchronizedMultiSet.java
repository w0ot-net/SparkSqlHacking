package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.collection.SynchronizedCollection;

public class SynchronizedMultiSet extends SynchronizedCollection implements MultiSet {
   private static final long serialVersionUID = 20150629L;

   public static SynchronizedMultiSet synchronizedMultiSet(MultiSet multiset) {
      return new SynchronizedMultiSet(multiset);
   }

   protected SynchronizedMultiSet(MultiSet multiset) {
      super(multiset);
   }

   protected SynchronizedMultiSet(MultiSet multiset, Object lock) {
      super(multiset, lock);
   }

   protected MultiSet decorated() {
      return (MultiSet)super.decorated();
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else {
         synchronized(this.lock) {
            return this.decorated().equals(object);
         }
      }
   }

   public int hashCode() {
      synchronized(this.lock) {
         return this.decorated().hashCode();
      }
   }

   public int add(Object object, int count) {
      synchronized(this.lock) {
         return this.decorated().add(object, count);
      }
   }

   public int remove(Object object, int count) {
      synchronized(this.lock) {
         return this.decorated().remove(object, count);
      }
   }

   public int getCount(Object object) {
      synchronized(this.lock) {
         return this.decorated().getCount(object);
      }
   }

   public int setCount(Object object, int count) {
      synchronized(this.lock) {
         return this.decorated().setCount(object, count);
      }
   }

   public Set uniqueSet() {
      synchronized(this.lock) {
         Set<E> set = this.decorated().uniqueSet();
         return new SynchronizedSet(set, this.lock);
      }
   }

   public Set entrySet() {
      synchronized(this.lock) {
         Set<MultiSet.Entry<E>> set = this.decorated().entrySet();
         return new SynchronizedSet(set, this.lock);
      }
   }

   static class SynchronizedSet extends SynchronizedCollection implements Set {
      private static final long serialVersionUID = 20150629L;

      SynchronizedSet(Set set, Object lock) {
         super(set, lock);
      }
   }
}
