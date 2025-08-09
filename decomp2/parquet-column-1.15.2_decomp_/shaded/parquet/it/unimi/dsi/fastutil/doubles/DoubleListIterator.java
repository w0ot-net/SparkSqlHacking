package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.ListIterator;

public interface DoubleListIterator extends DoubleBidirectionalIterator, ListIterator {
   default void set(double k) {
      throw new UnsupportedOperationException();
   }

   default void add(double k) {
      throw new UnsupportedOperationException();
   }

   default void remove() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Double k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Double k) {
      this.add(k);
   }

   /** @deprecated */
   @Deprecated
   default Double next() {
      return DoubleBidirectionalIterator.super.next();
   }

   /** @deprecated */
   @Deprecated
   default Double previous() {
      return DoubleBidirectionalIterator.super.previous();
   }
}
