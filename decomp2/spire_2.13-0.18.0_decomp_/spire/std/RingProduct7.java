package spire.std;

import algebra.ring.Ring;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006\u001b\u0002!\tA\u0014\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u00021\u0019a\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u0002!\t\u0005\u001b\u0005\u0006]\u0002!\ta\u001c\u0002\r%&tw\r\u0015:pIV\u001cGo\u000e\u0006\u0003\u001b9\t1a\u001d;e\u0015\u0005y\u0011!B:qSJ,W\u0003C\t/qmr\u0014\tR$\u0014\t\u0001\u0011\u0002$\u0013\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007e1\u0013F\u0004\u0002\u001bG9\u00111$\t\b\u00039\u0001j\u0011!\b\u0006\u0003=}\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u001f%\u0011!ED\u0001\bC2<WM\u0019:b\u0013\t!S%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\tr\u0011BA\u0014)\u0005\u0011\u0011\u0016N\\4\u000b\u0005\u0011*\u0003#C\n+Y]RT\bQ\"G\u0013\tYCC\u0001\u0004UkBdWm\u000e\t\u0003[9b\u0001\u0001B\u00030\u0001\t\u0007\u0001GA\u0001B#\t\tD\u0007\u0005\u0002\u0014e%\u00111\u0007\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u0019R'\u0003\u00027)\t\u0019\u0011I\\=\u0011\u00055BD!B\u001d\u0001\u0005\u0004\u0001$!\u0001\"\u0011\u00055ZD!\u0002\u001f\u0001\u0005\u0004\u0001$!A\"\u0011\u00055rD!B \u0001\u0005\u0004\u0001$!\u0001#\u0011\u00055\nE!\u0002\"\u0001\u0005\u0004\u0001$!A#\u0011\u00055\"E!B#\u0001\u0005\u0004\u0001$!\u0001$\u0011\u00055:E!\u0002%\u0001\u0005\u0004\u0001$!A$\u0011\u0013)[Ef\u000e\u001e>\u0001\u000e3U\"\u0001\u0007\n\u00051c!a\u0003*oOB\u0013x\u000eZ;di^\na\u0001J5oSR$C#A(\u0011\u0005M\u0001\u0016BA)\u0015\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001U!\rIb\u0005L\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#A,\u0011\u0007e1s'\u0001\u0006tiJ,8\r^;sKN*\u0012A\u0017\t\u00043\u0019R\u0014AC:ueV\u001cG/\u001e:fiU\tQ\fE\u0002\u001aMu\n!b\u001d;sk\u000e$XO]36+\u0005\u0001\u0007cA\r'\u0001\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0003\r\u00042!\u0007\u0014D\u0003)\u0019HO];diV\u0014XmN\u000b\u0002MB\u0019\u0011D\n$\u0002\u000f\u0019\u0014x.\\%oiR\u0011\u0011&\u001b\u0005\u0006U&\u0001\ra[\u0001\u0003qB\u0002\"a\u00057\n\u00055$\"aA%oi\u0006\u0019qN\\3\u0016\u0003%\u0002"
)
public interface RingProduct7 extends Ring, RngProduct7 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   // $FF: synthetic method
   static Tuple7 fromInt$(final RingProduct7 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple7 fromInt(final int x0) {
      return new Tuple7(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple7 one$(final RingProduct7 $this) {
      return $this.one();
   }

   default Tuple7 one() {
      return new Tuple7(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one());
   }

   static void $init$(final RingProduct7 $this) {
   }
}
