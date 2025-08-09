package spire.std;

import scala.Tuple2;

public interface EqProduct2$mcID$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcID$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcID$sp$(final EqProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcID$sp(x0, x1);
   }

   default boolean eqv$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcI$sp().eqv$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()) && this.structure2$mcD$sp().eqv$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp());
   }
}
