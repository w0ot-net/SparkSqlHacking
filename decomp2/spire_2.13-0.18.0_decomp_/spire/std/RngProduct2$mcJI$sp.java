package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcJI$sp extends RngProduct2, SemiringProduct2$mcJI$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcJI$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcJI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJI$sp$(final RngProduct2$mcJI$sp $this, final Tuple2 x0) {
      return $this.negate$mcJI$sp(x0);
   }

   default Tuple2 negate$mcJI$sp(final Tuple2 x0) {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().negate$mcJ$sp(x0._1$mcJ$sp()), this.structure2$mcI$sp().negate$mcI$sp(x0._2$mcI$sp()));
   }
}
