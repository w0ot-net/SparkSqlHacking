package spire.std;

import cats.kernel.Order;
import scala.Tuple11;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db\u0001C\b\u0011!\u0003\r\tA\u0005\u000b\t\u000bu\u0003A\u0011\u00010\t\u000b\t\u0004a1A2\t\u000b\u0015\u0004a1\u00014\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\r\u0001\u0011\u0005\u00131\u0004\u0002\u000f\u001fJ$WM\u001d)s_\u0012,8\r^\u00192\u0015\t\t\"#A\u0002ti\u0012T\u0011aE\u0001\u0006gBL'/Z\u000b\r+IbtHQ#I\u0017:\u000bFkV\n\u0005\u0001Ya\u0012\f\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VM\u001a\t\u0004;)jcB\u0001\u0010(\u001d\tyRE\u0004\u0002!I5\t\u0011E\u0003\u0002#G\u00051AH]8piz\u001a\u0001!C\u0001\u0014\u0013\t1##A\u0004bY\u001e,'M]1\n\u0005!J\u0013a\u00029bG.\fw-\u001a\u0006\u0003MII!a\u000b\u0017\u0003\u000b=\u0013H-\u001a:\u000b\u0005!J\u0003#D\f/amr\u0014\tR$K\u001bB\u001bf+\u0003\u000201\t9A+\u001e9mKF\n\u0004CA\u00193\u0019\u0001!Qa\r\u0001C\u0002Q\u0012\u0011!Q\t\u0003ka\u0002\"a\u0006\u001c\n\u0005]B\"a\u0002(pi\"Lgn\u001a\t\u0003/eJ!A\u000f\r\u0003\u0007\u0005s\u0017\u0010\u0005\u00022y\u0011)Q\b\u0001b\u0001i\t\t!\t\u0005\u00022\u007f\u0011)\u0001\t\u0001b\u0001i\t\t1\t\u0005\u00022\u0005\u0012)1\t\u0001b\u0001i\t\tA\t\u0005\u00022\u000b\u0012)a\t\u0001b\u0001i\t\tQ\t\u0005\u00022\u0011\u0012)\u0011\n\u0001b\u0001i\t\ta\t\u0005\u00022\u0017\u0012)A\n\u0001b\u0001i\t\tq\t\u0005\u00022\u001d\u0012)q\n\u0001b\u0001i\t\t\u0001\n\u0005\u00022#\u0012)!\u000b\u0001b\u0001i\t\t\u0011\n\u0005\u00022)\u0012)Q\u000b\u0001b\u0001i\t\t!\n\u0005\u00022/\u0012)\u0001\f\u0001b\u0001i\t\t1\nE\u0007[7BZd(\u0011#H\u00156\u00036KV\u0007\u0002!%\u0011A\f\u0005\u0002\f\u000bF\u0004&o\u001c3vGR\f\u0014'\u0001\u0004%S:LG\u000f\n\u000b\u0002?B\u0011q\u0003Y\u0005\u0003Cb\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\u0011\u00042!\b\u00161\u0003)\u0019HO];diV\u0014XMM\u000b\u0002OB\u0019QDK\u001e\u0002\u0015M$(/^2ukJ,7'F\u0001k!\ri\"FP\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A7\u0011\u0007uQ\u0013)\u0001\u0006tiJ,8\r^;sKV*\u0012\u0001\u001d\t\u0004;)\"\u0015AC:ueV\u001cG/\u001e:fmU\t1\u000fE\u0002\u001eU\u001d\u000b!b\u001d;sk\u000e$XO]38+\u00051\bcA\u000f+\u0015\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003e\u00042!\b\u0016N\u0003)\u0019HO];diV\u0014X-O\u000b\u0002yB\u0019QD\u000b)\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0002\u007fB\u0019QDK*\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003\u000b\u00012!\b\u0016W\u0003\u001d\u0019w.\u001c9be\u0016$b!a\u0003\u0002\u0012\u0005U\u0001cA\f\u0002\u000e%\u0019\u0011q\u0002\r\u0003\u0007%sG\u000f\u0003\u0004\u0002\u00145\u0001\r!L\u0001\u0003qBBa!a\u0006\u000e\u0001\u0004i\u0013A\u0001=2\u0003\r)\u0017O\u001e\u000b\u0007\u0003;\t\u0019#!\n\u0011\u0007]\ty\"C\u0002\u0002\"a\u0011qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002\u00149\u0001\r!\f\u0005\u0007\u0003/q\u0001\u0019A\u0017"
)
public interface OrderProduct11 extends Order, EqProduct11 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   Order structure5();

   Order structure6();

   Order structure7();

   Order structure8();

   Order structure9();

   Order structure10();

   Order structure11();

   // $FF: synthetic method
   static int compare$(final OrderProduct11 $this, final Tuple11 x0, final Tuple11 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple11 x0, final Tuple11 x1) {
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
                           if (cmp != 0) {
                              var10000 = cmp;
                           } else {
                              cmp = this.structure9().compare(x0._9(), x1._9());
                              if (cmp != 0) {
                                 var10000 = cmp;
                              } else {
                                 cmp = this.structure10().compare(x0._10(), x1._10());
                                 if (cmp != 0) {
                                    var10000 = cmp;
                                 } else {
                                    cmp = this.structure11().compare(x0._11(), x1._11());
                                    var10000 = cmp != 0 ? cmp : 0;
                                 }
                              }
                           }
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
   static boolean eqv$(final OrderProduct11 $this, final Tuple11 x0, final Tuple11 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple11 x0, final Tuple11 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct11 $this) {
   }
}
