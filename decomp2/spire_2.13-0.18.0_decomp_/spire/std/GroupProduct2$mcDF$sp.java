package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcDF$sp extends GroupProduct2, MonoidProduct2$mcDF$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcDF$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcDF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDF$sp$(final GroupProduct2$mcDF$sp $this, final Tuple2 x0) {
      return $this.inverse$mcDF$sp(x0);
   }

   default Tuple2 inverse$mcDF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().inverse$mcD$sp(x0._1$mcD$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
