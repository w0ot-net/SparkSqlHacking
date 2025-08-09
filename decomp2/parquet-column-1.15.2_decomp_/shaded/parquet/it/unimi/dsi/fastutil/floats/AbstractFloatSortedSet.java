package shaded.parquet.it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatSortedSet extends AbstractFloatSet implements FloatSortedSet {
   protected AbstractFloatSortedSet() {
   }

   public abstract FloatBidirectionalIterator iterator();
}
