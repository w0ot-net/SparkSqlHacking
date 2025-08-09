package spire.std;

import cats.kernel.Order;
import scala.Tuple10;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001\u0003\b\u0010!\u0003\r\t!E\n\t\u000be\u0003A\u0011\u0001.\t\u000by\u0003a1A0\t\u000b\u0005\u0004a1\u00012\t\u000b\u0011\u0004a1A3\t\u000b\u001d\u0004a1\u00015\t\u000b)\u0004a1A6\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004A\u0011A?\t\u000f\u0005-\u0001\u0001\"\u0011\u0002\u000e\tqqJ\u001d3feB\u0013x\u000eZ;diF\u0002$B\u0001\t\u0012\u0003\r\u0019H\u000f\u001a\u0006\u0002%\u0005)1\u000f]5sKVYA#M\u001e?\u0003\u0012;%*\u0014)T'\u0011\u0001QcG+\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g!\ra\u0012\u0006\f\b\u0003;\u0019r!A\b\u0013\u000f\u0005}\u0019S\"\u0001\u0011\u000b\u0005\u0005\u0012\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003II!!J\t\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0005K\u0001\ba\u0006\u001c7.Y4f\u0015\t)\u0013#\u0003\u0002+W\t)qJ\u001d3fe*\u0011q\u0005\u000b\t\r-5z#(\u0010!D\r&cuJU\u0005\u0003]]\u0011q\u0001V;qY\u0016\f\u0004\u0007\u0005\u00021c1\u0001A!\u0002\u001a\u0001\u0005\u0004\u0019$!A!\u0012\u0005Q:\u0004C\u0001\f6\u0013\t1tCA\u0004O_RD\u0017N\\4\u0011\u0005YA\u0014BA\u001d\u0018\u0005\r\te.\u001f\t\u0003am\"Q\u0001\u0010\u0001C\u0002M\u0012\u0011A\u0011\t\u0003ay\"Qa\u0010\u0001C\u0002M\u0012\u0011a\u0011\t\u0003a\u0005#QA\u0011\u0001C\u0002M\u0012\u0011\u0001\u0012\t\u0003a\u0011#Q!\u0012\u0001C\u0002M\u0012\u0011!\u0012\t\u0003a\u001d#Q\u0001\u0013\u0001C\u0002M\u0012\u0011A\u0012\t\u0003a)#Qa\u0013\u0001C\u0002M\u0012\u0011a\u0012\t\u0003a5#QA\u0014\u0001C\u0002M\u0012\u0011\u0001\u0013\t\u0003aA#Q!\u0015\u0001C\u0002M\u0012\u0011!\u0013\t\u0003aM#Q\u0001\u0016\u0001C\u0002M\u0012\u0011A\u0013\t\r-^{#(\u0010!D\r&cuJU\u0007\u0002\u001f%\u0011\u0001l\u0004\u0002\f\u000bF\u0004&o\u001c3vGR\f\u0004'\u0001\u0004%S:LG\u000f\n\u000b\u00027B\u0011a\u0003X\u0005\u0003;^\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\u0001\u00042\u0001H\u00150\u0003)\u0019HO];diV\u0014XMM\u000b\u0002GB\u0019A$\u000b\u001e\u0002\u0015M$(/^2ukJ,7'F\u0001g!\ra\u0012&P\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A5\u0011\u0007qI\u0003)\u0001\u0006tiJ,8\r^;sKV*\u0012\u0001\u001c\t\u00049%\u001a\u0015AC:ueV\u001cG/\u001e:fmU\tq\u000eE\u0002\u001dS\u0019\u000b!b\u001d;sk\u000e$XO]38+\u0005\u0011\bc\u0001\u000f*\u0013\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003U\u00042\u0001H\u0015M\u0003)\u0019HO];diV\u0014X-O\u000b\u0002qB\u0019A$K(\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0002wB\u0019A$\u000b*\u0002\u000f\r|W\u000e]1sKR)a0a\u0001\u0002\bA\u0011ac`\u0005\u0004\u0003\u00039\"aA%oi\"1\u0011Q\u0001\u0007A\u00021\n!\u0001\u001f\u0019\t\r\u0005%A\u00021\u0001-\u0003\tA\u0018'A\u0002fcZ$b!a\u0004\u0002\u0016\u0005]\u0001c\u0001\f\u0002\u0012%\u0019\u00111C\f\u0003\u000f\t{w\u000e\\3b]\"1\u0011QA\u0007A\u00021Ba!!\u0003\u000e\u0001\u0004a\u0003"
)
public interface OrderProduct10 extends Order, EqProduct10 {
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

   // $FF: synthetic method
   static int compare$(final OrderProduct10 $this, final Tuple10 x0, final Tuple10 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple10 x0, final Tuple10 x1) {
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

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct10 $this, final Tuple10 x0, final Tuple10 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple10 x0, final Tuple10 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct10 $this) {
   }
}
