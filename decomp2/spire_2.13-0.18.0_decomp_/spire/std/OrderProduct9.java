package spire.std;

import cats.kernel.Order;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-a\u0001C\u0007\u000f!\u0003\r\t\u0001\u0005\n\t\u000bU\u0003A\u0011\u0001,\t\u000bi\u0003a1A.\t\u000bu\u0003a1\u00010\t\u000b\u0001\u0004a1A1\t\u000b\r\u0004a1\u00013\t\u000b\u0019\u0004a1A4\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004A\u0011\u0001<\t\u000by\u0004A\u0011I@\u0003\u001b=\u0013H-\u001a:Qe>$Wo\u0019;:\u0015\ty\u0001#A\u0002ti\u0012T\u0011!E\u0001\u0006gBL'/Z\u000b\u000b'ART\bQ\"G\u00132{5\u0003\u0002\u0001\u00155E\u0003\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0007cA\u000e)W9\u0011A$\n\b\u0003;\rr!A\b\u0012\u000e\u0003}Q!\u0001I\u0011\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!E\u0005\u0003IA\tq!\u00197hK\n\u0014\u0018-\u0003\u0002'O\u00059\u0001/Y2lC\u001e,'B\u0001\u0013\u0011\u0013\tI#FA\u0003Pe\u0012,'O\u0003\u0002'OAYQ\u0003\f\u0018:y}\u0012U\tS&O\u0013\ticC\u0001\u0004UkBdW-\u000f\t\u0003_Ab\u0001\u0001B\u00032\u0001\t\u0007!GA\u0001B#\t\u0019d\u0007\u0005\u0002\u0016i%\u0011QG\u0006\u0002\b\u001d>$\b.\u001b8h!\t)r'\u0003\u00029-\t\u0019\u0011I\\=\u0011\u0005=RD!B\u001e\u0001\u0005\u0004\u0011$!\u0001\"\u0011\u0005=jD!\u0002 \u0001\u0005\u0004\u0011$!A\"\u0011\u0005=\u0002E!B!\u0001\u0005\u0004\u0011$!\u0001#\u0011\u0005=\u001aE!\u0002#\u0001\u0005\u0004\u0011$!A#\u0011\u0005=2E!B$\u0001\u0005\u0004\u0011$!\u0001$\u0011\u0005=JE!\u0002&\u0001\u0005\u0004\u0011$!A$\u0011\u0005=bE!B'\u0001\u0005\u0004\u0011$!\u0001%\u0011\u0005=zE!\u0002)\u0001\u0005\u0004\u0011$!A%\u0011\u0017I\u001bf&\u000f\u001f@\u0005\u0016C5JT\u0007\u0002\u001d%\u0011AK\u0004\u0002\u000b\u000bF\u0004&o\u001c3vGRL\u0014A\u0002\u0013j]&$H\u0005F\u0001X!\t)\u0002,\u0003\u0002Z-\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u00029B\u00191\u0004\u000b\u0018\u0002\u0015M$(/^2ukJ,''F\u0001`!\rY\u0002&O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u00012\u0011\u0007mAC(\u0001\u0006tiJ,8\r^;sKR*\u0012!\u001a\t\u00047!z\u0014AC:ueV\u001cG/\u001e:fkU\t\u0001\u000eE\u0002\u001cQ\t\u000b!b\u001d;sk\u000e$XO]37+\u0005Y\u0007cA\u000e)\u000b\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u00039\u00042a\u0007\u0015I\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002cB\u00191\u0004K&\u0002\u0015M$(/^2ukJ,\u0017(F\u0001u!\rY\u0002FT\u0001\bG>l\u0007/\u0019:f)\r9(\u0010 \t\u0003+aL!!\u001f\f\u0003\u0007%sG\u000fC\u0003|\u0017\u0001\u00071&\u0001\u0002ya!)Qp\u0003a\u0001W\u0005\u0011\u00010M\u0001\u0004KF4HCBA\u0001\u0003\u000f\tI\u0001E\u0002\u0016\u0003\u0007I1!!\u0002\u0017\u0005\u001d\u0011un\u001c7fC:DQa\u001f\u0007A\u0002-BQ! \u0007A\u0002-\u0002"
)
public interface OrderProduct9 extends Order, EqProduct9 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   Order structure5();

   Order structure6();

   Order structure7();

   Order structure8();

   Order structure9();

   // $FF: synthetic method
   static int compare$(final OrderProduct9 $this, final Tuple9 x0, final Tuple9 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple9 x0, final Tuple9 x1) {
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
                              var10000 = cmp != 0 ? cmp : 0;
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
   static boolean eqv$(final OrderProduct9 $this, final Tuple9 x0, final Tuple9 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple9 x0, final Tuple9 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct9 $this) {
   }
}
