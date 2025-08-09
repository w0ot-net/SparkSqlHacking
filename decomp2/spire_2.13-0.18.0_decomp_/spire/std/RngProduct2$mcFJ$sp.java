package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcFJ$sp extends RngProduct2, SemiringProduct2$mcFJ$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcFJ$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcFJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFJ$sp$(final RngProduct2$mcFJ$sp $this, final Tuple2 x0) {
      return $this.negate$mcFJ$sp(x0);
   }

   default Tuple2 negate$mcFJ$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().negate$mcJ$sp(x0._2$mcJ$sp())));
   }
}
