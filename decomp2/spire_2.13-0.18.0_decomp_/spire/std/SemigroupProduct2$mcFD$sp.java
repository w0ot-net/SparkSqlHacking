package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemigroupProduct2$mcFD$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcFD$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcFD$sp$(final SemigroupProduct2$mcFD$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcFD$sp(x0, x1);
   }

   default Tuple2 combine$mcFD$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().combine$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().combine$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp())));
   }
}
