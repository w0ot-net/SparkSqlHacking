package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

@FunctionalInterface
public interface FloatUnaryOperator extends UnaryOperator, DoubleUnaryOperator {
   float apply(float var1);

   static FloatUnaryOperator identity() {
      return (i) -> i;
   }

   static FloatUnaryOperator negation() {
      return (i) -> -i;
   }

   /** @deprecated */
   @Deprecated
   default double applyAsDouble(double x) {
      return (double)this.apply(SafeMath.safeDoubleToFloat(x));
   }

   /** @deprecated */
   @Deprecated
   default Float apply(Float x) {
      return this.apply(x);
   }
}
