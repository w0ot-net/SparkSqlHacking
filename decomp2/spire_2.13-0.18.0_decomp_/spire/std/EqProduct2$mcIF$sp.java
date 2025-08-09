package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface EqProduct2$mcIF$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcIF$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcIF$sp$(final EqProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcIF$sp(x0, x1);
   }

   default boolean eqv$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcI$sp().eqv$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()) && this.structure2$mcF$sp().eqv$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()));
   }
}
