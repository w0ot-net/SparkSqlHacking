package shaded.parquet.it.unimi.dsi.fastutil.longs;

public abstract class AbstractLongSortedSet extends AbstractLongSet implements LongSortedSet {
   protected AbstractLongSortedSet() {
   }

   public abstract LongBidirectionalIterator iterator();
}
