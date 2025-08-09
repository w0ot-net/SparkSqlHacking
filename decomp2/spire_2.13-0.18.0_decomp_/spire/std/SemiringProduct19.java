package spire.std;

import algebra.ring.Semiring;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee\u0001C\r\u001b!\u0003\r\t\u0001\b\u0010\t\u000bm\u0004A\u0011\u0001?\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0007\u0004\u0005u\u0003bBA1\u0001\u0019\r\u00111\r\u0005\b\u0003O\u0002a1AA5\u0011\u001d\ti\u0007\u0001D\u0002\u0003_Bq!a\u001d\u0001\t\u0003\t)\bC\u0004\u0002x\u0001!\t!!\u001f\t\u000f\u0005\r\u0005\u0001\"\u0001\u0002\u0006\"9\u00111\u0012\u0001\u0005B\u00055%!E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;2s)\u00111\u0004H\u0001\u0004gR$'\"A\u000f\u0002\u000bM\u0004\u0018N]3\u0016)}ad)\u0013'P%VC6LX1eO*l\u0007o\u001d<z'\r\u0001\u0001E\n\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u001d\"tG\u0004\u0002)c9\u0011\u0011f\f\b\u0003U9j\u0011a\u000b\u0006\u0003Y5\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002;%\u0011\u0001\u0007H\u0001\bC2<WM\u0019:b\u0013\t\u00114'A\u0004qC\u000e\\\u0017mZ3\u000b\u0005Ab\u0012BA\u001b7\u0005!\u0019V-\\5sS:<'B\u0001\u001a4!U\t\u0003HO#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8skbL!!\u000f\u0012\u0003\u000fQ+\b\u000f\\32sA\u00111\b\u0010\u0007\u0001\t\u0015i\u0004A1\u0001?\u0005\u0005\t\u0015CA C!\t\t\u0003)\u0003\u0002BE\t9aj\u001c;iS:<\u0007CA\u0011D\u0013\t!%EA\u0002B]f\u0004\"a\u000f$\u0005\u000b\u001d\u0003!\u0019\u0001 \u0003\u0003\t\u0003\"aO%\u0005\u000b)\u0003!\u0019\u0001 \u0003\u0003\r\u0003\"a\u000f'\u0005\u000b5\u0003!\u0019\u0001 \u0003\u0003\u0011\u0003\"aO(\u0005\u000bA\u0003!\u0019\u0001 \u0003\u0003\u0015\u0003\"a\u000f*\u0005\u000bM\u0003!\u0019\u0001 \u0003\u0003\u0019\u0003\"aO+\u0005\u000bY\u0003!\u0019\u0001 \u0003\u0003\u001d\u0003\"a\u000f-\u0005\u000be\u0003!\u0019\u0001 \u0003\u0003!\u0003\"aO.\u0005\u000bq\u0003!\u0019\u0001 \u0003\u0003%\u0003\"a\u000f0\u0005\u000b}\u0003!\u0019\u0001 \u0003\u0003)\u0003\"aO1\u0005\u000b\t\u0004!\u0019\u0001 \u0003\u0003-\u0003\"a\u000f3\u0005\u000b\u0015\u0004!\u0019\u0001 \u0003\u00031\u0003\"aO4\u0005\u000b!\u0004!\u0019\u0001 \u0003\u00035\u0003\"a\u000f6\u0005\u000b-\u0004!\u0019\u0001 \u0003\u00039\u0003\"aO7\u0005\u000b9\u0004!\u0019\u0001 \u0003\u0003=\u0003\"a\u000f9\u0005\u000bE\u0004!\u0019\u0001 \u0003\u0003A\u0003\"aO:\u0005\u000bQ\u0004!\u0019\u0001 \u0003\u0003E\u0003\"a\u000f<\u0005\u000b]\u0004!\u0019\u0001 \u0003\u0003I\u0003\"aO=\u0005\u000bi\u0004!\u0019\u0001 \u0003\u0003M\u000ba\u0001J5oSR$C#A?\u0011\u0005\u0005r\u0018BA@#\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'\u0006\u0002\u0002\u0006A\u0019q\u0005\u000e\u001e\u0002\u0015M$(/^2ukJ,''\u0006\u0002\u0002\fA\u0019q\u0005N#\u0002\u0015M$(/^2ukJ,7'\u0006\u0002\u0002\u0012A\u0019q\u0005\u000e%\u0002\u0015M$(/^2ukJ,G'\u0006\u0002\u0002\u0018A\u0019q\u0005N&\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002\u001eA\u0019q\u0005\u000e(\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002$A\u0019q\u0005N)\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002*A\u0019q\u0005\u000e+\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u00020A\u0019q\u0005N,\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u00026A\u0019q\u0005\u000e.\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003w\u00012a\n\u001b^\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005\u0005\u0003cA\u00145A\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\t9\u0005E\u0002(i\r\f1b\u001d;sk\u000e$XO]32gU\u0011\u0011Q\n\t\u0004OQ2\u0017aC:ueV\u001cG/\u001e:fcQ*\"!a\u0015\u0011\u0007\u001d\"\u0014.A\u0006tiJ,8\r^;sKF*TCAA-!\r9C\u0007\\\u0001\fgR\u0014Xo\u0019;ve\u0016\fd'\u0006\u0002\u0002`A\u0019q\u0005N8\u0002\u0017M$(/^2ukJ,\u0017gN\u000b\u0003\u0003K\u00022a\n\u001bs\u0003-\u0019HO];diV\u0014X-\r\u001d\u0016\u0005\u0005-\u0004cA\u00145k\u0006Y1\u000f\u001e:vGR,(/Z\u0019:+\t\t\t\bE\u0002(ia\fAA_3s_V\tq'\u0001\u0003qYV\u001cH#B\u001c\u0002|\u0005}\u0004BBA?-\u0001\u0007q'\u0001\u0002ya!1\u0011\u0011\u0011\fA\u0002]\n!\u0001_\u0019\u0002\u000bQLW.Z:\u0015\u000b]\n9)!#\t\r\u0005ut\u00031\u00018\u0011\u0019\t\ti\u0006a\u0001o\u0005\u0019\u0001o\\<\u0015\u000b]\ny)!%\t\r\u0005u\u0004\u00041\u00018\u0011\u001d\t\t\t\u0007a\u0001\u0003'\u00032!IAK\u0013\r\t9J\t\u0002\u0004\u0013:$\b"
)
public interface SemiringProduct19 extends Semiring {
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

   // $FF: synthetic method
   static Tuple19 zero$(final SemiringProduct19 $this) {
      return $this.zero();
   }

   default Tuple19 zero() {
      return new Tuple19(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero(), this.structure17().zero(), this.structure18().zero(), this.structure19().zero());
   }

   // $FF: synthetic method
   static Tuple19 plus$(final SemiringProduct19 $this, final Tuple19 x0, final Tuple19 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple19 plus(final Tuple19 x0, final Tuple19 x1) {
      return new Tuple19(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()), this.structure17().plus(x0._17(), x1._17()), this.structure18().plus(x0._18(), x1._18()), this.structure19().plus(x0._19(), x1._19()));
   }

   // $FF: synthetic method
   static Tuple19 times$(final SemiringProduct19 $this, final Tuple19 x0, final Tuple19 x1) {
      return $this.times(x0, x1);
   }

   default Tuple19 times(final Tuple19 x0, final Tuple19 x1) {
      return new Tuple19(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()), this.structure17().times(x0._17(), x1._17()), this.structure18().times(x0._18(), x1._18()), this.structure19().times(x0._19(), x1._19()));
   }

   // $FF: synthetic method
   static Tuple19 pow$(final SemiringProduct19 $this, final Tuple19 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple19 pow(final Tuple19 x0, final int x1) {
      return new Tuple19(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1), this.structure17().pow(x0._17(), x1), this.structure18().pow(x0._18(), x1), this.structure19().pow(x0._19(), x1));
   }

   static void $init$(final SemiringProduct19 $this) {
   }
}
