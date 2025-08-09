package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.Collection;
import java.util.SortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface DoubleSortedSet extends DoubleSet, SortedSet, DoubleBidirectionalIterable {
   DoubleBidirectionalIterator iterator(double var1);

   DoubleBidirectionalIterator iterator();

   default DoubleSpliterator spliterator() {
      return DoubleSpliterators.asSpliteratorFromSorted(this.iterator(), Size64.sizeOf((Collection)this), 341, this.comparator());
   }

   DoubleSortedSet subSet(double var1, double var3);

   DoubleSortedSet headSet(double var1);

   DoubleSortedSet tailSet(double var1);

   DoubleComparator comparator();

   double firstDouble();

   double lastDouble();

   /** @deprecated */
   @Deprecated
   default DoubleSortedSet subSet(Double from, Double to) {
      return this.subSet(from, to);
   }

   /** @deprecated */
   @Deprecated
   default DoubleSortedSet headSet(Double to) {
      return this.headSet(to);
   }

   /** @deprecated */
   @Deprecated
   default DoubleSortedSet tailSet(Double from) {
      return this.tailSet(from);
   }

   /** @deprecated */
   @Deprecated
   default Double first() {
      return this.firstDouble();
   }

   /** @deprecated */
   @Deprecated
   default Double last() {
      return this.lastDouble();
   }
}
