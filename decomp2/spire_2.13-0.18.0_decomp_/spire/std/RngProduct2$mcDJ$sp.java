package spire.std;

import scala.Tuple2;

public interface RngProduct2$mcDJ$sp extends RngProduct2, SemiringProduct2$mcDJ$sp {
   // $FF: synthetic method
   static Tuple2 negate$(final RngProduct2$mcDJ$sp $this, final Tuple2 x0) {
      return $this.negate(x0);
   }

   default Tuple2 negate(final Tuple2 x0) {
      return this.negate$mcDJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 negate$mcDJ$sp$(final RngProduct2$mcDJ$sp $this, final Tuple2 x0) {
      return $this.negate$mcDJ$sp(x0);
   }

   default Tuple2 negate$mcDJ$sp(final Tuple2 x0) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().negate$mcD$sp(x0._1$mcD$sp()), this.structure2$mcJ$sp().negate$mcJ$sp(x0._2$mcJ$sp()));
   }
}
