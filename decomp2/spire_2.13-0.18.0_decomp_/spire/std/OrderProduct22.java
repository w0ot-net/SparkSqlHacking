package spire.std;

import cats.kernel.Order;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005g\u0001\u0003\u000e\u001c!\u0003\r\t!H\u0010\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0019\r\u0011q\n\u0005\b\u0003'\u0002a1AA+\u0011\u001d\tI\u0006\u0001D\u0002\u00037Bq!a\u0018\u0001\r\u0007\t\t\u0007C\u0004\u0002f\u00011\u0019!a\u001a\t\u000f\u0005-\u0004Ab\u0001\u0002n!9\u0011\u0011\u000f\u0001\u0007\u0004\u0005M\u0004bBA<\u0001\u0019\r\u0011\u0011\u0010\u0005\b\u0003{\u0002a1AA@\u0011\u001d\t\u0019\t\u0001D\u0002\u0003\u000bCq!!#\u0001\r\u0007\tY\tC\u0004\u0002\u0010\u00021\u0019!!%\t\u000f\u0005U\u0005Ab\u0001\u0002\u0018\"9\u00111\u0014\u0001\u0007\u0004\u0005u\u0005bBAQ\u0001\u0011\u0005\u00111\u0015\u0005\b\u0003g\u0003A\u0011IA[\u00059y%\u000fZ3s!J|G-^2ueIR!\u0001H\u000f\u0002\u0007M$HMC\u0001\u001f\u0003\u0015\u0019\b/\u001b:f+e\u0001Sh\u0012&N!N3\u0016\fX0cK\"\\g.\u001d;xuv\f\t!a\u0002\u0014\u000b\u0001\ts%a\u0003\u0011\u0005\t*S\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\r\u0005s\u0017PU3g!\rAS\u0007\u000f\b\u0003SIr!A\u000b\u0019\u000f\u0005-zS\"\u0001\u0017\u000b\u00055r\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003yI!!M\u000f\u0002\u000f\u0005dw-\u001a2sC&\u00111\u0007N\u0001\ba\u0006\u001c7.Y4f\u0015\t\tT$\u0003\u00027o\t)qJ\u001d3fe*\u00111\u0007\u000e\t\u001aEeZd)\u0013'P%VC6LX1eO*l\u0007o\u001d<zy~\f)!\u0003\u0002;G\t9A+\u001e9mKJ\u0012\u0004C\u0001\u001f>\u0019\u0001!QA\u0010\u0001C\u0002}\u0012\u0011!Q\t\u0003\u0001\u000e\u0003\"AI!\n\u0005\t\u001b#a\u0002(pi\"Lgn\u001a\t\u0003E\u0011K!!R\u0012\u0003\u0007\u0005s\u0017\u0010\u0005\u0002=\u000f\u0012)\u0001\n\u0001b\u0001\u007f\t\t!\t\u0005\u0002=\u0015\u0012)1\n\u0001b\u0001\u007f\t\t1\t\u0005\u0002=\u001b\u0012)a\n\u0001b\u0001\u007f\t\tA\t\u0005\u0002=!\u0012)\u0011\u000b\u0001b\u0001\u007f\t\tQ\t\u0005\u0002='\u0012)A\u000b\u0001b\u0001\u007f\t\ta\t\u0005\u0002=-\u0012)q\u000b\u0001b\u0001\u007f\t\tq\t\u0005\u0002=3\u0012)!\f\u0001b\u0001\u007f\t\t\u0001\n\u0005\u0002=9\u0012)Q\f\u0001b\u0001\u007f\t\t\u0011\n\u0005\u0002=?\u0012)\u0001\r\u0001b\u0001\u007f\t\t!\n\u0005\u0002=E\u0012)1\r\u0001b\u0001\u007f\t\t1\n\u0005\u0002=K\u0012)a\r\u0001b\u0001\u007f\t\tA\n\u0005\u0002=Q\u0012)\u0011\u000e\u0001b\u0001\u007f\t\tQ\n\u0005\u0002=W\u0012)A\u000e\u0001b\u0001\u007f\t\ta\n\u0005\u0002=]\u0012)q\u000e\u0001b\u0001\u007f\t\tq\n\u0005\u0002=c\u0012)!\u000f\u0001b\u0001\u007f\t\t\u0001\u000b\u0005\u0002=i\u0012)Q\u000f\u0001b\u0001\u007f\t\t\u0011\u000b\u0005\u0002=o\u0012)\u0001\u0010\u0001b\u0001\u007f\t\t!\u000b\u0005\u0002=u\u0012)1\u0010\u0001b\u0001\u007f\t\t1\u000b\u0005\u0002={\u0012)a\u0010\u0001b\u0001\u007f\t\tA\u000bE\u0002=\u0003\u0003!a!a\u0001\u0001\u0005\u0004y$!A+\u0011\u0007q\n9\u0001\u0002\u0004\u0002\n\u0001\u0011\ra\u0010\u0002\u0002-BY\u0012QBA\bw\u0019KEj\u0014*V1ns\u0016\rZ4k[B\u001ch/\u001f?\u0000\u0003\u000bi\u0011aG\u0005\u0004\u0003#Y\"aC#r!J|G-^2ueI\na\u0001J5oSR$CCAA\f!\r\u0011\u0013\u0011D\u0005\u0004\u00037\u0019#\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\t\t\t\u0003E\u0002)km\n!b\u001d;sk\u000e$XO]33+\t\t9\u0003E\u0002)k\u0019\u000b!b\u001d;sk\u000e$XO]34+\t\ti\u0003E\u0002)k%\u000b!b\u001d;sk\u000e$XO]35+\t\t\u0019\u0004E\u0002)k1\u000b!b\u001d;sk\u000e$XO]36+\t\tI\u0004E\u0002)k=\u000b!b\u001d;sk\u000e$XO]37+\t\ty\u0004E\u0002)kI\u000b!b\u001d;sk\u000e$XO]38+\t\t)\u0005E\u0002)kU\u000b!b\u001d;sk\u000e$XO]39+\t\tY\u0005E\u0002)ka\u000b!b\u001d;sk\u000e$XO]3:+\t\t\t\u0006E\u0002)km\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011q\u000b\t\u0004QUr\u0016aC:ueV\u001cG/\u001e:fcE*\"!!\u0018\u0011\u0007!*\u0014-A\u0006tiJ,8\r^;sKF\u0012TCAA2!\rAS\u0007Z\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002jA\u0019\u0001&N4\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003_\u00022\u0001K\u001bk\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005U\u0004c\u0001\u00156[\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\tY\bE\u0002)kA\f1b\u001d;sk\u000e$XO]32oU\u0011\u0011\u0011\u0011\t\u0004QU\u001a\u0018aC:ueV\u001cG/\u001e:fca*\"!a\"\u0011\u0007!*d/A\u0006tiJ,8\r^;sKFJTCAAG!\rAS'_\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0004'\u0006\u0002\u0002\u0014B\u0019\u0001&\u000e?\u0002\u0017M$(/^2ukJ,''M\u000b\u0003\u00033\u00032\u0001K\u001b\u0000\u0003-\u0019HO];diV\u0014XM\r\u001a\u0016\u0005\u0005}\u0005\u0003\u0002\u00156\u0003\u000b\tqaY8na\u0006\u0014X\r\u0006\u0004\u0002&\u0006-\u0016q\u0016\t\u0004E\u0005\u001d\u0016bAAUG\t\u0019\u0011J\u001c;\t\r\u00055\u0006\u00041\u00019\u0003\tA\b\u0007\u0003\u0004\u00022b\u0001\r\u0001O\u0001\u0003qF\n1!Z9w)\u0019\t9,!0\u0002@B\u0019!%!/\n\u0007\u0005m6EA\u0004C_>dW-\u00198\t\r\u00055\u0016\u00041\u00019\u0011\u0019\t\t,\u0007a\u0001q\u0001"
)
public interface OrderProduct22 extends Order, EqProduct22 {
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

   Order structure18();

   Order structure19();

   Order structure20();

   Order structure21();

   Order structure22();

   // $FF: synthetic method
   static int compare$(final OrderProduct22 $this, final Tuple22 x0, final Tuple22 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple22 x0, final Tuple22 x1) {
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
                                                      if (cmp != 0) {
                                                         var10000 = cmp;
                                                      } else {
                                                         cmp = this.structure18().compare(x0._18(), x1._18());
                                                         if (cmp != 0) {
                                                            var10000 = cmp;
                                                         } else {
                                                            cmp = this.structure19().compare(x0._19(), x1._19());
                                                            if (cmp != 0) {
                                                               var10000 = cmp;
                                                            } else {
                                                               cmp = this.structure20().compare(x0._20(), x1._20());
                                                               if (cmp != 0) {
                                                                  var10000 = cmp;
                                                               } else {
                                                                  cmp = this.structure21().compare(x0._21(), x1._21());
                                                                  if (cmp != 0) {
                                                                     var10000 = cmp;
                                                                  } else {
                                                                     cmp = this.structure22().compare(x0._22(), x1._22());
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
                  }
               }
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct22 $this, final Tuple22 x0, final Tuple22 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple22 x0, final Tuple22 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct22 $this) {
   }
}
