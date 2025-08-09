package spire.std;

import algebra.ring.Ring;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u00021\u0019a\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u0002!\tE\u0017\u0005\u0006A\u0002!\t!\u0019\u0002\r%&tw\r\u0015:pIV\u001cG/\u000e\u0006\u0003\u00171\t1a\u001d;e\u0015\u0005i\u0011!B:qSJ,WCB\b-mebth\u0005\u0003\u0001!Y\t\u0005CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\rE\u0002\u0018I\u001dr!\u0001G\u0011\u000f\u0005eybB\u0001\u000e\u001f\u001b\u0005Y\"B\u0001\u000f\u001e\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0007\n\u0005\u0001b\u0011aB1mO\u0016\u0014'/Y\u0005\u0003E\r\nq\u0001]1dW\u0006<WM\u0003\u0002!\u0019%\u0011QE\n\u0002\u0005%&twM\u0003\u0002#GA9\u0011\u0003\u000b\u00166qmr\u0014BA\u0015\u0013\u0005\u0019!V\u000f\u001d7fkA\u00111\u0006\f\u0007\u0001\t\u0015i\u0003A1\u0001/\u0005\u0005\t\u0015CA\u00183!\t\t\u0002'\u0003\u00022%\t9aj\u001c;iS:<\u0007CA\t4\u0013\t!$CA\u0002B]f\u0004\"a\u000b\u001c\u0005\u000b]\u0002!\u0019\u0001\u0018\u0003\u0003\t\u0003\"aK\u001d\u0005\u000bi\u0002!\u0019\u0001\u0018\u0003\u0003\r\u0003\"a\u000b\u001f\u0005\u000bu\u0002!\u0019\u0001\u0018\u0003\u0003\u0011\u0003\"aK \u0005\u000b\u0001\u0003!\u0019\u0001\u0018\u0003\u0003\u0015\u0003rAQ\"+kaZd(D\u0001\u000b\u0013\t!%BA\u0006S]\u001e\u0004&o\u001c3vGR,\u0014A\u0002\u0013j]&$H\u0005F\u0001H!\t\t\u0002*\u0003\u0002J%\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002\u0019B\u0019q\u0003\n\u0016\u0002\u0015M$(/^2ukJ,''F\u0001P!\r9B%N\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u0001*\u0011\u0007]!\u0003(\u0001\u0006tiJ,8\r^;sKR*\u0012!\u0016\t\u0004/\u0011Z\u0014AC:ueV\u001cG/\u001e:fkU\t\u0001\fE\u0002\u0018Iy\nqA\u001a:p[&sG\u000f\u0006\u0002(7\")Al\u0002a\u0001;\u0006\u0011\u0001\u0010\r\t\u0003#yK!a\u0018\n\u0003\u0007%sG/A\u0002p]\u0016,\u0012a\n"
)
public interface RingProduct5 extends Ring, RngProduct5 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   // $FF: synthetic method
   static Tuple5 fromInt$(final RingProduct5 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple5 fromInt(final int x0) {
      return new Tuple5(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple5 one$(final RingProduct5 $this) {
      return $this.one();
   }

   default Tuple5 one() {
      return new Tuple5(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one());
   }

   static void $init$(final RingProduct5 $this) {
   }
}
