package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcIF$sp extends RngProduct2, SemiringProduct2$mcIF$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcIF$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcIF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcIF$sp$(final RngProduct2$mcIF$sp $this, final Tuple2 x0) {
      return $this.negate$mcIF$sp(x0);
   }

   default Tuple2 negate$mcIF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().negate$mcI$sp(x0._1$mcI$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
