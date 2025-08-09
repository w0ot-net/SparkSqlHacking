package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface OrderProduct2$mcFJ$sp extends OrderProduct2, EqProduct2$mcFJ$sp {
   // $FF: synthetic method
   static int compare$(final OrderProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcFJ$sp$(final OrderProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcFJ$sp(x0, x1);
   }

   default int compare$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1$mcF$sp().compare$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()));
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
   static boolean eqv$(final OrderProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcFJ$sp$(final OrderProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcFJ$sp(x0, x1);
   }

   default boolean eqv$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcFJ$sp(x0, x1) == 0;
   }
}
