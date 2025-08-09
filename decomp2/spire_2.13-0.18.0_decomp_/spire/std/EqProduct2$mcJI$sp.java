package spire.std;

import scala.Tuple2;

public interface EqProduct2$mcJI$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcJI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcJI$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJI$sp$(final EqProduct2$mcJI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJI$sp(x0, x1);
   }

   default boolean eqv$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcJ$sp().eqv$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()) && this.structure2$mcI$sp().eqv$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp());
   }
}
