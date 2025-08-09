package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemigroupProduct2$mcIF$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcIF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcIF$sp$(final SemigroupProduct2$mcIF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcIF$sp(x0, x1);
   }

   default Tuple2 combine$mcIF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().combine$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().combine$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }
}
