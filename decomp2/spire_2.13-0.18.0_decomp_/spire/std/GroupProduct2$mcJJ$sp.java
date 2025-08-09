package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcJJ$sp extends GroupProduct2, MonoidProduct2$mcJJ$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcJJ$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcJJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcJJ$sp$(final GroupProduct2$mcJJ$sp $this, final Tuple2 x0) {
      return $this.inverse$mcJJ$sp(x0);
   }

   default Tuple2 inverse$mcJJ$sp(final Tuple2 x0) {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().inverse$mcJ$sp(x0._1$mcJ$sp()), this.structure2$mcJ$sp().inverse$mcJ$sp(x0._2$mcJ$sp()));
   }
}
