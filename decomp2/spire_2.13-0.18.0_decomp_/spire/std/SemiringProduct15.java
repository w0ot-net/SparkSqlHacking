package spire.std;

import algebra.ring.Semiring;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000b-\u0004A\u0011\u00017\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u0001!\t!!\u0010\t\u000f\u0005}\u0002\u0001\"\u0001\u0002B!9\u00111\n\u0001\u0005\u0002\u00055\u0003bBA*\u0001\u0011\u0005\u0013Q\u000b\u0002\u0012'\u0016l\u0017N]5oOB\u0013x\u000eZ;diF*$BA\f\u0019\u0003\r\u0019H\u000f\u001a\u0006\u00023\u0005)1\u000f]5sKV\u00012\u0004\u000f\"F\u0011.s\u0015\u000bV,[;\u0002\u001cg-[\n\u0004\u0001q\u0011\u0003CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g\rE\u0002$aMr!\u0001J\u0017\u000f\u0005\u0015ZcB\u0001\u0014+\u001b\u00059#B\u0001\u0015*\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\r\n\u00051B\u0012aB1mO\u0016\u0014'/Y\u0005\u0003]=\nq\u0001]1dW\u0006<WM\u0003\u0002-1%\u0011\u0011G\r\u0002\t'\u0016l\u0017N]5oO*\u0011af\f\t\u0012;Q2\u0014\tR$K\u001bB\u001bf+\u0017/`E\u0016D\u0017BA\u001b\u001f\u0005\u001d!V\u000f\u001d7fcU\u0002\"a\u000e\u001d\r\u0001\u0011)\u0011\b\u0001b\u0001u\t\t\u0011)\u0005\u0002<}A\u0011Q\u0004P\u0005\u0003{y\u0011qAT8uQ&tw\r\u0005\u0002\u001e\u007f%\u0011\u0001I\b\u0002\u0004\u0003:L\bCA\u001cC\t\u0015\u0019\u0005A1\u0001;\u0005\u0005\u0011\u0005CA\u001cF\t\u00151\u0005A1\u0001;\u0005\u0005\u0019\u0005CA\u001cI\t\u0015I\u0005A1\u0001;\u0005\u0005!\u0005CA\u001cL\t\u0015a\u0005A1\u0001;\u0005\u0005)\u0005CA\u001cO\t\u0015y\u0005A1\u0001;\u0005\u00051\u0005CA\u001cR\t\u0015\u0011\u0006A1\u0001;\u0005\u00059\u0005CA\u001cU\t\u0015)\u0006A1\u0001;\u0005\u0005A\u0005CA\u001cX\t\u0015A\u0006A1\u0001;\u0005\u0005I\u0005CA\u001c[\t\u0015Y\u0006A1\u0001;\u0005\u0005Q\u0005CA\u001c^\t\u0015q\u0006A1\u0001;\u0005\u0005Y\u0005CA\u001ca\t\u0015\t\u0007A1\u0001;\u0005\u0005a\u0005CA\u001cd\t\u0015!\u0007A1\u0001;\u0005\u0005i\u0005CA\u001cg\t\u00159\u0007A1\u0001;\u0005\u0005q\u0005CA\u001cj\t\u0015Q\u0007A1\u0001;\u0005\u0005y\u0015A\u0002\u0013j]&$H\u0005F\u0001n!\tib.\u0003\u0002p=\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002eB\u00191\u0005\r\u001c\u0002\u0015M$(/^2ukJ,''F\u0001v!\r\u0019\u0003'Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#\u0001=\u0011\u0007\r\u0002D)\u0001\u0006tiJ,8\r^;sKR*\u0012a\u001f\t\u0004GA:\u0015AC:ueV\u001cG/\u001e:fkU\ta\u0010E\u0002$a)\u000b!b\u001d;sk\u000e$XO]37+\t\t\u0019\u0001E\u0002$a5\u000b!b\u001d;sk\u000e$XO]38+\t\tI\u0001E\u0002$aA\u000b!b\u001d;sk\u000e$XO]39+\t\ty\u0001E\u0002$aM\u000b!b\u001d;sk\u000e$XO]3:+\t\t)\u0002E\u0002$aY\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u00111\u0004\t\u0004GAJ\u0016aC:ueV\u001cG/\u001e:fcE*\"!!\t\u0011\u0007\r\u0002D,A\u0006tiJ,8\r^;sKF\u0012TCAA\u0014!\r\u0019\u0003gX\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002.A\u00191\u0005\r2\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003g\u00012a\t\u0019f\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005e\u0002cA\u00121Q\u0006!!0\u001a:p+\u0005\u0019\u0014\u0001\u00029mkN$RaMA\"\u0003\u000fBa!!\u0012\u0013\u0001\u0004\u0019\u0014A\u0001=1\u0011\u0019\tIE\u0005a\u0001g\u0005\u0011\u00010M\u0001\u0006i&lWm\u001d\u000b\u0006g\u0005=\u0013\u0011\u000b\u0005\u0007\u0003\u000b\u001a\u0002\u0019A\u001a\t\r\u0005%3\u00031\u00014\u0003\r\u0001xn\u001e\u000b\u0006g\u0005]\u0013\u0011\f\u0005\u0007\u0003\u000b\"\u0002\u0019A\u001a\t\u000f\u0005%C\u00031\u0001\u0002\\A\u0019Q$!\u0018\n\u0007\u0005}cDA\u0002J]R\u0004"
)
public interface SemiringProduct15 extends Semiring {
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

   // $FF: synthetic method
   static Tuple15 zero$(final SemiringProduct15 $this) {
      return $this.zero();
   }

   default Tuple15 zero() {
      return new Tuple15(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero(), this.structure9().zero(), this.structure10().zero(), this.structure11().zero(), this.structure12().zero(), this.structure13().zero(), this.structure14().zero(), this.structure15().zero());
   }

   // $FF: synthetic method
   static Tuple15 plus$(final SemiringProduct15 $this, final Tuple15 x0, final Tuple15 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple15 plus(final Tuple15 x0, final Tuple15 x1) {
      return new Tuple15(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()), this.structure9().plus(x0._9(), x1._9()), this.structure10().plus(x0._10(), x1._10()), this.structure11().plus(x0._11(), x1._11()), this.structure12().plus(x0._12(), x1._12()), this.structure13().plus(x0._13(), x1._13()), this.structure14().plus(x0._14(), x1._14()), this.structure15().plus(x0._15(), x1._15()));
   }

   // $FF: synthetic method
   static Tuple15 times$(final SemiringProduct15 $this, final Tuple15 x0, final Tuple15 x1) {
      return $this.times(x0, x1);
   }

   default Tuple15 times(final Tuple15 x0, final Tuple15 x1) {
      return new Tuple15(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()), this.structure9().times(x0._9(), x1._9()), this.structure10().times(x0._10(), x1._10()), this.structure11().times(x0._11(), x1._11()), this.structure12().times(x0._12(), x1._12()), this.structure13().times(x0._13(), x1._13()), this.structure14().times(x0._14(), x1._14()), this.structure15().times(x0._15(), x1._15()));
   }

   // $FF: synthetic method
   static Tuple15 pow$(final SemiringProduct15 $this, final Tuple15 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple15 pow(final Tuple15 x0, final int x1) {
      return new Tuple15(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1), this.structure9().pow(x0._9(), x1), this.structure10().pow(x0._10(), x1), this.structure11().pow(x0._11(), x1), this.structure12().pow(x0._12(), x1), this.structure13().pow(x0._13(), x1), this.structure14().pow(x0._14(), x1), this.structure15().pow(x0._15(), x1));
   }

   static void $init$(final SemiringProduct15 $this) {
   }
}
