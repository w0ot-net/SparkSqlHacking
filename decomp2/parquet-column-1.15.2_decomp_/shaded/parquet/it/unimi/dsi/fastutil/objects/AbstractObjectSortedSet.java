package shaded.parquet.it.unimi.dsi.fastutil.objects;

public abstract class AbstractObjectSortedSet extends AbstractObjectSet implements ObjectSortedSet {
   protected AbstractObjectSortedSet() {
   }

   public abstract ObjectBidirectionalIterator iterator();
}
