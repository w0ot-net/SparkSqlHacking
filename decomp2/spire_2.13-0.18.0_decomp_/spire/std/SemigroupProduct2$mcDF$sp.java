package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemigroupProduct2$mcDF$sp extends SemigroupProduct2 {
   // $FF: synthetic method
   static Tuple2 combine$(final SemigroupProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple2 combine(final Tuple2 x0, final Tuple2 x1) {
      return this.combine$mcDF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 combine$mcDF$sp$(final SemigroupProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.combine$mcDF$sp(x0, x1);
   }

   default Tuple2 combine$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().combine$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().combine$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }
}
