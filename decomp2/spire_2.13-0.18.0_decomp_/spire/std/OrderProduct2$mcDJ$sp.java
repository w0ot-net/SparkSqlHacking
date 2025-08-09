package spire.std;

import scala.Tuple2;

public interface OrderProduct2$mcDJ$sp extends OrderProduct2, EqProduct2$mcDJ$sp {
   // $FF: synthetic method
   static int compare$(final OrderProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcDJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcDJ$sp$(final OrderProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDJ$sp(x0, x1);
   }

   default int compare$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1$mcD$sp().compare$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2$mcJ$sp().compare$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp());
         var10000 = cmp != 0 ? cmp : 0;
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcDJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDJ$sp$(final OrderProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDJ$sp(x0, x1);
   }

   default boolean eqv$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcDJ$sp(x0, x1) == 0;
   }
}
