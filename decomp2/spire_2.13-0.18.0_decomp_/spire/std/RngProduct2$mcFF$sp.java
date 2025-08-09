package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RngProduct2$mcFF$sp extends RngProduct2, SemiringProduct2$mcFF$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcFF$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcFF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcFF$sp$(final RngProduct2$mcFF$sp $this, final Tuple2 x0) {
      return $this.negate$mcFF$sp(x0);
   }

   default Tuple2 negate$mcFF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().negate$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
