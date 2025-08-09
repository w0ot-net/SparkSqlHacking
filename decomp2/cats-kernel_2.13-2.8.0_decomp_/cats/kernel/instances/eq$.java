package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.EqToEquivConversion;
import scala.math.Equiv;

public final class eq$ implements EqInstances {
   public static final eq$ MODULE$ = new eq$();

   static {
      EqToEquivConversion.$init$(MODULE$);
   }

   public Equiv catsKernelEquivForEq(final Eq ev) {
      return EqToEquivConversion.catsKernelEquivForEq$(this, ev);
   }

   private eq$() {
   }
}
