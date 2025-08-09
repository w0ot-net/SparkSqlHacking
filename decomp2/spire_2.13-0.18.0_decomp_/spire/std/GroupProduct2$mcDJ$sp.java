package spire.std;

import scala.Tuple2;

public interface GroupProduct2$mcDJ$sp extends GroupProduct2, MonoidProduct2$mcDJ$sp {
   // $FF: synthetic method
   static Tuple2 inverse$(final GroupProduct2$mcDJ$sp $this, final Tuple2 x0) {
      return $this.inverse(x0);
   }

   default Tuple2 inverse(final Tuple2 x0) {
      return this.inverse$mcDJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 inverse$mcDJ$sp$(final GroupProduct2$mcDJ$sp $this, final Tuple2 x0) {
      return $this.inverse$mcDJ$sp(x0);
   }

   default Tuple2 inverse$mcDJ$sp(final Tuple2 x0) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().inverse$mcD$sp(x0._1$mcD$sp()), this.structure2$mcJ$sp().inverse$mcJ$sp(x0._2$mcJ$sp()));
   }
}
