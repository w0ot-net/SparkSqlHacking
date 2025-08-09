package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface EqProduct2$mcFJ$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFJ$sp$(final EqProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFJ$sp(x0, x1);
   }

   default boolean eqv$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcF$sp().eqv$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1())) && this.structure2$mcJ$sp().eqv$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp());
   }
}
