package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcJF$sp extends GroupProduct2, MonoidProduct2$mcJF$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcJF$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcJF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJF$sp$(final GroupProduct2$mcJF$sp $this, final Tuple2 x0) {
      return $this.inverse$mcJF$sp(x0);
   }

   default Tuple2 inverse$mcJF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().inverse$mcJ$sp(x0._1$mcJ$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
