package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcJJ$sp extends RngProduct2, SemiringProduct2$mcJJ$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcJJ$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcJJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcJJ$sp$(final RngProduct2$mcJJ$sp $this, final Tuple2 x0) {
      return $this.negate$mcJJ$sp(x0);
   }

   default Tuple2 negate$mcJJ$sp(final Tuple2 x0) {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().negate$mcJ$sp(x0._1$mcJ$sp()), this.structure2$mcJ$sp().negate$mcJ$sp(x0._2$mcJ$sp()));
   }
}
