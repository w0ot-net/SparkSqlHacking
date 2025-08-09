package spire.std;

import cats.kernel.Order;
import scala.Tuple12;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001\u0003\t\u0012!\u0003\r\taE\u000b\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u0019\u0004a1A4\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001A\u0011AA\f\u0011\u001d\t9\u0003\u0001C!\u0003S\u0011ab\u0014:eKJ\u0004&o\u001c3vGR\f$G\u0003\u0002\u0013'\u0005\u00191\u000f\u001e3\u000b\u0003Q\tQa\u001d9je\u0016,RBF\u001a>\u0001\u000e3\u0015\nT(S+b[6\u0003\u0002\u0001\u0018;u\u0003\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007c\u0001\u0010,]9\u0011q\u0004\u000b\b\u0003A\u0019r!!I\u0013\u000e\u0003\tR!a\t\u0013\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001F\u0005\u0003OM\tq!\u00197hK\n\u0014\u0018-\u0003\u0002*U\u00059\u0001/Y2lC\u001e,'BA\u0014\u0014\u0013\taSFA\u0003Pe\u0012,'O\u0003\u0002*UAq\u0001dL\u0019=\u007f\t+\u0005j\u0013(R)^S\u0016B\u0001\u0019\u001a\u0005\u001d!V\u000f\u001d7fcI\u0002\"AM\u001a\r\u0001\u0011)A\u0007\u0001b\u0001k\t\t\u0011)\u0005\u00027sA\u0011\u0001dN\u0005\u0003qe\u0011qAT8uQ&tw\r\u0005\u0002\u0019u%\u00111(\u0007\u0002\u0004\u0003:L\bC\u0001\u001a>\t\u0015q\u0004A1\u00016\u0005\u0005\u0011\u0005C\u0001\u001aA\t\u0015\t\u0005A1\u00016\u0005\u0005\u0019\u0005C\u0001\u001aD\t\u0015!\u0005A1\u00016\u0005\u0005!\u0005C\u0001\u001aG\t\u00159\u0005A1\u00016\u0005\u0005)\u0005C\u0001\u001aJ\t\u0015Q\u0005A1\u00016\u0005\u00051\u0005C\u0001\u001aM\t\u0015i\u0005A1\u00016\u0005\u00059\u0005C\u0001\u001aP\t\u0015\u0001\u0006A1\u00016\u0005\u0005A\u0005C\u0001\u001aS\t\u0015\u0019\u0006A1\u00016\u0005\u0005I\u0005C\u0001\u001aV\t\u00151\u0006A1\u00016\u0005\u0005Q\u0005C\u0001\u001aY\t\u0015I\u0006A1\u00016\u0005\u0005Y\u0005C\u0001\u001a\\\t\u0015a\u0006A1\u00016\u0005\u0005a\u0005C\u00040`cqz$)\u0012%L\u001dF#vKW\u0007\u0002#%\u0011\u0001-\u0005\u0002\f\u000bF\u0004&o\u001c3vGR\f$'\u0001\u0004%S:LG\u000f\n\u000b\u0002GB\u0011\u0001\u0004Z\u0005\u0003Kf\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003!\u00042AH\u00162\u0003)\u0019HO];diV\u0014XMM\u000b\u0002WB\u0019ad\u000b\u001f\u0002\u0015M$(/^2ukJ,7'F\u0001o!\rq2fP\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A9\u0011\u0007yY#)\u0001\u0006tiJ,8\r^;sKV*\u0012\u0001\u001e\t\u0004=-*\u0015AC:ueV\u001cG/\u001e:fmU\tq\u000fE\u0002\u001fW!\u000b!b\u001d;sk\u000e$XO]38+\u0005Q\bc\u0001\u0010,\u0017\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003u\u00042AH\u0016O\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003\u0003\u00012AH\u0016R\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005\u001d\u0001c\u0001\u0010,)\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\ti\u0001E\u0002\u001fW]\u000b1b\u001d;sk\u000e$XO]32eU\u0011\u00111\u0003\t\u0004=-R\u0016aB2p[B\f'/\u001a\u000b\u0007\u00033\ty\"a\t\u0011\u0007a\tY\"C\u0002\u0002\u001ee\u00111!\u00138u\u0011\u0019\t\tC\u0004a\u0001]\u0005\u0011\u0001\u0010\r\u0005\u0007\u0003Kq\u0001\u0019\u0001\u0018\u0002\u0005a\f\u0014aA3rmR1\u00111FA\u0019\u0003g\u00012\u0001GA\u0017\u0013\r\ty#\u0007\u0002\b\u0005>|G.Z1o\u0011\u0019\t\tc\u0004a\u0001]!1\u0011QE\bA\u00029\u0002"
)
public interface OrderProduct12 extends Order, EqProduct12 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct12 $this, final Tuple12 x0, final Tuple12 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple12 x0, final Tuple12 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct12 $this, final Tuple12 x0, final Tuple12 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple12 x0, final Tuple12 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct12 $this) {
   }
}
