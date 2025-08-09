package spire.std;

import cats.kernel.Group;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r\u001e\u001c\u000b\u0005-a\u0011aA:uI*\tQ\"A\u0003ta&\u0014X-F\u0004\u0010YYJDh\u0010\"\u0014\t\u0001\u0001b\u0003\u0012\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]!sE\u0004\u0002\u0019C9\u0011\u0011d\b\b\u00035yi\u0011a\u0007\u0006\u00039u\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u001b%\u0011\u0001\u0005D\u0001\bC2<WM\u0019:b\u0013\t\u00113%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0001b\u0011BA\u0013'\u0005\u00159%o\\;q\u0015\t\u00113\u0005\u0005\u0005\u0012Q)*\u0004h\u000f B\u0013\tI#C\u0001\u0004UkBdWM\u000e\t\u0003W1b\u0001\u0001B\u0003.\u0001\t\u0007aFA\u0001B#\ty#\u0007\u0005\u0002\u0012a%\u0011\u0011G\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t2'\u0003\u00025%\t\u0019\u0011I\\=\u0011\u0005-2D!B\u001c\u0001\u0005\u0004q#!\u0001\"\u0011\u0005-JD!\u0002\u001e\u0001\u0005\u0004q#!A\"\u0011\u0005-bD!B\u001f\u0001\u0005\u0004q#!\u0001#\u0011\u0005-zD!\u0002!\u0001\u0005\u0004q#!A#\u0011\u0005-\u0012E!B\"\u0001\u0005\u0004q#!\u0001$\u0011\u0011\u00153%&\u000e\u001d<}\u0005k\u0011AC\u0005\u0003\u000f*\u0011a\"T8o_&$\u0007K]8ek\u000e$h'\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0015B\u0011\u0011cS\u0005\u0003\u0019J\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003=\u00032a\u0006\u0013+\u0003)\u0019HO];diV\u0014XMM\u000b\u0002%B\u0019q\u0003J\u001b\u0002\u0015M$(/^2ukJ,7'F\u0001V!\r9B\u0005O\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001-\u0011\u0007]!3(\u0001\u0006tiJ,8\r^;sKV*\u0012a\u0017\t\u0004/\u0011r\u0014AC:ueV\u001cG/\u001e:fmU\ta\fE\u0002\u0018I\u0005\u000bq!\u001b8wKJ\u001cX\r\u0006\u0002(C\")!\r\u0003a\u0001O\u0005\u0011\u0001\u0010\r"
)
public interface GroupProduct6 extends Group, MonoidProduct6 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   // $FF: synthetic method
   static Tuple6 inverse$(final GroupProduct6 $this, final Tuple6 x0) {
      return $this.inverse(x0);
   }

   default Tuple6 inverse(final Tuple6 x0) {
      return new Tuple6(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()));
   }

   static void $init$(final GroupProduct6 $this) {
   }
}
