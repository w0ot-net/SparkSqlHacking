package spire.std;

import cats.kernel.Order;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006\u001b\u0002!\tA\u0014\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u00021\u0019a\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006a\u0002!\t%\u001d\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r^\u001c\u000b\u00055q\u0011aA:uI*\tq\"A\u0003ta&\u0014X-\u0006\u0005\u0012]aZd(\u0011#H'\u0011\u0001!\u0003G%\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rIb%\u000b\b\u00035\rr!aG\u0011\u000f\u0005q\u0001S\"A\u000f\u000b\u0005yy\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003=I!A\t\b\u0002\u000f\u0005dw-\u001a2sC&\u0011A%J\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0011c\"\u0003\u0002(Q\t)qJ\u001d3fe*\u0011A%\n\t\n')bsGO\u001fA\u0007\u001aK!a\u000b\u000b\u0003\rQ+\b\u000f\\38!\tic\u0006\u0004\u0001\u0005\u000b=\u0002!\u0019\u0001\u0019\u0003\u0003\u0005\u000b\"!\r\u001b\u0011\u0005M\u0011\u0014BA\u001a\u0015\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aE\u001b\n\u0005Y\"\"aA!osB\u0011Q\u0006\u000f\u0003\u0006s\u0001\u0011\r\u0001\r\u0002\u0002\u0005B\u0011Qf\u000f\u0003\u0006y\u0001\u0011\r\u0001\r\u0002\u0002\u0007B\u0011QF\u0010\u0003\u0006\u007f\u0001\u0011\r\u0001\r\u0002\u0002\tB\u0011Q&\u0011\u0003\u0006\u0005\u0002\u0011\r\u0001\r\u0002\u0002\u000bB\u0011Q\u0006\u0012\u0003\u0006\u000b\u0002\u0011\r\u0001\r\u0002\u0002\rB\u0011Qf\u0012\u0003\u0006\u0011\u0002\u0011\r\u0001\r\u0002\u0002\u000fBI!j\u0013\u00178uu\u00025IR\u0007\u0002\u0019%\u0011A\n\u0004\u0002\u000b\u000bF\u0004&o\u001c3vGR<\u0014A\u0002\u0013j]&$H\u0005F\u0001P!\t\u0019\u0002+\u0003\u0002R)\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002)B\u0019\u0011D\n\u0017\u0002\u0015M$(/^2ukJ,''F\u0001X!\rIbeN\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u0001.\u0011\u0007e1#(\u0001\u0006tiJ,8\r^;sKR*\u0012!\u0018\t\u00043\u0019j\u0014AC:ueV\u001cG/\u001e:fkU\t\u0001\rE\u0002\u001aM\u0001\u000b!b\u001d;sk\u000e$XO]37+\u0005\u0019\u0007cA\r'\u0007\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003\u0019\u00042!\u0007\u0014G\u0003\u001d\u0019w.\u001c9be\u0016$2!\u001b7o!\t\u0019\".\u0003\u0002l)\t\u0019\u0011J\u001c;\t\u000b5L\u0001\u0019A\u0015\u0002\u0005a\u0004\u0004\"B8\n\u0001\u0004I\u0013A\u0001=2\u0003\r)\u0017O\u001e\u000b\u0004eV4\bCA\nt\u0013\t!HCA\u0004C_>dW-\u00198\t\u000b5T\u0001\u0019A\u0015\t\u000b=T\u0001\u0019A\u0015"
)
public interface OrderProduct7 extends Order, EqProduct7 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   Order structure5();

   Order structure6();

   Order structure7();

   // $FF: synthetic method
   static int compare$(final OrderProduct7 $this, final Tuple7 x0, final Tuple7 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple7 x0, final Tuple7 x1) {
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
                  if (cmp != 0) {
                     var10000 = cmp;
                  } else {
                     cmp = this.structure6().compare(x0._6(), x1._6());
                     if (cmp != 0) {
                        var10000 = cmp;
                     } else {
                        cmp = this.structure7().compare(x0._7(), x1._7());
                        var10000 = cmp != 0 ? cmp : 0;
                     }
                  }
               }
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct7 $this, final Tuple7 x0, final Tuple7 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple7 x0, final Tuple7 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct7 $this) {
   }
}
