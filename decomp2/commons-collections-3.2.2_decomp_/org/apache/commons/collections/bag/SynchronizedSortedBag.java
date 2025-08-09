package org.apache.commons.collections.bag;

import java.util.Comparator;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.SortedBag;

public class SynchronizedSortedBag extends SynchronizedBag implements SortedBag {
   private static final long serialVersionUID = 722374056718497858L;

   public static SortedBag decorate(SortedBag bag) {
      return new SynchronizedSortedBag(bag);
   }

   protected SynchronizedSortedBag(SortedBag bag) {
      super(bag);
   }

   protected SynchronizedSortedBag(Bag bag, Object lock) {
      super(bag, lock);
   }

   protected SortedBag getSortedBag() {
      return (SortedBag)this.collection;
   }

   public synchronized Object first() {
      synchronized(this.lock) {
         return this.getSortedBag().first();
      }
   }

   public synchronized Object last() {
      synchronized(this.lock) {
         return this.getSortedBag().last();
      }
   }

   public synchronized Comparator comparator() {
      synchronized(this.lock) {
         return this.getSortedBag().comparator();
      }
   }
}
