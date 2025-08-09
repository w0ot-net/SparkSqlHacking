package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcFD$sp extends RngProduct2, SemiringProduct2$mcFD$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcFD$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcFD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFD$sp$(final RngProduct2$mcFD$sp $this, final Tuple2 x0) {
      return $this.negate$mcFD$sp(x0);
   }

   default Tuple2 negate$mcFD$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().negate$mcD$sp(x0._2$mcD$sp())));
   }
}
