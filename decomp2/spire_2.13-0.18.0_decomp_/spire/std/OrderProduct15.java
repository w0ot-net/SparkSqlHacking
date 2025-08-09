package spire.std;

import cats.kernel.Order;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001C\n\u0015!\u0003\r\tA\u0006\r\t\u000b5\u0004A\u0011\u00018\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001A\u0011AA!\u0011\u001d\t\t\u0006\u0001C!\u0003'\u0012ab\u0014:eKJ\u0004&o\u001c3vGR\fTG\u0003\u0002\u0016-\u0005\u00191\u000f\u001e3\u000b\u0003]\tQa\u001d9je\u0016,\u0002#\u0007\u001cA\u0007\u001aKEj\u0014*V1ns\u0016\rZ4\u0014\t\u0001Q\u0002%\u001b\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u0005r\u0013G\u0004\u0002#W9\u00111%\u000b\b\u0003I!j\u0011!\n\u0006\u0003M\u001d\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002/%\u0011!FF\u0001\bC2<WM\u0019:b\u0013\taS&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005)2\u0012BA\u00181\u0005\u0015y%\u000fZ3s\u0015\taS\u0006E\t\u001ceQz$)\u0012%L\u001dF#vKW/aG\u001aL!a\r\u000f\u0003\u000fQ+\b\u000f\\32kA\u0011QG\u000e\u0007\u0001\t\u00159\u0004A1\u00019\u0005\u0005\t\u0015CA\u001d=!\tY\"(\u0003\u0002<9\t9aj\u001c;iS:<\u0007CA\u000e>\u0013\tqDDA\u0002B]f\u0004\"!\u000e!\u0005\u000b\u0005\u0003!\u0019\u0001\u001d\u0003\u0003\t\u0003\"!N\"\u0005\u000b\u0011\u0003!\u0019\u0001\u001d\u0003\u0003\r\u0003\"!\u000e$\u0005\u000b\u001d\u0003!\u0019\u0001\u001d\u0003\u0003\u0011\u0003\"!N%\u0005\u000b)\u0003!\u0019\u0001\u001d\u0003\u0003\u0015\u0003\"!\u000e'\u0005\u000b5\u0003!\u0019\u0001\u001d\u0003\u0003\u0019\u0003\"!N(\u0005\u000bA\u0003!\u0019\u0001\u001d\u0003\u0003\u001d\u0003\"!\u000e*\u0005\u000bM\u0003!\u0019\u0001\u001d\u0003\u0003!\u0003\"!N+\u0005\u000bY\u0003!\u0019\u0001\u001d\u0003\u0003%\u0003\"!\u000e-\u0005\u000be\u0003!\u0019\u0001\u001d\u0003\u0003)\u0003\"!N.\u0005\u000bq\u0003!\u0019\u0001\u001d\u0003\u0003-\u0003\"!\u000e0\u0005\u000b}\u0003!\u0019\u0001\u001d\u0003\u00031\u0003\"!N1\u0005\u000b\t\u0004!\u0019\u0001\u001d\u0003\u00035\u0003\"!\u000e3\u0005\u000b\u0015\u0004!\u0019\u0001\u001d\u0003\u00039\u0003\"!N4\u0005\u000b!\u0004!\u0019\u0001\u001d\u0003\u0003=\u0003\u0012C[65\u007f\t+\u0005j\u0013(R)^SV\fY2g\u001b\u0005!\u0012B\u00017\u0015\u0005-)\u0015\u000f\u0015:pIV\u001cG/M\u001b\u0002\r\u0011Jg.\u001b;%)\u0005y\u0007CA\u000eq\u0013\t\tHD\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\tA\u000fE\u0002\"]Q\n!b\u001d;sk\u000e$XO]33+\u00059\bcA\u0011/\u007f\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003i\u00042!\t\u0018C\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002{B\u0019\u0011EL#\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002\u0002A\u0019\u0011E\f%\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002\bA\u0019\u0011EL&\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002\u000eA\u0019\u0011E\f(\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u0002\u0014A\u0019\u0011EL)\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u0002\u001aA\u0019\u0011E\f+\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003?\u00012!\t\u0018X\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005\u0015\u0002cA\u0011/5\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\tY\u0003E\u0002\"]u\u000b1b\u001d;sk\u000e$XO]32gU\u0011\u0011\u0011\u0007\t\u0004C9\u0002\u0017aC:ueV\u001cG/\u001e:fcQ*\"!a\u000e\u0011\u0007\u0005r3-A\u0006tiJ,8\r^;sKF*TCAA\u001f!\r\tcFZ\u0001\bG>l\u0007/\u0019:f)\u0019\t\u0019%!\u0013\u0002NA\u00191$!\u0012\n\u0007\u0005\u001dCDA\u0002J]RDa!a\u0013\u0012\u0001\u0004\t\u0014A\u0001=1\u0011\u0019\ty%\u0005a\u0001c\u0005\u0011\u00010M\u0001\u0004KF4HCBA+\u00037\ni\u0006E\u0002\u001c\u0003/J1!!\u0017\u001d\u0005\u001d\u0011un\u001c7fC:Da!a\u0013\u0013\u0001\u0004\t\u0004BBA(%\u0001\u0007\u0011\u0007"
)
public interface OrderProduct15 extends Order, EqProduct15 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct15 $this, final Tuple15 x0, final Tuple15 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple15 x0, final Tuple15 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct15 $this, final Tuple15 x0, final Tuple15 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple15 x0, final Tuple15 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct15 $this) {
   }
}
