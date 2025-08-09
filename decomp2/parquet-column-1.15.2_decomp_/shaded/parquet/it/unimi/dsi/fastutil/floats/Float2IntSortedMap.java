package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.SortedMap;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;

public interface Float2IntSortedMap extends Float2IntMap, SortedMap {
   Float2IntSortedMap subMap(float var1, float var2);

   Float2IntSortedMap headMap(float var1);

   Float2IntSortedMap tailMap(float var1);

   float firstFloatKey();

   float lastFloatKey();

   /** @deprecated */
   @Deprecated
   default Float2IntSortedMap subMap(Float from, Float to) {
      return this.subMap(from, to);
   }

   /** @deprecated */
   @Deprecated
   default Float2IntSortedMap headMap(Float to) {
      return this.headMap(to);
   }

   /** @deprecated */
   @Deprecated
   default Float2IntSortedMap tailMap(Float from) {
      return this.tailMap(from);
   }

   /** @deprecated */
   @Deprecated
   default Float firstKey() {
      return this.firstFloatKey();
   }

   /** @deprecated */
   @Deprecated
   default Float lastKey() {
      return this.lastFloatKey();
   }

   /** @deprecated */
   @Deprecated
   default ObjectSortedSet entrySet() {
      return this.float2IntEntrySet();
   }

   ObjectSortedSet float2IntEntrySet();

   FloatSortedSet keySet();

   IntCollection values();

   FloatComparator comparator();

   public interface FastSortedEntrySet extends ObjectSortedSet, Float2IntMap.FastEntrySet {
      ObjectBidirectionalIterator fastIterator();

      ObjectBidirectionalIterator fastIterator(Float2IntMap.Entry var1);
   }
}
