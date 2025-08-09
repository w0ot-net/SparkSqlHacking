package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcDF$sp extends RngProduct2, SemiringProduct2$mcDF$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcDF$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcDF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDF$sp$(final RngProduct2$mcDF$sp $this, final Tuple2 x0) {
      return $this.negate$mcDF$sp(x0);
   }

   default Tuple2 negate$mcDF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().negate$mcD$sp(x0._1$mcD$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
