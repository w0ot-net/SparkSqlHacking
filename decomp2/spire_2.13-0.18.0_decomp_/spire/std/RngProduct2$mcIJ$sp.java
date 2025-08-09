package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcIJ$sp extends RngProduct2, SemiringProduct2$mcIJ$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcIJ$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcIJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcIJ$sp$(final RngProduct2$mcIJ$sp $this, final Tuple2 x0) {
      return $this.negate$mcIJ$sp(x0);
   }

   default Tuple2 negate$mcIJ$sp(final Tuple2 x0) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().negate$mcI$sp(x0._1$mcI$sp()), this.structure2$mcJ$sp().negate$mcJ$sp(x0._2$mcJ$sp()));
   }
}
