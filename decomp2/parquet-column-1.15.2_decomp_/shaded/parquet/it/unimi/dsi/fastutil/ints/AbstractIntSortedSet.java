package shaded.parquet.it.unimi.dsi.fastutil.ints;

public abstract class AbstractIntSortedSet extends AbstractIntSet implements IntSortedSet {
   protected AbstractIntSortedSet() {
   }

   public abstract IntBidirectionalIterator iterator();
}
