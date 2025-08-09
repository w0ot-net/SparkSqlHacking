package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcDI$sp extends RngProduct2, SemiringProduct2$mcDI$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcDI$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcDI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDI$sp$(final RngProduct2$mcDI$sp $this, final Tuple2 x0) {
      return $this.negate$mcDI$sp(x0);
   }

   default Tuple2 negate$mcDI$sp(final Tuple2 x0) {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().negate$mcD$sp(x0._1$mcD$sp()), this.structure2$mcI$sp().negate$mcI$sp(x0._2$mcI$sp()));
   }
}
