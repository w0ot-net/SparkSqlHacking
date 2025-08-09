package spire.std;

import algebra.ring.Ring;
import scala.Tuple11;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001C\b\u0011!\u0003\r\tA\u0005\u000b\t\u000bu\u0003A\u0011\u00010\t\u000b\t\u0004a1A2\t\u000b\u0015\u0004a1\u00014\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0005B\u0005%\u0001bBA\u000b\u0001\u0011\u0005\u0011q\u0003\u0002\u000e%&tw\r\u0015:pIV\u001cG/M\u0019\u000b\u0005E\u0011\u0012aA:uI*\t1#A\u0003ta&\u0014X-\u0006\u0007\u0016eqz$)\u0012%L\u001dF#vk\u0005\u0003\u0001-qI\u0006CA\f\u001b\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"AB!osJ+g\rE\u0002\u001eU5r!AH\u0014\u000f\u0005})cB\u0001\u0011%\u001b\u0005\t#B\u0001\u0012$\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\n\n\u0005\u0019\u0012\u0012aB1mO\u0016\u0014'/Y\u0005\u0003Q%\nq\u0001]1dW\u0006<WM\u0003\u0002'%%\u00111\u0006\f\u0002\u0005%&twM\u0003\u0002)SAiqC\f\u0019<}\u0005#uIS'Q'ZK!a\f\r\u0003\u000fQ+\b\u000f\\32cA\u0011\u0011G\r\u0007\u0001\t\u0015\u0019\u0004A1\u00015\u0005\u0005\t\u0015CA\u001b9!\t9b'\u0003\u000281\t9aj\u001c;iS:<\u0007CA\f:\u0013\tQ\u0004DA\u0002B]f\u0004\"!\r\u001f\u0005\u000bu\u0002!\u0019\u0001\u001b\u0003\u0003\t\u0003\"!M \u0005\u000b\u0001\u0003!\u0019\u0001\u001b\u0003\u0003\r\u0003\"!\r\"\u0005\u000b\r\u0003!\u0019\u0001\u001b\u0003\u0003\u0011\u0003\"!M#\u0005\u000b\u0019\u0003!\u0019\u0001\u001b\u0003\u0003\u0015\u0003\"!\r%\u0005\u000b%\u0003!\u0019\u0001\u001b\u0003\u0003\u0019\u0003\"!M&\u0005\u000b1\u0003!\u0019\u0001\u001b\u0003\u0003\u001d\u0003\"!\r(\u0005\u000b=\u0003!\u0019\u0001\u001b\u0003\u0003!\u0003\"!M)\u0005\u000bI\u0003!\u0019\u0001\u001b\u0003\u0003%\u0003\"!\r+\u0005\u000bU\u0003!\u0019\u0001\u001b\u0003\u0003)\u0003\"!M,\u0005\u000ba\u0003!\u0019\u0001\u001b\u0003\u0003-\u0003RBW.1wy\nEi\u0012&N!N3V\"\u0001\t\n\u0005q\u0003\"\u0001\u0004*oOB\u0013x\u000eZ;diF\n\u0014A\u0002\u0013j]&$H\u0005F\u0001`!\t9\u0002-\u0003\u0002b1\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002IB\u0019QD\u000b\u0019\u0002\u0015M$(/^2ukJ,''F\u0001h!\ri\"fO\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u00016\u0011\u0007uQc(\u0001\u0006tiJ,8\r^;sKR*\u0012!\u001c\t\u0004;)\n\u0015AC:ueV\u001cG/\u001e:fkU\t\u0001\u000fE\u0002\u001eU\u0011\u000b!b\u001d;sk\u000e$XO]37+\u0005\u0019\bcA\u000f+\u000f\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003Y\u00042!\b\u0016K\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002sB\u0019QDK'\u0002\u0015M$(/^2ukJ,\u0017(F\u0001}!\ri\"\u0006U\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'F\u0001\u0000!\ri\"fU\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002\u0006A\u0019QD\u000b,\u0002\u000f\u0019\u0014x.\\%oiR\u0019Q&a\u0003\t\u000f\u00055Q\u00021\u0001\u0002\u0010\u0005\u0011\u0001\u0010\r\t\u0004/\u0005E\u0011bAA\n1\t\u0019\u0011J\u001c;\u0002\u0007=tW-F\u0001.\u0001"
)
public interface RingProduct11 extends Ring, RngProduct11 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   Ring structure7();

   Ring structure8();

   Ring structure9();

   Ring structure10();

   Ring structure11();

   // $FF: synthetic method
   static Tuple11 fromInt$(final RingProduct11 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple11 fromInt(final int x0) {
      return new Tuple11(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0), this.structure7().fromInt(x0), this.structure8().fromInt(x0), this.structure9().fromInt(x0), this.structure10().fromInt(x0), this.structure11().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple11 one$(final RingProduct11 $this) {
      return $this.one();
   }

   default Tuple11 one() {
      return new Tuple11(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one(), this.structure10().one(), this.structure11().one());
   }

   static void $init$(final RingProduct11 $this) {
   }
}
