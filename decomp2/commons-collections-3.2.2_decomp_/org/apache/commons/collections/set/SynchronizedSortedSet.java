package org.apache.commons.collections.set;

import java.util.Comparator;
import java.util.SortedSet;
import org.apache.commons.collections.collection.SynchronizedCollection;

public class SynchronizedSortedSet extends SynchronizedCollection implements SortedSet {
   private static final long serialVersionUID = 2775582861954500111L;

   public static SortedSet decorate(SortedSet set) {
      return new SynchronizedSortedSet(set);
   }

   protected SynchronizedSortedSet(SortedSet set) {
      super(set);
   }

   protected SynchronizedSortedSet(SortedSet set, Object lock) {
      super(set, lock);
   }

   protected SortedSet getSortedSet() {
      return (SortedSet)this.collection;
   }

   public SortedSet subSet(Object fromElement, Object toElement) {
      synchronized(this.lock) {
         SortedSet set = this.getSortedSet().subSet(fromElement, toElement);
         return new SynchronizedSortedSet(set, this.lock);
      }
   }

   public SortedSet headSet(Object toElement) {
      synchronized(this.lock) {
         SortedSet set = this.getSortedSet().headSet(toElement);
         return new SynchronizedSortedSet(set, this.lock);
      }
   }

   public SortedSet tailSet(Object fromElement) {
      synchronized(this.lock) {
         SortedSet set = this.getSortedSet().tailSet(fromElement);
         return new SynchronizedSortedSet(set, this.lock);
      }
   }

   public Object first() {
      synchronized(this.lock) {
         return this.getSortedSet().first();
      }
   }

   public Object last() {
      synchronized(this.lock) {
         return this.getSortedSet().last();
      }
   }

   public Comparator comparator() {
      synchronized(this.lock) {
         return this.getSortedSet().comparator();
      }
   }
}
