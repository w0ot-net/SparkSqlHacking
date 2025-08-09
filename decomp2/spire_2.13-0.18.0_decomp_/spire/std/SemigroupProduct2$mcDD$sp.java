package spire.std;

import scala.Tuple2;

public interface SemigroupProduct2$mcDD$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcDD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDD$sp$(final SemigroupProduct2$mcDD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDD$sp(x0, x1);
   }

   default Tuple2 combine$mcDD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().combine$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcD$sp().combine$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp()));
   }
}
