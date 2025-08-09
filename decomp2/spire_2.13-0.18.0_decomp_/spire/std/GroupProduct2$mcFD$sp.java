package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcFD$sp extends GroupProduct2, MonoidProduct2$mcFD$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcFD$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcFD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFD$sp$(final GroupProduct2$mcFD$sp $this, final Tuple2 x0) {
      return $this.inverse$mcFD$sp(x0);
   }

   default Tuple2 inverse$mcFD$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().inverse$mcD$sp(x0._2$mcD$sp())));
   }
}
