package spire.std;

import scala.Tuple2;

public interface SemigroupProduct2$mcIJ$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcIJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcIJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcIJ$sp$(final SemigroupProduct2$mcIJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcIJ$sp(x0, x1);
   }

   default Tuple2 combine$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().combine$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcJ$sp().combine$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }
}
