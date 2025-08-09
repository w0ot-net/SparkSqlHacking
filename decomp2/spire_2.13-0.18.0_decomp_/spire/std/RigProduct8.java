package spire.std;

import algebra.ring.Rig;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006!\u0002!\t!\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u00021\u0019a\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u00021\u0019\u0001\u001b\u0005\u0006U\u00021\u0019a\u001b\u0005\u0006[\u0002!\tA\u001c\u0002\f%&<\u0007K]8ek\u000e$\bH\u0003\u0002\u000e\u001d\u0005\u00191\u000f\u001e3\u000b\u0003=\tQa\u001d9je\u0016,\u0012\"\u0005\u00189wy\nEi\u0012&\u0014\t\u0001\u0011\u0002\u0004\u0014\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0007e1\u0013F\u0004\u0002\u001bG9\u00111$\t\b\u00039\u0001j\u0011!\b\u0006\u0003=}\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u001f%\u0011!ED\u0001\bC2<WM\u0019:b\u0013\t!S%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\tr\u0011BA\u0014)\u0005\r\u0011\u0016n\u001a\u0006\u0003I\u0015\u0002\"b\u0005\u0016-oij\u0004i\u0011$J\u0013\tYCC\u0001\u0004UkBdW\r\u000f\t\u0003[9b\u0001\u0001B\u00030\u0001\t\u0007\u0001GA\u0001B#\t\tD\u0007\u0005\u0002\u0014e%\u00111\u0007\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u0019R'\u0003\u00027)\t\u0019\u0011I\\=\u0011\u00055BD!B\u001d\u0001\u0005\u0004\u0001$!\u0001\"\u0011\u00055ZD!\u0002\u001f\u0001\u0005\u0004\u0001$!A\"\u0011\u00055rD!B \u0001\u0005\u0004\u0001$!\u0001#\u0011\u00055\nE!\u0002\"\u0001\u0005\u0004\u0001$!A#\u0011\u00055\"E!B#\u0001\u0005\u0004\u0001$!\u0001$\u0011\u00055:E!\u0002%\u0001\u0005\u0004\u0001$!A$\u0011\u00055RE!B&\u0001\u0005\u0004\u0001$!\u0001%\u0011\u00155sEf\u000e\u001e>\u0001\u000e3\u0015*D\u0001\r\u0013\tyEB\u0001\tTK6L'/\u001b8h!J|G-^2uq\u00051A%\u001b8ji\u0012\"\u0012A\u0015\t\u0003'MK!\u0001\u0016\u000b\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A,\u0011\u0007e1C&\u0001\u0006tiJ,8\r^;sKJ*\u0012A\u0017\t\u00043\u0019:\u0014AC:ueV\u001cG/\u001e:fgU\tQ\fE\u0002\u001aMi\n!b\u001d;sk\u000e$XO]35+\u0005\u0001\u0007cA\r'{\u0005Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0003\r\u00042!\u0007\u0014A\u0003)\u0019HO];diV\u0014XMN\u000b\u0002MB\u0019\u0011DJ\"\u0002\u0015M$(/^2ukJ,w'F\u0001j!\rIbER\u0001\u000bgR\u0014Xo\u0019;ve\u0016DT#\u00017\u0011\u0007e1\u0013*A\u0002p]\u0016,\u0012!\u000b"
)
public interface RigProduct8 extends Rig, SemiringProduct8 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   Rig structure7();

   Rig structure8();

   // $FF: synthetic method
   static Tuple8 one$(final RigProduct8 $this) {
      return $this.one();
   }

   default Tuple8 one() {
      return new Tuple8(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one());
   }

   static void $init$(final RigProduct8 $this) {
   }
}
