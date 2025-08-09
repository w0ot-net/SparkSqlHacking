package spire.std;

import cats.kernel.Eq;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u00021\u0019A\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u0002!\t\u0001\u0018\u0002\u000b\u000bF\u0004&o\u001c3vGR4$BA\u0006\r\u0003\r\u0019H\u000f\u001a\u0006\u0002\u001b\u0005)1\u000f]5sKV9q\u0002\f\u001c:y}\u00125c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u00042a\u0006\u0013(\u001d\tA\u0012E\u0004\u0002\u001a?9\u0011!DH\u0007\u00027)\u0011A$H\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ\"\u0003\u0002!\u0019\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0012$\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\t\u0007\n\u0005\u00152#AA#r\u0015\t\u00113\u0005\u0005\u0005\u0012Q)*\u0004h\u000f B\u0013\tI#C\u0001\u0004UkBdWM\u000e\t\u0003W1b\u0001\u0001B\u0003.\u0001\t\u0007aFA\u0001B#\ty#\u0007\u0005\u0002\u0012a%\u0011\u0011G\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t2'\u0003\u00025%\t\u0019\u0011I\\=\u0011\u0005-2D!B\u001c\u0001\u0005\u0004q#!\u0001\"\u0011\u0005-JD!\u0002\u001e\u0001\u0005\u0004q#!A\"\u0011\u0005-bD!B\u001f\u0001\u0005\u0004q#!\u0001#\u0011\u0005-zD!\u0002!\u0001\u0005\u0004q#!A#\u0011\u0005-\u0012E!B\"\u0001\u0005\u0004q#!\u0001$\u0002\r\u0011Jg.\u001b;%)\u00051\u0005CA\tH\u0013\tA%C\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\nE\u0002\u0018I)\n!b\u001d;sk\u000e$XO]33+\u0005q\u0005cA\f%k\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003E\u00032a\u0006\u00139\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002)B\u0019q\u0003J\u001e\u0002\u0015M$(/^2ukJ,W'F\u0001X!\r9BEP\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#\u0001.\u0011\u0007]!\u0013)A\u0002fcZ$2!\u00181c!\t\tb,\u0003\u0002`%\t9!i\\8mK\u0006t\u0007\"B1\t\u0001\u00049\u0013A\u0001=1\u0011\u0015\u0019\u0007\u00021\u0001(\u0003\tA\u0018\u0007"
)
public interface EqProduct6 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   Eq structure4();

   Eq structure5();

   Eq structure6();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct6 $this, final Tuple6 x0, final Tuple6 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple6 x0, final Tuple6 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4()) && this.structure5().eqv(x0._5(), x1._5()) && this.structure6().eqv(x0._6(), x1._6());
   }

   static void $init$(final EqProduct6 $this) {
   }
}
