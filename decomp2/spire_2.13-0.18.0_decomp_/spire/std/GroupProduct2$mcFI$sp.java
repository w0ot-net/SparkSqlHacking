package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcFI$sp extends GroupProduct2, MonoidProduct2$mcFI$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcFI$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcFI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFI$sp$(final GroupProduct2$mcFI$sp $this, final Tuple2 x0) {
      return $this.inverse$mcFI$sp(x0);
   }

   default Tuple2 inverse$mcFI$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().inverse$mcI$sp(x0._2$mcI$sp())));
   }
}
