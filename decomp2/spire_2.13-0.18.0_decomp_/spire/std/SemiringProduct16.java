package spire.std;

import algebra.ring.Semiring;
import scala.Tuple16;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000b=\u0004A\u0011\u00019\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\t\u0003\tY\u0005C\u0004\u0002N\u0001!\t!a\u0014\t\u000f\u0005e\u0003\u0001\"\u0001\u0002\\!9\u0011\u0011\r\u0001\u0005B\u0005\r$!E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;2m)\u0011\u0001$G\u0001\u0004gR$'\"\u0001\u000e\u0002\u000bM\u0004\u0018N]3\u0016#qI4IR%M\u001fJ+\u0006l\u00170bI\u001eTWnE\u0002\u0001;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007c\u0001\u00132i9\u0011QE\f\b\u0003M1r!aJ\u0016\u000e\u0003!R!!\u000b\u0016\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AG\u0005\u0003[e\tq!\u00197hK\n\u0014\u0018-\u0003\u00020a\u00059\u0001/Y2lC\u001e,'BA\u0017\u001a\u0013\t\u00114G\u0001\u0005TK6L'/\u001b8h\u0015\ty\u0003\u0007\u0005\n\u001fk]\u0012U\tS&O#R;&,\u00181dM&d\u0017B\u0001\u001c \u0005\u001d!V\u000f\u001d7fcY\u0002\"\u0001O\u001d\r\u0001\u0011)!\b\u0001b\u0001w\t\t\u0011)\u0005\u0002=\u007fA\u0011a$P\u0005\u0003}}\u0011qAT8uQ&tw\r\u0005\u0002\u001f\u0001&\u0011\u0011i\b\u0002\u0004\u0003:L\bC\u0001\u001dD\t\u0015!\u0005A1\u0001<\u0005\u0005\u0011\u0005C\u0001\u001dG\t\u00159\u0005A1\u0001<\u0005\u0005\u0019\u0005C\u0001\u001dJ\t\u0015Q\u0005A1\u0001<\u0005\u0005!\u0005C\u0001\u001dM\t\u0015i\u0005A1\u0001<\u0005\u0005)\u0005C\u0001\u001dP\t\u0015\u0001\u0006A1\u0001<\u0005\u00051\u0005C\u0001\u001dS\t\u0015\u0019\u0006A1\u0001<\u0005\u00059\u0005C\u0001\u001dV\t\u00151\u0006A1\u0001<\u0005\u0005A\u0005C\u0001\u001dY\t\u0015I\u0006A1\u0001<\u0005\u0005I\u0005C\u0001\u001d\\\t\u0015a\u0006A1\u0001<\u0005\u0005Q\u0005C\u0001\u001d_\t\u0015y\u0006A1\u0001<\u0005\u0005Y\u0005C\u0001\u001db\t\u0015\u0011\u0007A1\u0001<\u0005\u0005a\u0005C\u0001\u001de\t\u0015)\u0007A1\u0001<\u0005\u0005i\u0005C\u0001\u001dh\t\u0015A\u0007A1\u0001<\u0005\u0005q\u0005C\u0001\u001dk\t\u0015Y\u0007A1\u0001<\u0005\u0005y\u0005C\u0001\u001dn\t\u0015q\u0007A1\u0001<\u0005\u0005\u0001\u0016A\u0002\u0013j]&$H\u0005F\u0001r!\tq\"/\u0003\u0002t?\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002mB\u0019A%M\u001c\u0002\u0015M$(/^2ukJ,''F\u0001z!\r!\u0013GQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u0001?\u0011\u0007\u0011\nT)\u0001\u0006tiJ,8\r^;sKR*\u0012a \t\u0004IEB\u0015AC:ueV\u001cG/\u001e:fkU\u0011\u0011Q\u0001\t\u0004IEZ\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u00111\u0002\t\u0004IEr\u0015AC:ueV\u001cG/\u001e:foU\u0011\u0011\u0011\u0003\t\u0004IE\n\u0016AC:ueV\u001cG/\u001e:fqU\u0011\u0011q\u0003\t\u0004IE\"\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011Q\u0004\t\u0004IE:\u0016aC:ueV\u001cG/\u001e:fcA*\"!a\t\u0011\u0007\u0011\n$,A\u0006tiJ,8\r^;sKF\nTCAA\u0015!\r!\u0013'X\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u00020A\u0019A%\r1\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003k\u00012\u0001J\u0019d\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005m\u0002c\u0001\u00132M\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\t\t\u0005E\u0002%c%\f1b\u001d;sk\u000e$XO]32mU\u0011\u0011q\t\t\u0004IEb\u0017\u0001\u0002>fe>,\u0012\u0001N\u0001\u0005a2,8\u000fF\u00035\u0003#\n)\u0006\u0003\u0004\u0002TM\u0001\r\u0001N\u0001\u0003qBBa!a\u0016\u0014\u0001\u0004!\u0014A\u0001=2\u0003\u0015!\u0018.\\3t)\u0015!\u0014QLA0\u0011\u0019\t\u0019\u0006\u0006a\u0001i!1\u0011q\u000b\u000bA\u0002Q\n1\u0001]8x)\u0015!\u0014QMA4\u0011\u0019\t\u0019&\u0006a\u0001i!9\u0011qK\u000bA\u0002\u0005%\u0004c\u0001\u0010\u0002l%\u0019\u0011QN\u0010\u0003\u0007%sG\u000f"
)
public interface SemiringProduct16 extends Semiring {
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

   // $FF: synthetic method
   static Tuple16 zero$(final SemiringProduct16 $this) {
      return $this.zero();
   }

   default Tuple16 zero() {
      return new Tuple16(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero());
   }

   // $FF: synthetic method
   static Tuple16 plus$(final SemiringProduct16 $this, final Tuple16 x0, final Tuple16 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple16 plus(final Tuple16 x0, final Tuple16 x1) {
      return new Tuple16(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()));
   }

   // $FF: synthetic method
   static Tuple16 times$(final SemiringProduct16 $this, final Tuple16 x0, final Tuple16 x1) {
      return $this.times(x0, x1);
   }

   default Tuple16 times(final Tuple16 x0, final Tuple16 x1) {
      return new Tuple16(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()));
   }

   // $FF: synthetic method
   static Tuple16 pow$(final SemiringProduct16 $this, final Tuple16 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple16 pow(final Tuple16 x0, final int x1) {
      return new Tuple16(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1));
   }

   static void $init$(final SemiringProduct16 $this) {
   }
}
