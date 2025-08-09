package spire.std;

import algebra.ring.Semiring;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001df\u0001\u0003\u000e\u001c!\u0003\r\t!H\u0010\t\r}\u0004A\u0011AA\u0001\u0011\u001d\tI\u0001\u0001D\u0002\u0003\u0017Aq!a\u0004\u0001\r\u0007\t\t\u0002C\u0004\u0002\u0016\u00011\u0019!a\u0006\t\u000f\u0005m\u0001Ab\u0001\u0002\u001e!9\u0011\u0011\u0005\u0001\u0007\u0004\u0005\r\u0002bBA\u0014\u0001\u0019\r\u0011\u0011\u0006\u0005\b\u0003[\u0001a1AA\u0018\u0011\u001d\t\u0019\u0004\u0001D\u0002\u0003kAq!!\u000f\u0001\r\u0007\tY\u0004C\u0004\u0002@\u00011\u0019!!\u0011\t\u000f\u0005\u0015\u0003Ab\u0001\u0002H!9\u00111\n\u0001\u0007\u0004\u00055\u0003bBA)\u0001\u0019\r\u00111\u000b\u0005\b\u0003/\u0002a1AA-\u0011\u001d\ti\u0006\u0001D\u0002\u0003?Bq!a\u0019\u0001\r\u0007\t)\u0007C\u0004\u0002j\u00011\u0019!a\u001b\t\u000f\u0005=\u0004Ab\u0001\u0002r!9\u0011Q\u000f\u0001\u0007\u0004\u0005]\u0004bBA>\u0001\u0019\r\u0011Q\u0010\u0005\b\u0003\u0003\u0003A\u0011AAB\u0011\u001d\t)\t\u0001C\u0001\u0003\u000fCq!!%\u0001\t\u0003\t\u0019\nC\u0004\u0002\u001a\u0002!\t%a'\u0003#M+W.\u001b:j]\u001e\u0004&o\u001c3vGR\u0014\u0004G\u0003\u0002\u001d;\u0005\u00191\u000f\u001e3\u000b\u0003y\tQa\u001d9je\u0016,R\u0003I\u001fH\u00156\u00036KV-]?\n,\u0007n\u001b8ri^TXpE\u0002\u0001C\u001d\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007c\u0001\u00156q9\u0011\u0011F\r\b\u0003UAr!aK\u0018\u000e\u00031R!!\f\u0018\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AH\u0005\u0003cu\tq!\u00197hK\n\u0014\u0018-\u0003\u00024i\u00059\u0001/Y2lC\u001e,'BA\u0019\u001e\u0013\t1tG\u0001\u0005TK6L'/\u001b8h\u0015\t\u0019D\u0007\u0005\f#sm2\u0015\nT(S+b[f,\u00193hU6\u00048O^=}\u0013\tQ4EA\u0004UkBdWM\r\u0019\u0011\u0005qjD\u0002\u0001\u0003\u0006}\u0001\u0011\ra\u0010\u0002\u0002\u0003F\u0011\u0001i\u0011\t\u0003E\u0005K!AQ\u0012\u0003\u000f9{G\u000f[5oOB\u0011!\u0005R\u0005\u0003\u000b\u000e\u00121!\u00118z!\tat\tB\u0003I\u0001\t\u0007qHA\u0001C!\ta$\nB\u0003L\u0001\t\u0007qHA\u0001D!\taT\nB\u0003O\u0001\t\u0007qHA\u0001E!\ta\u0004\u000bB\u0003R\u0001\t\u0007qHA\u0001F!\ta4\u000bB\u0003U\u0001\t\u0007qHA\u0001G!\tad\u000bB\u0003X\u0001\t\u0007qHA\u0001H!\ta\u0014\fB\u0003[\u0001\t\u0007qHA\u0001I!\taD\fB\u0003^\u0001\t\u0007qHA\u0001J!\tat\fB\u0003a\u0001\t\u0007qHA\u0001K!\ta$\rB\u0003d\u0001\t\u0007qHA\u0001L!\taT\rB\u0003g\u0001\t\u0007qHA\u0001M!\ta\u0004\u000eB\u0003j\u0001\t\u0007qHA\u0001N!\ta4\u000eB\u0003m\u0001\t\u0007qHA\u0001O!\tad\u000eB\u0003p\u0001\t\u0007qHA\u0001P!\ta\u0014\u000fB\u0003s\u0001\t\u0007qHA\u0001Q!\taD\u000fB\u0003v\u0001\t\u0007qHA\u0001R!\tat\u000fB\u0003y\u0001\t\u0007qHA\u0001S!\ta$\u0010B\u0003|\u0001\t\u0007qHA\u0001T!\taT\u0010B\u0003\u007f\u0001\t\u0007qHA\u0001U\u0003\u0019!\u0013N\\5uIQ\u0011\u00111\u0001\t\u0004E\u0005\u0015\u0011bAA\u0004G\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0003\u0003\u001b\u00012\u0001K\u001b<\u0003)\u0019HO];diV\u0014XMM\u000b\u0003\u0003'\u00012\u0001K\u001bG\u0003)\u0019HO];diV\u0014XmM\u000b\u0003\u00033\u00012\u0001K\u001bJ\u0003)\u0019HO];diV\u0014X\rN\u000b\u0003\u0003?\u00012\u0001K\u001bM\u0003)\u0019HO];diV\u0014X-N\u000b\u0003\u0003K\u00012\u0001K\u001bP\u0003)\u0019HO];diV\u0014XMN\u000b\u0003\u0003W\u00012\u0001K\u001bS\u0003)\u0019HO];diV\u0014XmN\u000b\u0003\u0003c\u00012\u0001K\u001bV\u0003)\u0019HO];diV\u0014X\rO\u000b\u0003\u0003o\u00012\u0001K\u001bY\u0003)\u0019HO];diV\u0014X-O\u000b\u0003\u0003{\u00012\u0001K\u001b\\\u0003-\u0019HO];diV\u0014X-\r\u0019\u0016\u0005\u0005\r\u0003c\u0001\u00156=\u0006Y1\u000f\u001e:vGR,(/Z\u00192+\t\tI\u0005E\u0002)k\u0005\f1b\u001d;sk\u000e$XO]32eU\u0011\u0011q\n\t\u0004QU\"\u0017aC:ueV\u001cG/\u001e:fcM*\"!!\u0016\u0011\u0007!*t-A\u0006tiJ,8\r^;sKF\"TCAA.!\rASG[\u0001\fgR\u0014Xo\u0019;ve\u0016\fT'\u0006\u0002\u0002bA\u0019\u0001&N7\u0002\u0017M$(/^2ukJ,\u0017GN\u000b\u0003\u0003O\u00022\u0001K\u001bq\u0003-\u0019HO];diV\u0014X-M\u001c\u0016\u0005\u00055\u0004c\u0001\u00156g\u0006Y1\u000f\u001e:vGR,(/Z\u00199+\t\t\u0019\bE\u0002)kY\f1b\u001d;sk\u000e$XO]32sU\u0011\u0011\u0011\u0010\t\u0004QUJ\u0018aC:ueV\u001cG/\u001e:feA*\"!a \u0011\u0007!*D0\u0001\u0003{KJ|W#\u0001\u001d\u0002\tAdWo\u001d\u000b\u0006q\u0005%\u0015Q\u0012\u0005\u0007\u0003\u0017;\u0002\u0019\u0001\u001d\u0002\u0005a\u0004\u0004BBAH/\u0001\u0007\u0001(\u0001\u0002yc\u0005)A/[7fgR)\u0001(!&\u0002\u0018\"1\u00111\u0012\rA\u0002aBa!a$\u0019\u0001\u0004A\u0014a\u00019poR)\u0001(!(\u0002 \"1\u00111R\rA\u0002aBq!a$\u001a\u0001\u0004\t\t\u000bE\u0002#\u0003GK1!!*$\u0005\rIe\u000e\u001e"
)
public interface SemiringProduct20 extends Semiring {
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

   Semiring structure19();

   Semiring structure20();

   // $FF: synthetic method
   static Tuple20 zero$(final SemiringProduct20 $this) {
      return $this.zero();
   }

   default Tuple20 zero() {
      return new Tuple20(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero(), this.structure17().zero(), this.structure18().zero(), this.structure19().zero(), this.structure20().zero());
   }

   // $FF: synthetic method
   static Tuple20 plus$(final SemiringProduct20 $this, final Tuple20 x0, final Tuple20 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple20 plus(final Tuple20 x0, final Tuple20 x1) {
      return new Tuple20(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()), this.structure17().plus(x0._17(), x1._17()), this.structure18().plus(x0._18(), x1._18()), this.structure19().plus(x0._19(), x1._19()), this.structure20().plus(x0._20(), x1._20()));
   }

   // $FF: synthetic method
   static Tuple20 times$(final SemiringProduct20 $this, final Tuple20 x0, final Tuple20 x1) {
      return $this.times(x0, x1);
   }

   default Tuple20 times(final Tuple20 x0, final Tuple20 x1) {
      return new Tuple20(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()), this.structure17().times(x0._17(), x1._17()), this.structure18().times(x0._18(), x1._18()), this.structure19().times(x0._19(), x1._19()), this.structure20().times(x0._20(), x1._20()));
   }

   // $FF: synthetic method
   static Tuple20 pow$(final SemiringProduct20 $this, final Tuple20 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple20 pow(final Tuple20 x0, final int x1) {
      return new Tuple20(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1), this.structure17().pow(x0._17(), x1), this.structure18().pow(x0._18(), x1), this.structure19().pow(x0._19(), x1), this.structure20().pow(x0._20(), x1));
   }

   static void $init$(final SemiringProduct20 $this) {
   }
}
