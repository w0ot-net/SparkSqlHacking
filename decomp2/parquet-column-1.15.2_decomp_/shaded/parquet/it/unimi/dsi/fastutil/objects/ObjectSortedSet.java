package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import java.util.SortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface ObjectSortedSet extends ObjectSet, SortedSet, ObjectBidirectionalIterable {
   ObjectBidirectionalIterator iterator(Object var1);

   ObjectBidirectionalIterator iterator();

   default ObjectSpliterator spliterator() {
      return ObjectSpliterators.asSpliteratorFromSorted(this.iterator(), Size64.sizeOf((Collection)this), 85, this.comparator());
   }

   ObjectSortedSet subSet(Object var1, Object var2);

   ObjectSortedSet headSet(Object var1);

   ObjectSortedSet tailSet(Object var1);
}
