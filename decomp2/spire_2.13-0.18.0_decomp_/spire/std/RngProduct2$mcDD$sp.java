package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcDD$sp extends RngProduct2, SemiringProduct2$mcDD$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcDD$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcDD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDD$sp$(final RngProduct2$mcDD$sp $this, final Tuple2 x0) {
      return $this.negate$mcDD$sp(x0);
   }

   default Tuple2 negate$mcDD$sp(final Tuple2 x0) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().negate$mcD$sp(x0._1$mcD$sp()), this.structure2$mcD$sp().negate$mcD$sp(x0._2$mcD$sp()));
   }
}
