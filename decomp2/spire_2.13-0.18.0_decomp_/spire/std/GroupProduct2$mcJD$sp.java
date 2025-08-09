package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcJD$sp extends GroupProduct2, MonoidProduct2$mcJD$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcJD$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcJD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJD$sp$(final GroupProduct2$mcJD$sp $this, final Tuple2 x0) {
      return $this.inverse$mcJD$sp(x0);
   }

   default Tuple2 inverse$mcJD$sp(final Tuple2 x0) {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().inverse$mcJ$sp(x0._1$mcJ$sp()), this.structure2$mcD$sp().inverse$mcD$sp(x0._2$mcD$sp()));
   }
}
