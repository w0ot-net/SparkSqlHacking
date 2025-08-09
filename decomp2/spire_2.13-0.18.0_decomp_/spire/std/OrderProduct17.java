package spire.std;

import cats.kernel.Order;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000bU\u0004A\u0011\u0001<\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0005\u0002\u0005u\u0003bBA7\u0001\u0011\u0005\u0013q\u000e\u0002\u000f\u001fJ$WM\u001d)s_\u0012,8\r^\u00198\u0015\t9\u0002$A\u0002ti\u0012T\u0011!G\u0001\u0006gBL'/Z\u000b\u00137a\u0012U\tS&O#R;&,\u00181dM&dwn\u0005\u0003\u00019\t\n\bCA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g\rE\u0002$aMr!\u0001J\u0017\u000f\u0005\u0015ZcB\u0001\u0014+\u001b\u00059#B\u0001\u0015*\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\r\n\u00051B\u0012aB1mO\u0016\u0014'/Y\u0005\u0003]=\nq\u0001]1dW\u0006<WM\u0003\u0002-1%\u0011\u0011G\r\u0002\u0006\u001fJ$WM\u001d\u0006\u0003]=\u00022#\b\u001b7\u0003\u0012;%*\u0014)T-fcvLY3iW:L!!\u000e\u0010\u0003\u000fQ+\b\u000f\\32oA\u0011q\u0007\u000f\u0007\u0001\t\u0015I\u0004A1\u0001;\u0005\u0005\t\u0015CA\u001e?!\tiB(\u0003\u0002>=\t9aj\u001c;iS:<\u0007CA\u000f@\u0013\t\u0001eDA\u0002B]f\u0004\"a\u000e\"\u0005\u000b\r\u0003!\u0019\u0001\u001e\u0003\u0003\t\u0003\"aN#\u0005\u000b\u0019\u0003!\u0019\u0001\u001e\u0003\u0003\r\u0003\"a\u000e%\u0005\u000b%\u0003!\u0019\u0001\u001e\u0003\u0003\u0011\u0003\"aN&\u0005\u000b1\u0003!\u0019\u0001\u001e\u0003\u0003\u0015\u0003\"a\u000e(\u0005\u000b=\u0003!\u0019\u0001\u001e\u0003\u0003\u0019\u0003\"aN)\u0005\u000bI\u0003!\u0019\u0001\u001e\u0003\u0003\u001d\u0003\"a\u000e+\u0005\u000bU\u0003!\u0019\u0001\u001e\u0003\u0003!\u0003\"aN,\u0005\u000ba\u0003!\u0019\u0001\u001e\u0003\u0003%\u0003\"a\u000e.\u0005\u000bm\u0003!\u0019\u0001\u001e\u0003\u0003)\u0003\"aN/\u0005\u000by\u0003!\u0019\u0001\u001e\u0003\u0003-\u0003\"a\u000e1\u0005\u000b\u0005\u0004!\u0019\u0001\u001e\u0003\u00031\u0003\"aN2\u0005\u000b\u0011\u0004!\u0019\u0001\u001e\u0003\u00035\u0003\"a\u000e4\u0005\u000b\u001d\u0004!\u0019\u0001\u001e\u0003\u00039\u0003\"aN5\u0005\u000b)\u0004!\u0019\u0001\u001e\u0003\u0003=\u0003\"a\u000e7\u0005\u000b5\u0004!\u0019\u0001\u001e\u0003\u0003A\u0003\"aN8\u0005\u000bA\u0004!\u0019\u0001\u001e\u0003\u0003E\u00032C]:7\u0003\u0012;%*\u0014)T-fcvLY3iW:l\u0011AF\u0005\u0003iZ\u00111\"R9Qe>$Wo\u0019;2o\u00051A%\u001b8ji\u0012\"\u0012a\u001e\t\u0003;aL!!\u001f\u0010\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#\u0001?\u0011\u0007\r\u0002d'\u0001\u0006tiJ,8\r^;sKJ*\u0012a \t\u0004GA\n\u0015AC:ueV\u001cG/\u001e:fgU\u0011\u0011Q\u0001\t\u0004GA\"\u0015AC:ueV\u001cG/\u001e:fiU\u0011\u00111\u0002\t\u0004GA:\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011\u0011\u0003\t\u0004GAR\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011q\u0003\t\u0004GAj\u0015AC:ueV\u001cG/\u001e:foU\u0011\u0011Q\u0004\t\u0004GA\u0002\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u00111\u0005\t\u0004GA\u001a\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011\u0011\u0006\t\u0004GA2\u0016aC:ueV\u001cG/\u001e:fcA*\"!a\f\u0011\u0007\r\u0002\u0014,A\u0006tiJ,8\r^;sKF\nTCAA\u001b!\r\u0019\u0003\u0007X\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002<A\u00191\u0005M0\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003\u0003\u00022a\t\u0019c\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005\u001d\u0003cA\u00121K\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\ti\u0005E\u0002$a!\f1b\u001d;sk\u000e$XO]32mU\u0011\u00111\u000b\t\u0004GAZ\u0017aC:ueV\u001cG/\u001e:fc]*\"!!\u0017\u0011\u0007\r\u0002d.A\u0004d_6\u0004\u0018M]3\u0015\r\u0005}\u0013QMA5!\ri\u0012\u0011M\u0005\u0004\u0003Gr\"aA%oi\"1\u0011qM\nA\u0002M\n!\u0001\u001f\u0019\t\r\u0005-4\u00031\u00014\u0003\tA\u0018'A\u0002fcZ$b!!\u001d\u0002x\u0005e\u0004cA\u000f\u0002t%\u0019\u0011Q\u000f\u0010\u0003\u000f\t{w\u000e\\3b]\"1\u0011q\r\u000bA\u0002MBa!a\u001b\u0015\u0001\u0004\u0019\u0004"
)
public interface OrderProduct17 extends Order, EqProduct17 {
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

   Order structure17();

   // $FF: synthetic method
   static int compare$(final OrderProduct17 $this, final Tuple17 x0, final Tuple17 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple17 x0, final Tuple17 x1) {
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
                                                   if (cmp != 0) {
                                                      var10000 = cmp;
                                                   } else {
                                                      cmp = this.structure17().compare(x0._17(), x1._17());
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
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct17 $this, final Tuple17 x0, final Tuple17 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple17 x0, final Tuple17 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct17 $this) {
   }
}
