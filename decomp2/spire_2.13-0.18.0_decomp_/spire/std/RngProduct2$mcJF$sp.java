package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcJF$sp extends RngProduct2, SemiringProduct2$mcJF$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcJF$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcJF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJF$sp$(final RngProduct2$mcJF$sp $this, final Tuple2 x0) {
      return $this.negate$mcJF$sp(x0);
   }

   default Tuple2 negate$mcJF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().negate$mcJ$sp(x0._1$mcJ$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
