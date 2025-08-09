package org.sparkproject.guava.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.function.BiFunction;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
public abstract class ForwardingNavigableMap extends ForwardingSortedMap implements NavigableMap {
   protected ForwardingNavigableMap() {
   }

   protected abstract NavigableMap delegate();

   @CheckForNull
   public Map.Entry lowerEntry(@ParametricNullness Object key) {
      return this.delegate().lowerEntry(key);
   }

   @CheckForNull
   protected Map.Entry standardLowerEntry(@ParametricNullness Object key) {
      return this.headMap(key, false).lastEntry();
   }

   @CheckForNull
   public Object lowerKey(@ParametricNullness Object key) {
      return this.delegate().lowerKey(key);
   }

   @CheckForNull
   protected Object standardLowerKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.lowerEntry(key));
   }

   @CheckForNull
   public Map.Entry floorEntry(@ParametricNullness Object key) {
      return this.delegate().floorEntry(key);
   }

   @CheckForNull
   protected Map.Entry standardFloorEntry(@ParametricNullness Object key) {
      return this.headMap(key, true).lastEntry();
   }

   @CheckForNull
   public Object floorKey(@ParametricNullness Object key) {
      return this.delegate().floorKey(key);
   }

   @CheckForNull
   protected Object standardFloorKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.floorEntry(key));
   }

   @CheckForNull
   public Map.Entry ceilingEntry(@ParametricNullness Object key) {
      return this.delegate().ceilingEntry(key);
   }

   @CheckForNull
   protected Map.Entry standardCeilingEntry(@ParametricNullness Object key) {
      return this.tailMap(key, true).firstEntry();
   }

   @CheckForNull
   public Object ceilingKey(@ParametricNullness Object key) {
      return this.delegate().ceilingKey(key);
   }

   @CheckForNull
   protected Object standardCeilingKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.ceilingEntry(key));
   }

   @CheckForNull
   public Map.Entry higherEntry(@ParametricNullness Object key) {
      return this.delegate().higherEntry(key);
   }

   @CheckForNull
   protected Map.Entry standardHigherEntry(@ParametricNullness Object key) {
      return this.tailMap(key, false).firstEntry();
   }

   @CheckForNull
   public Object higherKey(@ParametricNullness Object key) {
      return this.delegate().higherKey(key);
   }

   @CheckForNull
   protected Object standardHigherKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.higherEntry(key));
   }

   @CheckForNull
   public Map.Entry firstEntry() {
      return this.delegate().firstEntry();
   }

   @CheckForNull
   protected Map.Entry standardFirstEntry() {
      return (Map.Entry)Iterables.getFirst(this.entrySet(), (Object)null);
   }

   protected Object standardFirstKey() {
      Map.Entry<K, V> entry = this.firstEntry();
      if (entry == null) {
         throw new NoSuchElementException();
      } else {
         return entry.getKey();
      }
   }

   @CheckForNull
   public Map.Entry lastEntry() {
      return this.delegate().lastEntry();
   }

   @CheckForNull
   protected Map.Entry standardLastEntry() {
      return (Map.Entry)Iterables.getFirst(this.descendingMap().entrySet(), (Object)null);
   }

   protected Object standardLastKey() {
      Map.Entry<K, V> entry = this.lastEntry();
      if (entry == null) {
         throw new NoSuchElementException();
      } else {
         return entry.getKey();
      }
   }

   @CheckForNull
   public Map.Entry pollFirstEntry() {
      return this.delegate().pollFirstEntry();
   }

   @CheckForNull
   protected Map.Entry standardPollFirstEntry() {
      return (Map.Entry)Iterators.pollNext(this.entrySet().iterator());
   }

   @CheckForNull
   public Map.Entry pollLastEntry() {
      return this.delegate().pollLastEntry();
   }

   @CheckForNull
   protected Map.Entry standardPollLastEntry() {
      return (Map.Entry)Iterators.pollNext(this.descendingMap().entrySet().iterator());
   }

   public NavigableMap descendingMap() {
      return this.delegate().descendingMap();
   }

   public NavigableSet navigableKeySet() {
      return this.delegate().navigableKeySet();
   }

   public NavigableSet descendingKeySet() {
      return this.delegate().descendingKeySet();
   }

   protected NavigableSet standardDescendingKeySet() {
      return this.descendingMap().navigableKeySet();
   }

   protected SortedMap standardSubMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
      return this.subMap(fromKey, true, toKey, false);
   }

   public NavigableMap subMap(@ParametricNullness Object fromKey, boolean fromInclusive, @ParametricNullness Object toKey, boolean toInclusive) {
      return this.delegate().subMap(fromKey, fromInclusive, toKey, toInclusive);
   }

   public NavigableMap headMap(@ParametricNullness Object toKey, boolean inclusive) {
      return this.delegate().headMap(toKey, inclusive);
   }

   public NavigableMap tailMap(@ParametricNullness Object fromKey, boolean inclusive) {
      return this.delegate().tailMap(fromKey, inclusive);
   }

   protected SortedMap standardHeadMap(@ParametricNullness Object toKey) {
      return this.headMap(toKey, false);
   }

   protected SortedMap standardTailMap(@ParametricNullness Object fromKey) {
      return this.tailMap(fromKey, true);
   }

   protected class StandardDescendingMap extends Maps.DescendingMap {
      public StandardDescendingMap() {
      }

      NavigableMap forward() {
         return ForwardingNavigableMap.this;
      }

      public void replaceAll(BiFunction function) {
         this.forward().replaceAll(function);
      }

      protected Iterator entryIterator() {
         return new Iterator() {
            @CheckForNull
            private Map.Entry toRemove = null;
            @CheckForNull
            private Map.Entry nextOrNull = StandardDescendingMap.this.forward().lastEntry();

            public boolean hasNext() {
               return this.nextOrNull != null;
            }

            public Map.Entry next() {
               if (this.nextOrNull == null) {
                  throw new NoSuchElementException();
               } else {
                  Map.Entry var1;
                  try {
                     var1 = this.nextOrNull;
                  } finally {
                     this.toRemove = this.nextOrNull;
                     this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                  }

                  return var1;
               }
            }

            public void remove() {
               if (this.toRemove == null) {
                  throw new IllegalStateException("no calls to next() since the last call to remove()");
               } else {
                  StandardDescendingMap.this.forward().remove(this.toRemove.getKey());
                  this.toRemove = null;
               }
            }
         };
      }
   }

   protected class StandardNavigableKeySet extends Maps.NavigableKeySet {
      public StandardNavigableKeySet() {
         super(ForwardingNavigableMap.this);
      }
   }
}
