package spire.std;

import scala.Tuple2;

public interface OrderProduct2$mcJJ$sp extends OrderProduct2, EqProduct2$mcJJ$sp {
   // $FF: synthetic method
   static int compare$(final OrderProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcJJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcJJ$sp$(final OrderProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcJJ$sp(x0, x1);
   }

   default int compare$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1$mcJ$sp().compare$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp());
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
   static boolean eqv$(final OrderProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcJJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcJJ$sp$(final OrderProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcJJ$sp(x0, x1);
   }

   default boolean eqv$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcJJ$sp(x0, x1) == 0;
   }
}
