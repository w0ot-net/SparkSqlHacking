package org.apache.commons.collections.bag;

import java.util.Set;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.collection.SynchronizedCollection;
import org.apache.commons.collections.set.SynchronizedSet;

public class SynchronizedBag extends SynchronizedCollection implements Bag {
   private static final long serialVersionUID = 8084674570753837109L;

   public static Bag decorate(Bag bag) {
      return new SynchronizedBag(bag);
   }

   protected SynchronizedBag(Bag bag) {
      super(bag);
   }

   protected SynchronizedBag(Bag bag, Object lock) {
      super(bag, lock);
   }

   protected Bag getBag() {
      return (Bag)this.collection;
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
         Set set = this.getBag().uniqueSet();
         return new SynchronizedBagSet(set, this.lock);
      }
   }

   public int getCount(Object object) {
      synchronized(this.lock) {
         return this.getBag().getCount(object);
      }
   }

   class SynchronizedBagSet extends SynchronizedSet {
      SynchronizedBagSet(Set set, Object lock) {
         super(set, lock);
      }
   }
}
