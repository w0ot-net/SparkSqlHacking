package spire.std;

import scala.Tuple2;

public interface SemigroupProduct2$mcDJ$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcDJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDJ$sp$(final SemigroupProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDJ$sp(x0, x1);
   }

   default Tuple2 combine$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().combine$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcJ$sp().combine$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }
}
