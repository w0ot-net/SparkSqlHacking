package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcIJ$sp extends GroupProduct2, MonoidProduct2$mcIJ$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcIJ$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcIJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcIJ$sp$(final GroupProduct2$mcIJ$sp $this, final Tuple2 x0) {
      return $this.inverse$mcIJ$sp(x0);
   }

   default Tuple2 inverse$mcIJ$sp(final Tuple2 x0) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().inverse$mcI$sp(x0._1$mcI$sp()), this.structure2$mcJ$sp().inverse$mcJ$sp(x0._2$mcJ$sp()));
   }
}
