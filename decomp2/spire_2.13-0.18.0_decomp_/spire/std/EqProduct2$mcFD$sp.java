package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface EqProduct2$mcFD$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcFD$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFD$sp$(final EqProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFD$sp(x0, x1);
   }

   default boolean eqv$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcF$sp().eqv$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1())) && this.structure2$mcD$sp().eqv$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp());
   }
}
