package spire.std;

import scala.Tuple2;

public interface EqProduct2$mcDD$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcDD$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDD$sp$(final EqProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDD$sp(x0, x1);
   }

   default boolean eqv$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcD$sp().eqv$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()) && this.structure2$mcD$sp().eqv$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp());
   }
}
