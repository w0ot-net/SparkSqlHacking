package spire.std;

import cats.kernel.Order;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006#\u0002!\tA\u0015\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u00021\u0019A\u001a\u0005\u0006Q\u00021\u0019!\u001b\u0005\u0006W\u00021\u0019\u0001\u001c\u0005\u0006]\u0002!\ta\u001c\u0005\u0006o\u0002!\t\u0005\u001f\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r\u001e\u001d\u000b\u00059y\u0011aA:uI*\t\u0001#A\u0003ta&\u0014X-F\u0005\u0013_ebtHQ#I\u0017N!\u0001aE\rN!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0019!d\n\u0016\u000f\u0005m!cB\u0001\u000f#\u001d\ti\u0012%D\u0001\u001f\u0015\ty\u0002%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0001\u0012BA\u0012\u0010\u0003\u001d\tGnZ3ce\u0006L!!\n\u0014\u0002\u000fA\f7m[1hK*\u00111eD\u0005\u0003Q%\u0012Qa\u0014:eKJT!!\n\u0014\u0011\u0015QYS\u0006O\u001e?\u0003\u0012;%*\u0003\u0002-+\t1A+\u001e9mKb\u0002\"AL\u0018\r\u0001\u0011)\u0001\u0007\u0001b\u0001c\t\t\u0011)\u0005\u00023kA\u0011AcM\u0005\u0003iU\u0011qAT8uQ&tw\r\u0005\u0002\u0015m%\u0011q'\u0006\u0002\u0004\u0003:L\bC\u0001\u0018:\t\u0015Q\u0004A1\u00012\u0005\u0005\u0011\u0005C\u0001\u0018=\t\u0015i\u0004A1\u00012\u0005\u0005\u0019\u0005C\u0001\u0018@\t\u0015\u0001\u0005A1\u00012\u0005\u0005!\u0005C\u0001\u0018C\t\u0015\u0019\u0005A1\u00012\u0005\u0005)\u0005C\u0001\u0018F\t\u00151\u0005A1\u00012\u0005\u00051\u0005C\u0001\u0018I\t\u0015I\u0005A1\u00012\u0005\u00059\u0005C\u0001\u0018L\t\u0015a\u0005A1\u00012\u0005\u0005A\u0005C\u0003(P[aZd(\u0011#H\u00156\tQ\"\u0003\u0002Q\u001b\tQQ)\u001d)s_\u0012,8\r\u001e\u001d\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0006C\u0001\u000bU\u0013\t)VC\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t\u0001\fE\u0002\u001bO5\n!b\u001d;sk\u000e$XO]33+\u0005Y\u0006c\u0001\u000e(q\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003y\u00032AG\u0014<\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002CB\u0019!d\n \u0002\u0015M$(/^2ukJ,W'F\u0001e!\rQr%Q\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#A4\u0011\u0007i9C)\u0001\u0006tiJ,8\r^;sK^*\u0012A\u001b\t\u00045\u001d:\u0015AC:ueV\u001cG/\u001e:fqU\tQ\u000eE\u0002\u001bO)\u000bqaY8na\u0006\u0014X\rF\u0002qgV\u0004\"\u0001F9\n\u0005I,\"aA%oi\")AO\u0003a\u0001U\u0005\u0011\u0001\u0010\r\u0005\u0006m*\u0001\rAK\u0001\u0003qF\n1!Z9w)\rIH0 \t\u0003)iL!a_\u000b\u0003\u000f\t{w\u000e\\3b]\")Ao\u0003a\u0001U!)ao\u0003a\u0001U\u0001"
)
public interface OrderProduct8 extends Order, EqProduct8 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   Order structure5();

   Order structure6();

   Order structure7();

   Order structure8();

   // $FF: synthetic method
   static int compare$(final OrderProduct8 $this, final Tuple8 x0, final Tuple8 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple8 x0, final Tuple8 x1) {
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
                        if (cmp != 0) {
                           var10000 = cmp;
                        } else {
                           cmp = this.structure8().compare(x0._8(), x1._8());
                           var10000 = cmp != 0 ? cmp : 0;
                        }
                     }
                  }
               }
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct8 $this, final Tuple8 x0, final Tuple8 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple8 x0, final Tuple8 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct8 $this) {
   }
}
