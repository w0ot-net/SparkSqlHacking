package spire.std;

import cats.kernel.Order;
import scala.Tuple16;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000bE\u0004A\u0011\u0001:\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0011\u0005\u0011q\n\u0005\b\u0003?\u0002A\u0011IA1\u00059y%\u000fZ3s!J|G-^2ucYR!AF\f\u0002\u0007M$HMC\u0001\u0019\u0003\u0015\u0019\b/\u001b:f+EQr'\u0011#H\u00156\u00036KV-]?\n,\u0007n[\n\u0005\u0001m\tS\u000e\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VM\u001a\t\u0004E=\u0012dBA\u0012-\u001d\t!#F\u0004\u0002&S5\taE\u0003\u0002(Q\u00051AH]8piz\u001a\u0001!C\u0001\u0019\u0013\tYs#A\u0004bY\u001e,'M]1\n\u00055r\u0013a\u00029bG.\fw-\u001a\u0006\u0003W]I!\u0001M\u0019\u0003\u000b=\u0013H-\u001a:\u000b\u00055r\u0003C\u0005\u000f4k\u0001\u001be)\u0013'P%VC6LX1eO*L!\u0001N\u000f\u0003\u000fQ+\b\u000f\\32mA\u0011ag\u000e\u0007\u0001\t\u0015A\u0004A1\u0001:\u0005\u0005\t\u0015C\u0001\u001e>!\ta2(\u0003\u0002=;\t9aj\u001c;iS:<\u0007C\u0001\u000f?\u0013\tyTDA\u0002B]f\u0004\"AN!\u0005\u000b\t\u0003!\u0019A\u001d\u0003\u0003\t\u0003\"A\u000e#\u0005\u000b\u0015\u0003!\u0019A\u001d\u0003\u0003\r\u0003\"AN$\u0005\u000b!\u0003!\u0019A\u001d\u0003\u0003\u0011\u0003\"A\u000e&\u0005\u000b-\u0003!\u0019A\u001d\u0003\u0003\u0015\u0003\"AN'\u0005\u000b9\u0003!\u0019A\u001d\u0003\u0003\u0019\u0003\"A\u000e)\u0005\u000bE\u0003!\u0019A\u001d\u0003\u0003\u001d\u0003\"AN*\u0005\u000bQ\u0003!\u0019A\u001d\u0003\u0003!\u0003\"A\u000e,\u0005\u000b]\u0003!\u0019A\u001d\u0003\u0003%\u0003\"AN-\u0005\u000bi\u0003!\u0019A\u001d\u0003\u0003)\u0003\"A\u000e/\u0005\u000bu\u0003!\u0019A\u001d\u0003\u0003-\u0003\"AN0\u0005\u000b\u0001\u0004!\u0019A\u001d\u0003\u00031\u0003\"A\u000e2\u0005\u000b\r\u0004!\u0019A\u001d\u0003\u00035\u0003\"AN3\u0005\u000b\u0019\u0004!\u0019A\u001d\u0003\u00039\u0003\"A\u000e5\u0005\u000b%\u0004!\u0019A\u001d\u0003\u0003=\u0003\"AN6\u0005\u000b1\u0004!\u0019A\u001d\u0003\u0003A\u0003\"C\\86\u0001\u000e3\u0015\nT(S+b[f,\u00193hU6\tQ#\u0003\u0002q+\tYQ)\u001d)s_\u0012,8\r^\u00197\u0003\u0019!\u0013N\\5uIQ\t1\u000f\u0005\u0002\u001di&\u0011Q/\b\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012\u0001\u001f\t\u0004E=*\u0014AC:ueV\u001cG/\u001e:feU\t1\u0010E\u0002#_\u0001\u000b!b\u001d;sk\u000e$XO]34+\u0005q\bc\u0001\u00120\u0007\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005\r\u0001c\u0001\u00120\r\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005%\u0001c\u0001\u00120\u0013\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u0005=\u0001c\u0001\u00120\u0019\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005U\u0001c\u0001\u00120\u001f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005m\u0001c\u0001\u00120%\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005\u0005\u0002c\u0001\u00120+\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t9\u0003E\u0002#_a\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u0011Q\u0006\t\u0004E=Z\u0016aC:ueV\u001cG/\u001e:fcI*\"!a\r\u0011\u0007\tzc,A\u0006tiJ,8\r^;sKF\u001aTCAA\u001d!\r\u0011s&Y\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002@A\u0019!e\f3\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003\u000b\u00022AI\u0018h\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005-\u0003c\u0001\u00120U\u000691m\\7qCJ,GCBA)\u0003/\nY\u0006E\u0002\u001d\u0003'J1!!\u0016\u001e\u0005\rIe\u000e\u001e\u0005\u0007\u00033\u0012\u0002\u0019\u0001\u001a\u0002\u0005a\u0004\u0004BBA/%\u0001\u0007!'\u0001\u0002yc\u0005\u0019Q-\u001d<\u0015\r\u0005\r\u0014\u0011NA6!\ra\u0012QM\u0005\u0004\u0003Oj\"a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u00033\u001a\u0002\u0019\u0001\u001a\t\r\u0005u3\u00031\u00013\u0001"
)
public interface OrderProduct16 extends Order, EqProduct16 {
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

   Order structure12();

   Order structure13();

   Order structure14();

   Order structure15();

   Order structure16();

   // $FF: synthetic method
   static int compare$(final OrderProduct16 $this, final Tuple16 x0, final Tuple16 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple16 x0, final Tuple16 x1) {
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
                                    if (cmp != 0) {
                                       var10000 = cmp;
                                    } else {
                                       cmp = this.structure12().compare(x0._12(), x1._12());
                                       if (cmp != 0) {
                                          var10000 = cmp;
                                       } else {
                                          cmp = this.structure13().compare(x0._13(), x1._13());
                                          if (cmp != 0) {
                                             var10000 = cmp;
                                          } else {
                                             cmp = this.structure14().compare(x0._14(), x1._14());
                                             if (cmp != 0) {
                                                var10000 = cmp;
                                             } else {
                                                cmp = this.structure15().compare(x0._15(), x1._15());
                                                if (cmp != 0) {
                                                   var10000 = cmp;
                                                } else {
                                                   cmp = this.structure16().compare(x0._16(), x1._16());
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
                  }
               }
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct16 $this, final Tuple16 x0, final Tuple16 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple16 x0, final Tuple16 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct16 $this) {
   }
}
