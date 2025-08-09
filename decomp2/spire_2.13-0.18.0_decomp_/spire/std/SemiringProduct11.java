package spire.std;

import algebra.ring.Semiring;
import scala.Tuple11;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0001\u0004a1A1\t\u000b\r\u0004a1\u00013\t\u000b\u0019\u0004a1A4\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011IA\u000f\u0005E\u0019V-\\5sS:<\u0007K]8ek\u000e$\u0018'\r\u0006\u0003'Q\t1a\u001d;e\u0015\u0005)\u0012!B:qSJ,W\u0003D\f5}\u0005#uIS'Q'ZK6c\u0001\u0001\u0019=A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t1\u0011I\\=SK\u001a\u00042a\b\u00170\u001d\t\u0001\u0013F\u0004\u0002\"O9\u0011!EJ\u0007\u0002G)\u0011A%J\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ#\u0003\u0002))\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0016,\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\u000b\u000b\n\u00055r#\u0001C*f[&\u0014\u0018N\\4\u000b\u0005)Z\u0003#D\r1eu\u00025IR%M\u001fJ+\u0006,\u0003\u000225\t9A+\u001e9mKF\n\u0004CA\u001a5\u0019\u0001!Q!\u000e\u0001C\u0002Y\u0012\u0011!Q\t\u0003oi\u0002\"!\u0007\u001d\n\u0005eR\"a\u0002(pi\"Lgn\u001a\t\u00033mJ!\u0001\u0010\u000e\u0003\u0007\u0005s\u0017\u0010\u0005\u00024}\u0011)q\b\u0001b\u0001m\t\t!\t\u0005\u00024\u0003\u0012)!\t\u0001b\u0001m\t\t1\t\u0005\u00024\t\u0012)Q\t\u0001b\u0001m\t\tA\t\u0005\u00024\u000f\u0012)\u0001\n\u0001b\u0001m\t\tQ\t\u0005\u00024\u0015\u0012)1\n\u0001b\u0001m\t\ta\t\u0005\u00024\u001b\u0012)a\n\u0001b\u0001m\t\tq\t\u0005\u00024!\u0012)\u0011\u000b\u0001b\u0001m\t\t\u0001\n\u0005\u00024'\u0012)A\u000b\u0001b\u0001m\t\t\u0011\n\u0005\u00024-\u0012)q\u000b\u0001b\u0001m\t\t!\n\u0005\u000243\u0012)!\f\u0001b\u0001m\t\t1*\u0001\u0004%S:LG\u000f\n\u000b\u0002;B\u0011\u0011DX\u0005\u0003?j\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\t\u00042a\b\u00173\u0003)\u0019HO];diV\u0014XMM\u000b\u0002KB\u0019q\u0004L\u001f\u0002\u0015M$(/^2ukJ,7'F\u0001i!\ryB\u0006Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A6\u0011\u0007}a3)\u0001\u0006tiJ,8\r^;sKV*\u0012A\u001c\t\u0004?12\u0015AC:ueV\u001cG/\u001e:fmU\t\u0011\u000fE\u0002 Y%\u000b!b\u001d;sk\u000e$XO]38+\u0005!\bcA\u0010-\u0019\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003]\u00042a\b\u0017P\u0003)\u0019HO];diV\u0014X-O\u000b\u0002uB\u0019q\u0004\f*\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0002{B\u0019q\u0004L+\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003\u0003\u00012a\b\u0017Y\u0003\u0011QXM]8\u0016\u0003=\nA\u0001\u001d7vgR)q&a\u0003\u0002\u0010!1\u0011Q\u0002\bA\u0002=\n!\u0001\u001f\u0019\t\r\u0005Ea\u00021\u00010\u0003\tA\u0018'A\u0003uS6,7\u000fF\u00030\u0003/\tI\u0002\u0003\u0004\u0002\u000e=\u0001\ra\f\u0005\u0007\u0003#y\u0001\u0019A\u0018\u0002\u0007A|w\u000fF\u00030\u0003?\t\t\u0003\u0003\u0004\u0002\u000eA\u0001\ra\f\u0005\b\u0003#\u0001\u0002\u0019AA\u0012!\rI\u0012QE\u0005\u0004\u0003OQ\"aA%oi\u0002"
)
public interface SemiringProduct11 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   Semiring structure5();

   Semiring structure6();

   Semiring structure7();

   Semiring structure8();

   Semiring structure9();

   Semiring structure10();

   Semiring structure11();

   // $FF: synthetic method
   static Tuple11 zero$(final SemiringProduct11 $this) {
      return $this.zero();
   }

   default Tuple11 zero() {
      return new Tuple11(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero());
   }

   // $FF: synthetic method
   static Tuple11 plus$(final SemiringProduct11 $this, final Tuple11 x0, final Tuple11 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple11 plus(final Tuple11 x0, final Tuple11 x1) {
      return new Tuple11(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()));
   }

   // $FF: synthetic method
   static Tuple11 times$(final SemiringProduct11 $this, final Tuple11 x0, final Tuple11 x1) {
      return $this.times(x0, x1);
   }

   default Tuple11 times(final Tuple11 x0, final Tuple11 x1) {
      return new Tuple11(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()));
   }

   // $FF: synthetic method
   static Tuple11 pow$(final SemiringProduct11 $this, final Tuple11 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple11 pow(final Tuple11 x0, final int x1) {
      return new Tuple11(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1));
   }

   static void $init$(final SemiringProduct11 $this) {
   }
}
