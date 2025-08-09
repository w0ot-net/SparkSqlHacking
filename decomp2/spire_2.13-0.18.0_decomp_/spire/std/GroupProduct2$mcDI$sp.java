package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcDI$sp extends GroupProduct2, MonoidProduct2$mcDI$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcDI$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcDI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDI$sp$(final GroupProduct2$mcDI$sp $this, final Tuple2 x0) {
      return $this.inverse$mcDI$sp(x0);
   }

   default Tuple2 inverse$mcDI$sp(final Tuple2 x0) {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().inverse$mcD$sp(x0._1$mcD$sp()), this.structure2$mcI$sp().inverse$mcI$sp(x0._2$mcI$sp()));
   }
}
