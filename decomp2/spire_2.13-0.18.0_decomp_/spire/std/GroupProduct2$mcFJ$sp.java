package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcFJ$sp extends GroupProduct2, MonoidProduct2$mcFJ$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcFJ$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcFJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFJ$sp$(final GroupProduct2$mcFJ$sp $this, final Tuple2 x0) {
      return $this.inverse$mcFJ$sp(x0);
   }

   default Tuple2 inverse$mcFJ$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().inverse$mcJ$sp(x0._2$mcJ$sp())));
   }
}
