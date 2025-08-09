package spire.std;

import cats.kernel.Order;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u00021\u0019a\u0012\u0005\u0006\u0013\u00021\u0019A\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u0002!\ta\u0015\u0005\u00067\u0002!\t\u0005\u0018\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r\u001e\u001b\u000b\u0005)Y\u0011aA:uI*\tA\"A\u0003ta&\u0014X-F\u0003\u000fWUB4h\u0005\u0003\u0001\u001fUi\u0004C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017G\u0019r!a\u0006\u0011\u000f\u0005aqbBA\r\u001e\u001b\u0005Q\"BA\u000e\u001d\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0007\n\u0005}Y\u0011aB1mO\u0016\u0014'/Y\u0005\u0003C\t\nq\u0001]1dW\u0006<WM\u0003\u0002 \u0017%\u0011A%\n\u0002\u0006\u001fJ$WM\u001d\u0006\u0003C\t\u0002b\u0001E\u0014*i]R\u0014B\u0001\u0015\u0012\u0005\u0019!V\u000f\u001d7fiA\u0011!f\u000b\u0007\u0001\t\u0015a\u0003A1\u0001.\u0005\u0005\t\u0015C\u0001\u00182!\t\u0001r&\u0003\u00021#\t9aj\u001c;iS:<\u0007C\u0001\t3\u0013\t\u0019\u0014CA\u0002B]f\u0004\"AK\u001b\u0005\u000bY\u0002!\u0019A\u0017\u0003\u0003\t\u0003\"A\u000b\u001d\u0005\u000be\u0002!\u0019A\u0017\u0003\u0003\r\u0003\"AK\u001e\u0005\u000bq\u0002!\u0019A\u0017\u0003\u0003\u0011\u0003bAP *i]RT\"A\u0005\n\u0005\u0001K!AC#r!J|G-^2ui\u00051A%\u001b8ji\u0012\"\u0012a\u0011\t\u0003!\u0011K!!R\t\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#\u0001%\u0011\u0007Y\u0019\u0013&\u0001\u0006tiJ,8\r^;sKJ*\u0012a\u0013\t\u0004-\r\"\u0014AC:ueV\u001cG/\u001e:fgU\ta\nE\u0002\u0017G]\n!b\u001d;sk\u000e$XO]35+\u0005\t\u0006c\u0001\f$u\u000591m\\7qCJ,Gc\u0001+X3B\u0011\u0001#V\u0005\u0003-F\u00111!\u00138u\u0011\u0015Af\u00011\u0001'\u0003\tA\b\u0007C\u0003[\r\u0001\u0007a%\u0001\u0002yc\u0005\u0019Q-\u001d<\u0015\u0007u\u0003\u0017\r\u0005\u0002\u0011=&\u0011q,\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015Av\u00011\u0001'\u0011\u0015Qv\u00011\u0001'\u0001"
)
public interface OrderProduct4 extends Order, EqProduct4 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   // $FF: synthetic method
   static int compare$(final OrderProduct4 $this, final Tuple4 x0, final Tuple4 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple4 x0, final Tuple4 x1) {
      int cmp = 0;
      cmp = this.structure1().compare(x0._1(), x1._1());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2().compare(x0._2(), x1._2());
         if (cmp != 0) {
            var10000 = cmp;
         } else {
            cmp = this.structure3().compare(x0._3(), x1._3());
            if (cmp != 0) {
               var10000 = cmp;
            } else {
               cmp = this.structure4().compare(x0._4(), x1._4());
               var10000 = cmp != 0 ? cmp : 0;
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct4 $this, final Tuple4 x0, final Tuple4 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple4 x0, final Tuple4 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct4 $this) {
   }
}
