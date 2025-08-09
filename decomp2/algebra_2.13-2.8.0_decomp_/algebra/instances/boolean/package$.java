package algebra.instances.boolean;

import algebra.instances.BooleanAlgebra;
import algebra.instances.BooleanInstances;
import cats.kernel.Order;

public final class package$ implements BooleanInstances {
   public static final package$ MODULE$ = new package$();
   private static BooleanAlgebra booleanAlgebra;
   private static Object booleanRing;
   private static Order catsKernelStdOrderForBoolean;

   static {
      cats.kernel.instances.BooleanInstances.$init$(MODULE$);
      BooleanInstances.$init$(MODULE$);
   }

   public BooleanAlgebra booleanAlgebra() {
      return booleanAlgebra;
   }

   public Object booleanRing() {
      return booleanRing;
   }

   public void algebra$instances$BooleanInstances$_setter_$booleanAlgebra_$eq(final BooleanAlgebra x$1) {
      booleanAlgebra = x$1;
   }

   public void algebra$instances$BooleanInstances$_setter_$booleanRing_$eq(final Object x$1) {
      booleanRing = x$1;
   }

   public Order catsKernelStdOrderForBoolean() {
      return catsKernelStdOrderForBoolean;
   }

   public void cats$kernel$instances$BooleanInstances$_setter_$catsKernelStdOrderForBoolean_$eq(final Order x$1) {
      catsKernelStdOrderForBoolean = x$1;
   }

   private package$() {
   }
}
