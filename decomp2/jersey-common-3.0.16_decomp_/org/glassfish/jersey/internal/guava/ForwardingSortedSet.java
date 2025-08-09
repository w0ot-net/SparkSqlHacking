package org.glassfish.jersey.internal.guava;

import java.util.Comparator;
import java.util.SortedSet;

public abstract class ForwardingSortedSet extends ForwardingSet implements SortedSet {
   ForwardingSortedSet() {
   }

   protected abstract SortedSet delegate();

   public Comparator comparator() {
      return this.delegate().comparator();
   }

   public Object first() {
      return this.delegate().first();
   }

   public SortedSet headSet(Object toElement) {
      return this.delegate().headSet(toElement);
   }

   public Object last() {
      return this.delegate().last();
   }

   public SortedSet subSet(Object fromElement, Object toElement) {
      return this.delegate().subSet(fromElement, toElement);
   }

   public SortedSet tailSet(Object fromElement) {
      return this.delegate().tailSet(fromElement);
   }
}
