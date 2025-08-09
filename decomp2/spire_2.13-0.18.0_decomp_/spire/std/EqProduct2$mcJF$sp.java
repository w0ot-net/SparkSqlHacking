package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface EqProduct2$mcJF$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcJF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcJF$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJF$sp$(final EqProduct2$mcJF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJF$sp(x0, x1);
   }

   default boolean eqv$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcJ$sp().eqv$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()) && this.structure2$mcF$sp().eqv$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()));
   }
}
