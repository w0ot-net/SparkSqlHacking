package shaded.parquet.it.unimi.dsi.fastutil.longs;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface LongUnaryOperator extends UnaryOperator, java.util.function.LongUnaryOperator {
   long apply(long var1);

   static LongUnaryOperator identity() {
      return (i) -> i;
   }

   static LongUnaryOperator negation() {
      return (i) -> -i;
   }

   /** @deprecated */
   @Deprecated
   default long applyAsLong(long x) {
      return this.apply(x);
   }

   /** @deprecated */
   @Deprecated
   default Long apply(Long x) {
      return this.apply(x);
   }
}
