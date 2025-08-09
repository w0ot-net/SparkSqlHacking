package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcID$sp extends GroupProduct2, MonoidProduct2$mcID$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcID$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcID$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcID$sp$(final GroupProduct2$mcID$sp $this, final Tuple2 x0) {
      return $this.inverse$mcID$sp(x0);
   }

   default Tuple2 inverse$mcID$sp(final Tuple2 x0) {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().inverse$mcI$sp(x0._1$mcI$sp()), this.structure2$mcD$sp().inverse$mcD$sp(x0._2$mcD$sp()));
   }
}
