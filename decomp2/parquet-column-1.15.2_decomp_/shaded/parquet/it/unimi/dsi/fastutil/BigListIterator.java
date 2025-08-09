package shaded.parquet.it.unimi.dsi.fastutil;

public interface BigListIterator extends BidirectionalIterator {
   long nextIndex();

   long previousIndex();

   default void set(Object e) {
      throw new UnsupportedOperationException();
   }

   default void add(Object e) {
      throw new UnsupportedOperationException();
   }
}
