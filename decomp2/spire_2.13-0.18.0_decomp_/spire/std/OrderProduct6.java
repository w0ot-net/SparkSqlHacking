package spire.std;

import cats.kernel.Order;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006S\u0002!\tE\u001b\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r\u001e\u001c\u000b\u00051i\u0011aA:uI*\ta\"A\u0003ta&\u0014X-F\u0004\u0011[]RT\bQ\"\u0014\t\u0001\tr#\u0012\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007a)\u0003F\u0004\u0002\u001aE9\u0011!\u0004\t\b\u00037}i\u0011\u0001\b\u0006\u0003;y\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u001d%\u0011\u0011%D\u0001\bC2<WM\u0019:b\u0013\t\u0019C%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0005j\u0011B\u0001\u0014(\u0005\u0015y%\u000fZ3s\u0015\t\u0019C\u0005\u0005\u0005\u0013S-2\u0014\bP C\u0013\tQ3C\u0001\u0004UkBdWM\u000e\t\u0003Y5b\u0001\u0001B\u0003/\u0001\t\u0007qFA\u0001B#\t\u00014\u0007\u0005\u0002\u0013c%\u0011!g\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011B'\u0003\u00026'\t\u0019\u0011I\\=\u0011\u00051:D!\u0002\u001d\u0001\u0005\u0004y#!\u0001\"\u0011\u00051RD!B\u001e\u0001\u0005\u0004y#!A\"\u0011\u00051jD!\u0002 \u0001\u0005\u0004y#!\u0001#\u0011\u00051\u0002E!B!\u0001\u0005\u0004y#!A#\u0011\u00051\u001aE!\u0002#\u0001\u0005\u0004y#!\u0001$\u0011\u0011\u0019;5FN\u001d=\u007f\tk\u0011aC\u0005\u0003\u0011.\u0011!\"R9Qe>$Wo\u0019;7\u0003\u0019!\u0013N\\5uIQ\t1\n\u0005\u0002\u0013\u0019&\u0011Qj\u0005\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012\u0001\u0015\t\u00041\u0015Z\u0013AC:ueV\u001cG/\u001e:feU\t1\u000bE\u0002\u0019KY\n!b\u001d;sk\u000e$XO]34+\u00051\u0006c\u0001\r&s\u0005Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0003e\u00032\u0001G\u0013=\u0003)\u0019HO];diV\u0014X-N\u000b\u00029B\u0019\u0001$J \u0002\u0015M$(/^2ukJ,g'F\u0001`!\rAREQ\u0001\bG>l\u0007/\u0019:f)\r\u0011Wm\u001a\t\u0003%\rL!\u0001Z\n\u0003\u0007%sG\u000fC\u0003g\u0011\u0001\u0007\u0001&\u0001\u0002ya!)\u0001\u000e\u0003a\u0001Q\u0005\u0011\u00010M\u0001\u0004KF4HcA6o_B\u0011!\u0003\\\u0005\u0003[N\u0011qAQ8pY\u0016\fg\u000eC\u0003g\u0013\u0001\u0007\u0001\u0006C\u0003i\u0013\u0001\u0007\u0001\u0006"
)
public interface OrderProduct6 extends Order, EqProduct6 {
   Order structure1();

   Order structure2();

   Order structure3();

   Order structure4();

   Order structure5();

   Order structure6();

   // $FF: synthetic method
   static int compare$(final OrderProduct6 $this, final Tuple6 x0, final Tuple6 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple6 x0, final Tuple6 x1) {
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
                     var10000 = cmp != 0 ? cmp : 0;
                  }
               }
            }
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct6 $this, final Tuple6 x0, final Tuple6 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple6 x0, final Tuple6 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct6 $this) {
   }
}
