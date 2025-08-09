package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemigroupProduct2$mcFJ$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcFJ$sp$(final SemigroupProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcFJ$sp(x0, x1);
   }

   default Tuple2 combine$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().combine$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().combine$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp())));
   }
}
