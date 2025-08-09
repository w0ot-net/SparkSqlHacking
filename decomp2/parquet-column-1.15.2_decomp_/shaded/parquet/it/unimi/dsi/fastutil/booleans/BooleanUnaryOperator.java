package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface BooleanUnaryOperator extends UnaryOperator {
   boolean apply(boolean var1);

   static BooleanUnaryOperator identity() {
      return (i) -> i;
   }

   static BooleanUnaryOperator negation() {
      return (i) -> !i;
   }

   /** @deprecated */
   @Deprecated
   default Boolean apply(Boolean x) {
      return this.apply(x);
   }
}
