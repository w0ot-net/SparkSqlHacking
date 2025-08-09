package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcII$sp extends GroupProduct2, MonoidProduct2$mcII$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcII$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcII$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcII$sp$(final GroupProduct2$mcII$sp $this, final Tuple2 x0) {
      return $this.inverse$mcII$sp(x0);
   }

   default Tuple2 inverse$mcII$sp(final Tuple2 x0) {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().inverse$mcI$sp(x0._1$mcI$sp()), this.structure2$mcI$sp().inverse$mcI$sp(x0._2$mcI$sp()));
   }
}
