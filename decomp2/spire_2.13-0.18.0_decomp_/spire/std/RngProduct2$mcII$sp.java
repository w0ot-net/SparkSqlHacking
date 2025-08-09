package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcII$sp extends RngProduct2, SemiringProduct2$mcII$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcII$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcII$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcII$sp$(final RngProduct2$mcII$sp $this, final Tuple2 x0) {
      return $this.negate$mcII$sp(x0);
   }

   default Tuple2 negate$mcII$sp(final Tuple2 x0) {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().negate$mcI$sp(x0._1$mcI$sp()), this.structure2$mcI$sp().negate$mcI$sp(x0._2$mcI$sp()));
   }
}
