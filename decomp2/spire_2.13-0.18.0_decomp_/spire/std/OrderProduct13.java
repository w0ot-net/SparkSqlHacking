package spire.std;

import cats.kernel.Order;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000b\u0015\u0004A\u0011\u00014\t\u000b)\u0004a1A6\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0011\u0005\u0011Q\u0005\u0005\b\u0003k\u0001A\u0011IA\u001c\u00059y%\u000fZ3s!J|G-^2ucMR!a\u0005\u000b\u0002\u0007M$HMC\u0001\u0016\u0003\u0015\u0019\b/\u001b:f+99BGP!E\u000f*k\u0005k\u0015,Z9~\u001bB\u0001\u0001\r\u001fCB\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t1\u0011I\\=SK\u001a\u00042a\b\u00170\u001d\t\u0001\u0013F\u0004\u0002\"O9\u0011!EJ\u0007\u0002G)\u0011A%J\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ#\u0003\u0002))\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0016,\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\u000b\u000b\n\u00055r#!B(sI\u0016\u0014(B\u0001\u0016,!=I\u0002GM\u001fA\u0007\u001aKEj\u0014*V1ns\u0016BA\u0019\u001b\u0005\u001d!V\u000f\u001d7fcM\u0002\"a\r\u001b\r\u0001\u0011)Q\u0007\u0001b\u0001m\t\t\u0011)\u0005\u00028uA\u0011\u0011\u0004O\u0005\u0003si\u0011qAT8uQ&tw\r\u0005\u0002\u001aw%\u0011AH\u0007\u0002\u0004\u0003:L\bCA\u001a?\t\u0015y\u0004A1\u00017\u0005\u0005\u0011\u0005CA\u001aB\t\u0015\u0011\u0005A1\u00017\u0005\u0005\u0019\u0005CA\u001aE\t\u0015)\u0005A1\u00017\u0005\u0005!\u0005CA\u001aH\t\u0015A\u0005A1\u00017\u0005\u0005)\u0005CA\u001aK\t\u0015Y\u0005A1\u00017\u0005\u00051\u0005CA\u001aN\t\u0015q\u0005A1\u00017\u0005\u00059\u0005CA\u001aQ\t\u0015\t\u0006A1\u00017\u0005\u0005A\u0005CA\u001aT\t\u0015!\u0006A1\u00017\u0005\u0005I\u0005CA\u001aW\t\u00159\u0006A1\u00017\u0005\u0005Q\u0005CA\u001aZ\t\u0015Q\u0006A1\u00017\u0005\u0005Y\u0005CA\u001a]\t\u0015i\u0006A1\u00017\u0005\u0005a\u0005CA\u001a`\t\u0015\u0001\u0007A1\u00017\u0005\u0005i\u0005c\u00042deu\u00025IR%M\u001fJ+\u0006l\u00170\u000e\u0003II!\u0001\u001a\n\u0003\u0017\u0015\u000b\bK]8ek\u000e$\u0018gM\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0004\"!\u00075\n\u0005%T\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005a\u0007cA\u0010-e\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003=\u00042a\b\u0017>\u0003)\u0019HO];diV\u0014XmM\u000b\u0002eB\u0019q\u0004\f!\u0002\u0015M$(/^2ukJ,G'F\u0001v!\ryBfQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#\u0001=\u0011\u0007}ac)\u0001\u0006tiJ,8\r^;sKZ*\u0012a\u001f\t\u0004?1J\u0015AC:ueV\u001cG/\u001e:foU\ta\u0010E\u0002 Y1\u000b!b\u001d;sk\u000e$XO]39+\t\t\u0019\u0001E\u0002 Y=\u000b!b\u001d;sk\u000e$XO]3:+\t\tI\u0001E\u0002 YI\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011q\u0002\t\u0004?1*\u0016aC:ueV\u001cG/\u001e:fcE*\"!!\u0006\u0011\u0007}a\u0003,A\u0006tiJ,8\r^;sKF\u0012TCAA\u000e!\ryBfW\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002\"A\u0019q\u0004\f0\u0002\u000f\r|W\u000e]1sKR1\u0011qEA\u0017\u0003c\u00012!GA\u0015\u0013\r\tYC\u0007\u0002\u0004\u0013:$\bBBA\u0018\u001f\u0001\u0007q&\u0001\u0002ya!1\u00111G\bA\u0002=\n!\u0001_\u0019\u0002\u0007\u0015\fh\u000f\u0006\u0004\u0002:\u0005}\u0012\u0011\t\t\u00043\u0005m\u0012bAA\u001f5\t9!i\\8mK\u0006t\u0007BBA\u0018!\u0001\u0007q\u0006\u0003\u0004\u00024A\u0001\ra\f"
)
public interface OrderProduct13 extends Order, EqProduct13 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct13 $this, final Tuple13 x0, final Tuple13 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple13 x0, final Tuple13 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct13 $this, final Tuple13 x0, final Tuple13 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple13 x0, final Tuple13 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct13 $this) {
   }
}
