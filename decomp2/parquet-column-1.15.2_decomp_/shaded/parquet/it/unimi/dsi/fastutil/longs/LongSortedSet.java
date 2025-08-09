package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.Collection;
import java.util.SortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface LongSortedSet extends LongSet, SortedSet, LongBidirectionalIterable {
   LongBidirectionalIterator iterator(long var1);

   LongBidirectionalIterator iterator();

   default LongSpliterator spliterator() {
      return LongSpliterators.asSpliteratorFromSorted(this.iterator(), Size64.sizeOf((Collection)this), 341, this.comparator());
   }

   LongSortedSet subSet(long var1, long var3);

   LongSortedSet headSet(long var1);

   LongSortedSet tailSet(long var1);

   LongComparator comparator();

   long firstLong();

   long lastLong();

   /** @deprecated */
   @Deprecated
   default LongSortedSet subSet(Long from, Long to) {
      return this.subSet(from, to);
   }

   /** @deprecated */
   @Deprecated
   default LongSortedSet headSet(Long to) {
      return this.headSet(to);
   }

   /** @deprecated */
   @Deprecated
   default LongSortedSet tailSet(Long from) {
      return this.tailSet(from);
   }

   /** @deprecated */
   @Deprecated
   default Long first() {
      return this.firstLong();
   }

   /** @deprecated */
   @Deprecated
   default Long last() {
      return this.lastLong();
   }
}
