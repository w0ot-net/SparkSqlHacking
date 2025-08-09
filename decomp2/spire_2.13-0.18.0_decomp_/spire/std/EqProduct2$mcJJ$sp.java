package spire.std;

import scala.Tuple2;

public interface EqProduct2$mcJJ$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcJJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJJ$sp$(final EqProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJJ$sp(x0, x1);
   }

   default boolean eqv$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcJ$sp().eqv$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()) && this.structure2$mcJ$sp().eqv$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp());
   }
}
