package shaded.parquet.it.unimi.dsi.fastutil.objects;

public interface ObjectIterable extends Iterable {
   ObjectIterator iterator();

   default ObjectSpliterator spliterator() {
      return ObjectSpliterators.asSpliteratorUnknownSize(this.iterator(), 0);
   }
}
