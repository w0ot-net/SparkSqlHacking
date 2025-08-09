package org.sparkproject.guava.collect;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSortedMap extends ForwardingMap implements SortedMap {
   protected ForwardingSortedMap() {
   }

   protected abstract SortedMap delegate();

   @CheckForNull
   public Comparator comparator() {
      return this.delegate().comparator();
   }

   @ParametricNullness
   public Object firstKey() {
      return this.delegate().firstKey();
   }

   public SortedMap headMap(@ParametricNullness Object toKey) {
      return this.delegate().headMap(toKey);
   }

   @ParametricNullness
   public Object lastKey() {
      return this.delegate().lastKey();
   }

   public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
      return this.delegate().subMap(fromKey, toKey);
   }

   public SortedMap tailMap(@ParametricNullness Object fromKey) {
      return this.delegate().tailMap(fromKey);
   }

   static int unsafeCompare(@CheckForNull Comparator comparator, @CheckForNull Object o1, @CheckForNull Object o2) {
      return comparator == null ? ((Comparable)o1).compareTo(o2) : comparator.compare(o1, o2);
   }

   protected boolean standardContainsKey(@CheckForNull Object key) {
      try {
         Object ceilingKey = this.tailMap(key).firstKey();
         return unsafeCompare(this.comparator(), ceilingKey, key) == 0;
      } catch (NoSuchElementException | NullPointerException | ClassCastException var4) {
         return false;
      }
   }

   protected SortedMap standardSubMap(Object fromKey, Object toKey) {
      Preconditions.checkArgument(unsafeCompare(this.comparator(), fromKey, toKey) <= 0, "fromKey must be <= toKey");
      return this.tailMap(fromKey).headMap(toKey);
   }

   protected class StandardKeySet extends Maps.SortedKeySet {
      public StandardKeySet() {
         super(ForwardingSortedMap.this);
      }
   }
}
