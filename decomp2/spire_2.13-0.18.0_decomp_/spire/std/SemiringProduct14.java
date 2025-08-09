package spire.std;

import algebra.ring.Semiring;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000b\u001d\u0004A\u0011\u00015\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002\u0001\"\u0001\u00020!9\u0011\u0011\u0007\u0001\u0005\u0002\u0005M\u0002bBA\u001f\u0001\u0011\u0005\u0011q\b\u0005\b\u0003\u000b\u0002A\u0011IA$\u0005E\u0019V-\\5sS:<\u0007K]8ek\u000e$\u0018\u0007\u000e\u0006\u0003-]\t1a\u001d;e\u0015\u0005A\u0012!B:qSJ,Wc\u0004\u000e8\u0003\u0012;%*\u0014)T-fcvLY3\u0014\u0007\u0001Y\u0012\u0005\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VM\u001a\t\u0004E=\u0012dBA\u0012-\u001d\t!#F\u0004\u0002&S5\taE\u0003\u0002(Q\u00051AH]8piz\u001a\u0001!C\u0001\u0019\u0013\tYs#A\u0004bY\u001e,'M]1\n\u00055r\u0013a\u00029bG.\fw-\u001a\u0006\u0003W]I!\u0001M\u0019\u0003\u0011M+W.\u001b:j]\u001eT!!\f\u0018\u0011!q\u0019T\u0007Q\"G\u00132{%+\u0016-\\=\u0006$\u0017B\u0001\u001b\u001e\u0005\u001d!V\u000f\u001d7fcQ\u0002\"AN\u001c\r\u0001\u0011)\u0001\b\u0001b\u0001s\t\t\u0011)\u0005\u0002;{A\u0011AdO\u0005\u0003yu\u0011qAT8uQ&tw\r\u0005\u0002\u001d}%\u0011q(\b\u0002\u0004\u0003:L\bC\u0001\u001cB\t\u0015\u0011\u0005A1\u0001:\u0005\u0005\u0011\u0005C\u0001\u001cE\t\u0015)\u0005A1\u0001:\u0005\u0005\u0019\u0005C\u0001\u001cH\t\u0015A\u0005A1\u0001:\u0005\u0005!\u0005C\u0001\u001cK\t\u0015Y\u0005A1\u0001:\u0005\u0005)\u0005C\u0001\u001cN\t\u0015q\u0005A1\u0001:\u0005\u00051\u0005C\u0001\u001cQ\t\u0015\t\u0006A1\u0001:\u0005\u00059\u0005C\u0001\u001cT\t\u0015!\u0006A1\u0001:\u0005\u0005A\u0005C\u0001\u001cW\t\u00159\u0006A1\u0001:\u0005\u0005I\u0005C\u0001\u001cZ\t\u0015Q\u0006A1\u0001:\u0005\u0005Q\u0005C\u0001\u001c]\t\u0015i\u0006A1\u0001:\u0005\u0005Y\u0005C\u0001\u001c`\t\u0015\u0001\u0007A1\u0001:\u0005\u0005a\u0005C\u0001\u001cc\t\u0015\u0019\u0007A1\u0001:\u0005\u0005i\u0005C\u0001\u001cf\t\u00151\u0007A1\u0001:\u0005\u0005q\u0015A\u0002\u0013j]&$H\u0005F\u0001j!\ta\".\u0003\u0002l;\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002]B\u0019!eL\u001b\u0002\u0015M$(/^2ukJ,''F\u0001r!\r\u0011s\u0006Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u0001;\u0011\u0007\tz3)\u0001\u0006tiJ,8\r^;sKR*\u0012a\u001e\t\u0004E=2\u0015AC:ueV\u001cG/\u001e:fkU\t!\u0010E\u0002#_%\u000b!b\u001d;sk\u000e$XO]37+\u0005i\bc\u0001\u00120\u0019\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005\u0005\u0001c\u0001\u00120\u001f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005\u001d\u0001c\u0001\u00120%\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u00055\u0001c\u0001\u00120+\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t\u0019\u0002E\u0002#_a\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u0011\u0011\u0004\t\u0004E=Z\u0016aC:ueV\u001cG/\u001e:fcI*\"!a\b\u0011\u0007\tzc,A\u0006tiJ,8\r^;sKF\u001aTCAA\u0013!\r\u0011s&Y\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002,A\u0019!e\f3\u0002\ti,'o\\\u000b\u0002e\u0005!\u0001\u000f\\;t)\u0015\u0011\u0014QGA\u001d\u0011\u0019\t9$\u0005a\u0001e\u0005\u0011\u0001\u0010\r\u0005\u0007\u0003w\t\u0002\u0019\u0001\u001a\u0002\u0005a\f\u0014!\u0002;j[\u0016\u001cH#\u0002\u001a\u0002B\u0005\r\u0003BBA\u001c%\u0001\u0007!\u0007\u0003\u0004\u0002<I\u0001\rAM\u0001\u0004a><H#\u0002\u001a\u0002J\u0005-\u0003BBA\u001c'\u0001\u0007!\u0007C\u0004\u0002<M\u0001\r!!\u0014\u0011\u0007q\ty%C\u0002\u0002Ru\u00111!\u00138u\u0001"
)
public interface SemiringProduct14 extends Semiring {
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

   // $FF: synthetic method
   static Tuple14 zero$(final SemiringProduct14 $this) {
      return $this.zero();
   }

   default Tuple14 zero() {
      return new Tuple14(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero());
   }

   // $FF: synthetic method
   static Tuple14 plus$(final SemiringProduct14 $this, final Tuple14 x0, final Tuple14 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple14 plus(final Tuple14 x0, final Tuple14 x1) {
      return new Tuple14(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()));
   }

   // $FF: synthetic method
   static Tuple14 times$(final SemiringProduct14 $this, final Tuple14 x0, final Tuple14 x1) {
      return $this.times(x0, x1);
   }

   default Tuple14 times(final Tuple14 x0, final Tuple14 x1) {
      return new Tuple14(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()));
   }

   // $FF: synthetic method
   static Tuple14 pow$(final SemiringProduct14 $this, final Tuple14 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple14 pow(final Tuple14 x0, final int x1) {
      return new Tuple14(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1));
   }

   static void $init$(final SemiringProduct14 $this) {
   }
}
