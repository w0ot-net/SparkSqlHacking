package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface DoubleUnaryOperator extends UnaryOperator, java.util.function.DoubleUnaryOperator {
   double apply(double var1);

   static DoubleUnaryOperator identity() {
      return (i) -> i;
   }

   static DoubleUnaryOperator negation() {
      return (i) -> -i;
   }

   /** @deprecated */
   @Deprecated
   default double applyAsDouble(double x) {
      return this.apply(x);
   }

   /** @deprecated */
   @Deprecated
   default Double apply(Double x) {
      return this.apply(x);
   }
}
