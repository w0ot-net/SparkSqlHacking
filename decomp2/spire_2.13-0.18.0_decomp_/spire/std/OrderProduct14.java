package spire.std;

import cats.kernel.Order;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001\u0003\n\u0014!\u0003\r\t!F\f\t\u000b%\u0004A\u0011\u00016\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0005\u0002\u0005M\u0002bBA\"\u0001\u0011\u0005\u0013Q\t\u0002\u000f\u001fJ$WM\u001d)s_\u0012,8\r^\u00195\u0015\t!R#A\u0002ti\u0012T\u0011AF\u0001\u0006gBL'/Z\u000b\u00101Uz$)\u0012%L\u001dF#vKW/aGN!\u0001!G\u0010f!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0019\u0001%\f\u0019\u000f\u0005\u0005RcB\u0001\u0012)\u001d\t\u0019s%D\u0001%\u0015\t)c%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u00051\u0012BA\u0015\u0016\u0003\u001d\tGnZ3ce\u0006L!a\u000b\u0017\u0002\u000fA\f7m[1hK*\u0011\u0011&F\u0005\u0003]=\u0012Qa\u0014:eKJT!a\u000b\u0017\u0011!i\t4GP!E\u000f*k\u0005k\u0015,Z9~\u0013\u0017B\u0001\u001a\u001c\u0005\u001d!V\u000f\u001d7fcQ\u0002\"\u0001N\u001b\r\u0001\u0011)a\u0007\u0001b\u0001o\t\t\u0011)\u0005\u00029wA\u0011!$O\u0005\u0003um\u0011qAT8uQ&tw\r\u0005\u0002\u001by%\u0011Qh\u0007\u0002\u0004\u0003:L\bC\u0001\u001b@\t\u0015\u0001\u0005A1\u00018\u0005\u0005\u0011\u0005C\u0001\u001bC\t\u0015\u0019\u0005A1\u00018\u0005\u0005\u0019\u0005C\u0001\u001bF\t\u00151\u0005A1\u00018\u0005\u0005!\u0005C\u0001\u001bI\t\u0015I\u0005A1\u00018\u0005\u0005)\u0005C\u0001\u001bL\t\u0015a\u0005A1\u00018\u0005\u00051\u0005C\u0001\u001bO\t\u0015y\u0005A1\u00018\u0005\u00059\u0005C\u0001\u001bR\t\u0015\u0011\u0006A1\u00018\u0005\u0005A\u0005C\u0001\u001bU\t\u0015)\u0006A1\u00018\u0005\u0005I\u0005C\u0001\u001bX\t\u0015A\u0006A1\u00018\u0005\u0005Q\u0005C\u0001\u001b[\t\u0015Y\u0006A1\u00018\u0005\u0005Y\u0005C\u0001\u001b^\t\u0015q\u0006A1\u00018\u0005\u0005a\u0005C\u0001\u001ba\t\u0015\t\u0007A1\u00018\u0005\u0005i\u0005C\u0001\u001bd\t\u0015!\u0007A1\u00018\u0005\u0005q\u0005\u0003\u00054hgy\nEi\u0012&N!N3\u0016\fX0c\u001b\u0005\u0019\u0012B\u00015\u0014\u0005-)\u0015\u000f\u0015:pIV\u001cG/\r\u001b\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0007C\u0001\u000em\u0013\ti7D\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t\u0001\u000fE\u0002![M\n!b\u001d;sk\u000e$XO]33+\u0005\u0019\bc\u0001\u0011.}\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003Y\u00042\u0001I\u0017B\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002sB\u0019\u0001%\f#\u0002\u0015M$(/^2ukJ,W'F\u0001}!\r\u0001SfR\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#A@\u0011\u0007\u0001j#*\u0001\u0006tiJ,8\r^;sK^*\"!!\u0002\u0011\u0007\u0001jS*\u0001\u0006tiJ,8\r^;sKb*\"!a\u0003\u0011\u0007\u0001j\u0003+\u0001\u0006tiJ,8\r^;sKf*\"!!\u0005\u0011\u0007\u0001j3+A\u0006tiJ,8\r^;sKF\u0002TCAA\f!\r\u0001SFV\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002\u001eA\u0019\u0001%L-\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003G\u00012\u0001I\u0017]\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005%\u0002c\u0001\u0011.?\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\ty\u0003E\u0002![\t\fqaY8na\u0006\u0014X\r\u0006\u0004\u00026\u0005m\u0012q\b\t\u00045\u0005]\u0012bAA\u001d7\t\u0019\u0011J\u001c;\t\r\u0005u\u0002\u00031\u00011\u0003\tA\b\u0007\u0003\u0004\u0002BA\u0001\r\u0001M\u0001\u0003qF\n1!Z9w)\u0019\t9%!\u0014\u0002PA\u0019!$!\u0013\n\u0007\u0005-3DA\u0004C_>dW-\u00198\t\r\u0005u\u0012\u00031\u00011\u0011\u0019\t\t%\u0005a\u0001a\u0001"
)
public interface OrderProduct14 extends Order, EqProduct14 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct14 $this, final Tuple14 x0, final Tuple14 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple14 x0, final Tuple14 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct14 $this, final Tuple14 x0, final Tuple14 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple14 x0, final Tuple14 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct14 $this) {
   }
}
