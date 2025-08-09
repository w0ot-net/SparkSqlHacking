package spire.std;

import cats.kernel.Group;
import scala.Tuple10;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0001\"\u0004\b\u0011\u0002\u0007\u0005\u0001C\u0005\u0005\u00061\u0002!\t!\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u00021\u0019a\u001a\u0005\u0006S\u00021\u0019A\u001b\u0005\u0006Y\u00021\u0019!\u001c\u0005\u0006_\u00021\u0019\u0001\u001d\u0005\u0006e\u00021\u0019a\u001d\u0005\u0006k\u00021\u0019A\u001e\u0005\u0006q\u00021\u0019!\u001f\u0005\u0006w\u0002!\t\u0001 \u0002\u000f\u000fJ|W\u000f\u001d)s_\u0012,8\r^\u00191\u0015\ty\u0001#A\u0002ti\u0012T\u0011!E\u0001\u0006gBL'/Z\u000b\f'ART\bQ\"G\u00132{%k\u0005\u0003\u0001)i!\u0006CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\rE\u0002\u001cQ-r!\u0001H\u0013\u000f\u0005u\u0019cB\u0001\u0010#\u001b\u0005y\"B\u0001\u0011\"\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\t\n\u0005\u0011\u0002\u0012aB1mO\u0016\u0014'/Y\u0005\u0003M\u001d\nq\u0001]1dW\u0006<WM\u0003\u0002%!%\u0011\u0011F\u000b\u0002\u0006\u000fJ|W\u000f\u001d\u0006\u0003M\u001d\u0002B\"\u0006\u0017/sqz$)\u0012%L\u001dFK!!\f\f\u0003\u000fQ+\b\u000f\\32aA\u0011q\u0006\r\u0007\u0001\t\u0015\t\u0004A1\u00013\u0005\u0005\t\u0015CA\u001a7!\t)B'\u0003\u00026-\t9aj\u001c;iS:<\u0007CA\u000b8\u0013\tAdCA\u0002B]f\u0004\"a\f\u001e\u0005\u000bm\u0002!\u0019\u0001\u001a\u0003\u0003\t\u0003\"aL\u001f\u0005\u000by\u0002!\u0019\u0001\u001a\u0003\u0003\r\u0003\"a\f!\u0005\u000b\u0005\u0003!\u0019\u0001\u001a\u0003\u0003\u0011\u0003\"aL\"\u0005\u000b\u0011\u0003!\u0019\u0001\u001a\u0003\u0003\u0015\u0003\"a\f$\u0005\u000b\u001d\u0003!\u0019\u0001\u001a\u0003\u0003\u0019\u0003\"aL%\u0005\u000b)\u0003!\u0019\u0001\u001a\u0003\u0003\u001d\u0003\"a\f'\u0005\u000b5\u0003!\u0019\u0001\u001a\u0003\u0003!\u0003\"aL(\u0005\u000bA\u0003!\u0019\u0001\u001a\u0003\u0003%\u0003\"a\f*\u0005\u000bM\u0003!\u0019\u0001\u001a\u0003\u0003)\u0003B\"\u0016,/sqz$)\u0012%L\u001dFk\u0011AD\u0005\u0003/:\u0011q\"T8o_&$\u0007K]8ek\u000e$\u0018\u0007M\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0003\"!F.\n\u0005q3\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005y\u0006cA\u000e)]\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003\t\u00042a\u0007\u0015:\u0003)\u0019HO];diV\u0014XmM\u000b\u0002KB\u00191\u0004\u000b\u001f\u0002\u0015M$(/^2ukJ,G'F\u0001i!\rY\u0002fP\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A6\u0011\u0007mA#)\u0001\u0006tiJ,8\r^;sKZ*\u0012A\u001c\t\u00047!*\u0015AC:ueV\u001cG/\u001e:foU\t\u0011\u000fE\u0002\u001cQ!\u000b!b\u001d;sk\u000e$XO]39+\u0005!\bcA\u000e)\u0017\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0003]\u00042a\u0007\u0015O\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0003i\u00042a\u0007\u0015R\u0003\u001dIgN^3sg\u0016$\"aK?\t\u000byd\u0001\u0019A\u0016\u0002\u0005a\u0004\u0004"
)
public interface GroupProduct10 extends Group, MonoidProduct10 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   Group structure10();

   // $FF: synthetic method
   static Tuple10 inverse$(final GroupProduct10 $this, final Tuple10 x0) {
      return $this.inverse(x0);
   }

   default Tuple10 inverse(final Tuple10 x0) {
      return new Tuple10(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()));
   }

   static void $init$(final GroupProduct10 $this) {
   }
}
