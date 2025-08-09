package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcFI$sp extends RngProduct2, SemiringProduct2$mcFI$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcFI$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcFI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFI$sp$(final RngProduct2$mcFI$sp $this, final Tuple2 x0) {
      return $this.negate$mcFI$sp(x0);
   }

   default Tuple2 negate$mcFI$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().negate$mcI$sp(x0._2$mcI$sp())));
   }
}
