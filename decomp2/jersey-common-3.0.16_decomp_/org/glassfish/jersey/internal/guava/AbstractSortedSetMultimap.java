package org.glassfish.jersey.internal.guava;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;

abstract class AbstractSortedSetMultimap extends AbstractSetMultimap implements SortedSetMultimap {
   private static final long serialVersionUID = 430848587173315748L;

   AbstractSortedSetMultimap(Map map) {
      super(map);
   }

   abstract SortedSet createCollection();

   SortedSet createUnmodifiableEmptyCollection() {
      return Collections.unmodifiableSortedSet(this.createCollection());
   }

   public SortedSet get(Object key) {
      return (SortedSet)super.get(key);
   }

   public SortedSet removeAll(Object key) {
      return (SortedSet)super.removeAll(key);
   }

   public Map asMap() {
      return super.asMap();
   }

   public Collection values() {
      return super.values();
   }
}
