package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.Collection;
import java.util.SortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface IntSortedSet extends IntSet, SortedSet, IntBidirectionalIterable {
   IntBidirectionalIterator iterator(int var1);

   IntBidirectionalIterator iterator();

   default IntSpliterator spliterator() {
      return IntSpliterators.asSpliteratorFromSorted(this.iterator(), Size64.sizeOf((Collection)this), 341, this.comparator());
   }

   IntSortedSet subSet(int var1, int var2);

   IntSortedSet headSet(int var1);

   IntSortedSet tailSet(int var1);

   IntComparator comparator();

   int firstInt();

   int lastInt();

   /** @deprecated */
   @Deprecated
   default IntSortedSet subSet(Integer from, Integer to) {
      return this.subSet(from, to);
   }

   /** @deprecated */
   @Deprecated
   default IntSortedSet headSet(Integer to) {
      return this.headSet(to);
   }

   /** @deprecated */
   @Deprecated
   default IntSortedSet tailSet(Integer from) {
      return this.tailSet(from);
   }

   /** @deprecated */
   @Deprecated
   default Integer first() {
      return this.firstInt();
   }

   /** @deprecated */
   @Deprecated
   default Integer last() {
      return this.lastInt();
   }
}
