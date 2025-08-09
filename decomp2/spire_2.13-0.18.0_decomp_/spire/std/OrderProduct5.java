package spire.std;

import cats.kernel.Order;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u00021\u0019a\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u0002!\tA\u0017\u0005\u0006E\u0002!\te\u0019\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r^\u001b\u000b\u0005-a\u0011aA:uI*\tQ\"A\u0003ta&\u0014X-\u0006\u0004\u0010YYJDhP\n\u0005\u0001A1\u0012\t\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0004/\u0011:cB\u0001\r\"\u001d\tIrD\u0004\u0002\u001b=5\t1D\u0003\u0002\u001d;\u00051AH]8piz\u001a\u0001!C\u0001\u000e\u0013\t\u0001C\"A\u0004bY\u001e,'M]1\n\u0005\t\u001a\u0013a\u00029bG.\fw-\u001a\u0006\u0003A1I!!\n\u0014\u0003\u000b=\u0013H-\u001a:\u000b\u0005\t\u001a\u0003cB\t)UUB4HP\u0005\u0003SI\u0011a\u0001V;qY\u0016,\u0004CA\u0016-\u0019\u0001!Q!\f\u0001C\u00029\u0012\u0011!Q\t\u0003_I\u0002\"!\u0005\u0019\n\u0005E\u0012\"a\u0002(pi\"Lgn\u001a\t\u0003#MJ!\u0001\u000e\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002,m\u0011)q\u0007\u0001b\u0001]\t\t!\t\u0005\u0002,s\u0011)!\b\u0001b\u0001]\t\t1\t\u0005\u0002,y\u0011)Q\b\u0001b\u0001]\t\tA\t\u0005\u0002,\u007f\u0011)\u0001\t\u0001b\u0001]\t\tQ\tE\u0004C\u0007**\u0004h\u000f \u000e\u0003)I!\u0001\u0012\u0006\u0003\u0015\u0015\u000b\bK]8ek\u000e$X'\u0001\u0004%S:LG\u000f\n\u000b\u0002\u000fB\u0011\u0011\u0003S\u0005\u0003\u0013J\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u00031\u00032a\u0006\u0013+\u0003)\u0019HO];diV\u0014XMM\u000b\u0002\u001fB\u0019q\u0003J\u001b\u0002\u0015M$(/^2ukJ,7'F\u0001S!\r9B\u0005O\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A+\u0011\u0007]!3(\u0001\u0006tiJ,8\r^;sKV*\u0012\u0001\u0017\t\u0004/\u0011r\u0014aB2p[B\f'/\u001a\u000b\u00047z\u0003\u0007CA\t]\u0013\ti&CA\u0002J]RDQaX\u0004A\u0002\u001d\n!\u0001\u001f\u0019\t\u000b\u0005<\u0001\u0019A\u0014\u0002\u0005a\f\u0014aA3rmR\u0019Am\u001a5\u0011\u0005E)\u0017B\u00014\u0013\u0005\u001d\u0011un\u001c7fC:DQa\u0018\u0005A\u0002\u001dBQ!\u0019\u0005A\u0002\u001d\u0002"
)
public interface OrderProduct5 extends Order, EqProduct5 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   Order structure5();

   // $FF: synthetic method
   static int compare$(final OrderProduct5 $this, final Tuple5 x0, final Tuple5 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple5 x0, final Tuple5 x1) {
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
               if (cmp != 0) {
                  var10000 = cmp;
               } else {
                  cmp = this.structure5().compare(x0._5(), x1._5());
                  var10000 = cmp != 0 ? cmp : 0;
               }
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct5 $this, final Tuple5 x0, final Tuple5 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple5 x0, final Tuple5 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct5 $this) {
   }
}
