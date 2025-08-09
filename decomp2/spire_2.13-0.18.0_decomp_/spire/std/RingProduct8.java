package spire.std;

import algebra.ring.Ring;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006#\u0002!\tA\u0015\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u00021\u0019A\u001a\u0005\u0006Q\u00021\u0019!\u001b\u0005\u0006W\u00021\u0019\u0001\u001c\u0005\u0006]\u0002!\te\u001c\u0005\u0006k\u0002!\tA\u001e\u0002\r%&tw\r\u0015:pIV\u001cG\u000f\u000f\u0006\u0003\u001d=\t1a\u001d;e\u0015\u0005\u0001\u0012!B:qSJ,W#\u0003\n0sqz$)\u0012%L'\u0011\u00011#G'\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\rQrE\u000b\b\u00037\u0011r!\u0001\b\u0012\u000f\u0005u\tS\"\u0001\u0010\u000b\u0005}\u0001\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003AI!aI\b\u0002\u000f\u0005dw-\u001a2sC&\u0011QEJ\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0019s\"\u0003\u0002)S\t!!+\u001b8h\u0015\t)c\u0005\u0005\u0006\u0015W5B4HP!E\u000f*K!\u0001L\u000b\u0003\rQ+\b\u000f\\39!\tqs\u0006\u0004\u0001\u0005\u000bA\u0002!\u0019A\u0019\u0003\u0003\u0005\u000b\"AM\u001b\u0011\u0005Q\u0019\u0014B\u0001\u001b\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\u001c\n\u0005]*\"aA!osB\u0011a&\u000f\u0003\u0006u\u0001\u0011\r!\r\u0002\u0002\u0005B\u0011a\u0006\u0010\u0003\u0006{\u0001\u0011\r!\r\u0002\u0002\u0007B\u0011af\u0010\u0003\u0006\u0001\u0002\u0011\r!\r\u0002\u0002\tB\u0011aF\u0011\u0003\u0006\u0007\u0002\u0011\r!\r\u0002\u0002\u000bB\u0011a&\u0012\u0003\u0006\r\u0002\u0011\r!\r\u0002\u0002\rB\u0011a\u0006\u0013\u0003\u0006\u0013\u0002\u0011\r!\r\u0002\u0002\u000fB\u0011af\u0013\u0003\u0006\u0019\u0002\u0011\r!\r\u0002\u0002\u0011BQajT\u00179wy\nEi\u0012&\u000e\u00035I!\u0001U\u0007\u0003\u0017Isw\r\u0015:pIV\u001cG\u000fO\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0003\"\u0001\u0006+\n\u0005U+\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005A\u0006c\u0001\u000e([\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003m\u00032AG\u00149\u0003)\u0019HO];diV\u0014XmM\u000b\u0002=B\u0019!dJ\u001e\u0002\u0015M$(/^2ukJ,G'F\u0001b!\rQrEP\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#\u00013\u0011\u0007i9\u0013)\u0001\u0006tiJ,8\r^;sKZ*\u0012a\u001a\t\u00045\u001d\"\u0015AC:ueV\u001cG/\u001e:foU\t!\u000eE\u0002\u001bO\u001d\u000b!b\u001d;sk\u000e$XO]39+\u0005i\u0007c\u0001\u000e(\u0015\u00069aM]8n\u0013:$HC\u0001\u0016q\u0011\u0015\t(\u00021\u0001s\u0003\tA\b\u0007\u0005\u0002\u0015g&\u0011A/\u0006\u0002\u0004\u0013:$\u0018aA8oKV\t!\u0006"
)
public interface RingProduct8 extends Ring, RngProduct8 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   Ring structure8();

   // $FF: synthetic method
   static Tuple8 fromInt$(final RingProduct8 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple8 fromInt(final int x0) {
      return new Tuple8(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple8 one$(final RingProduct8 $this) {
      return $this.one();
   }

   default Tuple8 one() {
      return new Tuple8(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one());
   }

   static void $init$(final RingProduct8 $this) {
   }
}
