package spire.std;

import algebra.ring.Semiring;
import scala.Tuple12;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001\u0003\n\u0014!\u0003\r\t!F\f\t\u000b}\u0003A\u0011\u00011\t\u000b\u0011\u0004a1A3\t\u000b\u001d\u0004a1\u00015\t\u000b)\u0004a1A6\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u0001!\t!a\u0005\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u0011\u0011\u0005\u0001\u0005\u0002\u0005\r\u0002bBA\u0015\u0001\u0011\u0005\u00131\u0006\u0002\u0012'\u0016l\u0017N]5oOB\u0013x\u000eZ;diF\u0012$B\u0001\u000b\u0016\u0003\r\u0019H\u000f\u001a\u0006\u0002-\u0005)1\u000f]5sKVi\u0001$N C\u000b\"[e*\u0015+X5v\u001b2\u0001A\r !\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0019\u0001%\f\u0019\u000f\u0005\u0005RcB\u0001\u0012)\u001d\t\u0019s%D\u0001%\u0015\t)c%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u00051\u0012BA\u0015\u0016\u0003\u001d\tGnZ3ce\u0006L!a\u000b\u0017\u0002\u000fA\f7m[1hK*\u0011\u0011&F\u0005\u0003]=\u0012\u0001bU3nSJLgn\u001a\u0006\u0003W1\u0002bBG\u00194}\u0005#uIS'Q'ZKF,\u0003\u000237\t9A+\u001e9mKF\u0012\u0004C\u0001\u001b6\u0019\u0001!QA\u000e\u0001C\u0002]\u0012\u0011!Q\t\u0003qm\u0002\"AG\u001d\n\u0005iZ\"a\u0002(pi\"Lgn\u001a\t\u00035qJ!!P\u000e\u0003\u0007\u0005s\u0017\u0010\u0005\u00025\u007f\u0011)\u0001\t\u0001b\u0001o\t\t!\t\u0005\u00025\u0005\u0012)1\t\u0001b\u0001o\t\t1\t\u0005\u00025\u000b\u0012)a\t\u0001b\u0001o\t\tA\t\u0005\u00025\u0011\u0012)\u0011\n\u0001b\u0001o\t\tQ\t\u0005\u00025\u0017\u0012)A\n\u0001b\u0001o\t\ta\t\u0005\u00025\u001d\u0012)q\n\u0001b\u0001o\t\tq\t\u0005\u00025#\u0012)!\u000b\u0001b\u0001o\t\t\u0001\n\u0005\u00025)\u0012)Q\u000b\u0001b\u0001o\t\t\u0011\n\u0005\u00025/\u0012)\u0001\f\u0001b\u0001o\t\t!\n\u0005\u000255\u0012)1\f\u0001b\u0001o\t\t1\n\u0005\u00025;\u0012)a\f\u0001b\u0001o\t\tA*\u0001\u0004%S:LG\u000f\n\u000b\u0002CB\u0011!DY\u0005\u0003Gn\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\u0019\u00042\u0001I\u00174\u0003)\u0019HO];diV\u0014XMM\u000b\u0002SB\u0019\u0001%\f \u0002\u0015M$(/^2ukJ,7'F\u0001m!\r\u0001S&Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A8\u0011\u0007\u0001jC)\u0001\u0006tiJ,8\r^;sKV*\u0012A\u001d\t\u0004A5:\u0015AC:ueV\u001cG/\u001e:fmU\tQ\u000fE\u0002![)\u000b!b\u001d;sk\u000e$XO]38+\u0005A\bc\u0001\u0011.\u001b\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003m\u00042\u0001I\u0017Q\u0003)\u0019HO];diV\u0014X-O\u000b\u0002}B\u0019\u0001%L*\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003\u0007\u00012\u0001I\u0017W\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005%\u0001c\u0001\u0011.3\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\ty\u0001E\u0002![q\u000bAA_3s_V\t\u0001'\u0001\u0003qYV\u001cH#\u0002\u0019\u0002\u001a\u0005u\u0001BBA\u000e\u001f\u0001\u0007\u0001'\u0001\u0002ya!1\u0011qD\bA\u0002A\n!\u0001_\u0019\u0002\u000bQLW.Z:\u0015\u000bA\n)#a\n\t\r\u0005m\u0001\u00031\u00011\u0011\u0019\ty\u0002\u0005a\u0001a\u0005\u0019\u0001o\\<\u0015\u000bA\ni#a\f\t\r\u0005m\u0011\u00031\u00011\u0011\u001d\ty\"\u0005a\u0001\u0003c\u00012AGA\u001a\u0013\r\t)d\u0007\u0002\u0004\u0013:$\b"
)
public interface SemiringProduct12 extends Semiring {
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

   // $FF: synthetic method
   static Tuple12 zero$(final SemiringProduct12 $this) {
      return $this.zero();
   }

   default Tuple12 zero() {
      return new Tuple12(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero());
   }

   // $FF: synthetic method
   static Tuple12 plus$(final SemiringProduct12 $this, final Tuple12 x0, final Tuple12 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple12 plus(final Tuple12 x0, final Tuple12 x1) {
      return new Tuple12(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()));
   }

   // $FF: synthetic method
   static Tuple12 times$(final SemiringProduct12 $this, final Tuple12 x0, final Tuple12 x1) {
      return $this.times(x0, x1);
   }

   default Tuple12 times(final Tuple12 x0, final Tuple12 x1) {
      return new Tuple12(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()));
   }

   // $FF: synthetic method
   static Tuple12 pow$(final SemiringProduct12 $this, final Tuple12 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple12 pow(final Tuple12 x0, final int x1) {
      return new Tuple12(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1));
   }

   static void $init$(final SemiringProduct12 $this) {
   }
}
