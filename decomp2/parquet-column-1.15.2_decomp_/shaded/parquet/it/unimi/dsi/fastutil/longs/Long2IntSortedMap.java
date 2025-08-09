package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.SortedMap;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;

public interface Long2IntSortedMap extends Long2IntMap, SortedMap {
   Long2IntSortedMap subMap(long var1, long var3);

   Long2IntSortedMap headMap(long var1);

   Long2IntSortedMap tailMap(long var1);

   long firstLongKey();

   long lastLongKey();

   /** @deprecated */
   @Deprecated
   default Long2IntSortedMap subMap(Long from, Long to) {
      return this.subMap(from, to);
   }

   /** @deprecated */
   @Deprecated
   default Long2IntSortedMap headMap(Long to) {
      return this.headMap(to);
   }

   /** @deprecated */
   @Deprecated
   default Long2IntSortedMap tailMap(Long from) {
      return this.tailMap(from);
   }

   /** @deprecated */
   @Deprecated
   default Long firstKey() {
      return this.firstLongKey();
   }

   /** @deprecated */
   @Deprecated
   default Long lastKey() {
      return this.lastLongKey();
   }

   /** @deprecated */
   @Deprecated
   default ObjectSortedSet entrySet() {
      return this.long2IntEntrySet();
   }

   ObjectSortedSet long2IntEntrySet();

   LongSortedSet keySet();

   IntCollection values();

   LongComparator comparator();

   public interface FastSortedEntrySet extends ObjectSortedSet, Long2IntMap.FastEntrySet {
      ObjectBidirectionalIterator fastIterator();

      ObjectBidirectionalIterator fastIterator(Long2IntMap.Entry var1);
   }
}
