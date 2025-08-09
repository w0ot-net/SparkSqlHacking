package spire.std;

import scala.Tuple2;

public interface OrderProduct2$mcDI$sp extends OrderProduct2, EqProduct2$mcDI$sp {
   // $FF: synthetic method
   static int compare$(final OrderProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcDI$sp(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcDI$sp$(final OrderProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDI$sp(x0, x1);
   }

   default int compare$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1$mcD$sp().compare$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2$mcI$sp().compare$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp());
         var10000 = cmp != 0 ? cmp : 0;
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcDI$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDI$sp$(final OrderProduct2$mcDI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDI$sp(x0, x1);
   }

   default boolean eqv$mcDI$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcDI$sp(x0, x1) == 0;
   }
}
