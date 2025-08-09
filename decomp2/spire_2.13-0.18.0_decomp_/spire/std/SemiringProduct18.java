package spire.std;

import algebra.ring.Semiring;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000b]\u0004A\u0011\u0001=\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0019\r\u0011q\n\u0005\b\u0003'\u0002a1AA+\u0011\u001d\tI\u0006\u0001D\u0002\u00037Bq!a\u0018\u0001\r\u0007\t\t\u0007C\u0004\u0002f\u0001!\t!a\u001a\t\u000f\u0005%\u0004\u0001\"\u0001\u0002l!9\u0011Q\u000f\u0001\u0005\u0002\u0005]\u0004bBA?\u0001\u0011\u0005\u0013q\u0010\u0002\u0012'\u0016l\u0017N]5oOB\u0013x\u000eZ;diFB$B\u0001\u000e\u001c\u0003\r\u0019H\u000f\u001a\u0006\u00029\u0005)1\u000f]5sKV\u0019bdO#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8skN\u0019\u0001aH\u0013\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\r13G\u000e\b\u0003OAr!\u0001\u000b\u0018\u000f\u0005%jS\"\u0001\u0016\u000b\u0005-b\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003qI!aL\u000e\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011GM\u0001\ba\u0006\u001c7.Y4f\u0015\ty3$\u0003\u00025k\tA1+Z7je&twM\u0003\u00022eA!\u0002eN\u001dE\u000f*k\u0005k\u0015,Z9~\u0013W\r[6ocRL!\u0001O\u0011\u0003\u000fQ+\b\u000f\\32qA\u0011!h\u000f\u0007\u0001\t\u0015a\u0004A1\u0001>\u0005\u0005\t\u0015C\u0001 B!\t\u0001s(\u0003\u0002AC\t9aj\u001c;iS:<\u0007C\u0001\u0011C\u0013\t\u0019\u0015EA\u0002B]f\u0004\"AO#\u0005\u000b\u0019\u0003!\u0019A\u001f\u0003\u0003\t\u0003\"A\u000f%\u0005\u000b%\u0003!\u0019A\u001f\u0003\u0003\r\u0003\"AO&\u0005\u000b1\u0003!\u0019A\u001f\u0003\u0003\u0011\u0003\"A\u000f(\u0005\u000b=\u0003!\u0019A\u001f\u0003\u0003\u0015\u0003\"AO)\u0005\u000bI\u0003!\u0019A\u001f\u0003\u0003\u0019\u0003\"A\u000f+\u0005\u000bU\u0003!\u0019A\u001f\u0003\u0003\u001d\u0003\"AO,\u0005\u000ba\u0003!\u0019A\u001f\u0003\u0003!\u0003\"A\u000f.\u0005\u000bm\u0003!\u0019A\u001f\u0003\u0003%\u0003\"AO/\u0005\u000by\u0003!\u0019A\u001f\u0003\u0003)\u0003\"A\u000f1\u0005\u000b\u0005\u0004!\u0019A\u001f\u0003\u0003-\u0003\"AO2\u0005\u000b\u0011\u0004!\u0019A\u001f\u0003\u00031\u0003\"A\u000f4\u0005\u000b\u001d\u0004!\u0019A\u001f\u0003\u00035\u0003\"AO5\u0005\u000b)\u0004!\u0019A\u001f\u0003\u00039\u0003\"A\u000f7\u0005\u000b5\u0004!\u0019A\u001f\u0003\u0003=\u0003\"AO8\u0005\u000bA\u0004!\u0019A\u001f\u0003\u0003A\u0003\"A\u000f:\u0005\u000bM\u0004!\u0019A\u001f\u0003\u0003E\u0003\"AO;\u0005\u000bY\u0004!\u0019A\u001f\u0003\u0003I\u000ba\u0001J5oSR$C#A=\u0011\u0005\u0001R\u0018BA>\"\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001\u007f!\r13'O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014TCAA\u0002!\r13\u0007R\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cTCAA\u0005!\r13gR\u0001\u000bgR\u0014Xo\u0019;ve\u0016$TCAA\b!\r13GS\u0001\u000bgR\u0014Xo\u0019;ve\u0016,TCAA\u000b!\r13'T\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u000e!\r13\u0007U\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u0011!\r13gU\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\u0014!\r13GV\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\u0017!\r13'W\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u00024A\u0019ae\r/\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003s\u00012AJ\u001a`\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005}\u0002c\u0001\u00144E\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\t)\u0005E\u0002'g\u0015\f1b\u001d;sk\u000e$XO]32iU\u0011\u00111\n\t\u0004MMB\u0017aC:ueV\u001cG/\u001e:fcU*\"!!\u0015\u0011\u0007\u0019\u001a4.A\u0006tiJ,8\r^;sKF2TCAA,!\r13G\\\u0001\fgR\u0014Xo\u0019;ve\u0016\ft'\u0006\u0002\u0002^A\u0019aeM9\u0002\u0017M$(/^2ukJ,\u0017\u0007O\u000b\u0003\u0003G\u00022AJ\u001au\u0003\u0011QXM]8\u0016\u0003Y\nA\u0001\u001d7vgR)a'!\u001c\u0002r!1\u0011qN\u000bA\u0002Y\n!\u0001\u001f\u0019\t\r\u0005MT\u00031\u00017\u0003\tA\u0018'A\u0003uS6,7\u000fF\u00037\u0003s\nY\b\u0003\u0004\u0002pY\u0001\rA\u000e\u0005\u0007\u0003g2\u0002\u0019\u0001\u001c\u0002\u0007A|w\u000fF\u00037\u0003\u0003\u000b\u0019\t\u0003\u0004\u0002p]\u0001\rA\u000e\u0005\b\u0003g:\u0002\u0019AAC!\r\u0001\u0013qQ\u0005\u0004\u0003\u0013\u000b#aA%oi\u0002"
)
public interface SemiringProduct18 extends Semiring {
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

   Semiring structure12();

   Semiring structure13();

   Semiring structure14();

   Semiring structure15();

   Semiring structure16();

   Semiring structure17();

   Semiring structure18();

   // $FF: synthetic method
   static Tuple18 zero$(final SemiringProduct18 $this) {
      return $this.zero();
   }

   default Tuple18 zero() {
      return new Tuple18(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero(), this.structure17().zero(), this.structure18().zero());
   }

   // $FF: synthetic method
   static Tuple18 plus$(final SemiringProduct18 $this, final Tuple18 x0, final Tuple18 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple18 plus(final Tuple18 x0, final Tuple18 x1) {
      return new Tuple18(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()), this.structure17().plus(x0._17(), x1._17()), this.structure18().plus(x0._18(), x1._18()));
   }

   // $FF: synthetic method
   static Tuple18 times$(final SemiringProduct18 $this, final Tuple18 x0, final Tuple18 x1) {
      return $this.times(x0, x1);
   }

   default Tuple18 times(final Tuple18 x0, final Tuple18 x1) {
      return new Tuple18(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()), this.structure17().times(x0._17(), x1._17()), this.structure18().times(x0._18(), x1._18()));
   }

   // $FF: synthetic method
   static Tuple18 pow$(final SemiringProduct18 $this, final Tuple18 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple18 pow(final Tuple18 x0, final int x1) {
      return new Tuple18(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1), this.structure17().pow(x0._17(), x1), this.structure18().pow(x0._18(), x1));
   }

   static void $init$(final SemiringProduct18 $this) {
   }
}
