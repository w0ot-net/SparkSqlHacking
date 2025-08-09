package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcIF$sp extends GroupProduct2, MonoidProduct2$mcIF$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcIF$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcIF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcIF$sp$(final GroupProduct2$mcIF$sp $this, final Tuple2 x0) {
      return $this.inverse$mcIF$sp(x0);
   }

   default Tuple2 inverse$mcIF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().inverse$mcI$sp(x0._1$mcI$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
