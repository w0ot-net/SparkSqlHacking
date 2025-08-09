package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcJI$sp extends GroupProduct2, MonoidProduct2$mcJI$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcJI$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcJI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJI$sp$(final GroupProduct2$mcJI$sp $this, final Tuple2 x0) {
      return $this.inverse$mcJI$sp(x0);
   }

   default Tuple2 inverse$mcJI$sp(final Tuple2 x0) {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().inverse$mcJ$sp(x0._1$mcJ$sp()), this.structure2$mcI$sp().inverse$mcI$sp(x0._2$mcI$sp()));
   }
}
