package spire.std;

import algebra.ring.Semiring;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uf\u0001C\u000e\u001d!\u0003\r\tA\b\u0011\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n!9\u0011\u0011\u0003\u0001\u0007\u0004\u0005M\u0001bBA\f\u0001\u0019\r\u0011\u0011\u0004\u0005\b\u0003;\u0001a1AA\u0010\u0011\u001d\t\u0019\u0003\u0001D\u0002\u0003KAq!!\u000b\u0001\r\u0007\tY\u0003C\u0004\u00020\u00011\u0019!!\r\t\u000f\u0005U\u0002Ab\u0001\u00028!9\u00111\b\u0001\u0007\u0004\u0005u\u0002bBA!\u0001\u0019\r\u00111\t\u0005\b\u0003\u000f\u0002a1AA%\u0011\u001d\ti\u0005\u0001D\u0002\u0003\u001fBq!a\u0015\u0001\r\u0007\t)\u0006C\u0004\u0002Z\u00011\u0019!a\u0017\t\u000f\u0005}\u0003Ab\u0001\u0002b!9\u0011Q\r\u0001\u0007\u0004\u0005\u001d\u0004bBA6\u0001\u0019\r\u0011Q\u000e\u0005\b\u0003c\u0002a1AA:\u0011\u001d\t9\b\u0001D\u0002\u0003sBq!! \u0001\r\u0007\ty\bC\u0004\u0002\u0004\u00021\u0019!!\"\t\u000f\u0005%\u0005Ab\u0001\u0002\f\"9\u0011q\u0012\u0001\u0005\u0002\u0005E\u0005bBAJ\u0001\u0011\u0005\u0011Q\u0013\u0005\b\u0003?\u0003A\u0011AAQ\u0011\u001d\t9\u000b\u0001C!\u0003S\u0013\u0011cU3nSJLgn\u001a)s_\u0012,8\r\u001e\u001a2\u0015\tib$A\u0002ti\u0012T\u0011aH\u0001\u0006gBL'/Z\u000b\u0018CyB5JT)U/jk\u0006m\u00194jY>\u0014X\u000f_>\u007f\u0003\u0007\u00192\u0001\u0001\u0012)!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0019\u0011FN\u001d\u000f\u0005)\u001adBA\u00162\u001d\ta\u0003'D\u0001.\u0015\tqs&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005y\u0012B\u0001\u001a\u001f\u0003\u001d\tGnZ3ce\u0006L!\u0001N\u001b\u0002\u000fA\f7m[1hK*\u0011!GH\u0005\u0003oa\u0012\u0001bU3nSJLgn\u001a\u0006\u0003iU\u0002\u0002d\t\u001e=\u000f*k\u0005k\u0015,Z9~\u0013W\r[6ocR<(0`A\u0001\u0013\tYDEA\u0004UkBdWMM\u0019\u0011\u0005urD\u0002\u0001\u0003\u0006\u007f\u0001\u0011\r\u0001\u0011\u0002\u0002\u0003F\u0011\u0011\t\u0012\t\u0003G\tK!a\u0011\u0013\u0003\u000f9{G\u000f[5oOB\u00111%R\u0005\u0003\r\u0012\u00121!\u00118z!\ti\u0004\nB\u0003J\u0001\t\u0007\u0001IA\u0001C!\ti4\nB\u0003M\u0001\t\u0007\u0001IA\u0001D!\tid\nB\u0003P\u0001\t\u0007\u0001IA\u0001E!\ti\u0014\u000bB\u0003S\u0001\t\u0007\u0001IA\u0001F!\tiD\u000bB\u0003V\u0001\t\u0007\u0001IA\u0001G!\tit\u000bB\u0003Y\u0001\t\u0007\u0001IA\u0001H!\ti$\fB\u0003\\\u0001\t\u0007\u0001IA\u0001I!\tiT\fB\u0003_\u0001\t\u0007\u0001IA\u0001J!\ti\u0004\rB\u0003b\u0001\t\u0007\u0001IA\u0001K!\ti4\rB\u0003e\u0001\t\u0007\u0001IA\u0001L!\tid\rB\u0003h\u0001\t\u0007\u0001IA\u0001M!\ti\u0014\u000eB\u0003k\u0001\t\u0007\u0001IA\u0001N!\tiD\u000eB\u0003n\u0001\t\u0007\u0001IA\u0001O!\tit\u000eB\u0003q\u0001\t\u0007\u0001IA\u0001P!\ti$\u000fB\u0003t\u0001\t\u0007\u0001IA\u0001Q!\tiT\u000fB\u0003w\u0001\t\u0007\u0001IA\u0001R!\ti\u0004\u0010B\u0003z\u0001\t\u0007\u0001IA\u0001S!\ti4\u0010B\u0003}\u0001\t\u0007\u0001IA\u0001T!\tid\u0010B\u0003\u0000\u0001\t\u0007\u0001IA\u0001U!\ri\u00141\u0001\u0003\u0007\u0003\u000b\u0001!\u0019\u0001!\u0003\u0003U\u000ba\u0001J5oSR$CCAA\u0006!\r\u0019\u0013QB\u0005\u0004\u0003\u001f!#\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\t\t)\u0002E\u0002*mq\n!b\u001d;sk\u000e$XO]33+\t\tY\u0002E\u0002*m\u001d\u000b!b\u001d;sk\u000e$XO]34+\t\t\t\u0003E\u0002*m)\u000b!b\u001d;sk\u000e$XO]35+\t\t9\u0003E\u0002*m5\u000b!b\u001d;sk\u000e$XO]36+\t\ti\u0003E\u0002*mA\u000b!b\u001d;sk\u000e$XO]37+\t\t\u0019\u0004E\u0002*mM\u000b!b\u001d;sk\u000e$XO]38+\t\tI\u0004E\u0002*mY\u000b!b\u001d;sk\u000e$XO]39+\t\ty\u0004E\u0002*me\u000b!b\u001d;sk\u000e$XO]3:+\t\t)\u0005E\u0002*mq\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u00111\n\t\u0004SYz\u0016aC:ueV\u001cG/\u001e:fcE*\"!!\u0015\u0011\u0007%2$-A\u0006tiJ,8\r^;sKF\u0012TCAA,!\rIc'Z\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002^A\u0019\u0011F\u000e5\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003G\u00022!\u000b\u001cl\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005%\u0004cA\u00157]\u0006Y1\u000f\u001e:vGR,(/Z\u00197+\t\ty\u0007E\u0002*mE\f1b\u001d;sk\u000e$XO]32oU\u0011\u0011Q\u000f\t\u0004SY\"\u0018aC:ueV\u001cG/\u001e:fca*\"!a\u001f\u0011\u0007%2t/A\u0006tiJ,8\r^;sKFJTCAAA!\rIcG_\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0004'\u0006\u0002\u0002\bB\u0019\u0011FN?\u0002\u0017M$(/^2ukJ,''M\u000b\u0003\u0003\u001b\u0003B!\u000b\u001c\u0002\u0002\u0005!!0\u001a:p+\u0005I\u0014\u0001\u00029mkN$R!OAL\u00037Ca!!'\u0019\u0001\u0004I\u0014A\u0001=1\u0011\u0019\ti\n\u0007a\u0001s\u0005\u0011\u00010M\u0001\u0006i&lWm\u001d\u000b\u0006s\u0005\r\u0016Q\u0015\u0005\u0007\u00033K\u0002\u0019A\u001d\t\r\u0005u\u0015\u00041\u0001:\u0003\r\u0001xn\u001e\u000b\u0006s\u0005-\u0016Q\u0016\u0005\u0007\u00033S\u0002\u0019A\u001d\t\u000f\u0005u%\u00041\u0001\u00020B\u00191%!-\n\u0007\u0005MFEA\u0002J]R\u0004"
)
public interface SemiringProduct21 extends Semiring {
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

   Semiring structure21();

   // $FF: synthetic method
   static Tuple21 zero$(final SemiringProduct21 $this) {
      return $this.zero();
   }

   default Tuple21 zero() {
      return new Tuple21(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero(), this.structure16().zero(), this.structure17().zero(), this.structure18().zero(), this.structure19().zero(), this.structure20().zero(), this.structure21().zero());
   }

   // $FF: synthetic method
   static Tuple21 plus$(final SemiringProduct21 $this, final Tuple21 x0, final Tuple21 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple21 plus(final Tuple21 x0, final Tuple21 x1) {
      return new Tuple21(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()), this.structure16().plus(x0._16(), x1._16()), this.structure17().plus(x0._17(), x1._17()), this.structure18().plus(x0._18(), x1._18()), this.structure19().plus(x0._19(), x1._19()), this.structure20().plus(x0._20(), x1._20()), this.structure21().plus(x0._21(), x1._21()));
   }

   // $FF: synthetic method
   static Tuple21 times$(final SemiringProduct21 $this, final Tuple21 x0, final Tuple21 x1) {
      return $this.times(x0, x1);
   }

   default Tuple21 times(final Tuple21 x0, final Tuple21 x1) {
      return new Tuple21(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()), this.structure16().times(x0._16(), x1._16()), this.structure17().times(x0._17(), x1._17()), this.structure18().times(x0._18(), x1._18()), this.structure19().times(x0._19(), x1._19()), this.structure20().times(x0._20(), x1._20()), this.structure21().times(x0._21(), x1._21()));
   }

   // $FF: synthetic method
   static Tuple21 pow$(final SemiringProduct21 $this, final Tuple21 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple21 pow(final Tuple21 x0, final int x1) {
      return new Tuple21(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1), this.structure16().pow(x0._16(), x1), this.structure17().pow(x0._17(), x1), this.structure18().pow(x0._18(), x1), this.structure19().pow(x0._19(), x1), this.structure20().pow(x0._20(), x1), this.structure21().pow(x0._21(), x1));
   }

   static void $init$(final SemiringProduct21 $this) {
   }
}
