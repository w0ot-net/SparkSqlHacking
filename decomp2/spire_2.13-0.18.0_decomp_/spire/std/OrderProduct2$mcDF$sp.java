package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface OrderProduct2$mcDF$sp extends OrderProduct2, EqProduct2$mcDF$sp {
   // $FF: synthetic method
   static int compare$(final OrderProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcDF$sp(x0, x1);
   }

   // $FF: synthetic method
   static int compare$mcDF$sp$(final OrderProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.compare$mcDF$sp(x0, x1);
   }

   default int compare$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      int cmp = 0;
      cmp = this.structure1$mcD$sp().compare$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2$mcF$sp().compare$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()));
         var10000 = cmp != 0 ? cmp : 0;
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple2 x0, final Tuple2 x1) {
      return this.eqv$mcDF$sp(x0, x1);
   }

   // $FF: synthetic method
   static boolean eqv$mcDF$sp$(final OrderProduct2$mcDF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.eqv$mcDF$sp(x0, x1);
   }

   default boolean eqv$mcDF$sp(final Tuple2 x0, final Tuple2 x1) {
      return this.compare$mcDF$sp(x0, x1) == 0;
   }
}
