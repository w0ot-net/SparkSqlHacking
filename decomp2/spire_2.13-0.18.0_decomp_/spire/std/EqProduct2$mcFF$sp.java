package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface EqProduct2$mcFF$sp extends EqProduct2 {
   // $FF: synthetic method
   static boolean eqv$(final EqProduct2$mcFF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcFF$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFF$sp$(final EqProduct2$mcFF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFF$sp(x0, x1);
   }

   default boolean eqv$mcFF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.structure1$mcF$sp().eqv$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1())) && this.structure2$mcF$sp().eqv$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()));
   }
}
