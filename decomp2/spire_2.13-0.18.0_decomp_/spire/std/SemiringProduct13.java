package spire.std;

import algebra.ring.Semiring;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c\u0001C\n\u0015!\u0003\r\tA\u0006\r\t\u000b\r\u0004A\u0011\u00013\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\t\u0003\t\t\u0003C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005=\u0002\u0001\"\u0001\u00022!9\u0011q\u0007\u0001\u0005B\u0005e\"!E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;2g)\u0011QCF\u0001\u0004gR$'\"A\f\u0002\u000bM\u0004\u0018N]3\u0016\u001de1\u0004i\u0011$J\u0019>\u0013V\u000bW._CN\u0019\u0001A\u0007\u0011\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\r\u0005s\u0017PU3g!\r\tc&\r\b\u0003E-r!aI\u0015\u000f\u0005\u0011BS\"A\u0013\u000b\u0005\u0019:\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003]I!A\u000b\f\u0002\u000f\u0005dw-\u001a2sC&\u0011A&L\u0001\ba\u0006\u001c7.Y4f\u0015\tQc#\u0003\u00020a\tA1+Z7je&twM\u0003\u0002-[Ay1D\r\u001b@\u0005\u0016C5JT)U/jk\u0006-\u0003\u000249\t9A+\u001e9mKF\u001a\u0004CA\u001b7\u0019\u0001!Qa\u000e\u0001C\u0002a\u0012\u0011!Q\t\u0003sq\u0002\"a\u0007\u001e\n\u0005mb\"a\u0002(pi\"Lgn\u001a\t\u00037uJ!A\u0010\u000f\u0003\u0007\u0005s\u0017\u0010\u0005\u00026\u0001\u0012)\u0011\t\u0001b\u0001q\t\t!\t\u0005\u00026\u0007\u0012)A\t\u0001b\u0001q\t\t1\t\u0005\u00026\r\u0012)q\t\u0001b\u0001q\t\tA\t\u0005\u00026\u0013\u0012)!\n\u0001b\u0001q\t\tQ\t\u0005\u00026\u0019\u0012)Q\n\u0001b\u0001q\t\ta\t\u0005\u00026\u001f\u0012)\u0001\u000b\u0001b\u0001q\t\tq\t\u0005\u00026%\u0012)1\u000b\u0001b\u0001q\t\t\u0001\n\u0005\u00026+\u0012)a\u000b\u0001b\u0001q\t\t\u0011\n\u0005\u000261\u0012)\u0011\f\u0001b\u0001q\t\t!\n\u0005\u000267\u0012)A\f\u0001b\u0001q\t\t1\n\u0005\u00026=\u0012)q\f\u0001b\u0001q\t\tA\n\u0005\u00026C\u0012)!\r\u0001b\u0001q\t\tQ*\u0001\u0004%S:LG\u000f\n\u000b\u0002KB\u00111DZ\u0005\u0003Or\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003)\u00042!\t\u00185\u0003)\u0019HO];diV\u0014XMM\u000b\u0002[B\u0019\u0011EL \u0002\u0015M$(/^2ukJ,7'F\u0001q!\r\tcFQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A:\u0011\u0007\u0005rS)\u0001\u0006tiJ,8\r^;sKV*\u0012A\u001e\t\u0004C9B\u0015AC:ueV\u001cG/\u001e:fmU\t\u0011\u0010E\u0002\"]-\u000b!b\u001d;sk\u000e$XO]38+\u0005a\bcA\u0011/\u001d\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003}\u00042!\t\u0018R\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003\u000b\u00012!\t\u0018U\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005-\u0001cA\u0011//\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\t\t\u0002E\u0002\"]i\u000b1b\u001d;sk\u000e$XO]32eU\u0011\u0011q\u0003\t\u0004C9j\u0016aC:ueV\u001cG/\u001e:fcM*\"!!\b\u0011\u0007\u0005r\u0003-\u0001\u0003{KJ|W#A\u0019\u0002\tAdWo\u001d\u000b\u0006c\u0005\u001d\u00121\u0006\u0005\u0007\u0003S\u0001\u0002\u0019A\u0019\u0002\u0005a\u0004\u0004BBA\u0017!\u0001\u0007\u0011'\u0001\u0002yc\u0005)A/[7fgR)\u0011'a\r\u00026!1\u0011\u0011F\tA\u0002EBa!!\f\u0012\u0001\u0004\t\u0014a\u00019poR)\u0011'a\u000f\u0002>!1\u0011\u0011\u0006\nA\u0002EBq!!\f\u0013\u0001\u0004\ty\u0004E\u0002\u001c\u0003\u0003J1!a\u0011\u001d\u0005\rIe\u000e\u001e"
)
public interface SemiringProduct13 extends Semiring {
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

   // $FF: synthetic method
   static Tuple13 zero$(final SemiringProduct13 $this) {
      return $this.zero();
   }

   default Tuple13 zero() {
      return new Tuple13(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero());
   }

   // $FF: synthetic method
   static Tuple13 plus$(final SemiringProduct13 $this, final Tuple13 x0, final Tuple13 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple13 plus(final Tuple13 x0, final Tuple13 x1) {
      return new Tuple13(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()));
   }

   // $FF: synthetic method
   static Tuple13 times$(final SemiringProduct13 $this, final Tuple13 x0, final Tuple13 x1) {
      return $this.times(x0, x1);
   }

   default Tuple13 times(final Tuple13 x0, final Tuple13 x1) {
      return new Tuple13(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()));
   }

   // $FF: synthetic method
   static Tuple13 pow$(final SemiringProduct13 $this, final Tuple13 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple13 pow(final Tuple13 x0, final int x1) {
      return new Tuple13(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1));
   }

   static void $init$(final SemiringProduct13 $this) {
   }
}
