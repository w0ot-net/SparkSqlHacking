package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcID$sp extends RngProduct2, SemiringProduct2$mcID$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcID$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcID$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcID$sp$(final RngProduct2$mcID$sp $this, final Tuple2 x0) {
      return $this.negate$mcID$sp(x0);
   }

   default Tuple2 negate$mcID$sp(final Tuple2 x0) {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().negate$mcI$sp(x0._1$mcI$sp()), this.structure2$mcD$sp().negate$mcD$sp(x0._2$mcD$sp()));
   }
}
