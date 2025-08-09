package shaded.parquet.it.unimi.dsi.fastutil.ints;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface IntUnaryOperator extends UnaryOperator, java.util.function.IntUnaryOperator {
   int apply(int var1);

   static IntUnaryOperator identity() {
      return (i) -> i;
   }

   static IntUnaryOperator negation() {
      return (i) -> -i;
   }

   /** @deprecated */
   @Deprecated
   default int applyAsInt(int x) {
      return this.apply(x);
   }

   /** @deprecated */
   @Deprecated
   default Integer apply(Integer x) {
      return this.apply(x);
   }
}
