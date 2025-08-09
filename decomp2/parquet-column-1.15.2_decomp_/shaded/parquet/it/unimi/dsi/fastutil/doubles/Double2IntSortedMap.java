package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.SortedMap;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;

public interface Double2IntSortedMap extends Double2IntMap, SortedMap {
   Double2IntSortedMap subMap(double var1, double var3);

   Double2IntSortedMap headMap(double var1);

   Double2IntSortedMap tailMap(double var1);

   double firstDoubleKey();

   double lastDoubleKey();

   /** @deprecated */
   @Deprecated
   default Double2IntSortedMap subMap(Double from, Double to) {
      return this.subMap(from, to);
   }

   /** @deprecated */
   @Deprecated
   default Double2IntSortedMap headMap(Double to) {
      return this.headMap(to);
   }

   /** @deprecated */
   @Deprecated
   default Double2IntSortedMap tailMap(Double from) {
      return this.tailMap(from);
   }

   /** @deprecated */
   @Deprecated
   default Double firstKey() {
      return this.firstDoubleKey();
   }

   /** @deprecated */
   @Deprecated
   default Double lastKey() {
      return this.lastDoubleKey();
   }

   /** @deprecated */
   @Deprecated
   default ObjectSortedSet entrySet() {
      return this.double2IntEntrySet();
   }

   ObjectSortedSet double2IntEntrySet();

   DoubleSortedSet keySet();

   IntCollection values();

   DoubleComparator comparator();

   public interface FastSortedEntrySet extends ObjectSortedSet, Double2IntMap.FastEntrySet {
      ObjectBidirectionalIterator fastIterator();

      ObjectBidirectionalIterator fastIterator(Double2IntMap.Entry var1);
   }
}
