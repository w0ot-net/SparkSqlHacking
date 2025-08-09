package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.Collection;
import java.util.SortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface FloatSortedSet extends FloatSet, SortedSet, FloatBidirectionalIterable {
   FloatBidirectionalIterator iterator(float var1);

   FloatBidirectionalIterator iterator();

   default FloatSpliterator spliterator() {
      return FloatSpliterators.asSpliteratorFromSorted(this.iterator(), Size64.sizeOf((Collection)this), 341, this.comparator());
   }

   FloatSortedSet subSet(float var1, float var2);

   FloatSortedSet headSet(float var1);

   FloatSortedSet tailSet(float var1);

   FloatComparator comparator();

   float firstFloat();

   float lastFloat();

   /** @deprecated */
   @Deprecated
   default FloatSortedSet subSet(Float from, Float to) {
      return this.subSet(from, to);
   }

   /** @deprecated */
   @Deprecated
   default FloatSortedSet headSet(Float to) {
      return this.headSet(to);
   }

   /** @deprecated */
   @Deprecated
   default FloatSortedSet tailSet(Float from) {
      return this.tailSet(from);
   }

   /** @deprecated */
   @Deprecated
   default Float first() {
      return this.firstFloat();
   }

   /** @deprecated */
   @Deprecated
   default Float last() {
      return this.lastFloat();
   }
}
