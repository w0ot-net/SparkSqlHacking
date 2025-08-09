package spire.std;

import cats.kernel.Order;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000f\u0005-\u0001\u0001\"\u0001\u0002\u000e!9\u0011Q\u0003\u0001\u0007\u0004\u0005]\u0001bBA\u000e\u0001\u0019\r\u0011Q\u0004\u0005\b\u0003C\u0001a1AA\u0012\u0011\u001d\t9\u0003\u0001D\u0002\u0003SAq!!\f\u0001\r\u0007\ty\u0003C\u0004\u00024\u00011\u0019!!\u000e\t\u000f\u0005e\u0002Ab\u0001\u0002<!9\u0011q\b\u0001\u0007\u0004\u0005\u0005\u0003bBA#\u0001\u0019\r\u0011q\t\u0005\b\u0003\u0017\u0002a1AA'\u0011\u001d\t\t\u0006\u0001D\u0002\u0003'Bq!a\u0016\u0001\r\u0007\tI\u0006C\u0004\u0002^\u00011\u0019!a\u0018\t\u000f\u0005\r\u0004Ab\u0001\u0002f!9\u0011\u0011\u000e\u0001\u0007\u0004\u0005-\u0004bBA8\u0001\u0019\r\u0011\u0011\u000f\u0005\b\u0003k\u0002a1AA<\u0011\u001d\tY\b\u0001D\u0002\u0003{Bq!!!\u0001\r\u0007\t\u0019\tC\u0004\u0002\b\u00021\u0019!!#\t\u000f\u00055\u0005Ab\u0001\u0002\u0010\"9\u00111\u0013\u0001\u0005\u0002\u0005U\u0005bBAS\u0001\u0011\u0005\u0013q\u0015\u0002\u000f\u001fJ$WM\u001d)s_\u0012,8\r\u001e\u001a2\u0015\tYB$A\u0002ti\u0012T\u0011!H\u0001\u0006gBL'/Z\u000b\u0017?q2\u0015\nT(S+b[f,\u00193hU6\u00048O^=}\u007fN)\u0001\u0001\t\u0014\u0002\u0004A\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u00042a\n\u001b8\u001d\tA\u0013G\u0004\u0002*_9\u0011!FL\u0007\u0002W)\u0011A&L\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ$\u0003\u000219\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u001a4\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\r\u000f\n\u0005U2$!B(sI\u0016\u0014(B\u0001\u001a4!]\t\u0003HO#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8skb\\h0\u0003\u0002:E\t9A+\u001e9mKJ\n\u0004CA\u001e=\u0019\u0001!Q!\u0010\u0001C\u0002y\u0012\u0011!Q\t\u0003\u007f\t\u0003\"!\t!\n\u0005\u0005\u0013#a\u0002(pi\"Lgn\u001a\t\u0003C\rK!\u0001\u0012\u0012\u0003\u0007\u0005s\u0017\u0010\u0005\u0002<\r\u0012)q\t\u0001b\u0001}\t\t!\t\u0005\u0002<\u0013\u0012)!\n\u0001b\u0001}\t\t1\t\u0005\u0002<\u0019\u0012)Q\n\u0001b\u0001}\t\tA\t\u0005\u0002<\u001f\u0012)\u0001\u000b\u0001b\u0001}\t\tQ\t\u0005\u0002<%\u0012)1\u000b\u0001b\u0001}\t\ta\t\u0005\u0002<+\u0012)a\u000b\u0001b\u0001}\t\tq\t\u0005\u0002<1\u0012)\u0011\f\u0001b\u0001}\t\t\u0001\n\u0005\u0002<7\u0012)A\f\u0001b\u0001}\t\t\u0011\n\u0005\u0002<=\u0012)q\f\u0001b\u0001}\t\t!\n\u0005\u0002<C\u0012)!\r\u0001b\u0001}\t\t1\n\u0005\u0002<I\u0012)Q\r\u0001b\u0001}\t\tA\n\u0005\u0002<O\u0012)\u0001\u000e\u0001b\u0001}\t\tQ\n\u0005\u0002<U\u0012)1\u000e\u0001b\u0001}\t\ta\n\u0005\u0002<[\u0012)a\u000e\u0001b\u0001}\t\tq\n\u0005\u0002<a\u0012)\u0011\u000f\u0001b\u0001}\t\t\u0001\u000b\u0005\u0002<g\u0012)A\u000f\u0001b\u0001}\t\t\u0011\u000b\u0005\u0002<m\u0012)q\u000f\u0001b\u0001}\t\t!\u000b\u0005\u0002<s\u0012)!\u0010\u0001b\u0001}\t\t1\u000b\u0005\u0002<y\u0012)Q\u0010\u0001b\u0001}\t\tA\u000b\u0005\u0002<\u007f\u00121\u0011\u0011\u0001\u0001C\u0002y\u0012\u0011!\u0016\t\u001a\u0003\u000b\t9AO#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8skb\\h0D\u0001\u001b\u0013\r\tIA\u0007\u0002\f\u000bF\u0004&o\u001c3vGR\u0014\u0014'\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003\u001f\u00012!IA\t\u0013\r\t\u0019B\t\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\"!!\u0007\u0011\u0007\u001d\"$(\u0001\u0006tiJ,8\r^;sKJ*\"!a\b\u0011\u0007\u001d\"T)\u0001\u0006tiJ,8\r^;sKN*\"!!\n\u0011\u0007\u001d\"\u0004*\u0001\u0006tiJ,8\r^;sKR*\"!a\u000b\u0011\u0007\u001d\"4*\u0001\u0006tiJ,8\r^;sKV*\"!!\r\u0011\u0007\u001d\"d*\u0001\u0006tiJ,8\r^;sKZ*\"!a\u000e\u0011\u0007\u001d\"\u0014+\u0001\u0006tiJ,8\r^;sK^*\"!!\u0010\u0011\u0007\u001d\"D+\u0001\u0006tiJ,8\r^;sKb*\"!a\u0011\u0011\u0007\u001d\"t+\u0001\u0006tiJ,8\r^;sKf*\"!!\u0013\u0011\u0007\u001d\"$,A\u0006tiJ,8\r^;sKF\u0002TCAA(!\r9C'X\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002VA\u0019q\u0005\u000e1\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u00037\u00022a\n\u001bd\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005\u0005\u0004cA\u00145M\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\t9\u0007E\u0002(i%\f1b\u001d;sk\u000e$XO]32kU\u0011\u0011Q\u000e\t\u0004OQb\u0017aC:ueV\u001cG/\u001e:fcY*\"!a\u001d\u0011\u0007\u001d\"t.A\u0006tiJ,8\r^;sKF:TCAA=!\r9CG]\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004(\u0006\u0002\u0002\u0000A\u0019q\u0005N;\u0002\u0017M$(/^2ukJ,\u0017'O\u000b\u0003\u0003\u000b\u00032a\n\u001by\u0003-\u0019HO];diV\u0014XM\r\u0019\u0016\u0005\u0005-\u0005cA\u00145w\u0006Y1\u000f\u001e:vGR,(/\u001a\u001a2+\t\t\t\nE\u0002(iy\fqaY8na\u0006\u0014X\r\u0006\u0004\u0002\u0018\u0006u\u0015\u0011\u0015\t\u0004C\u0005e\u0015bAANE\t\u0019\u0011J\u001c;\t\r\u0005}u\u00031\u00018\u0003\tA\b\u0007\u0003\u0004\u0002$^\u0001\raN\u0001\u0003qF\n1!Z9w)\u0019\tI+a,\u00022B\u0019\u0011%a+\n\u0007\u00055&EA\u0004C_>dW-\u00198\t\r\u0005}\u0005\u00041\u00018\u0011\u0019\t\u0019\u000b\u0007a\u0001o\u0001"
)
public interface OrderProduct21 extends Order, EqProduct21 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct21 $this, final Tuple21 x0, final Tuple21 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple21 x0, final Tuple21 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct21 $this, final Tuple21 x0, final Tuple21 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple21 x0, final Tuple21 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct21 $this) {
   }
}
