package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface GroupProduct2$mcFF$sp extends GroupProduct2, MonoidProduct2$mcFF$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcFF$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcFF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcFF$sp$(final GroupProduct2$mcFF$sp $this, final Tuple2 x0) {
      return $this.inverse$mcFF$sp(x0);
   }

   default Tuple2 inverse$mcFF$sp(final Tuple2 x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()))), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().inverse$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()))));
   }
}
