package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcJD$sp extends RngProduct2, SemiringProduct2$mcJD$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcJD$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcJD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJD$sp$(final RngProduct2$mcJD$sp $this, final Tuple2 x0) {
      return $this.negate$mcJD$sp(x0);
   }

   default Tuple2 negate$mcJD$sp(final Tuple2 x0) {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().negate$mcJ$sp(x0._1$mcJ$sp()), this.structure2$mcD$sp().negate$mcD$sp(x0._2$mcD$sp()));
   }
}
