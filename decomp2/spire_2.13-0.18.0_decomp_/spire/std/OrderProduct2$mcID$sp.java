package spire.std;

import scala.Tuple2;

public interface OrderProduct2$mcID$sp extends OrderProduct2, EqProduct2$mcID$sp {
   // $FF: synthetic method
   static int compare$(final OrderProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcID$sp(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcID$sp$(final OrderProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcID$sp(x0, x1);
   }

   default int compare$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1$mcI$sp().compare$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2$mcD$sp().compare$mcD$sp(x0._2$mcD$sp(), x1._2$mcD$sp());
         var10000 = cmp != 0 ? cmp : 0;
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcID$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcID$sp$(final OrderProduct2$mcID$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcID$sp(x0, x1);
   }

   default boolean eqv$mcID$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcID$sp(x0, x1) == 0;
   }
}
