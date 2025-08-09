package spire.std;

import cats.kernel.Order;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000be\u0004A\u0011\u0001>\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003Ab\u0001\u0002Z!9\u0011Q\f\u0001\u0007\u0004\u0005}\u0003bBA2\u0001\u0019\r\u0011Q\r\u0005\b\u0003S\u0002A\u0011AA6\u0011\u001d\tY\b\u0001C!\u0003{\u0012ab\u0014:eKJ\u0004&o\u001c3vGR\f\u0004H\u0003\u0002\u00193\u0005\u00191\u000f\u001e3\u000b\u0003i\tQa\u001d9je\u0016,2\u0003H\u001dD\r&cuJU+Y7z\u000bGm\u001a6naN\u001cB\u0001A\u000f$kB\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\u00042\u0001J\u00195\u001d\t)cF\u0004\u0002'Y9\u0011qeK\u0007\u0002Q)\u0011\u0011FK\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t!$\u0003\u0002.3\u00059\u0011\r\\4fEJ\f\u0017BA\u00181\u0003\u001d\u0001\u0018mY6bO\u0016T!!L\r\n\u0005I\u001a$!B(sI\u0016\u0014(BA\u00181!QqRg\u000e\"F\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7pe&\u0011ag\b\u0002\b)V\u0004H.Z\u00199!\tA\u0014\b\u0004\u0001\u0005\u000bi\u0002!\u0019A\u001e\u0003\u0003\u0005\u000b\"\u0001P \u0011\u0005yi\u0014B\u0001  \u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\b!\n\u0005\u0005{\"aA!osB\u0011\u0001h\u0011\u0003\u0006\t\u0002\u0011\ra\u000f\u0002\u0002\u0005B\u0011\u0001H\u0012\u0003\u0006\u000f\u0002\u0011\ra\u000f\u0002\u0002\u0007B\u0011\u0001(\u0013\u0003\u0006\u0015\u0002\u0011\ra\u000f\u0002\u0002\tB\u0011\u0001\b\u0014\u0003\u0006\u001b\u0002\u0011\ra\u000f\u0002\u0002\u000bB\u0011\u0001h\u0014\u0003\u0006!\u0002\u0011\ra\u000f\u0002\u0002\rB\u0011\u0001H\u0015\u0003\u0006'\u0002\u0011\ra\u000f\u0002\u0002\u000fB\u0011\u0001(\u0016\u0003\u0006-\u0002\u0011\ra\u000f\u0002\u0002\u0011B\u0011\u0001\b\u0017\u0003\u00063\u0002\u0011\ra\u000f\u0002\u0002\u0013B\u0011\u0001h\u0017\u0003\u00069\u0002\u0011\ra\u000f\u0002\u0002\u0015B\u0011\u0001H\u0018\u0003\u0006?\u0002\u0011\ra\u000f\u0002\u0002\u0017B\u0011\u0001(\u0019\u0003\u0006E\u0002\u0011\ra\u000f\u0002\u0002\u0019B\u0011\u0001\b\u001a\u0003\u0006K\u0002\u0011\ra\u000f\u0002\u0002\u001bB\u0011\u0001h\u001a\u0003\u0006Q\u0002\u0011\ra\u000f\u0002\u0002\u001dB\u0011\u0001H\u001b\u0003\u0006W\u0002\u0011\ra\u000f\u0002\u0002\u001fB\u0011\u0001(\u001c\u0003\u0006]\u0002\u0011\ra\u000f\u0002\u0002!B\u0011\u0001\b\u001d\u0003\u0006c\u0002\u0011\ra\u000f\u0002\u0002#B\u0011\u0001h\u001d\u0003\u0006i\u0002\u0011\ra\u000f\u0002\u0002%B!bo^\u001cC\u000b\"[e*\u0015+X5v\u00037MZ5m_Jl\u0011aF\u0005\u0003q^\u00111\"R9Qe>$Wo\u0019;2q\u00051A%\u001b8ji\u0012\"\u0012a\u001f\t\u0003=qL!!`\u0010\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fTCAA\u0001!\r!\u0013gN\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014TCAA\u0004!\r!\u0013GQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cTCAA\u0007!\r!\u0013'R\u0001\u000bgR\u0014Xo\u0019;ve\u0016$TCAA\n!\r!\u0013\u0007S\u0001\u000bgR\u0014Xo\u0019;ve\u0016,TCAA\r!\r!\u0013gS\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u0010!\r!\u0013GT\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u0013!\r!\u0013'U\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\u0016!\r!\u0013\u0007V\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\u0019!\r!\u0013gV\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u00028A\u0019A%\r.\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003{\u00012\u0001J\u0019^\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005\r\u0003c\u0001\u00132A\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\tI\u0005E\u0002%c\r\f1b\u001d;sk\u000e$XO]32iU\u0011\u0011q\n\t\u0004IE2\u0017aC:ueV\u001cG/\u001e:fcU*\"!!\u0016\u0011\u0007\u0011\n\u0014.A\u0006tiJ,8\r^;sKF2TCAA.!\r!\u0013\u0007\\\u0001\fgR\u0014Xo\u0019;ve\u0016\ft'\u0006\u0002\u0002bA\u0019A%M8\u0002\u0017M$(/^2ukJ,\u0017\u0007O\u000b\u0003\u0003O\u00022\u0001J\u0019s\u0003\u001d\u0019w.\u001c9be\u0016$b!!\u001c\u0002t\u0005]\u0004c\u0001\u0010\u0002p%\u0019\u0011\u0011O\u0010\u0003\u0007%sG\u000f\u0003\u0004\u0002vQ\u0001\r\u0001N\u0001\u0003qBBa!!\u001f\u0015\u0001\u0004!\u0014A\u0001=2\u0003\r)\u0017O\u001e\u000b\u0007\u0003\u007f\n))a\"\u0011\u0007y\t\t)C\u0002\u0002\u0004~\u0011qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002vU\u0001\r\u0001\u000e\u0005\u0007\u0003s*\u0002\u0019\u0001\u001b"
)
public interface OrderProduct18 extends Order, EqProduct18 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct18 $this, final Tuple18 x0, final Tuple18 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple18 x0, final Tuple18 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct18 $this, final Tuple18 x0, final Tuple18 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple18 x0, final Tuple18 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct18 $this) {
   }
}
