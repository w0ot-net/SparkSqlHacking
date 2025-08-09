package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcDD$sp extends GroupProduct2, MonoidProduct2$mcDD$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcDD$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcDD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDD$sp$(final GroupProduct2$mcDD$sp $this, final Tuple2 x0) {
      return $this.inverse$mcDD$sp(x0);
   }

   default Tuple2 inverse$mcDD$sp(final Tuple2 x0) {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().inverse$mcD$sp(x0._1$mcD$sp()), this.structure2$mcD$sp().inverse$mcD$sp(x0._2$mcD$sp()));
   }
}
