package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.ListIterator;

public interface BooleanListIterator extends BooleanBidirectionalIterator, ListIterator {
   default void set(boolean k) {
      throw new UnsupportedOperationException();
   }

   default void add(boolean k) {
      throw new UnsupportedOperationException();
   }

   default void remove() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Boolean k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Boolean k) {
      this.add(k);
   }

   /** @deprecated */
   @Deprecated
   default Boolean next() {
      return BooleanBidirectionalIterator.super.next();
   }

   /** @deprecated */
   @Deprecated
   default Boolean previous() {
      return BooleanBidirectionalIterator.super.previous();
   }
}
