package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.ListIterator;

public interface FloatListIterator extends FloatBidirectionalIterator, ListIterator {
   default void set(float k) {
      throw new UnsupportedOperationException();
   }

   default void add(float k) {
      throw new UnsupportedOperationException();
   }

   default void remove() {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Float k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Float k) {
      this.add(k);
   }

   /** @deprecated */
   @Deprecated
   default Float next() {
      return FloatBidirectionalIterator.super.next();
   }

   /** @deprecated */
   @Deprecated
   default Float previous() {
      return FloatBidirectionalIterator.super.previous();
   }
}
