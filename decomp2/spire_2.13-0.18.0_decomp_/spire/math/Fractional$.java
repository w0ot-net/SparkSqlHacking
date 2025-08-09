package spire.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Fractional$ implements Serializable {
   public static final Fractional$ MODULE$ = new Fractional$();
   private static final Fractional FloatIsFractional = new FloatIsFractional();
   private static final Fractional DoubleIsFractional = new DoubleIsFractional();
   private static final Fractional BigDecimalIsFractional = new BigDecimalIsFractional();
   private static final Fractional AlgebraicIsFractional = new AlgebraicIsFractional();
   private static final Fractional NumberIsFractional = new NumberIsFractional();
   private static final Fractional RationalIsFractional = new RationalIsFractional();

   public final Fractional FloatIsFractional() {
      return FloatIsFractional;
   }

   public final Fractional DoubleIsFractional() {
      return DoubleIsFractional;
   }

   public final Fractional BigDecimalIsFractional() {
      return BigDecimalIsFractional;
   }

   public final Fractional AlgebraicIsFractional() {
      return AlgebraicIsFractional;
   }

   public final Fractional NumberIsFractional() {
      return NumberIsFractional;
   }

   public final Fractional RationalIsFractional() {
      return RationalIsFractional;
   }

   public final Fractional apply(final Fractional ev) {
      return ev;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Fractional$.class);
   }

   private Fractional$() {
   }
}
