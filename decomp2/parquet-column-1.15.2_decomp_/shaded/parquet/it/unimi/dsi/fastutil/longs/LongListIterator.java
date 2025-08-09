package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.ListIterator;

public interface LongListIterator extends LongBidirectionalIterator, ListIterator {
   default void set(long k) {
      throw new UnsupportedOperationException();
   }

   default void add(long k) {
      throw new UnsupportedOperationException();
   }

   default void remove() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Long k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Long k) {
      this.add(k);
   }

   /** @deprecated */
   @Deprecated
   default Long next() {
      return LongBidirectionalIterator.super.next();
   }

   /** @deprecated */
   @Deprecated
   default Long previous() {
      return LongBidirectionalIterator.super.previous();
   }
}
