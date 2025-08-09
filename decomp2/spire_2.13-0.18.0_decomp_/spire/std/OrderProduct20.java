package spire.std;

import cats.kernel.Order;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011Q\u0002\u0001\u0007\u0004\u0005=\u0001bBA\n\u0001\u0019\r\u0011Q\u0003\u0005\b\u00033\u0001a1AA\u000e\u0011\u001d\ty\u0002\u0001D\u0002\u0003CAq!!\n\u0001\r\u0007\t9\u0003C\u0004\u0002,\u00011\u0019!!\f\t\u000f\u0005E\u0002Ab\u0001\u00024!9\u0011q\u0007\u0001\u0007\u0004\u0005e\u0002bBA\u001f\u0001\u0019\r\u0011q\b\u0005\b\u0003\u0007\u0002a1AA#\u0011\u001d\tI\u0005\u0001D\u0002\u0003\u0017Bq!a\u0014\u0001\r\u0007\t\t\u0006C\u0004\u0002V\u00011\u0019!a\u0016\t\u000f\u0005m\u0003Ab\u0001\u0002^!9\u0011\u0011\r\u0001\u0007\u0004\u0005\r\u0004bBA4\u0001\u0019\r\u0011\u0011\u000e\u0005\b\u0003[\u0002a1AA8\u0011\u001d\t\u0019\b\u0001D\u0002\u0003kBq!!\u001f\u0001\r\u0007\tY\bC\u0004\u0002\u0000\u00011\u0019!!!\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u0011q\u0013\u0001\u0005B\u0005e%AD(sI\u0016\u0014\bK]8ek\u000e$(\u0007\r\u0006\u00035m\t1a\u001d;e\u0015\u0005a\u0012!B:qSJ,W#\u0006\u0010<\u000b\"[e*\u0015+X5v\u00037MZ5m_J,\bp_\n\u0005\u0001})S\u0010\u0005\u0002!G5\t\u0011EC\u0001#\u0003\u0015\u00198-\u00197b\u0013\t!\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u0004MM2dBA\u00141\u001d\tAcF\u0004\u0002*[5\t!F\u0003\u0002,Y\u00051AH]8piz\u001a\u0001!C\u0001\u001d\u0013\ty3$A\u0004bY\u001e,'M]1\n\u0005E\u0012\u0014a\u00029bG.\fw-\u001a\u0006\u0003_mI!\u0001N\u001b\u0003\u000b=\u0013H-\u001a:\u000b\u0005E\u0012\u0004C\u0006\u00118s\u0011;%*\u0014)T-fcvLY3iW:\fHo\u001e>\n\u0005a\n#a\u0002+va2,'\u0007\r\t\u0003umb\u0001\u0001B\u0003=\u0001\t\u0007QHA\u0001B#\tq\u0014\t\u0005\u0002!\u007f%\u0011\u0001)\t\u0002\b\u001d>$\b.\u001b8h!\t\u0001#)\u0003\u0002DC\t\u0019\u0011I\\=\u0011\u0005i*E!\u0002$\u0001\u0005\u0004i$!\u0001\"\u0011\u0005iBE!B%\u0001\u0005\u0004i$!A\"\u0011\u0005iZE!\u0002'\u0001\u0005\u0004i$!\u0001#\u0011\u0005irE!B(\u0001\u0005\u0004i$!A#\u0011\u0005i\nF!\u0002*\u0001\u0005\u0004i$!\u0001$\u0011\u0005i\"F!B+\u0001\u0005\u0004i$!A$\u0011\u0005i:F!\u0002-\u0001\u0005\u0004i$!\u0001%\u0011\u0005iRF!B.\u0001\u0005\u0004i$!A%\u0011\u0005ijF!\u00020\u0001\u0005\u0004i$!\u0001&\u0011\u0005i\u0002G!B1\u0001\u0005\u0004i$!A&\u0011\u0005i\u001aG!\u00023\u0001\u0005\u0004i$!\u0001'\u0011\u0005i2G!B4\u0001\u0005\u0004i$!A'\u0011\u0005iJG!\u00026\u0001\u0005\u0004i$!\u0001(\u0011\u0005ibG!B7\u0001\u0005\u0004i$!A(\u0011\u0005izG!\u00029\u0001\u0005\u0004i$!\u0001)\u0011\u0005i\u0012H!B:\u0001\u0005\u0004i$!A)\u0011\u0005i*H!\u0002<\u0001\u0005\u0004i$!\u0001*\u0011\u0005iBH!B=\u0001\u0005\u0004i$!A*\u0011\u0005iZH!\u0002?\u0001\u0005\u0004i$!\u0001+\u0011-y|\u0018\bR$K\u001bB\u001bf+\u0017/`E\u0016D7N\\9uojl\u0011!G\u0005\u0004\u0003\u0003I\"aC#r!J|G-^2ueA\na\u0001J5oSR$CCAA\u0004!\r\u0001\u0013\u0011B\u0005\u0004\u0003\u0017\t#\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\t\t\t\u0002E\u0002'ge\n!b\u001d;sk\u000e$XO]33+\t\t9\u0002E\u0002'g\u0011\u000b!b\u001d;sk\u000e$XO]34+\t\ti\u0002E\u0002'g\u001d\u000b!b\u001d;sk\u000e$XO]35+\t\t\u0019\u0003E\u0002'g)\u000b!b\u001d;sk\u000e$XO]36+\t\tI\u0003E\u0002'g5\u000b!b\u001d;sk\u000e$XO]37+\t\ty\u0003E\u0002'gA\u000b!b\u001d;sk\u000e$XO]38+\t\t)\u0004E\u0002'gM\u000b!b\u001d;sk\u000e$XO]39+\t\tY\u0004E\u0002'gY\u000b!b\u001d;sk\u000e$XO]3:+\t\t\t\u0005E\u0002'ge\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011q\t\t\u0004MMb\u0016aC:ueV\u001cG/\u001e:fcE*\"!!\u0014\u0011\u0007\u0019\u001at,A\u0006tiJ,8\r^;sKF\u0012TCAA*!\r13GY\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002ZA\u0019aeM3\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003?\u00022AJ\u001ai\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005\u0015\u0004c\u0001\u00144W\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\tY\u0007E\u0002'g9\f1b\u001d;sk\u000e$XO]32oU\u0011\u0011\u0011\u000f\t\u0004MM\n\u0018aC:ueV\u001cG/\u001e:fca*\"!a\u001e\u0011\u0007\u0019\u001aD/A\u0006tiJ,8\r^;sKFJTCAA?!\r13g^\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0004'\u0006\u0002\u0002\u0004B\u0019ae\r>\u0002\u000f\r|W\u000e]1sKR1\u0011\u0011RAH\u0003'\u00032\u0001IAF\u0013\r\ti)\t\u0002\u0004\u0013:$\bBBAI-\u0001\u0007a'\u0001\u0002ya!1\u0011Q\u0013\fA\u0002Y\n!\u0001_\u0019\u0002\u0007\u0015\fh\u000f\u0006\u0004\u0002\u001c\u0006\u0005\u00161\u0015\t\u0004A\u0005u\u0015bAAPC\t9!i\\8mK\u0006t\u0007BBAI/\u0001\u0007a\u0007\u0003\u0004\u0002\u0016^\u0001\rA\u000e"
)
public interface OrderProduct20 extends Order, EqProduct20 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct20 $this, final Tuple20 x0, final Tuple20 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple20 x0, final Tuple20 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct20 $this, final Tuple20 x0, final Tuple20 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple20 x0, final Tuple20 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct20 $this) {
   }
}
