package shaded.parquet.it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleSortedSet extends AbstractDoubleSet implements DoubleSortedSet {
   protected AbstractDoubleSortedSet() {
   }

   public abstract DoubleBidirectionalIterator iterator();
}
