package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.collection.SynchronizedCollection;

public class SynchronizedBag extends SynchronizedCollection implements Bag {
   private static final long serialVersionUID = 8084674570753837109L;

   public static SynchronizedBag synchronizedBag(Bag bag) {
      return new SynchronizedBag(bag);
   }

   protected SynchronizedBag(Bag bag) {
      super(bag);
   }

   protected SynchronizedBag(Bag bag, Object lock) {
      super(bag, lock);
   }

   protected Bag getBag() {
      return (Bag)this.decorated();
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else {
         synchronized(this.lock) {
            return this.getBag().equals(object);
         }
      }
   }

   public int hashCode() {
      synchronized(this.lock) {
         return this.getBag().hashCode();
      }
   }

   public boolean add(Object object, int count) {
      synchronized(this.lock) {
         return this.getBag().add(object, count);
      }
   }

   public boolean remove(Object object, int count) {
      synchronized(this.lock) {
         return this.getBag().remove(object, count);
      }
   }

   public Set uniqueSet() {
      synchronized(this.lock) {
         Set<E> set = this.getBag().uniqueSet();
         return new SynchronizedBagSet(set, this.lock);
      }
   }

   public int getCount(Object object) {
      synchronized(this.lock) {
         return this.getBag().getCount(object);
      }
   }

   class SynchronizedBagSet extends SynchronizedCollection implements Set {
      private static final long serialVersionUID = 2990565892366827855L;

      SynchronizedBagSet(Set set, Object lock) {
         super(set, lock);
      }
   }
}
