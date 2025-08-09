package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.SortedMap;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;

public interface Int2IntSortedMap extends Int2IntMap, SortedMap {
   Int2IntSortedMap subMap(int var1, int var2);

   Int2IntSortedMap headMap(int var1);

   Int2IntSortedMap tailMap(int var1);

   int firstIntKey();

   int lastIntKey();

   /** @deprecated */
   @Deprecated
   default Int2IntSortedMap subMap(Integer from, Integer to) {
      return this.subMap(from, to);
   }

   /** @deprecated */
   @Deprecated
   default Int2IntSortedMap headMap(Integer to) {
      return this.headMap(to);
   }

   /** @deprecated */
   @Deprecated
   default Int2IntSortedMap tailMap(Integer from) {
      return this.tailMap(from);
   }

   /** @deprecated */
   @Deprecated
   default Integer firstKey() {
      return this.firstIntKey();
   }

   /** @deprecated */
   @Deprecated
   default Integer lastKey() {
      return this.lastIntKey();
   }

   /** @deprecated */
   @Deprecated
   default ObjectSortedSet entrySet() {
      return this.int2IntEntrySet();
   }

   ObjectSortedSet int2IntEntrySet();

   IntSortedSet keySet();

   IntCollection values();

   IntComparator comparator();

   public interface FastSortedEntrySet extends ObjectSortedSet, Int2IntMap.FastEntrySet {
      ObjectBidirectionalIterator fastIterator();

      ObjectBidirectionalIterator fastIterator(Int2IntMap.Entry var1);
   }
}
