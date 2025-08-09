package org.apache.curator.shaded.com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
abstract class AbstractNavigableMap extends Maps.IteratorBasedAbstractMap implements NavigableMap {
   @CheckForNull
   public abstract Object get(@CheckForNull Object key);

   @CheckForNull
   public Map.Entry firstEntry() {
      return (Map.Entry)Iterators.getNext(this.entryIterator(), (Object)null);
   }

   @CheckForNull
   public Map.Entry lastEntry() {
      return (Map.Entry)Iterators.getNext(this.descendingEntryIterator(), (Object)null);
   }

   @CheckForNull
   public Map.Entry pollFirstEntry() {
      return (Map.Entry)Iterators.pollNext(this.entryIterator());
   }

   @CheckForNull
   public Map.Entry pollLastEntry() {
      return (Map.Entry)Iterators.pollNext(this.descendingEntryIterator());
   }

   @ParametricNullness
   public Object firstKey() {
      Map.Entry<K, V> entry = this.firstEntry();
      if (entry == null) {
         throw new NoSuchElementException();
      } else {
         return entry.getKey();
      }
   }

   @ParametricNullness
   public Object lastKey() {
      Map.Entry<K, V> entry = this.lastEntry();
      if (entry == null) {
         throw new NoSuchElementException();
      } else {
         return entry.getKey();
      }
   }

   @CheckForNull
   public Map.Entry lowerEntry(@ParametricNullness Object key) {
      return this.headMap(key, false).lastEntry();
   }

   @CheckForNull
   public Map.Entry floorEntry(@ParametricNullness Object key) {
      return this.headMap(key, true).lastEntry();
   }

   @CheckForNull
   public Map.Entry ceilingEntry(@ParametricNullness Object key) {
      return this.tailMap(key, true).firstEntry();
   }

   @CheckForNull
   public Map.Entry higherEntry(@ParametricNullness Object key) {
      return this.tailMap(key, false).firstEntry();
   }

   @CheckForNull
   public Object lowerKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.lowerEntry(key));
   }

   @CheckForNull
   public Object floorKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.floorEntry(key));
   }

   @CheckForNull
   public Object ceilingKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.ceilingEntry(key));
   }

   @CheckForNull
   public Object higherKey(@ParametricNullness Object key) {
      return Maps.keyOrNull(this.higherEntry(key));
   }

   abstract Iterator descendingEntryIterator();

   public SortedMap subMap(@ParametricNullness Object fromKey, @ParametricNullness Object toKey) {
      return this.subMap(fromKey, true, toKey, false);
   }

   public SortedMap headMap(@ParametricNullness Object toKey) {
      return this.headMap(toKey, false);
   }

   public SortedMap tailMap(@ParametricNullness Object fromKey) {
      return this.tailMap(fromKey, true);
   }

   public NavigableSet navigableKeySet() {
      return new Maps.NavigableKeySet(this);
   }

   public Set keySet() {
      return this.navigableKeySet();
   }

   public NavigableSet descendingKeySet() {
      return this.descendingMap().navigableKeySet();
   }

   public NavigableMap descendingMap() {
      return new DescendingMap();
   }

   private final class DescendingMap extends Maps.DescendingMap {
      private DescendingMap() {
      }

      NavigableMap forward() {
         return AbstractNavigableMap.this;
      }

      Iterator entryIterator() {
         return AbstractNavigableMap.this.descendingEntryIterator();
      }
   }
}
